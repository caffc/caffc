package com.germaniumhq.caffc.compiler.model.asm.vars;

import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.compiler.model.type.DataType;

/**
 * Holds a constant. Constants aren't backed by variables. They just hold a single value
 * that can be assigned to an AsmVar or passed as a parameter to a function call.
 */
final public class AsmConstant implements AsmValue {
    public Symbol type;
    public String value;

    public AsmConstant(Symbol type, String value) {
        this.type = type;
        this.value = value;

        // FIXME: this looks like a massive hack that needs a massive refactoring
        TypeName typeName = type.typeName();
        if (TypeName.STR.equals(typeName) || TypeName.OBJ.equals(typeName) || typeName.dataType == DataType.ARRAY) {
            if ("0".equals(value)) {
                this.value = "caffc_null";
                return;
            }
            
            if (TypeName.STR.equals(typeName)) {
                this.value = "(caffc_str*)&" + value;
            }
        }
    }

    @Override
    public Symbol typeSymbol() {
        return type;
    }
}
