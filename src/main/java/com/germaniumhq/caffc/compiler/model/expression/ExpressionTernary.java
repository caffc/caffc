package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

public class ExpressionTernary implements Expression {
    public Expression checkExpression;
    public Expression trueExpression;
    public Expression falseExpression;

    public AstItem owner;

    public String astFilePath;
    public int astColumn;
    public int astLine;
    public Symbol symbol;

    public static ExpressionTernary fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExTernaryContext shiftContext) {
        ExpressionTernary result = new ExpressionTernary();

        result.astFilePath = unit.astFilePath;
        result.astLine = shiftContext.getStart().getLine();
        result.astColumn = shiftContext.getStart().getCharPositionInLine();

        result.owner = owner;
        result.checkExpression = Expression.fromAntlr(unit, result, shiftContext.checkExpression);
        result.trueExpression = Expression.fromAntlr(unit, result, shiftContext.trueExpression);
        result.falseExpression = Expression.fromAntlr(unit, result, shiftContext.falseExpression);

        if (result.trueExpression == null) {
            CaffcCompiler.get().error(result, "optional true ternary is not yet supported");
        }

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
        this.checkExpression.recurseResolveTypes();

        if (this.trueExpression != null) {
            this.trueExpression.recurseResolveTypes();
        }

        this.falseExpression.recurseResolveTypes();

        this.symbol = this.falseExpression.typeSymbol();
    }
}
