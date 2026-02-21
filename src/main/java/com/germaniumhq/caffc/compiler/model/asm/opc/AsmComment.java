package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;

/**
 * A noop, that doesn't actually generate an instruction in the
 * C code, but a helper comment to see where multiple instruction
 * blocks such as if/ternary if/for begin and end.
 */
public class AsmComment implements AsmInstruction {
    public final SourceLocation sourceLocation;
    public final String comment;

    public AsmComment(SourceLocation sourceLocation, String label, int labelIndex) {
        this.sourceLocation = sourceLocation;
        this.comment = label + labelIndex + ":";
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }
}
