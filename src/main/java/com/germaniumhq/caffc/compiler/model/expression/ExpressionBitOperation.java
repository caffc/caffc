package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

public class ExpressionBitOperation implements Expression {
    public Expression left;
    public Expression right;
    public String operator;
    public AstItem owner;

    public String astFilePath;
    public int astColumn;
    public int astLine;
    public Symbol symbol;

    public static ExpressionBitOperation fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExBitOrContext bitOrContext) {
        ExpressionBitOperation result = new ExpressionBitOperation();

        result.astFilePath = unit.astFilePath;
        result.astLine = bitOrContext.getStart().getLine();
        result.astColumn = bitOrContext.getStart().getCharPositionInLine();

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, bitOrContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, bitOrContext.rightExpression);
        result.operator = bitOrContext.getChild(1).getText();

        return result;
    }

    public static ExpressionBitOperation fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExBitAndContext bitAndContext) {
        ExpressionBitOperation result = new ExpressionBitOperation();

        result.astFilePath = unit.astFilePath;
        result.astLine = bitAndContext.getStart().getLine();
        result.astColumn = bitAndContext.getStart().getCharPositionInLine();

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, bitAndContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, bitAndContext.rightExpression);
        result.operator = bitAndContext.getChild(1).getText();

        return result;
    }

    public static ExpressionBitOperation fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExBitXorContext bitOrContext) {
        ExpressionBitOperation result = new ExpressionBitOperation();

        result.astFilePath = unit.astFilePath;
        result.astLine = bitOrContext.getStart().getLine();
        result.astColumn = bitOrContext.getStart().getCharPositionInLine();

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, bitOrContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, bitOrContext.rightExpression);
        result.operator = bitOrContext.getChild(1).getText();

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
