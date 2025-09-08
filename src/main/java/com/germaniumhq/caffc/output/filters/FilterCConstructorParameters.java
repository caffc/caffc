package com.germaniumhq.caffc.output.filters;

import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.Parameter;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.List;
import java.util.Map;

public class FilterCConstructorParameters implements Filter {
    @Override
    public Object apply(Object o, Map<String, Object> map, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int j) throws PebbleException {
        if (o == null) {
            // no constructor exists
            return "";
        }

        if (o instanceof Function function) {
            FunctionDefinition definition = function.definition;
            return getConstructorParameters(definition);
        }

        if (o instanceof FunctionDefinition definition) {
            return getConstructorParameters(definition);
        }

        throw new IllegalArgumentException("unable to parse c constructor parameters for " + o);
    }

    private static String getConstructorParameters(FunctionDefinition definition) {
        String result = "";
        String comma = "";

        // we skip the _this parameter
        for (int i = 1; i < definition.parameters.size(); i++) {
            Parameter parameter = definition.parameters.get(i);
            result += comma + FilterCFunctionSignature.getSingleParameterAsString(parameter);
            comma = ", ";
        }

        return result;
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }
}
