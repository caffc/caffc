package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.AstItemCodeRenderer;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBitOperation;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmCall;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.instruction.ExceptionHandler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ExpressionBitOperation implements Expression {
    public Expression left;
    public Expression right;
    public String operator;
    public AstItem owner;

    public SourceLocation sourceLocation;
    public Symbol symbol;

    private boolean isResolved;

    /** When set for {@code |}, lowers to {@code left.pipe(right)}. */
    private FunctionDefinition overloadFunction;

    public static ExpressionBitOperation fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExBitOrContext bitOrContext) {
        ExpressionBitOperation result = new ExpressionBitOperation();

        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, bitOrContext);

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, bitOrContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, bitOrContext.rightExpression);
        result.operator = bitOrContext.getChild(1).getText();

        return result;
    }

    public static ExpressionBitOperation fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExBitAndContext bitAndContext) {
        ExpressionBitOperation result = new ExpressionBitOperation();

        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, bitAndContext);

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, bitAndContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, bitAndContext.rightExpression);
        result.operator = bitAndContext.getChild(1).getText();

        return result;
    }

    public static ExpressionBitOperation fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExBitXorContext bitOrContext) {
        ExpressionBitOperation result = new ExpressionBitOperation();

        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, bitOrContext);

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
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    @Override
    public void recurseResolveTypes() {
        if (isResolved) {
            return;
        }

        isResolved = true;

        this.left.recurseResolveTypes();
        this.right.recurseResolveTypes();

        this.overloadFunction = CustomOperators.findMethod(
                this.left.typeSymbol(),
                CustomOperators.binaryMethodName(this.operator));

        if (this.overloadFunction != null) {
            this.overloadFunction.recurseResolveTypes();
            this.symbol = this.overloadFunction.returnType;
        } else {
            this.symbol = this.left.typeSymbol();
        }
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

        AsmVar resultValue = block.addTempVar(this, this.symbol);

        if (this.overloadFunction != null) {
            AsmLabel exceptionLabel = this.findAstParent(ExceptionHandler.class).getExceptionHandlingTargetLabel();
            AsmCall call = new AsmCall(
                    this.sourceLocation,
                    exceptionLabel,
                    this.overloadFunction,
                    value1.value,
                    value2.value);
            call.result = resultValue;
            result.instructions.add(call);
        } else {
            result.instructions.add(new AsmBitOperation(
                    this.sourceLocation, resultValue, this.operator, value1.value, value2.value));
        }

        result.value = resultValue;

        return result;
    }
}
