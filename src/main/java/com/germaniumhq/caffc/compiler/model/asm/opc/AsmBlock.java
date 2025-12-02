package com.germaniumhq.caffc.compiler.model.asm.opc;

import java.util.ArrayList;
import java.util.List;

/**
 * An AsmBlock contains a list of instructions.
 */
public final class AsmBlock implements AsmInstruction {
    public List<AsmInstruction> instructions = new ArrayList<>();

    public void add(AsmInstruction instruction) {
        instructions.add(instruction);
    }
}
