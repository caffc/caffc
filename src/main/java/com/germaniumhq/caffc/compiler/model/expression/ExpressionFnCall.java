package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.GenericInstantiations;
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

    public List<Expression> parameters = new ArrayList<>();
    private Symbol symbol;

    public SourceLocation sourceLocation;

    private boolean isResolved;

    /**
     * Converts an ANTLR-based function call expression into a corresponding ExpressionFnCall object.
     *
     * @param unit             The CompilationUnit containing the AST information.
     * @param owner            The AstItem that owns the function call expression.
     * @param fnCallExpression The ANTLR context representing the function call expression.
     * @return An ExpressionFnCall object representing the function call expression.
     */
    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExFnCallContext fnCallExpression) {
        ExpressionFnCall result = new ExpressionFnCall();

        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlr(unit.sourceLocation.filePath, fnCallExpression);
        result.functionExpression = Expression.fromAntlr(unit, result, fnCallExpression.expression());
        result.genericsInstantiations = GenericInstantiations.fromAntlr(unit, result, fnCallExpression.genericsInstantiations());

        if (fnCallExpression.expressionTuple() != null) {
            for (caffcParser.ExpressionContext parameterExpression: fnCallExpression.expressionTuple().expression()) {
                result.parameters.add(Expression.fromAntlr(unit, result, parameterExpression));
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

        if (this.genericsInstantiations != null) {
            this.genericsInstantiations.recurseResolveTypes();
            this.symbol = GenericsDefinitionsSymbol.instantiateCopy(
                    this.functionExpression.typeSymbol(),
                    this.genericsInstantiations.getResolvedSymbolList());
        } else {
            this.symbol = this.functionExpression.typeSymbol();
        }

        if (!(this.symbol instanceof FunctionDefinition)) {
            CaffcCompiler.get().fatal(this.functionExpression, "not a function definition");
        }

        FunctionDefinition functionDefinition = (FunctionDefinition) this.symbol;

        // if we have a dot access function, it means the function is a field of something
        // else, so we need to get the first part as its first parameter, and call the function.
        if (this.functionExpression instanceof ExpressionDotAccess dotAccess && !functionDefinition.isStatic) {
            if (dotAccess.leftOfDot.typeSymbol().typeName().dataType != DataType.MODULE) {
                this.parameters.add(0, dotAccess.leftOfDot);
            }
        }

        for (Expression parameter: parameters) {
            parameter.recurseResolveTypes();
        }
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        FunctionDefinition functionDefinition = (FunctionDefinition) this.symbol;

        AsmLinearFormResult result = new AsmLinearFormResult();

        // first we flatten the parameters themselves
        List<AsmLinearFormResult> linearParameters = new ArrayList<>();
        for (Expression parameter: this.parameters) {
            linearParameters.add(parameter.asLinearForm(block));
        }

        // add the parameters instructions + prepare the parameter values array for the call
        AsmValue[] callParameters = new AsmValue[linearParameters.size()];
        for (int i = 0; i < linearParameters.size(); i++) {
            AsmLinearFormResult linearParameter = linearParameters.get(i);

            callParameters[i] = linearParameter.value;

            result.instructions.addAll(linearParameter.instructions);
        }

        // add call instruction
        AsmCall call = new AsmCall(functionDefinition, callParameters);

        if (!functionDefinition.isVoid()) {
            call.result = block.addTempVar(this, functionDefinition.returnType);
            result.value = call.result;
        }

        result.instructions.add(call);

        return result;
    }
}
