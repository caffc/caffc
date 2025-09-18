package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

public class ExpressionUnaryMinus implements Expression {
    public Expression expression;
    public AstItem owner;
    public Symbol symbol;

    public String astFilePath;
    public int astColumn;
    public int astLine;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExUnaryMinusContext exUnaryMinusContext) {
        ExpressionUnaryMinus result = new ExpressionUnaryMinus();

        result.astFilePath = unit.astFilePath;
        result.astLine = exUnaryMinusContext.getStart().getLine();
        result.astColumn = exUnaryMinusContext.getStart().getCharPositionInLine();

        result.owner = owner;
        result.expression = Expression.fromAntlr(unit, result, exUnaryMinusContext.expression());

        return result;
    }

    @Override
    public Symbol typeSymbol() {
        return this.symbol;
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
        this.expression.recurseResolveTypes();

        this.symbol = this.expression.typeSymbol();
    }
}
