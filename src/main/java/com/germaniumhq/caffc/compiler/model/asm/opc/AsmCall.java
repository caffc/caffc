package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;

/**
 * Represents a function call. If the function is going to return something,
 * the value returned will be assigned into a temp variable as the `result`.
 */
public final class AsmCall implements AsmInstruction {
    public SourceLocation sourceLocation;
    public AsmValue result;
    public FunctionDefinition function;
    public AsmValue[] parameters;

    public AsmCall(FunctionDefinition function, AsmValue ... parameters) {
        if (function == null) {
            throw new IllegalArgumentException("Function cannot be null");
        }

        this.function = function;
        this.parameters = parameters;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }
}
