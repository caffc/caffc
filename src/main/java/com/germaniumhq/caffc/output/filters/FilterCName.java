package com.germaniumhq.caffc.output.filters;

import com.germaniumhq.caffc.compiler.model.ClassDefinition;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.List;
import java.util.Map;

public class FilterCName implements Filter {
    @Override
    public Object apply(Object o, Map<String, Object> map, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) throws PebbleException {
        if (o instanceof ClassDefinition classDefinition) {
            return getCType(classDefinition.typeName);
        }

        if (o instanceof TypeName t) {
            return getCType(t);
        }

        throw new IllegalArgumentException("Unable to find the C name for " + o);
    }

    public static String getCType(TypeName t) {
        if (t.isPrimitive()) {
            return t.name;
        }

        if (t.dataType == DataType.ARRAY) {
            if (t.module == null) {
                return "caffc_" + t.name.replace("[]", "_arr");
            } else {
                return t.module + "_" + t.name.replace("[]", "_arr");
            }
        }

        return t.fqdn().replace(".", "_");
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }
}
