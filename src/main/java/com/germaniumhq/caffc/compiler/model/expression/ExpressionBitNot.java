package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBitNot;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ExpressionBitNot implements Expression {
    public Expression expression;
    public AstItem owner;
    public Symbol symbol;

    public SourceLocation sourceLocation;

    private boolean isResolved;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExBitNotContext bitNotContext) {
        ExpressionBitNot result = new ExpressionBitNot();

        result.sourceLocation = SourceLocation.fromAntlr(unit.sourceLocation.filePath, bitNotContext);

        result.owner = owner;
        result.expression = Expression.fromAntlr(unit, result, bitNotContext.expression());

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
        if (isResolved) {
            return;
        }

        isResolved = true;

        this.expression.recurseResolveTypes();
        this.symbol = this.expression.typeSymbol();
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        AsmLinearFormResult linearExpression = this.expression.asLinearForm(block);

        AsmVar tempVar = block.addTempVar(this, linearExpression.value.typeSymbol());
        result.instructions.addAll(linearExpression.instructions);
        result.instructions.add(new AsmBitNot(tempVar, linearExpression.value));
        result.value = tempVar;

        return result;
    }
}
