package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;

final public class AsmAssign implements AsmInstruction {
    public SourceLocation sourceLocation;
    public AsmVar left;
    public AsmValue right;

    public AsmAssign(AsmVar left, AsmValue right) {
        if (left == null) {
            throw new IllegalArgumentException("null left value passed to AsmAssign");
        }

        if (right == null) {
            throw new IllegalArgumentException("null right value passed to AsmAssign");
        }

        this.left = left;
        this.right = right;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }
}
