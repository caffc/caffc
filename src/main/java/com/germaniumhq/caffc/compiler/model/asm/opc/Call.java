package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;

public class Call implements AsmInstruction {
    public FunctionDefinition function;
    public AsmValue[] parameters;

    public Call(FunctionDefinition function, AsmValue ... parameters) {
        this.function = function;
        this.parameters = parameters;
    }
}
