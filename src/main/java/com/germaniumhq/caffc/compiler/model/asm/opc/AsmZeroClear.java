package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;

/**
 * Clears the given variable memory with 0.
 */
public final class AsmZeroClear implements AsmInstruction {
    public final SourceLocation sourceLocation;
    public final AsmVar variable;

    public AsmZeroClear(SourceLocation sourceLocation, AsmVar variable) {
        this.sourceLocation = sourceLocation;
        this.variable = variable;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }
}
