package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;

/**
 * Clears the given variable memory with 0.
 */
public final class AsmZeroClear implements AsmInstruction {
    public final AsmVar variable;

    public AsmZeroClear(AsmVar variable) {
        this.variable = variable;
    }
}
