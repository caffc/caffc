package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.Function;

final public class AsmLabel implements AsmInstruction {
    public final SourceLocation sourceLocation;
    public final String name;

    public AsmLabel(SourceLocation sourceLocation, String name, int index) {
        this.sourceLocation = sourceLocation;
        this.name = name + index;
    }

    /**
     * Allocates a number for the labels and allows creating the label. This
     * construct exists so it becomes easier in the generated code to figure out
     * what if belongs to what else, etc., since they would have the same index.
     * Otherwise, while the code technically works it's impossible to figure out
     * what's going on.
     */
    public static int allocateNumber(AstItem owner) {
        Function f = AstItem.findParentOrSelf(owner, Function.class);
        return f.labelIndex++;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }
}
