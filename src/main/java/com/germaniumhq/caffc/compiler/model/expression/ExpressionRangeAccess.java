package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.HasMethods;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmCall;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.instruction.ExceptionHandler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

/**
 * Range sugar {@code x[from:to]} that lowers to {@code x.range(from, to)}.
 * Omitted {@code from} becomes {@code 0}; omitted {@code to} becomes {@code x.size()}.
 */
public final class ExpressionRangeAccess implements Expression, AstItem {
    private AstItem owner;
    public Expression expression;
    /** Inclusive start; {@code null} means {@code 0}. */
    public Expression start;
    /** Exclusive end; {@code null} means {@code expression.size()}. */
    public Expression end;

    public SourceLocation sourceLocation;
    private Symbol symbol;
    private FunctionDefinition rangeFunction;
    private FunctionDefinition sizeFunction;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExRangeAccessContext rangeAccessExpression) {
        ExpressionRangeAccess result = new ExpressionRangeAccess();

        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, rangeAccessExpression);
        result.owner = owner;
        result.expression = Expression.fromAntlr(unit, result, rangeAccessExpression.arrayExpression);

        if (rangeAccessExpression.startExpression != null) {
            result.start = Expression.fromAntlr(unit, result, rangeAccessExpression.startExpression);
        }

        if (rangeAccessExpression.endExpression != null) {
            result.end = Expression.fromAntlr(unit, result, rangeAccessExpression.endExpression);
        }

        return result;
    }

    public static ExpressionRangeAccess fromParts(AstItem owner, Expression expression, Expression start, Expression end) {
        ExpressionRangeAccess result = new ExpressionRangeAccess();

        result.sourceLocation = owner.getSourceLocation();
        result.owner = owner;
        result.expression = expression;
        result.start = start;
        result.end = end;

        return result;
    }

    @Override
    public Symbol typeSymbol() {
        return symbol;
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

        if (this.start != null) {
            this.start.recurseResolveTypes();
        }

        if (this.end != null) {
            this.end.recurseResolveTypes();
        }

        Symbol expressionSymbol = this.expression.typeSymbol();
        Symbol type = expressionSymbol;

        while (!(type instanceof HasMethods) && type != null && type.typeSymbol() != type) {
            type = type.typeSymbol();
        }

        if (!(type instanceof HasMethods hasMethods)) {
            CaffcCompiler.get().fatal(this, "range access requires a type with methods: " + expressionSymbol);
            throw new IllegalStateException("not reachable");
        }

        this.rangeFunction = hasMethods.getFunction("range");

        if (this.rangeFunction == null) {
            CaffcCompiler.get().fatal(this, "no `range` function defined for " + hasMethods);
            throw new IllegalStateException("not reachable");
        }

        this.rangeFunction.recurseResolveTypes();
        this.symbol = this.rangeFunction.returnType;

        if (this.end == null) {
            this.sizeFunction = hasMethods.getFunction("size");
            if (this.sizeFunction == null) {
                CaffcCompiler.get().fatal(this,
                        "omitted range end requires a `size` function on " + hasMethods);
                throw new IllegalStateException("not reachable");
            }
            this.sizeFunction.recurseResolveTypes();
        }
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        AsmLinearFormResult expressionLinear = this.expression.asLinearForm(block);
        result.instructions.addAll(expressionLinear.instructions);

        AsmValue startValue;
        if (this.start != null) {
            AsmLinearFormResult startLinear = this.start.asLinearForm(block);
            result.instructions.addAll(startLinear.instructions);
            startValue = startLinear.value;
        } else {
            startValue = new AsmConstant(TypeSymbol.I32, "0");
        }

        AsmValue endValue;
        if (this.end != null) {
            AsmLinearFormResult endLinear = this.end.asLinearForm(block);
            result.instructions.addAll(endLinear.instructions);
            endValue = endLinear.value;
        } else {
            AsmVar sizeResult = block.addTempVar(this, this.sizeFunction.returnType);
            AsmLabel exceptionLabel = this.findAstParent(ExceptionHandler.class).getExceptionHandlingTargetLabel();
            AsmCall sizeCall = new AsmCall(
                    this.sourceLocation,
                    exceptionLabel,
                    this.sizeFunction,
                    expressionLinear.value);
            sizeCall.result = sizeResult;
            result.instructions.add(sizeCall);
            endValue = sizeResult;
        }

        AsmVar resultValue = block.addTempVar(this, this.symbol);
        result.value = resultValue;

        AsmLabel exceptionLabel = this.findAstParent(ExceptionHandler.class).getExceptionHandlingTargetLabel();
        AsmCall rangeCall = new AsmCall(
                this.sourceLocation,
                exceptionLabel,
                this.rangeFunction,
                expressionLinear.value,
                startValue,
                endValue);
        rangeCall.result = resultValue;
        result.instructions.add(rangeCall);

        return result;
    }
}
