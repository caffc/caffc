package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;

/**
 * Function returns the given value. It can be any of void (when null), primitive,
 * struct, object or array reference.
 */
final public class AsmReturn implements AsmInstruction {
    public AsmValue what;

    public AsmReturn(AsmValue what) {
        this.what = what;
    }
}

