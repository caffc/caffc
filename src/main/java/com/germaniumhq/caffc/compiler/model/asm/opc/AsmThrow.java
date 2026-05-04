package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;

/**
 * Represents a throw instruction. This will set the global exception variable
 * and jump to the exception handler.
 */
public final class AsmThrow implements AsmInstruction {
    public final SourceLocation sourceLocation;
    public final AsmValue exceptionValue;
    public final AsmLabel handlerLabel;

    public AsmThrow(SourceLocation sourceLocation, AsmValue exceptionValue) {
        this(sourceLocation, exceptionValue, null);
    }

    public AsmThrow(SourceLocation sourceLocation, AsmValue exceptionValue, AsmLabel handlerLabel) {
        if (exceptionValue == null) {
            throw new IllegalArgumentException("Exception value cannot be null");
        }

        this.sourceLocation = sourceLocation;
        this.exceptionValue = exceptionValue;
        this.handlerLabel = handlerLabel;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }
}
