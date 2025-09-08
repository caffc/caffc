package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;

/**
 * A symbol that's just a type wrapper for primitives + void.
 */
public class TypeSymbol implements Symbol, AstItem {
    public static final Symbol VOID = new TypeSymbol(TypeName.VOID);

    private final TypeName typeName;

    public String astFilePath;
    public int astColumn;
    public int astLine;

    public TypeSymbol(TypeName typeName) {
        this.typeName = typeName;
    }

    @Override
    public String name() {
        return typeName.name;
    }

    @Override
    public TypeName typeName() {
        return typeName;
    }

    @Override
    public Symbol typeSymbol() {
        return this;
    }

    @Override
    public AstItem getOwner() {
        return Program.get();
    }

    @Override
    public String getFilePath() {
        return astFilePath;
    }

    @Override
    public int getLineNumber() {
        return astLine;
    }

    @Override
    public int getColumnNumber() {
        return astColumn;
    }

    @Override
    public void recurseResolveTypes() {
    }
}
