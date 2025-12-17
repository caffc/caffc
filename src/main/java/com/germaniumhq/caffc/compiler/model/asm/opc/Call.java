package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;

/**
 * Represents a function call. If the function is going to return something,
 * the value returned will be assigned into a temp variable as the `result`.
 * (this will be really an AsmVar)
 */
public final class Call implements AsmInstruction {
    public AsmValue result;
    public FunctionDefinition function;
    public AsmValue[] parameters;

    public Call(FunctionDefinition function, AsmValue ... parameters) {
        this.function = function;
        this.parameters = parameters;
    }
}
