package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

public class GenericInstantiation implements AstItem, Symbol {
    public AstItem owner;
    public SourceLocation sourceLocation;

    public SymbolSearch typeSearch;
    public Symbol type;

    private boolean isResolved;

    public static GenericInstantiation fromAntlr(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.TypeNameContext genericDeclaration) {
        GenericInstantiation result = new GenericInstantiation();

        result.owner = owner;

        result.sourceLocation = SourceLocation.fromAntlr(unit.sourceLocation.filePath, genericDeclaration);

        result.typeSearch = SymbolSearch.fromAntlr(unit, genericDeclaration);

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

        this.type = SymbolResolver.mustResolveSymbol(this, typeSearch);
    }

    @Override
    public String name() {
        return this.type.name();
    }

    @Override
    public TypeName typeName() {
        return type.typeName();
    }

    @Override
    public Symbol typeSymbol() {
        return type;
    }
}
