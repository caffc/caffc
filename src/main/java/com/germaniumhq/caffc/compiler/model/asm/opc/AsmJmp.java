package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;

/**
 * A blind jump to the target label.
 */
final public class AsmJmp implements AsmInstruction {
    public SourceLocation sourceLocation;
    public AsmLabel label;

    public AsmJmp(AsmLabel label) {
        this.label = label;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }
}
