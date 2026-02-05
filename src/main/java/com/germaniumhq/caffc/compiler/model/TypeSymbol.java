package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;

import java.util.Objects;

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
