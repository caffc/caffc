package com.germaniumhq.caffc.output.filters;

import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Module;
import com.germaniumhq.caffc.output.OutputFilePathCalculator;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.List;
import java.util.Map;

/**
 * Given the compilation unit, return the C header file that
 * defines it.
 *
 * i.e. "main_native.h"
 */
public class FilterCGlobalHeader implements Filter {
    @Override
    public Object apply(Object o, Map<String, Object> map, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) throws PebbleException {
        if (o instanceof CompilationUnit compilationUnit) {
            return OutputFilePathCalculator.getModuleFileName(compilationUnit.module, ".h");
        }

        if (o instanceof Module module) {
            return OutputFilePathCalculator.getModuleFileName(module, ".h");
        }

        throw new IllegalArgumentException("Unable to find the C global header for " + o);
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }
}
