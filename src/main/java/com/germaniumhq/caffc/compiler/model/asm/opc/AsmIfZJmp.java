package com.germaniumhq.caffc.compiler.model.asm.opc;


import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;

/**
 * Jumps to the label if the test value is zero.
 */
final public class AsmIfZJmp implements AsmInstruction {
    public final AsmValue testValue;
    public final AsmLabel label;

    public AsmIfZJmp(AsmValue testValue, AsmLabel targetLabel) {
        this.testValue = testValue;
        this.label = targetLabel;
    }
}
