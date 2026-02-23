package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ExpressionParens implements Expression {
    public Expression expression;
    public AstItem owner;

    public SourceLocation sourceLocation;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExParensContext parensContext) {
        ExpressionParens result = new ExpressionParens();

        result.sourceLocation = SourceLocation.fromAntlr(unit.sourceLocation.filePath, parensContext);

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
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    @Override
    public void recurseResolveTypes() {
        this.expression.recurseResolveTypes();
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        return expression.asLinearForm(block);
    }
}
