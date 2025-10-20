package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;

/**
 * A variable defined by the return. It lives inside a struct.
 */
public class StructReturnVariableDefinition implements Symbol {
    public AstItem owner;
    public String name;
    public Symbol typeSymbol;

    public StructReturnVariableDefinition(AstItem owner, String name, Symbol typeSymbol) {
        this.owner = owner;
        this.name = name;
        this.typeSymbol = typeSymbol;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public TypeName typeName() {
        return typeSymbol.typeName();
    }

    @Override
    public Symbol typeSymbol() {
        return typeSymbol;
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
    }
}
