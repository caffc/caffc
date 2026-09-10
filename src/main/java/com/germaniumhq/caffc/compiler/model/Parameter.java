package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.type.GenericsSymbol;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.Map;

public class Parameter implements AstItem, GenericsSymbol, AsmVar {
    public FunctionDefinition owner;
    public Symbol typeSymbol;
    public String name;

    /**
     * Optional default value expression. Evaluated (inlined) at each call site
     * where the argument is omitted.
     */
    public Expression defaultExpression;

    /**
     * True when this parameter appears after a bare {@code ...} separator in the
     * parameter list (varargs section).
     */
    public boolean isVarargsSection;

    /**
     * True when the parameter name was followed by {@code ...} ({@code arr...}).
     */
    public boolean isVarargsMarked;

    public SourceLocation sourceLocation;

    private SymbolSearch symbolSearch;
    private boolean isResolved;

    public Parameter(FunctionDefinition owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public static Parameter fromAntlr(
            CompilationUnit unit,
            FunctionDefinition owner,
            AstItem defaultExpressionOwner,
            caffcParser.ParameterDefinitionContext parameter) {
        String name = parameter.ID().getText();
        Parameter result = new Parameter(owner, name);

        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, parameter);
        result.symbolSearch = SymbolSearch.fromAntlr(unit, parameter.typeName());

        if (parameter.expression() != null) {
            AstItem expressionOwner = defaultExpressionOwner != null ? defaultExpressionOwner : owner;
            result.defaultExpression = Expression.fromAntlr(unit, expressionOwner, parameter.expression());
        }

        return result;
    }

    /**
     * Backwards-compatible overload when no separate owner for defaults is available.
     */
    public static Parameter fromAntlr(CompilationUnit unit, FunctionDefinition owner, caffcParser.ParameterDefinitionContext parameter) {
        return fromAntlr(unit, owner, owner, parameter);
    }

    public boolean isExplicitVarargs() {
        return isVarargsMarked || isVarargsSection;
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
    public SourceLocation getSourceLocation() {
        return sourceLocation;
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

        if (this.defaultExpression != null) {
            this.defaultExpression.recurseResolveTypes();
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
        newParameter.defaultExpression = this.defaultExpression;
        newParameter.isVarargsSection = this.isVarargsSection;
        newParameter.isVarargsMarked = this.isVarargsMarked;
        newParameter.sourceLocation = this.sourceLocation;

        if (newParameter.typeSymbol instanceof GenericDefinition typeSymbolGenericDefinition) {
            Symbol resolvedReturnType = resolvedGenerics.get(typeSymbolGenericDefinition.name);
            if (resolvedReturnType != null) {
                newParameter.typeSymbol = resolvedReturnType;
            }
        }

        return (T) newParameter;
    }
}
