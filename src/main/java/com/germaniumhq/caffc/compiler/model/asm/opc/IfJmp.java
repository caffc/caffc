package com.germaniumhq.caffc.compiler.model.asm.opc;


import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;

/**
 * Jumps to the label if the test value is non-zero.
 */
final public class IfJmp implements AsmInstruction {
    private final AsmValue testValue;
    private final Label label;

    public IfJmp(AsmValue testValue, Label targetLabel) {
        this.testValue = testValue;
        this.label = targetLabel;
    }
}
