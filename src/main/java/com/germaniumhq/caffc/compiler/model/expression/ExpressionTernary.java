package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.BlockVariable;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmAssign;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmIfZJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ExpressionTernary implements Expression {
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

        if (shiftContext.trueExpression != null) {
            result.trueExpression = Expression.fromAntlr(unit, result, shiftContext.trueExpression);
        }

        result.falseExpression = Expression.fromAntlr(unit, result, shiftContext.falseExpression);

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

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult linearFormResult = new AsmLinearFormResult();
        AsmLabel ternaryElseLabel = new AsmLabel("ternaryElse");
        AsmLabel ternaryEndLabel = new AsmLabel("ternaryEnd");

        BlockVariable resultAsmVar;

        if (this.trueExpression != null) {
            resultAsmVar = block.addTempVar(this, this.trueExpression.typeSymbol());
        } else {
            resultAsmVar = block.addTempVar(this, this.falseExpression.typeSymbol());
        }
        linearFormResult.value = resultAsmVar;

        // check
        AsmLinearFormResult checkLinear = this.checkExpression.asLinearForm(block);
        linearFormResult.instructions.addAll(checkLinear.instructions);
        linearFormResult.instructions.add(new AsmIfZJmp(checkLinear.value, ternaryElseLabel));

        // true matches
        if (this.trueExpression != null) {
            AsmLinearFormResult trueExpressionLinear = this.trueExpression.asLinearForm(block);
            linearFormResult.instructions.addAll(trueExpressionLinear.instructions);
            linearFormResult.instructions.add(new AsmAssign(resultAsmVar, trueExpressionLinear.value));
        } else {
            linearFormResult.instructions.add(new AsmAssign(resultAsmVar, checkLinear.value));
        }

        linearFormResult.instructions.add(new AsmJmp(ternaryEndLabel));

        // else matches
        linearFormResult.instructions.add(ternaryElseLabel);

        AsmLinearFormResult falseExpressionLinear = this.falseExpression.asLinearForm(block);
        linearFormResult.instructions.addAll(falseExpressionLinear.instructions);

        linearFormResult.instructions.add(new AsmAssign(resultAsmVar, falseExpressionLinear.value));

        // end of ternary
        linearFormResult.instructions.add(ternaryEndLabel);

        return linearFormResult;
    }
}
