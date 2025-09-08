package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.ClassDefinition;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

public class ExpressionIndexAccess implements Expression, AstItem {
    private AstItem owner;
    public Expression expression;
    public Expression index;

    public String astFilePath;
    public int astColumn;
    public int astLine;
    private Symbol symbol;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExIndexAccessContext indexAccessExpression) {
        ExpressionIndexAccess result = new ExpressionIndexAccess();

        result.astFilePath = unit.astFilePath;
        result.astLine = indexAccessExpression.getStart().getLine();
        result.astColumn = indexAccessExpression.getStart().getCharPositionInLine();

        result.owner = owner;
        result.expression = Expression.fromAntlr(unit, result, indexAccessExpression.arraryExpression);
        result.index = Expression.fromAntlr(unit, result, indexAccessExpression.indexExpression);

        return result;
    }

    @Override
    public Symbol getSymbol() {
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
        this.index.recurseResolveTypes();
        this.expression.recurseResolveTypes();

        Symbol expressionSymbol = this.expression.getSymbol();

        // FIXME: probably anything that implements some List interface should be ok,
        //        not only arrays.
        if (expressionSymbol.typeName().arrayDimensions() == 0) {
            CaffcCompiler.get().fatal(this, "not an array: index access not possible.");
            throw new IllegalStateException("not reachable");
        }

        Symbol symbol = expressionSymbol.typeSymbol();

        if (symbol instanceof ClassDefinition classDefinitionSymbol) {
            this.symbol = classDefinitionSymbol.childDefinition;
        }

        if (this.symbol == null) {
            CaffcCompiler.get().fatal(this, "not a symbol: index access not possible");
        }
    }
}
