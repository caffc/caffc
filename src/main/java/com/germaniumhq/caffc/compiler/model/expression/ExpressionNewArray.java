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

/**
 * Instantiate an array that can be uni or multi-dimensional.
 */
public class ExpressionNewArray implements Expression {
    public AstItem owner;
    public List<Expression> countExpressions = new ArrayList<>();

    public String astFilePath;
    public int astColumn;
    public int astLine;

    private SymbolSearch symbolSearch;
    public Symbol symbol;

    public static ExpressionNewArray fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExNewArrayContext newArrayExpression) {
        ExpressionNewArray result = new ExpressionNewArray();

        result.astFilePath = unit.astFilePath;
        result.astLine = newArrayExpression.getStart().getLine();
        result.astColumn = newArrayExpression.getStart().getCharPositionInLine();
        result.owner = owner;

        final String baseType = newArrayExpression.newType().getText();

        for (caffcParser.ExpressionContext expression: newArrayExpression.expression()) {
            result.countExpressions.add(Expression.fromAntlr(unit, result, expression));
        }

        String arrayType = asArray(baseType, result.countExpressions.size());
        result.symbolSearch = SymbolSearch.ofName(arrayType);

        return result;
    }

    private static String asArray(String baseType, int size) {
        StringBuilder result = new StringBuilder(baseType);

        for (int i = 0; i < size; i++) {
            result.append("[]");
        }

        return result.toString();
    }

    @Override
    public Symbol typeSymbol() {
        return symbol;
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
        for (Expression countExpression : countExpressions) {
            countExpression.recurseResolveTypes();
        }

        this.symbol = SymbolResolver.mustResolveSymbol(this, this.symbolSearch);
    }
}
