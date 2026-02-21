package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;

/**
 * Does a mathematical operation.
 */
final public class AsmMath implements AsmInstruction {
    public SourceLocation sourceLocation;
    public AsmVar lValue;
    public AsmMathOperator operator;
    public AsmValue value1;
    public AsmValue value2;

    public AsmMath(AsmVar lValue, AsmMathOperator operator, AsmValue value1, AsmValue value2) {
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
