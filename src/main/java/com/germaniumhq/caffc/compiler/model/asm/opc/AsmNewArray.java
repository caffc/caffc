package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.type.Symbol;

import java.util.List;

public final class AsmNewArray implements AsmInstruction{
    public AsmValue result;
    public final Symbol instantiatedType;
    public final List<AsmValue> dimensions;

    public AsmNewArray(AsmValue result, Symbol instantiatedType, List<AsmValue> dimensions) {
        this.result = result;
        this.instantiatedType = instantiatedType;
        this.dimensions = dimensions;
    }
}
