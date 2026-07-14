package com.germaniumhq.caffc.compiler.model.asm.vars;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;

/**
 * Represents a reference to the global _caffc_exception variable.
 */
public final class AsmGlobalExceptionVar implements AsmVar {
    public static final AsmGlobalExceptionVar INSTANCE = new AsmGlobalExceptionVar();
    
    private AsmGlobalExceptionVar() {
    }

    @Override
    public SourceLocation getSourceLocation() {
        return null;
    }

    @Override
    public Symbol typeSymbol() {
        return TypeSymbol.OBJ;
    }
}
