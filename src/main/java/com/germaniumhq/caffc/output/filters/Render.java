package com.germaniumhq.caffc.output.filters;

import com.germaniumhq.caffc.output.PebbleTemplater;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Render implements Filter {
    private final Map<Class, String> knownTemplates = new HashMap<>();

    @Override
    public Object apply(Object o, Map<String, Object> map, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) throws PebbleException {
        if (o == null) {
            return "";
        }

        String templateName = knownTemplates.get(o.getClass());

        if (templateName == null) {
            throw new IllegalArgumentException(String.format(
                    "Unknown class %s. Unable to render it, since there's no template associated with it.",
                    o.getClass().getCanonicalName()
            ));
        }

        String result = PebbleTemplater.INSTANCE.renderToString(
            "caffc/template/" + templateName,
                PebbleTemplater.createRenderContext(o, evaluationContext));

        if (isCaffcDebugTemplates()) {
            result = extractName(templateName) + ":«" + result + "»";
        }

        return result;
    }

    private boolean isCaffcDebugTemplates() {
        return System.getenv("CAFFC_DEBUG_TEMPLATES") != null;
    }

    private String extractName(String templateName) {
        String[] path = templateName.split("/");
        return path[path.length - 1].replaceAll("\\.[^.]+$", "");
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }

    public Render withMapping(Class clazz, String templateName) {
        this.knownTemplates.put(clazz, templateName);
        return this;
    }
}
