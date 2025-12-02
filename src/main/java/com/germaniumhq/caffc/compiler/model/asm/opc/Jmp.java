package com.germaniumhq.caffc.compiler.model.asm.opc;

/**
 * A blind jump to the target label.
 */
final public class Jmp implements AsmInstruction {
    public Label target;

    public Jmp(Label target) {
        this.target = target;
    }
}
