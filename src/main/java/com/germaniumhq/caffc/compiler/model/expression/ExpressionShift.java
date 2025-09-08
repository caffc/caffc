package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

public class ExpressionShift implements Expression {
    public Expression left;
    public Expression right;
    public String operator;
    public AstItem owner;

    public String astFilePath;
    public int astColumn;
    public int astLine;
    public Symbol symbol;

    public static ExpressionShift fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExShiftContext shiftContext) {
        ExpressionShift result = new ExpressionShift();

        result.astFilePath = unit.astFilePath;
        result.astLine = shiftContext.getStart().getLine();
        result.astColumn = shiftContext.getStart().getCharPositionInLine();

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, shiftContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, shiftContext.rightExpression);
        result.operator = shiftContext.getChild(1).getText();

        return result;
    }

    @Override
    public Symbol getSymbol() {
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
        this.left.recurseResolveTypes();
        this.right.recurseResolveTypes();

        this.symbol = this.left.getSymbol();
    }
}
