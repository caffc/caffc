package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

public class ExpressionBoolCompare implements Expression {
    public AstItem owner;
    public Expression left;
    public Expression right;
    public String operator;

    public String astFilePath;
    public int astColumn;
    public int astLine;

    public static ExpressionBoolCompare fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExEqNeqContext eqNeqExpression) {
        ExpressionBoolCompare result = new ExpressionBoolCompare();

        result.astFilePath = unit.astFilePath;
        result.astLine = eqNeqExpression.getStart().getLine();
        result.astColumn = eqNeqExpression.getStart().getCharPositionInLine();

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, eqNeqExpression.leftExpression);
        result.right = Expression.fromAntlr(unit, result, eqNeqExpression.rightExpression);
        result.operator = eqNeqExpression.getChild(1).getText();

        return result;
    }

    public static Expression fromAntlr2(CompilationUnit unit, AstItem owner, caffcParser.ExLtLteGtGteContext ltLteGtGteExpression) {
        ExpressionBoolCompare result = new ExpressionBoolCompare();

        result.astFilePath = unit.astFilePath;
        result.astLine = ltLteGtGteExpression.getStart().getLine();
        result.astColumn = ltLteGtGteExpression.getStart().getCharPositionInLine();

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, ltLteGtGteExpression.leftExpression);
        result.right = Expression.fromAntlr(unit, result, ltLteGtGteExpression.rightExpression);
        result.operator = ltLteGtGteExpression.getChild(1).getText();

        return result;
    }

    @Override
    public Symbol typeSymbol() {
        return new TypeSymbol(TypeName.BOOL);
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
    }
}
