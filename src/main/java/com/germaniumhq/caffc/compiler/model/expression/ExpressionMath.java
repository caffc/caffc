package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.AstItemCodeRenderer;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

public class ExpressionMath implements Expression {
    public Expression left;
    public Expression right;
    public String operator;
    public AstItem owner;

    public String astFilePath;
    public int astColumn;
    public int astLine;
    public Symbol symbol;

    public static ExpressionMath fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExAddSubContext addSubExpression) {
        ExpressionMath result = new ExpressionMath();

        result.astFilePath = unit.astFilePath;
        result.astLine = addSubExpression.getStart().getLine();
        result.astColumn = addSubExpression.getStart().getCharPositionInLine();

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, addSubExpression.leftExpression);
        result.right = Expression.fromAntlr(unit, result, addSubExpression.rightExpression);
        result.operator = addSubExpression.getChild(1).getText();

        return result;
    }

    public static ExpressionMath fromAntlrMulMod(CompilationUnit unit, AstItem owner, caffcParser.ExMulModContext mulModContext) {
        ExpressionMath result = new ExpressionMath();

        result.astFilePath = unit.astFilePath;
        result.astLine = mulModContext.getStart().getLine();
        result.astColumn = mulModContext.getStart().getCharPositionInLine();

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, mulModContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, mulModContext.rightExpression);
        result.operator = mulModContext.getChild(1).getText();

        return result;
    }

    public static ExpressionMath fromAntlrDiv(CompilationUnit unit, AstItem owner, caffcParser.ExDivContext divContext) {
        ExpressionMath result = new ExpressionMath();

        result.astFilePath = unit.astFilePath;
        result.astLine = divContext.getStart().getLine();
        result.astColumn = divContext.getStart().getCharPositionInLine();

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, divContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, divContext.rightExpression);
        result.operator = divContext.getChild(1).getText();

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
        this.left.recurseResolveTypes();
        this.right.recurseResolveTypes();

        this.symbol = this.left.typeSymbol();
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
            codeRenderer.field("left", this.left);
            codeRenderer.field("operator", this.operator);
            codeRenderer.field("right", this.right);
        });
    }
}
