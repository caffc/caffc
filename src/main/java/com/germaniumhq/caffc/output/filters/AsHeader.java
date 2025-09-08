package com.germaniumhq.caffc.output.filters;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.List;
import java.util.Map;

public class AsHeader implements Filter {
    @Override
    public Object apply(Object o, Map<String, Object> map, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) throws PebbleException {
        if (o instanceof String intermediaryType) {
            return "yolo";
        }

        throw new IllegalArgumentException("Unable to get the ifndef for " + o);
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }
}
