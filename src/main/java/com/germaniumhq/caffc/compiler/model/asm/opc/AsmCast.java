package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.type.Symbol;

public final class AsmCast implements AsmInstruction {
    public final SourceLocation sourceLocation;
    public final Symbol castType;
    public final AsmVar left;
    public final AsmValue right;

    public AsmCast(SourceLocation sourceLocation, Symbol castType, AsmVar left, AsmValue right) {
        this.sourceLocation = sourceLocation;
        this.castType = castType;
        this.left = left;
        this.right = right;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }
}
