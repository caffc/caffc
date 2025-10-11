package com.germaniumhq.caffc.output.filters;

import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.List;
import java.util.Map;

public class FilterCType implements Filter {
    @Override
    public Object apply(Object o, Map<String, Object> map, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) throws PebbleException {
        if (o instanceof Symbol s) {
            o = s.typeName();
        }

        if (o instanceof TypeName t) {
            return getCType(t);
        }

        throw new IllegalArgumentException("Unable to find the C name for " + o);
    }

    public static String getCType(TypeName t) {
        if (t.isPrimitive() && t.name.equals("void")) {
            return t.name;
        }

        if (t.isPrimitive()) {
            return "caffc_" + t.name;
        }

        if (t.dataType == DataType.ARRAY) {
            if (t.module == null) {
                return "caffc_" + t.name.replace("[]", "_arr") + "*";
            } else {
                return t.module + "_" + t.name.replace("[]", "_arr") + "*";
            }
        }

        return t.fqdn().replace(".", "_") + "*";
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }
}
