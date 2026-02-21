package com.germaniumhq.caffc.output;

import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Module;

/**
 * Computes the file name where a compilation unit should be written.
 */
public class OutputFilePathCalculator {
    public static String getOutputFileName(CompilationUnit compilationUnit, String fileExtension) {
        String baseName = extractBaseName(compilationUnit);
        return compilationUnit.module.name + "_" + baseName + "." + fileExtension;
    }

    public static String getHeaderGuard(Module module) {
        return "CAFFCM_" + module.name ;
    }

    private static String extractBaseName(CompilationUnit compilationUnit) {
        String[] items = compilationUnit.sourceLocation.filePath.split("/");
        String basename = items[items.length - 1];
        return basename.substring(0, basename.length() - ".caffc".length());
    }

    public static String getModuleFileName(Module module, String fileExtension) {
        return "module_" + module.name + fileExtension;
    }
}
