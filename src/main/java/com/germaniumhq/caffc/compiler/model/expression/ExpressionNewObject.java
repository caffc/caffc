package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

public class ExpressionNewObject implements Expression {
    public AstItem owner;
    public List<Expression> parameters = new ArrayList<>();

    public String astFilePath;
    public int astColumn;
    public int astLine;

    public Symbol instantiatedType;
    private SymbolSearch instantiatedTypeSearch;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExNewObjectContext newObject) {
        ExpressionNewObject result = new ExpressionNewObject();

        result.owner = owner;
        result.astFilePath = unit.astFilePath;
        result.astLine = newObject.getStart().getLine();
        result.astColumn = newObject.getStart().getCharPositionInLine();
        result.instantiatedTypeSearch = SymbolSearch.fromAntlr(unit, newObject.newType());

        if (newObject.expressionTuple() != null) {
            for (caffcParser.ExpressionContext parameterExpression: newObject.expressionTuple().expression()) {
                result.parameters.add(Expression.fromAntlr(unit, result, parameterExpression));
            }
        }

        return result;
    }

    @Override
    public Symbol typeSymbol() {
        return instantiatedType;
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
        for (Expression parameter: parameters) {
            parameter.recurseResolveTypes();
        }

        this.instantiatedType = SymbolResolver.mustResolveSymbol(this, this.instantiatedTypeSearch);
    }

}
