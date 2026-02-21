package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;

/**
 * Function returns the given value. It can be any of void (when null), primitive,
 * struct, object or array reference.
 */
final public class AsmReturn implements AsmInstruction {
    public final SourceLocation sourceLocation;
    private final Function function;
    public final AsmValue what;

    public AsmReturn(SourceLocation sourceLocation, Function f, AsmValue what) {
        this.sourceLocation = sourceLocation;
        this.function = f;
        this.what = what;
    }

    public int gcVariableCount() {
        return function.gcVariableCount();
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }
}

