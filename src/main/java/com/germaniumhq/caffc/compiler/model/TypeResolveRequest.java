package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.type.TypeName;

public class TypeResolveRequest {
    public AstItem owner;
    public TypeName typeName;

    public TypeResolveRequest(AstItem owner, TypeName typeName) {
        this.owner = owner;
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        var loc = owner.getSourceLocation();
        return String.format("%s:%d -> %s", loc.getFilePath(), loc.getLineNumber(), typeName);
    }
}
