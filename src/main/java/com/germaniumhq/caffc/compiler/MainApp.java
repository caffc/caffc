package com.germaniumhq.caffc.compiler;

import com.germaniumhq.caffc.args.ArgumentsParser;
import com.germaniumhq.caffc.compiler.error.CaffcAntlrErrorListener;
import com.germaniumhq.caffc.compiler.model.*;
import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.error.CancelCompilationException;
import com.germaniumhq.caffc.compiler.model.Module;
import com.germaniumhq.caffc.compiler.optimizer.LinearFormConverter;
import com.germaniumhq.caffc.generated.caffcLexer;
import com.germaniumhq.caffc.generated.caffcParser;
import com.germaniumhq.caffc.output.OutputFilePathCalculator;
import com.germaniumhq.caffc.output.PebbleTemplater;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MainApp {
    private Program program = Program.reset();

    public static void main(String[] args) throws IOException {
        MainApp mainApp = new MainApp();
        try {
            mainApp.mainRun(args);
        } catch (CancelCompilationException e) {
            // the error was already displayed to the user
            System.exit(1);
        }
    }

    public void mainRun(String[] args) throws IOException {
        BuildConfig buildConfig = ArgumentsParser.parse(args);
        String[] features = {"common", "gc", "string"};

        if (buildConfig.getInputSources().isEmpty()) {
            reportError("no sources passed for the build");
        }

        Set<CompilationUnit> compilationUnits = new HashSet<>();

        for (String feature : features) {
            copyCSources(buildConfig, feature);
            compilationUnits.addAll( parseCaffcSources(program, buildConfig, feature) );
        }

        for (String sourcePath: buildConfig.getInputSources()) {
            for (String sourceFile: resolveGlob(sourcePath)) {
                CompilationUnit compilationUnit = parseCaffcFile(program, sourceFile);
                compilationUnits.add(compilationUnit);
            }
        }

        for (CompilationUnit compilationUnit: compilationUnits) {
            compilationUnit.recurseResolveTypes();
        }

        for (CompilationUnit compilationUnit: compilationUnits) {
            LinearFormConverter.convertAstToLinearForm(compilationUnit);
        }

        program.recreateConstants();

        for (CompilationUnit compilationUnit: compilationUnits) {
            renderCompilationUnit(buildConfig, compilationUnit, "caffc/template/c/compilation_unit_c.peb", "c");
        }

        for (Module module: getModuleHeaders()) {
            generateModuleHeader(buildConfig, module);
            generateModuleC(buildConfig, module);
        }

        generateConstantsHeader(buildConfig, program);
        generateConstantsC(buildConfig, program);
    }

    public static Collection<CompilationUnit> parseCaffcSources(Program program, BuildConfig buildConfig, String feature) {
        String selectedOption = buildConfig.getFeatureSetting(feature, "default");

        String caffcFilesFolderString = String.format("%s/%s/%s/caffc",
                buildConfig.getTemplatesFolder(), feature, selectedOption);
        File caffcFolder = new File(caffcFilesFolderString).getAbsoluteFile();

        if (!caffcFolder.isDirectory()) {
            return Collections.emptyList();
        }

        List<CompilationUnit> compilationUnits = new ArrayList<>();

        for (String file: caffcFolder.list()) {
            try {
                String stringPath = Paths.get(caffcFilesFolderString, file).toAbsolutePath().toString();
                CompilationUnit unit = parseCaffcFile(program, stringPath);

                if (CaffcCompiler.get().hasErrors) {
                    throw new CancelCompilationException("compilation failed");
                }

                compilationUnits.add(unit);
            } catch (IOException e) {
                CaffcCompiler.get().fatal(AstItem.fromFilePath(file),
                        "I/O exception: " + e.getMessage());
            }
        }

        return compilationUnits;
    }

    private Iterable<Module> getModuleHeaders() {
        return program.modules.values();
    }

    private void reportError(String errorMessage) {
        System.err.println(errorMessage);
        System.exit(1);
    }

    private void copyCSources(BuildConfig buildConfig, String feature) {
        String selectedOption = buildConfig.getFeatureSetting(feature, "default");

        String cFilesFolderString = String.format("%s/%s/%s/c",
                buildConfig.getTemplatesFolder(), feature, selectedOption);
        File cFilesFolder = new File(cFilesFolderString).getAbsoluteFile();

        // it's ok not to have C sources
        if (!cFilesFolder.isDirectory()) {
            return;
        }

        for (String file: cFilesFolder.list()) {
            try {
                Files.copy(
                        Paths.get(cFilesFolderString, file),
                        Paths.get(buildConfig.getOutputFolder(), file),
                        StandardCopyOption.REPLACE_EXISTING
                );
            } catch (IOException e) {
                CaffcCompiler.get().fatal(
                        AstItem.fromFilePath(file),
                        "I/O exception: " + e.getMessage());
            }
        }
    }

    private String[] resolveGlob(String sourcePath) {
        return new String[]{ new File(sourcePath).getAbsolutePath() };
    }

    /**
     * Write a module_name.h file that contains all the functions, and classes
     * definitions for the C compiler.
     *
     * @param buildConfig
     * @param module
     */
    private void generateModuleHeader(BuildConfig buildConfig, Module module) {
        String outputFileName = OutputFilePathCalculator.getModuleFileName(module, ".h");
        renderFile(buildConfig, module, "caffc/template/c/module_h.peb", outputFileName);
    }

    private void generateModuleC(BuildConfig buildConfig, Module module) {
        String outputFileName = OutputFilePathCalculator.getModuleFileName(module, ".c");
        renderFile(buildConfig, module, "caffc/template/c/module_c.peb", outputFileName);
    }

    private void generateConstantsHeader(BuildConfig buildConfig, Program program) {
        renderFile(buildConfig, program, "caffc/template/c/constants_h.peb", "caffc_program_constants.h");
    }

    private void generateConstantsC(BuildConfig buildConfig, Program program) {
        renderFile(buildConfig, program, "caffc/template/c/constants_c.peb", "caffc_program_constants.c");
    }

    /**
     * Render the code for the compilation unit, and write the file with
     * the given extension. The file name is computed by the OutputFilePathCalculatOr.
     * @param buildConfig
     * @param compilationUnit
     * @param templatePath
     * @param fileExtension
     */
    private void renderCompilationUnit(BuildConfig buildConfig, CompilationUnit compilationUnit, String templatePath, String fileExtension) {
        String outputFileName = OutputFilePathCalculator.getOutputFileName(compilationUnit, fileExtension);
        String code = renderCode(buildConfig, compilationUnit, templatePath);

        writeToFile(buildConfig, outputFileName, code);
    }

    /**
     * Render a file into the output folder, using the given template. The idea is to
     * start from an object, use the given template, and spit out a bunch of code.
     * This will be saved as a file under the output folder with the given name.
     *
     * @param buildConfig
     * @param context
     * @param templatePath
     * @param outputFileName
     */
    private void renderFile(BuildConfig buildConfig, Object context, String templatePath, String outputFileName) {
        String code = renderCode(buildConfig, context, templatePath);
        writeToFile(buildConfig, outputFileName, code);
    }

    /**
     * Render a bunch of
     * @param buildConfig
     * @param context
     * @param template
     * @return
     */
    private String renderCode(BuildConfig buildConfig, Object context, String template) {
        Map<String, Object> renderContext = PebbleTemplater.createRenderContext(context);
        String code = PebbleTemplater.INSTANCE.renderToString(template, renderContext);

        return code;
    }

    /**
     * Write the file content into a file with the given name into the output folder.
     * @param buildConfig
     * @param filePath
     * @param fileContent
     */
    private void writeToFile(BuildConfig buildConfig, String filePath, String fileContent) {
        String name = new File(filePath).getName();
        String outputName = new File(buildConfig.getOutputFolder(), name).getAbsolutePath();

        try (FileWriter writer = new FileWriter(outputName)){
            writer.write(fileContent);
        } catch (IOException e) {
            CaffcCompiler.get().fatal(
                    AstItem.fromFilePath(outputName),
                    "I/O exception: " + e.getMessage());
        }
    }

    public static CompilationUnit parseCaffcFile(Program program, String filePath) throws IOException {
        caffcLexer lexer = new caffcLexer(new ANTLRInputStream(new FileReader(filePath)));
        lexer.removeErrorListeners();
        lexer.addErrorListener(new CaffcAntlrErrorListener(filePath));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        caffcParser parser = new caffcParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new CaffcAntlrErrorListener(filePath));

        caffcParser.CompilationUnitContext antlrCompilationUnit = parser.compilationUnit();

        CompilationUnit compilationUnit = CompilationUnit.fromAntlr(program, antlrCompilationUnit, filePath);

        return compilationUnit;
    }
}
