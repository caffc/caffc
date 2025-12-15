package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;

/**
 * A block variable is a temporary variable created in a block. It will
 * eventually be merged in the function variables.
 */
public class BlockVariable implements Symbol, AsmVar {
    public String name;
    public Symbol typeSymbol;

    public AstItem owner;

    public BlockVariable(AstItem owner, Symbol typeSymbol, String name) {
        this.owner = owner;
        this.typeSymbol = typeSymbol;
        this.name = name;
    }

    @Override
    public AstItem getOwner() {
        return owner;
    }

    @Override
    public String getFilePath() {
        return owner.getFilePath();
    }

    @Override
    public int getLineNumber() {
        return owner.getLineNumber();
    }

    @Override
    public int getColumnNumber() {
        return owner.getColumnNumber();
    }

    @Override
    public void recurseResolveTypes() {
        throw new IllegalStateException("CaffC BUG: should never happen. Block variables " +
            "are temp variables created when creating the linear form.");
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public TypeName typeName() {
        return this.typeSymbol.typeName();
    }

    @Override
    public Symbol typeSymbol() {
        return this.typeSymbol;
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
            codeRenderer.field("type", typeSymbol);
            codeRenderer.field("name", name);
        });
    }
}
