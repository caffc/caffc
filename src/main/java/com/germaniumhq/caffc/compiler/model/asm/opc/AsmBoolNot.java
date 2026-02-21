package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;

public final class AsmBoolNot implements AsmInstruction {
    public SourceLocation sourceLocation;
    public final AsmVar result;
    public final AsmValue value;

    public AsmBoolNot(AsmVar tempVar, AsmValue value) {
        this.result = tempVar;
        this.value = value;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }
}
