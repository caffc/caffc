package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

public class ExpressionBoolOperation implements Expression {
    public AstItem owner;
    public Expression left;
    public Expression right;
    public String operator;

    public String astFilePath;
    public int astColumn;
    public int astLine;

    public static ExpressionBoolOperation fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExBoolAndContext exBoolAndContext) {
        ExpressionBoolOperation result = new ExpressionBoolOperation();

        result.astFilePath = unit.astFilePath;
        result.astLine = exBoolAndContext.getStart().getLine();
        result.astColumn = exBoolAndContext.getStart().getCharPositionInLine();

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, exBoolAndContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, exBoolAndContext.rightExpression);
        result.operator = "&&";

        return result;
    }


    public static ExpressionBoolOperation fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExBoolOrContext exBoolOrContext) {
        ExpressionBoolOperation result = new ExpressionBoolOperation();

        result.astFilePath = unit.astFilePath;
        result.astLine = exBoolOrContext.getStart().getLine();
        result.astColumn = exBoolOrContext.getStart().getCharPositionInLine();

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, exBoolOrContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, exBoolOrContext.rightExpression);
        result.operator = "||";

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
