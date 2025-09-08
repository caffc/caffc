package com.germaniumhq.caffc.output.filters;

import com.germaniumhq.caffc.output.PebbleTemplater;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Render implements Filter {
    private Map<Class, String> knownTemplates = new HashMap<>();
    private Set<Class> ignoredClasses = new HashSet<>();

    @Override
    public Object apply(Object o, Map<String, Object> map, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) throws PebbleException {
        if (o == null) {
            throw new IllegalArgumentException("Null object passed to render");
        }

        // ignored classes shouldn't be rendered
        if (ignoredClasses.contains(o.getClass())) {
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

//         FIXME: implement as debug flag
//         result = extractName(templateName) + "«" + result + "»";

        return result;
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

    public Render withIgnored(Class ... clazzes) {
        Collections.addAll(this.ignoredClasses, clazzes);

        return this;
    }
}
