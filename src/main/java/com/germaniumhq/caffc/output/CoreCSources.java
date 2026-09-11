package com.germaniumhq.caffc.output;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.settings.BuildSettings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Loads the runtime / feature C sources ({@code templates/.../c/*.h|*.c}) for
 * embedding into a single onefile output. Local {@code #include "..."} lines are
 * stripped because everything is concatenated; system includes ({@code #include <...>})
 * are kept.
 */
public final class CoreCSources {
    private static final String[] FEATURES = {"common", "exception", "gc", "string", "i18n"};

    private static final String[] HEADER_ORDER = {
            "caffcpt.h", "caffcc.h", "caffco.h", "caffca.h"
    };

    private static final String[] IMPL_ORDER = {
            "caffca.c", "caffcmem.c", "caffcgcpl.c", "caffcgcps.c",
            "caffcms.c", "caffcstk.c"
    };

    private CoreCSources() {
    }

    public static List<String> loadHeaders(BuildSettings buildSettings) {
        return loadOrdered(buildSettings, ".h", HEADER_ORDER);
    }

    public static List<String> loadImplementations(BuildSettings buildSettings) {
        return loadOrdered(buildSettings, ".c", IMPL_ORDER);
    }

    private static List<String> loadOrdered(
            BuildSettings buildSettings,
            String extension,
            String[] preferredOrder) {
        Map<String, Path> filesByName = collectFiles(buildSettings, extension);

        List<String> result = new ArrayList<>();
        for (String name : preferredOrder) {
            Path path = filesByName.remove(name);
            if (path != null) {
                result.add(readStripped(path));
            }
        }

        // Remaining files (stable by name)
        List<String> remaining = new ArrayList<>(filesByName.keySet());
        remaining.sort(String::compareTo);
        for (String name : remaining) {
            result.add(readStripped(filesByName.get(name)));
        }

        return result;
    }

    /**
     * First occurrence of each basename across feature folders wins.
     */
    private static Map<String, Path> collectFiles(BuildSettings buildSettings, String extension) {
        Map<String, Path> filesByName = new LinkedHashMap<>();

        for (String feature : FEATURES) {
            String selectedOption = buildSettings.getFeatureSetting(feature);
            String cFilesFolderString = String.format("%s/%s/%s/c",
                    buildSettings.getTemplatesFolder(), feature, selectedOption);
            File cFilesFolder = new File(cFilesFolderString).getAbsoluteFile();

            if (!cFilesFolder.isDirectory()) {
                continue;
            }

            String[] names = cFilesFolder.list();
            if (names == null) {
                continue;
            }

            for (String fileName : names) {
                if (!fileName.endsWith(extension)) {
                    continue;
                }
                filesByName.putIfAbsent(fileName, Paths.get(cFilesFolderString, fileName));
            }
        }

        return filesByName;
    }

    private static String readStripped(Path path) {
        try {
            String code = Files.readString(path);
            StringBuilder result = new StringBuilder();
            for (String line : code.split("\n", -1)) {
                String trimmed = line.trim();
                if (trimmed.startsWith("#include \"") || trimmed.startsWith("#include\"")) {
                    continue;
                }
                result.append(line);
                result.append("\n");
            }
            // Note: wrapping is applied by CCodeGenerator on the final onefile render.
            return result.toString();
        } catch (IOException e) {
            CaffcCompiler.get().fatal(
                    SourceLocation.fromFilePath(path.toString()),
                    "I/O exception: " + e.getMessage());
            return "";
        }
    }
}
