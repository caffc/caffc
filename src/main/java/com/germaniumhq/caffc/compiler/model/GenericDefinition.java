package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;


public class GenericDefinition implements AstItem, Symbol {
    public AstItem owner;
    public SourceLocation sourceLocation;

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

        result.sourceLocation = SourceLocation.fromAntlr(unit.sourceLocation.filePath, genericDeclaration);

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
    public SourceLocation getSourceLocation() {
        return sourceLocation;
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
