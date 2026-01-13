package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.BlockVariable;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;

public final class AsmBoolNot implements AsmInstruction {
    public final BlockVariable result;
    public final AsmValue value;

    public AsmBoolNot(BlockVariable tempVar, AsmValue value) {
        this.result = tempVar;
        this.value = value;
    }
}
