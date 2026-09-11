package com.germaniumhq.caffc.compiler;

import com.germaniumhq.caffc.args.ArgumentsParser;
import com.germaniumhq.caffc.compiler.error.CaffcAntlrErrorListener;
import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.error.CancelCompilationException;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Module;
import com.germaniumhq.caffc.compiler.model.Program;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.optimizer.LinearFormConverter;
import com.germaniumhq.caffc.compiler.settings.BuildSettings;
import com.germaniumhq.caffc.generated.caffcLexer;
import com.germaniumhq.caffc.generated.caffcParser;
import com.germaniumhq.caffc.output.CCodeGenerator;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
        BuildSettings buildConfig = ArgumentsParser.parse(args);
        program.buildSettings = buildConfig;
        String[] features = {"common", "exception", "gc", "string", "i18n"};

        if (buildConfig.getInputSources().isEmpty()) {
            reportError("no sources passed for the build");
        }

        Set<CompilationUnit> compilationUnits = new HashSet<>();
        CCodeGenerator codeGenerator = new CCodeGenerator(buildConfig, program);
        boolean inOneFileMode = buildConfig.getOneFile() != null;

        if (!inOneFileMode) {
            codeGenerator.copyFeatureCSources();
        }

        for (String feature : features) {
            compilationUnits.addAll(parseCaffcSources(program, buildConfig, feature));
        }

        for (String sourcePath : buildConfig.getInputSources()) {
            for (String sourceFile : resolveGlob(sourcePath)) {
                CompilationUnit compilationUnit = parseCaffcFile(program, sourceFile);
                compilationUnits.add(compilationUnit);
            }
        }

        // The GlobalVariables must be moved _after_ the `recurseResolveTypes`.
        // The rationale is explained in Module.createInitModule().
        for (CompilationUnit compilationUnit : compilationUnits) {
            compilationUnit.recurseResolveTypes();
        }

        for (Module module : program.modules.values()) {
            Module.createInitModule(module, compilationUnits);
        }

        program.recreateConstants();

        for (CompilationUnit compilationUnit : compilationUnits) {
            LinearFormConverter.convertAstToLinearForm(compilationUnit);
        }

        codeGenerator.generate(compilationUnits);
    }

    public static Collection<CompilationUnit> parseCaffcSources(Program program, BuildSettings buildConfig, String feature) {
        String selectedOption = buildConfig.getFeatureSetting(feature);

        String caffcFilesFolderString = String.format("%s/%s/%s/caffc",
                buildConfig.getTemplatesFolder(), feature, selectedOption);
        File caffcFolder = new File(caffcFilesFolderString).getAbsoluteFile();

        if (!caffcFolder.isDirectory()) {
            return Collections.emptyList();
        }

        List<CompilationUnit> compilationUnits = new ArrayList<>();

        try {
            Files.walk(caffcFolder.toPath())
                    .filter(path -> path.toString().endsWith(".caffc"))
                    .filter(path -> Files.isRegularFile(path))
                    .sorted()
                    .forEach(path -> {
                        try {
                            CompilationUnit unit = parseCaffcFile(program, path.toAbsolutePath().toString());

                            if (CaffcCompiler.get().hasErrors) {
                                throw new CancelCompilationException("compilation failed");
                            }

                            compilationUnits.add(unit);
                        } catch (IOException e) {
                            CaffcCompiler.get().fatal(SourceLocation.fromFilePath(path.toString()),
                                    "I/O exception: " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            CaffcCompiler.get().fatal(SourceLocation.fromFilePath(caffcFilesFolderString),
                    "I/O exception: " + e.getMessage());
        }

        return compilationUnits;
    }

    private void reportError(String errorMessage) {
        System.err.println(errorMessage);
        System.exit(1);
    }

    private String[] resolveGlob(String sourcePath) {
        return new String[]{new File(sourcePath).getAbsolutePath()};
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

        return CompilationUnit.fromAntlr(program, antlrCompilationUnit, filePath);
    }
}
