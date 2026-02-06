package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;

/**
 * Function returns the given value. It can be any of void (when null), primitive,
 * struct, object or array reference.
 */
final public class AsmReturn implements AsmInstruction {
    private final Function function;
    public AsmValue what;

    public AsmReturn(Function f, AsmValue what) {
        this.function = f;
        this.what = what;
    }

    public int gcVariableCount() {
        return function.gcVariableCount();
    }
}

