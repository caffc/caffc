package com.germaniumhq.caffc.output.filters;

import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.Parameter;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.List;
import java.util.Map;

public class FilterCConstructorParametersCall implements Filter {
    @Override
    public Object apply(Object o, Map<String, Object> map, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int j) throws PebbleException {
        if (o instanceof Function function) {
            String result = "";
            String comma = "";

            // we skip the _this parameter
            for (Parameter parameter: function.definition.parameters) {
                result += comma + parameter.name;
                comma = ", ";
            }

            return result;
        }

        throw new IllegalArgumentException("Unable to read the parameter names for " + o);
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }
}
