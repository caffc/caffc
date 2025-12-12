package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;

final public class AsmAssign implements AsmInstruction {
    public AsmVar left;
    public AsmValue right;

    public AsmAssign(AsmVar left, AsmValue right) {
        this.left = left;
        this.right = right;
    }
}
