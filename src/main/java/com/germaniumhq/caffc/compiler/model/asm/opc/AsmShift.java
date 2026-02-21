package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;

public final class AsmShift implements AsmInstruction {
    public SourceLocation sourceLocation;
    public final AsmVar lValue;
    public final AsmValue value1;
    public final AsmValue value2;
    public final String operator;

    public AsmShift(AsmVar lValue, AsmValue value1, AsmValue value2, String operator) {
        this.lValue = lValue;
        this.value1 = value1;
        this.value2 = value2;
        this.operator = operator;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }
}
