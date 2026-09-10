package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.compiler.model.instruction.ExceptionHandler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.GenericInstantiations;
import com.germaniumhq.caffc.compiler.model.Module;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmCall;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.GenericsDefinitionsSymbol;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

public final class ExpressionFnCall implements Expression {
    private AstItem owner;

    public Expression functionExpression;
    public GenericInstantiations genericsInstantiations;

    /**
     * Raw call-site arguments (positional and/or named), before binding.
     */
    public List<CallArgument> callArguments = new ArrayList<>();

    /**
     * Fully bound positional argument expressions (including {@code _this},
     * defaults, and packed varargs/kwargs), filled during type resolution.
     */
    public List<Expression> parameters = new ArrayList<>();
    private Symbol symbol;

    public SourceLocation sourceLocation;

    private boolean isResolved;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExFnCallContext fnCallExpression) {
        ExpressionFnCall result = new ExpressionFnCall();

        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, fnCallExpression);
        result.functionExpression = Expression.fromAntlr(unit, result, fnCallExpression.expression());
        result.genericsInstantiations = GenericInstantiations.fromAntlr(unit, result, fnCallExpression.genericsInstantiations());

        if (fnCallExpression.callArgumentList() != null) {
            for (caffcParser.CallArgumentContext argumentContext : fnCallExpression.callArgumentList().callArgument()) {
                result.callArguments.add(CallArgument.fromAntlr(unit, result, argumentContext));
            }
        }

        return result;
    }

    @Override
    public Symbol typeSymbol() {
        FunctionDefinition functionDefinition = (FunctionDefinition) this.symbol;

        return functionDefinition.returnType;
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

        this.functionExpression.recurseResolveTypes();

        Symbol functionSymbol = this.functionExpression.typeSymbol();
        if (functionSymbol instanceof FunctionDefinition originalFunction) {
            // Ensure the canonical definition (and its parameters) are resolved before
            // instantiation / argument binding — needed for cross-module calls.
            originalFunction.recurseResolveTypes();
        }

        if (this.genericsInstantiations != null) {
            this.genericsInstantiations.recurseResolveTypes();
            this.symbol = GenericsDefinitionsSymbol.instantiateCopy(
                    functionSymbol,
                    this.genericsInstantiations.getResolvedSymbolList());
        } else {
            this.symbol = functionSymbol;
        }

        if (!(this.symbol instanceof FunctionDefinition)) {
            CaffcCompiler.get().fatal(this.functionExpression, "not a function definition");
        }

        FunctionDefinition functionDefinition = (FunctionDefinition) this.symbol;

        if (functionDefinition.clazz == null && Module.UNIT_INIT.equals(functionDefinition.name)) {
            CaffcCompiler.get().fatal(this,
                    "cannot call " + Module.UNIT_INIT + "(); it is inlined into " +
                            Module.MODULE_INIT + " and cannot be invoked");
        }

        for (CallArgument argument : callArguments) {
            argument.value.recurseResolveTypes();
        }

        Expression thisReceiver = null;
        if (this.functionExpression instanceof ExpressionDotAccess dotAccess && !functionDefinition.isStatic) {
            if (dotAccess.leftOfDot.typeSymbol().typeName().dataType != DataType.MODULE) {
                thisReceiver = dotAccess.leftOfDot;
            }
        }

        this.parameters = FunctionCallBinder.bind(
                this,
                this,
                functionDefinition,
                thisReceiver,
                callArguments);
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        FunctionDefinition functionDefinition = (FunctionDefinition) this.symbol;

        AsmLinearFormResult result = new AsmLinearFormResult();

        // Flatten each bound argument first (defaults / packs emit their instructions here)
        List<AsmLinearFormResult> linearParameters = new ArrayList<>();
        for (Expression parameter : this.parameters) {
            linearParameters.add(parameter.asLinearForm(block));
        }

        AsmValue[] callParameters = new AsmValue[linearParameters.size()];
        for (int i = 0; i < linearParameters.size(); i++) {
            AsmLinearFormResult linearParameter = linearParameters.get(i);

            callParameters[i] = linearParameter.value;

            result.instructions.addAll(linearParameter.instructions);
        }

        AsmLabel exceptionLabel = block.findAstParent(ExceptionHandler.class).getExceptionHandlingTargetLabel();
        AsmCall call = new AsmCall(this.sourceLocation, exceptionLabel, functionDefinition, callParameters);

        if (!functionDefinition.isVoid()) {
            call.result = block.addTempVar(this, functionDefinition.returnType);
            result.value = call.result;
        }

        result.instructions.add(call);

        return result;
    }
}
