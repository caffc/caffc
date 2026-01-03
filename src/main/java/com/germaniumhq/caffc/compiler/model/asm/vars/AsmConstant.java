package com.germaniumhq.caffc.compiler.model.asm.vars;

import com.germaniumhq.caffc.compiler.model.type.Symbol;

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
    }

    @Override
    public Symbol typeSymbol() {
        return type;
    }
}
