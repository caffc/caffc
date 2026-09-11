package com.germaniumhq.caffc.output;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Module;
import com.germaniumhq.caffc.compiler.model.Program;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.settings.BuildSettings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Renders C output for a compiled program — either one file per compilation
 * unit / module, or a single onefile via {@code onefile_c.peb}.
 */
public class CCodeGenerator {
    private static final String[] FEATURES = {"common", "exception", "gc", "string", "i18n"};

    private final BuildSettings buildSettings;
    private final Program program;

    public CCodeGenerator(BuildSettings buildSettings, Program program) {
        this.buildSettings = buildSettings;
        this.program = program;
    }

    public void generate(Collection<CompilationUnit> compilationUnits) {
        if (buildSettings.getOneFile() != null) {
            generateOneFile(compilationUnits);
        } else {
            generateMultiFile(compilationUnits);
        }
    }

    /**
     * Copy runtime C sources into the output folder (multi-file mode only).
     */
    public void copyFeatureCSources() {
        for (String feature : FEATURES) {
            copyCSources(feature);
        }
    }

    private void generateMultiFile(Collection<CompilationUnit> compilationUnits) {
        for (CompilationUnit compilationUnit : compilationUnits) {
            renderCompilationUnit(
                    compilationUnit,
                    "caffc/template/c/compilation_unit_c.peb",
                    "c");
        }

        for (Module module : program.modules.values()) {
            renderFile(module, "caffc/template/c/module_h.peb",
                    OutputFilePathCalculator.getModuleFileName(module, ".h"));
            renderFile(module, "caffc/template/c/module_c.peb",
                    OutputFilePathCalculator.getModuleFileName(module, ".c"));
        }

        renderFile(program, "caffc/template/c/constants_h.peb", "caffc_program_constants.h");
        renderFile(program, "caffc/template/c/constants_c.peb", "caffc_program_constants.c");
    }

    private void generateOneFile(Collection<CompilationUnit> compilationUnits) {
        List<Module> modules = new ArrayList<>(program.modules.values());
        modules.sort(Comparator.comparing(m -> m.name));

        List<CompilationUnit> units = new ArrayList<>(compilationUnits);
        units.sort(Comparator.comparing(u -> u.sourceLocation.filePath));

        Map<String, Object> renderContext = PebbleTemplater.createRenderContext(program, buildSettings);
        renderContext.put("modules", modules);
        renderContext.put("compilationUnits", units);
        renderContext.put("coreHeaders", CoreCSources.loadHeaders(buildSettings));
        renderContext.put("coreImplementations", CoreCSources.loadImplementations(buildSettings));

        String code = PebbleTemplater.INSTANCE.renderToString(
                "caffc/template/c/onefile_c.peb",
                renderContext);

        String outputFileName = new File(buildSettings.getOneFile()).getName();
        writeToFile(outputFileName, code);
    }

    private void renderCompilationUnit(
            CompilationUnit compilationUnit,
            String templatePath,
            String fileExtension) {
        String outputFileName = OutputFilePathCalculator.getOutputFileName(compilationUnit, fileExtension);
        String code = renderCode(compilationUnit, templatePath);
        writeToFile(outputFileName, code);
    }

    private void renderFile(Object context, String templatePath, String outputFileName) {
        String code = renderCode(context, templatePath);
        writeToFile(outputFileName, code);
    }

    private String renderCode(Object context, String template) {
        Map<String, Object> renderContext = PebbleTemplater.createRenderContext(context, buildSettings);
        return PebbleTemplater.INSTANCE.renderToString(template, renderContext);
    }

    private void writeToFile(String filePath, String fileContent) {
        String name = new File(filePath).getName();
        String outputName = new File(buildSettings.getOutputFolder(), name).getAbsolutePath();

        try (FileWriter writer = new FileWriter(outputName)) {
            writer.write(fileContent);
        } catch (IOException e) {
            CaffcCompiler.get().fatal(
                    SourceLocation.fromFilePath(outputName),
                    "I/O exception: " + e.getMessage());
        }
    }

    private void copyCSources(String feature) {
        String selectedOption = buildSettings.getFeatureSetting(feature);

        String cFilesFolderString = String.format("%s/%s/%s/c",
                buildSettings.getTemplatesFolder(), feature, selectedOption);
        File cFilesFolder = new File(cFilesFolderString).getAbsoluteFile();

        if (!cFilesFolder.isDirectory()) {
            return;
        }

        String[] files = cFilesFolder.list();
        if (files == null) {
            return;
        }

        for (String file : files) {
            try {
                Files.copy(
                        Paths.get(cFilesFolderString, file),
                        Paths.get(buildSettings.getOutputFolder(), file),
                        StandardCopyOption.REPLACE_EXISTING
                );
            } catch (IOException e) {
                CaffcCompiler.get().fatal(
                        SourceLocation.fromFilePath(file),
                        "I/O exception: " + e.getMessage());
            }
        }
    }
}
