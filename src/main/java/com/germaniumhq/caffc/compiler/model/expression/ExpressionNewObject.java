package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmNew;
import com.germaniumhq.caffc.compiler.model.asm.opc.Block;
import com.germaniumhq.caffc.compiler.model.asm.opc.Call;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

public final class ExpressionNewObject implements Expression {
    public AstItem owner;
    public List<Expression> parameters = new ArrayList<>();

    public String astFilePath;
    public int astColumn;
    public int astLine;

    public Symbol instantiatedType;
    private SymbolSearch instantiatedTypeSearch;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExNewObjectContext newObject) {
        ExpressionNewObject result = new ExpressionNewObject();

        result.owner = owner;
        result.astFilePath = unit.astFilePath;
        result.astLine = newObject.getStart().getLine();
        result.astColumn = newObject.getStart().getCharPositionInLine();
        result.instantiatedTypeSearch = SymbolSearch.fromAntlr(unit, newObject.newType());

        if (newObject.expressionTuple() != null) {
            for (caffcParser.ExpressionContext parameterExpression: newObject.expressionTuple().expression()) {
                result.parameters.add(Expression.fromAntlr(unit, result, parameterExpression));
            }
        }

        return result;
    }

    @Override
    public Symbol typeSymbol() {
        return instantiatedType;
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
        for (Expression parameter: parameters) {
            parameter.recurseResolveTypes();
        }

        this.instantiatedType = SymbolResolver.mustResolveSymbol(this, this.instantiatedTypeSearch);
    }

    @Override
    public AsmLinearFormResult asLinearForm(Block block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        // first we flatten the parameters themselves
        List<AsmLinearFormResult> linearParameters = new ArrayList<>();
        for (Expression parameter: this.parameters) {
            linearParameters.add(parameter.asLinearForm(block));
        }

        // add the parameters instructions + prepare the parameter values array for the asmNew
        AsmValue[] callParameters = new AsmValue[linearParameters.size()];
        for (int i = 0; i < linearParameters.size(); i++) {
            AsmLinearFormResult linearParameter = linearParameters.get(i);

            callParameters[i] = linearParameter.value;

            result.instructions.addAll(linearParameter.instructions);
        }

        AsmNew asmNew = new AsmNew(instantiatedType, callParameters);
        asmNew.result = block.addTempVar(this, instantiatedType);

        result.value = asmNew.result;
        result.instructions.add(asmNew);

        return result;
    }
}
