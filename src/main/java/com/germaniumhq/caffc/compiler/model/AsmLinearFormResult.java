package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.asm.opc.AsmInstruction;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;

import java.util.List;

/**
 * The list of instructions for a statement that was flattened into a linear form.
 * Is also contains a value.
 */
public class AsmLinearFormResult {
    public AsmValue value;
    public List<AsmInstruction> instructions;

    public AsmLinearFormResult(AsmValue value, List<AsmInstruction> instructions) {
        this.value = value;
        this.instructions = instructions;
    }

    public AsmLinearFormResult(List<AsmInstruction> instructions) {
        this.instructions = instructions;
    }
}
