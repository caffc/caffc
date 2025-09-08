package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;


public class GenericDefinition implements AstItem, Symbol {
    public AstItem owner;
    public String astFilePath;
    public int astColumn;
    public int astLine;

    public String name;
    public SymbolSearch typeRestrictionSearch; // this will be implemented with type restrictions
    public Symbol typeRestriction;

    private boolean isResolved;

    public static GenericDefinition fromAntlr(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.GenericDeclarationContext genericDeclaration) {
        GenericDefinition result = new GenericDefinition();

        result.owner = owner;

        result.astFilePath = unit.astFilePath;
        result.astLine = genericDeclaration.getStart().getLine();
        result.astColumn = genericDeclaration.getStart().getCharPositionInLine();

        if (genericDeclaration instanceof caffcParser.GenericDeclarationIdContext) {
            result.name = genericDeclaration.getText();
        } else if (genericDeclaration instanceof caffcParser.GenericDeclarationRestrictedContext genericDeclarationRestrictedContext) {
            result.name = genericDeclaration.getChild(0).getText();
            result.typeRestrictionSearch = SymbolSearch.fromTypeContext(unit, genericDeclarationRestrictedContext.typeName());
        }

        return result;
    }

    @Override
    public AstItem getOwner() {
        return owner;
    }

    @Override
    public String getFilePath() {
        return this.astFilePath;
    }

    @Override
    public int getLineNumber() {
        return this.astLine;
    }

    @Override
    public int getColumnNumber() {
        return this.astColumn;
    }

    @Override
    public void recurseResolveTypes() {
        if (this.isResolved) {
            return;
        }

        this.isResolved = true;

        this.typeRestriction = SymbolResolver.mustResolveSymbol(this,
                typeRestrictionSearch != null ?
                typeRestrictionSearch :
                SymbolSearch.ofName("obj"));
    }

    @Override
    public Symbol typeSymbol() {
        return this.typeRestriction;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public TypeName typeName() {
        return this.typeRestriction.typeName();
    }
}
