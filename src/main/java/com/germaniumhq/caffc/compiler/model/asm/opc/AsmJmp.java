package com.germaniumhq.caffc.compiler.model.asm.opc;

/**
 * A blind jump to the target label.
 */
final public class AsmJmp implements AsmInstruction {
    public AsmLabel label;

    public AsmJmp(AsmLabel label) {
        this.label = label;
    }
}
