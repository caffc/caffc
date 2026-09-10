package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBitOperation;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmCall;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.instruction.ExceptionHandler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ExpressionOpAssign implements Expression {
    public Expression left;
    public Expression right;
    public String operator;
    public AstItem owner;

    public SourceLocation sourceLocation;

    /**
     * When set, {@code left op= right} lowers to {@code left = left.method(right)}
     * ({@code *All} methods are expected to return {@code _this}).
     */
    private FunctionDefinition overloadFunction;

    public static ExpressionOpAssign fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExOpAssignContext opAssignContext) {
        ExpressionOpAssign result = new ExpressionOpAssign();

        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, opAssignContext);

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, opAssignContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, opAssignContext.rightExpression);
        result.operator = opAssignContext.getChild(1).getText();

        return result;
    }

    @Override
    public Symbol typeSymbol() {
        return TypeSymbol.VOID;
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

        ReadonlyAssignCheck.check(this, this.left);

        this.overloadFunction = CustomOperators.findMethod(
                this.left.typeSymbol(),
                CustomOperators.assignMethodName(this.operator));

        if (this.overloadFunction != null) {
            this.overloadFunction.recurseResolveTypes();
        }
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

        if (this.overloadFunction != null) {
            AsmLabel exceptionLabel = this.findAstParent(ExceptionHandler.class).getExceptionHandlingTargetLabel();
            AsmCall call = new AsmCall(
                    this.sourceLocation,
                    exceptionLabel,
                    this.overloadFunction,
                    leftLinear.value,
                    rightLinear.value);
            // Assign return value (_this) back onto the left lvalue.
            call.result = (AsmVar) leftLinear.value;
            result.instructions.add(call);
        } else {
            result.instructions.add(new AsmBitOperation(
                    this.sourceLocation,
                    (AsmVar) result.value,
                    this.operator.substring(0, this.operator.length() - 1),
                    result.value,
                    rightLinear.value));
        }

        return result;
    }
}
