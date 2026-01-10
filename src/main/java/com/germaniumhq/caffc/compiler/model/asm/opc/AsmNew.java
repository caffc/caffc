package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.type.Symbol;

public final class AsmNew implements AsmInstruction{
    public AsmValue result;
    public final Symbol instantiatedType;
    public final AsmValue[] parameters;

    public AsmNew(Symbol instantiatedType, AsmValue[] parameters) {
        this.instantiatedType = instantiatedType;
        this.parameters = parameters;
    }
}
