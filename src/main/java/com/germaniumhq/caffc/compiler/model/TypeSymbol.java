package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;

import java.util.Objects;

/**
 * A symbol that's just a type wrapper for primitives + void.
 */
public class TypeSymbol implements Symbol, AstItem {
    public static final Symbol VOID = new TypeSymbol(TypeName.VOID);

    private final TypeName typeName;

    public SourceLocation sourceLocation;

    public TypeSymbol(TypeName typeName) {
        this.typeName = typeName;
        this.sourceLocation = new SourceLocation("builtin", 0, 0);
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
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    @Override
    public void recurseResolveTypes() {
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        String fqdn = String.format(
            "%s:%s:%s:%s",
            typeName.module,
            typeName.functionClass,
            typeName.name,
            typeName.dataType
        );
        String generics = "";

        codeRenderer.text(fqdn + generics);
    }

    @Override
    public String toString() {
        return "TypeSymbol{" +
            "typeName=" + typeName +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TypeSymbol that = (TypeSymbol) o;
        return Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(typeName);
    }
}
