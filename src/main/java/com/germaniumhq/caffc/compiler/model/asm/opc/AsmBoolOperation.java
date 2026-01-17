package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;

/**
 * Does a boolean operation.
 */
final public class AsmBoolOperation implements AsmInstruction {
    public AsmVar lValue;
    public String operator;
    public AsmValue value1;
    public AsmValue value2;

    public AsmBoolOperation(AsmVar lValue, String operator, AsmValue value1, AsmValue value2) {
        this.lValue = lValue;
        this.operator = operator;
        this.value1 = value1;
        this.value2 = value2;
    }
}
