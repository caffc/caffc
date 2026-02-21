package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBoolOperation;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ExpressionBoolOperation implements Expression {
    public AstItem owner;
    public Expression left;
    public Expression right;
    public String operator;

    public SourceLocation sourceLocation;

    public static ExpressionBoolOperation fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExBoolAndContext exBoolAndContext) {
        ExpressionBoolOperation result = new ExpressionBoolOperation();

        result.sourceLocation = SourceLocation.fromAntlr(unit.sourceLocation.filePath, exBoolAndContext);

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, exBoolAndContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, exBoolAndContext.rightExpression);
        result.operator = "&&";

        return result;
    }


    public static ExpressionBoolOperation fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExBoolOrContext exBoolOrContext) {
        ExpressionBoolOperation result = new ExpressionBoolOperation();

        result.sourceLocation = SourceLocation.fromAntlr(unit.sourceLocation.filePath, exBoolOrContext);

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
        AsmLinearFormResult linearFormResult = new AsmLinearFormResult();

        AsmLinearFormResult leftLinearForm = this.left.asLinearForm(block);
        AsmLinearFormResult rightLinearForm = this.right.asLinearForm(block);

        AsmVar resultVar = block.addTempVar(this, new TypeSymbol(TypeName.BOOL));

        linearFormResult.instructions.addAll(leftLinearForm.instructions);
        linearFormResult.instructions.addAll(rightLinearForm.instructions);

        linearFormResult.instructions.add(
            new AsmBoolOperation(
                resultVar,
                this.operator,
                leftLinearForm.value,
                rightLinearForm.value
            ));
        linearFormResult.value = resultVar;

        return linearFormResult;
    }
}
