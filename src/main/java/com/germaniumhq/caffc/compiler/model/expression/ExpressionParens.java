package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ExpressionParens implements Expression {
    public Expression expression;
    public AstItem owner;

    public String astFilePath;
    public int astColumn;
    public int astLine;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExParensContext parensContext) {
        ExpressionParens result = new ExpressionParens();

        result.astFilePath = unit.astFilePath;
        result.astLine = parensContext.getStart().getLine();
        result.astColumn = parensContext.getStart().getCharPositionInLine();

        result.owner = owner;
        result.expression = Expression.fromAntlr(unit, result, parensContext.expression());

        return result;
    }

    @Override
    public Symbol typeSymbol() {
        return this.expression.typeSymbol();
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
    }

    @Override
    public AsmLinearFormResult asLinearForm(Function function) {
        return expression.asLinearForm(function);
    }
}
