package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBoolOperation;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ExpressionBoolCompare implements Expression {
    public AstItem owner;
    public Expression left;
    public Expression right;
    public String operator;

    public SourceLocation sourceLocation;

    public static ExpressionBoolCompare fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExEqNeqContext eqNeqExpression) {
        ExpressionBoolCompare result = new ExpressionBoolCompare();

        result.sourceLocation = SourceLocation.fromAntlr(unit.sourceLocation.filePath, eqNeqExpression);

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, eqNeqExpression.leftExpression);
        result.right = Expression.fromAntlr(unit, result, eqNeqExpression.rightExpression);
        result.operator = eqNeqExpression.getChild(1).getText();

        return result;
    }

    public static Expression fromAntlr2(CompilationUnit unit, AstItem owner, caffcParser.ExLtLteGtGteContext ltLteGtGteExpression) {
        ExpressionBoolCompare result = new ExpressionBoolCompare();

        result.sourceLocation = SourceLocation.fromAntlr(unit.sourceLocation.filePath, ltLteGtGteExpression);

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
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    @Override
    public void recurseResolveTypes() {
        this.left.recurseResolveTypes();
        this.right.recurseResolveTypes();
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        AsmLinearFormResult value1 = this.left.asLinearForm(block);
        AsmLinearFormResult value2 = this.right.asLinearForm(block);

        result.instructions.addAll(value1.instructions);
        result.instructions.addAll(value2.instructions);

        AsmVar resultValue = block.addTempVar(this, this.typeSymbol());

        result.instructions.add(new AsmBoolOperation(resultValue, this.operator, value1.value, value2.value));
        result.value = resultValue;

        return result;
    }
}
