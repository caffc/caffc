package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBitOperation;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ExpressionOpAssign implements Expression {
    public Expression left;
    public Expression right;
    public String operator;
    public AstItem owner;

    public SourceLocation sourceLocation;
    public Symbol symbol;

    public static ExpressionOpAssign fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExOpAssignContext opAssignContext) {
        ExpressionOpAssign result = new ExpressionOpAssign();

        result.sourceLocation = SourceLocation.fromAntlr(unit.sourceLocation.filePath, opAssignContext);

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, opAssignContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, opAssignContext.rightExpression);
        result.operator = opAssignContext.getChild(1).getText();

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
        this.left.recurseResolveTypes();
        this.right.recurseResolveTypes();

        this.symbol = new TypeSymbol(TypeName.VOID);
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        AsmLinearFormResult leftLinear = left.asLinearForm(block);
        AsmLinearFormResult rightLinear = right.asLinearForm(block);

        if (!(leftLinear.value instanceof AsmVar)) {
            CaffcCompiler.get().fatal(this, "invalid left value: left expression is not a variable.");
        }

        result.value = leftLinear.value;
        result.instructions.addAll(leftLinear.instructions);
        result.instructions.addAll(rightLinear.instructions);

        result.instructions.add(new AsmBitOperation(
            (AsmVar) result.value,
            this.operator.substring(0, this.operator.length() - 1),
            result.value,
            rightLinear.value));

        return result;
    }
}
