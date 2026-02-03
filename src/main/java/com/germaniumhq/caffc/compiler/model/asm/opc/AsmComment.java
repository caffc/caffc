package com.germaniumhq.caffc.compiler.model.asm.opc;

/**
 * A noop, that doesn't actually generate an instruction in the
 * C code, but a helper comment to see where multiple instruction
 * blocks such as if/ternary if/for begin and end.
 */
public class AsmComment implements AsmInstruction {
    public String comment;

    public AsmComment(String label, int labelIndex) {
        this.comment = label + labelIndex + ":";
    }
}
