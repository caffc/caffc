package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.asm.opc.AsmInstruction;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;

import java.util.ArrayList;
import java.util.List;

/**
 * The list of instructions for a statement that was flattened into a linear form.
 * It also contains a value in case this was an expression, so they can use nested
 * expressions.
 */
public class AsmLinearFormResult {
    public AsmValue value;
    public List<AsmInstruction> instructions;

    public AsmLinearFormResult() {
        this.instructions = new ArrayList<>();
    }

    public AsmLinearFormResult(AsmValue value, List<AsmInstruction> instructions) {
        this.value = value;
        this.instructions = instructions;
    }

    public AsmLinearFormResult(List<AsmInstruction> instructions) {
        this.instructions = instructions;
    }
}
