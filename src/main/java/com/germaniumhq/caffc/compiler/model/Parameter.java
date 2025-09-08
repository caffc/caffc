package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.type.*;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.Map;

public class Parameter implements AstItem, GenericsSymbol {
    public FunctionDefinition owner;
    public Symbol typeSymbol;
    public String name;

    public String astFilePath;
    public int astColumn;
    public int astLine;

    private SymbolSearch symbolSearch;
    private boolean isResolved;

    public Parameter(FunctionDefinition owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public static Parameter fromAntlr(CompilationUnit unit, FunctionDefinition owner, caffcParser.ParameterDefinitionContext parameter) {
        String name = parameter.ID().getText();
        Parameter result = new Parameter(owner, name);

        result.astFilePath = unit.astFilePath;
        result.astLine = parameter.getStart().getLine();
        result.astColumn = parameter.getStart().getCharPositionInLine();

        result.symbolSearch = SymbolSearch.fromAntlr(unit, parameter.typeName());

        return result;
    }

    @Override
    public AstItem getOwner() {
        return owner;
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

        if ("_this".equals(name) && this.owner.clazz != null) {
            this.typeSymbol = (Symbol) this.owner.clazz;
        } else {
            this.typeSymbol = SymbolResolver.mustResolveSymbol(this, this.symbolSearch);
        }
    }

    @Override
    public <T extends Symbol> T newGenericsCopy(Map<String, Symbol> resolvedGenerics) {
        if (resolvedGenerics == null || resolvedGenerics.isEmpty()) {
            return (T) this;
        }

        Parameter newParameter = new Parameter(owner, name);

        newParameter.isResolved = this.isResolved;
        newParameter.typeSymbol = this.typeSymbol;
        // name
        // owner

        newParameter.astFilePath = this.astFilePath;
        newParameter.astColumn = this.astColumn;
        newParameter.astLine = this.astLine;

        if (newParameter.typeSymbol instanceof GenericDefinition typeSymbolGenericDefinition) {
            Symbol resolvedReturnType = resolvedGenerics.get(typeSymbolGenericDefinition.name);
            if (resolvedReturnType != null) {
                newParameter.typeSymbol = resolvedReturnType;
            }
        }

        return (T) newParameter;
    }
}
