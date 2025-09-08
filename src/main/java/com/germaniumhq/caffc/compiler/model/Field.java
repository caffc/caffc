package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.type.*;
import com.germaniumhq.caffc.generated.caffcParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Field implements GenericsSymbol, Symbol, AstItem {
    public Symbol typeSymbol;
    public String name;
    public AstItem owner;

    public String astFilePath;
    public int astColumn;
    public int astLine;
    private SymbolSearch typeSearch;
    private boolean isResolved;

    public Field(AstItem owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public static List<Field> fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.FieldDeclarationContext fieldDeclarationContext) {
        List<Field> fields = new ArrayList<>();

        for (TerminalNode name: fieldDeclarationContext.ID()) {
            Field field = new Field(owner, name.getText());

            field.astFilePath = unit.astFilePath;
            field.astLine = name.getSymbol().getLine();
            field.astColumn = name.getSymbol().getCharPositionInLine();

            field.typeSearch = SymbolSearch.fromAntlr(unit, fieldDeclarationContext.typeName());

            fields.add(field);
        }

        return fields;
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
        if (this.isResolved) {
            return;
        }

        this.isResolved = true;

        this.typeSymbol = SymbolResolver.mustResolveSymbol(this, typeSearch);
    }

    @Override
    public <T extends Symbol> T newGenericsCopy(Map<String, Symbol> resolvedGenerics) {
        if (resolvedGenerics == null || resolvedGenerics.isEmpty()) {
            return (T) this;
        }

        Field newField = new Field(owner, name);

        newField.typeSymbol = this.typeSymbol;
        // name
        // owner

        newField.astFilePath = this.astFilePath;
        newField.astColumn = this.astColumn;
        newField.astLine = this.astLine;

        if (newField.typeSymbol instanceof GenericDefinition typeSymbolGenericDefinition) {
            Symbol resolvedReturnType = resolvedGenerics.get(typeSymbolGenericDefinition.name);
            if (resolvedReturnType != null) {
                newField.typeSymbol = resolvedReturnType;
            }
        }

        return (T) newField;
    }
}
