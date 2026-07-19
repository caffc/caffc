package com.germaniumhq.caffc.output.functions;

import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FunctionClassName implements com.mitchellbosecke.pebble.extension.Function {
    @Override
    public Object execute(Map<String, Object> parameters, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) {
        Iterator<Object> iterator = parameters.values().iterator();
        Object object = iterator.next();

        if (object != null) {
            return object.getClass().getSimpleName();
        }

        return null;
    }

    @Override
    public List<String> getArgumentNames() {
        return List.of("object");
    }
}
