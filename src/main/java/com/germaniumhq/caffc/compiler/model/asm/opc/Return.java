package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;

/**
 * Function returns the given value. It can be any of void (when null), primitive,
 * struct, object or array reference.
 */
final public class Return implements AsmInstruction {
    public AsmValue what;

    public Return(AsmValue what) {
        this.what = what;
    }
}

