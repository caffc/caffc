package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;

/**
 * Jumps to the label if the test value is zero.
 */
final public class AsmIfZJmp implements AsmInstruction {
    public final SourceLocation sourceLocation;
    public final AsmValue testValue;
    public final AsmLabel label;

    public AsmIfZJmp(SourceLocation sourceLocation, AsmValue testValue, AsmLabel targetLabel) {
        this.sourceLocation = sourceLocation;
        this.testValue = testValue;
        this.label = targetLabel;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }
}
