package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;

/**
 * Does a boolean operation.
 */
final public class AsmBoolOperation implements AsmInstruction {
    public final SourceLocation sourceLocation;
    public final AsmVar lValue;
    public final String operator;
    public final AsmValue value1;
    public final AsmValue value2;

    public AsmBoolOperation(SourceLocation sourceLocation, AsmVar lValue, String operator, AsmValue value1, AsmValue value2) {
        this.sourceLocation = sourceLocation;
        this.lValue = lValue;
        this.operator = operator;
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }
}
