package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBoolNot;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ExpressionBoolNot implements Expression {
    public Expression expression;
    public AstItem owner;

    public SourceLocation sourceLocation;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExBoolNotContext boolNotContext) {
        ExpressionBoolNot result = new ExpressionBoolNot();

        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, boolNotContext);

        result.owner = owner;
        result.expression = Expression.fromAntlr(unit, result, boolNotContext.expression());

        return result;
    }

    @Override
    public Symbol typeSymbol() {
        return TypeSymbol.BOOL;
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
        AsmLinearFormResult result = new AsmLinearFormResult();

        AsmLinearFormResult linearExpression = this.expression.asLinearForm(block);

        AsmVar tempVar = block.addTempVar(this, TypeSymbol.BOOL);
        result.instructions.addAll(linearExpression.instructions);
        result.instructions.add(new AsmBoolNot(this.sourceLocation, tempVar, linearExpression.value));
        result.value = tempVar;

        return result;
    }
}
