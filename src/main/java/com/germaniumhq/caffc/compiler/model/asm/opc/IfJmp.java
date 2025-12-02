package com.germaniumhq.caffc.compiler.model.asm.opc;


import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;

/**
 * Jumps to the label if the value is non-zero.
 */
final public class IfJmp implements AsmInstruction {
    private final AsmValue value;
    private final Label label;

    public IfJmp(AsmValue ifCondition, Label ifLabel) {
        this.value = ifCondition;
        this.label = ifLabel;
    }
}
