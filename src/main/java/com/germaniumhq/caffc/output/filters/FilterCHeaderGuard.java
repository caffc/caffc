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

public class FilterCHeaderGuard implements Filter  {
    @Override
    public Object apply(Object o, Map<String, Object> map, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) throws PebbleException {
        if (o instanceof Module module) {
            return OutputFilePathCalculator.getHeaderGuard(module);
        }

        throw new IllegalArgumentException("Unable to find the C header guard for " + o);
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }
}
