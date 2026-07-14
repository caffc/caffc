package com.germaniumhq.caffc.output.filters;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.Struct;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.source.HasSourceLocation;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.List;
import java.util.Map;

/**
 * Returns the 0 value of something for initialization of a variable.
 */
public class FilterNullValue implements Filter {
    @Override
    public Object apply(Object o, Map<String, Object> map, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) throws PebbleException {
        if (!(o instanceof AsmVar)) {
            if (o instanceof HasSourceLocation sourceLocation) {
                CaffcCompiler.get().fatal(sourceLocation, "null_value requires an instance of AsmVar");
            }

            throw new RuntimeException("null_value requires an instance of AsmVar");
        }

        return getZeroValue(((AsmVar) o).typeSymbol());
    }

    public String getZeroValue(Symbol symbol) {
        TypeName typeName = symbol.typeName();
        if (TypeName.PTR.equals(typeName)) { // primitive && name == "ptr"
            return "caffc_null";
        } else if (typeName.isPrimitive()) {
            return "0";
        } else if (typeName.dataType == DataType.OBJECT) {
            return "caffc_null";
        } else if (typeName.dataType == DataType.ARRAY) {
            return "caffc_null";
        } else if (typeName.dataType == DataType.STRUCT) {
            Struct structSymbol = (Struct) symbol;
            StringBuilder result = new StringBuilder();

            result.append("{");
            for (int i = 0; i < structSymbol.fields.size(); i++) {
                result.append(getZeroValue(structSymbol.fields.get(i)));
                if (i < structSymbol.fields.size() - 1) {
                    result.append(", ");
                }
            }
            result.append("}");

            return result.toString();
        } else {
            CaffcCompiler.get().fatal(symbol, "unknown type " + symbol.typeName());
            return "0"; // not reached
        }
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }
}
