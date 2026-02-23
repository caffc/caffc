package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ExpressionUnaryMinus implements Expression {
    public Expression expression;
    public AstItem owner;
    public Symbol symbol;

    public SourceLocation sourceLocation;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExUnaryMinusContext exUnaryMinusContext) {
        ExpressionUnaryMinus result = new ExpressionUnaryMinus();

        result.sourceLocation = SourceLocation.fromAntlr(unit.sourceLocation.filePath, exUnaryMinusContext);

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
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    @Override
    public void recurseResolveTypes() {
        this.expression.recurseResolveTypes();

        this.symbol = this.expression.typeSymbol();
    }
}
