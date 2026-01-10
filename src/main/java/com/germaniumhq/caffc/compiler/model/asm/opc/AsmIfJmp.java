package com.germaniumhq.caffc.compiler.model.asm.opc;


import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;

/**
 * Jumps to the label if the test value is non-zero.
 */
final public class AsmIfJmp implements AsmInstruction {
    private final AsmValue testValue;
    private final AsmLabel label;

    public AsmIfJmp(AsmValue testValue, AsmLabel targetLabel) {
        this.testValue = testValue;
        this.label = targetLabel;
    }
}
