package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.AstItemCodeRenderer;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmCall;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmMath;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmMathOperator;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.instruction.ExceptionHandler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ExpressionMath implements Expression {
    public Expression left;
    public Expression right;
    public String operator;
    public AstItem owner;

    public SourceLocation sourceLocation;
    public Symbol symbol;

    /** When set, {@code left op right} lowers to {@code left.method(right)}. */
    private FunctionDefinition overloadFunction;

    public static ExpressionMath fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExAddSubContext addSubExpression) {
        ExpressionMath result = new ExpressionMath();

        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, addSubExpression);

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, addSubExpression.leftExpression);
        result.right = Expression.fromAntlr(unit, result, addSubExpression.rightExpression);
        result.operator = addSubExpression.getChild(1).getText();

        return result;
    }

    public static ExpressionMath fromAntlrMulMod(CompilationUnit unit, AstItem owner, caffcParser.ExMulModContext mulModContext) {
        ExpressionMath result = new ExpressionMath();

        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, mulModContext);

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, mulModContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, mulModContext.rightExpression);
        result.operator = mulModContext.getChild(1).getText();

        return result;
    }

    public static ExpressionMath fromAntlrDiv(CompilationUnit unit, AstItem owner, caffcParser.ExDivContext divContext) {
        ExpressionMath result = new ExpressionMath();

        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, divContext);

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, divContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, divContext.rightExpression);
        result.operator = divContext.getChild(1).getText();

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
            codeRenderer.field("left", this.left);
            codeRenderer.field("operator", this.operator);
            codeRenderer.field("right", this.right);
        });
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult left = this.left.asLinearForm(block);
        AsmLinearFormResult right = this.right.asLinearForm(block);

        AsmLinearFormResult result = new AsmLinearFormResult();
        result.value = block.addTempVar(this, this.symbol);

        result.instructions.addAll(left.instructions);
        result.instructions.addAll(right.instructions);

        if (this.overloadFunction != null) {
            AsmLabel exceptionLabel = this.findAstParent(ExceptionHandler.class).getExceptionHandlingTargetLabel();
            AsmCall call = new AsmCall(
                    this.sourceLocation,
                    exceptionLabel,
                    this.overloadFunction,
                    left.value,
                    right.value);
            call.result = (AsmVar) result.value;
            result.instructions.add(call);
        } else {
            result.instructions.add(new AsmMath(
                    this.sourceLocation,
                    (AsmVar) result.value,
                    AsmMathOperator.fromString(this.operator),
                    left.value,
                    right.value
            ));
        }

        return result;
    }
}
