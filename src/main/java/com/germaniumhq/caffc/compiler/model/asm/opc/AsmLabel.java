package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.Program;

final public class AsmLabel implements AsmInstruction {
    public String name;

    public AsmLabel(String name) {
        this.name = name + (Program.INSTANCE.labelIndex++);
    }
}
