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
        return String.format("%s:%d -> %s", owner.getFilePath(), owner.getLineNumber(), typeName);
    }
}
