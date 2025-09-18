package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.*;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.GenericsDefinitionsSymbol;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

public class ExpressionFnCall implements Expression {
    private AstItem owner;

    public Expression functionExpression;
    public GenericInstantiations genericsInstantiations;

    public List<Expression> parameters = new ArrayList<>();
    private Symbol symbol;

    public String astFilePath;
    public int astColumn;
    public int astLine;

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
        result.astFilePath = unit.astFilePath;
        result.astLine = fnCallExpression.getStart().getLine();
        result.astColumn = fnCallExpression.getStart().getCharPositionInLine();
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
        this.functionExpression.recurseResolveTypes();

        // if we have a dot access function, it means the function is a field of something
        // else, so we need to get the first part as its first parameter, and call the function.
        if (this.functionExpression instanceof ExpressionDotAccess dotAccess) {
            if (dotAccess.leftOfDot.typeSymbol().typeName().dataType != DataType.MODULE) {
                this.parameters.add(0, dotAccess.leftOfDot);
            }
        }

        for (Expression parameter: parameters) {
            parameter.recurseResolveTypes();
        }

        // FIXME: resolve the generics instantiations, and copy the function
        if (this.genericsInstantiations != null) {
            this.genericsInstantiations.recurseResolveTypes();
            this.symbol = GenericsDefinitionsSymbol.instantiateCopy(
                    this.functionExpression.typeSymbol(),
                    this.genericsInstantiations.getResolvedSymbolList());
        } else {
            this.symbol = this.functionExpression.typeSymbol();
        }
    }

}
