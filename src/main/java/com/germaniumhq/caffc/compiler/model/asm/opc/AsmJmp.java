package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;

/**
 * A blind jump to the target label.
 */
final public class AsmJmp implements AsmInstruction {
    public final SourceLocation sourceLocation;
    public final AsmLabel label;

    public AsmJmp(SourceLocation sourceLocation, AsmLabel label) {
        this.sourceLocation = sourceLocation;
        this.label = label;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }
}
