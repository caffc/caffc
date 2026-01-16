package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.AstItemCodeRenderer;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBitOperation;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ExpressionBitOperation implements Expression {
    public Expression left;
    public Expression right;
    public String operator;
    public AstItem owner;

    public String astFilePath;
    public int astColumn;
    public int astLine;
    public Symbol symbol;

    private boolean isResolved;

    public static ExpressionBitOperation fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExBitOrContext bitOrContext) {
        ExpressionBitOperation result = new ExpressionBitOperation();

        result.astFilePath = unit.astFilePath;
        result.astLine = bitOrContext.getStart().getLine();
        result.astColumn = bitOrContext.getStart().getCharPositionInLine();

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, bitOrContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, bitOrContext.rightExpression);
        result.operator = bitOrContext.getChild(1).getText();

        return result;
    }

    public static ExpressionBitOperation fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExBitAndContext bitAndContext) {
        ExpressionBitOperation result = new ExpressionBitOperation();

        result.astFilePath = unit.astFilePath;
        result.astLine = bitAndContext.getStart().getLine();
        result.astColumn = bitAndContext.getStart().getCharPositionInLine();

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, bitAndContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, bitAndContext.rightExpression);
        result.operator = bitAndContext.getChild(1).getText();

        return result;
    }

    public static ExpressionBitOperation fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExBitXorContext bitOrContext) {
        ExpressionBitOperation result = new ExpressionBitOperation();

        result.astFilePath = unit.astFilePath;
        result.astLine = bitOrContext.getStart().getLine();
        result.astColumn = bitOrContext.getStart().getCharPositionInLine();

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, bitOrContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, bitOrContext.rightExpression);
        result.operator = bitOrContext.getChild(1).getText();

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
        if (isResolved) {
            return;
        }

        isResolved = true;

        this.left.recurseResolveTypes();
        this.right.recurseResolveTypes();

        this.symbol = this.left.typeSymbol();
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
            codeRenderer.field("left", left);
            codeRenderer.field("operator", operator);
            codeRenderer.field("right", right);
        });
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        AsmLinearFormResult value1 = this.left.asLinearForm(block);
        AsmLinearFormResult value2 = this.right.asLinearForm(block);

        result.instructions.addAll(value1.instructions);
        result.instructions.addAll(value2.instructions);

        AsmVar resultValue = block.addTempVar(this, value1.value.typeSymbol());

        result.instructions.add(new AsmBitOperation(resultValue, this.operator, value1.value, value2.value));
        result.value = resultValue;

        return result;
    }
}
