package com.germaniumhq.caffc.output.functions;

import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FunctionGet implements com.mitchellbosecke.pebble.extension.Function {
    @Override
    public Object execute(Map<String, Object> parameters, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) {
        Iterator<Object> iterator = parameters.values().iterator();
        Object collection = iterator.next();
        Object index = iterator.next();

        if (collection instanceof List list) {
            return list.get(Integer.parseInt(index.toString()));
        }

        if (collection instanceof Map map) {
            return map.get(index);
        }

        throw new IllegalStateException("unsupported collection " + collection);
    }

    @Override
    public List<String> getArgumentNames() {
        return List.of("collection", "key");
    }
}
