package com.germaniumhq.caffc.output.filters;

import com.germaniumhq.caffc.compiler.model.ClassDefinition;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.InterfaceDefinition;
import com.germaniumhq.caffc.compiler.model.Struct;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclaration;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.List;
import java.util.Map;

/**
 * Returns the name of the type behind, without a pointer or anything else.
 * So for a class it would be `module_Type`, for a function it could be either
 * `module_function_name`, either `module_Type_function_name` if it belongs to
 * a class.
 *
 * This is useful for having a function call, since there we need the name,
 * but without the pointer.
 */
public class FilterCTypeName implements Filter {
    @Override
    public Object apply(Object o, Map<String, Object> map, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) throws PebbleException {
        if (o instanceof FunctionDefinition fd) {
            return getCType(fd.typeName());
        }

        if (o instanceof TypeSymbol ts) {
            return getCType(ts.typeName());
        }

        if (o instanceof ClassDefinition cd) {
            return getCType(cd.typeName());
        }

        if (o instanceof InterfaceDefinition id) {
            return getCType(id.typeName());
        }

        if (o instanceof TypeName t) {
            return getCType(t);
        }

        if (o instanceof Struct s) {
            return getCType(s.typeName());
        }

        if (o instanceof VariableDeclaration) {
            return getCType(((VariableDeclaration) o).typeName());
        }

        throw new IllegalArgumentException("Unable to find the C name for " + o);
    }

    public static String getCType(TypeName t) {
        String result = t.module + "_";

        if (t.functionClass != null) {
            result += t.functionClass + "_";
        }

        result += t.name;

        return result;
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }
}
