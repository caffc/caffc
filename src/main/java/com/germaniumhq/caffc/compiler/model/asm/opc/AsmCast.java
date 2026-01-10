package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.type.Symbol;

public final class AsmCast implements AsmInstruction {
    public Symbol castType;
    public AsmVar left;
    public AsmValue right;

    public AsmCast(Symbol castType, AsmVar left, AsmValue right) {
        this.castType = castType;
        this.left = left;
        this.right = right;
    }
}
