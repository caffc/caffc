package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.BlockVariable;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmNewArray;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Instantiate an array that can be uni or multi-dimensional.
 */
public class ExpressionNewArray implements Expression {
    public AstItem owner;
    public List<Expression> countExpressions = new ArrayList<>();

    public String astFilePath;
    public int astColumn;
    public int astLine;

    private SymbolSearch symbolSearch;
    public Symbol symbol;

    public static ExpressionNewArray fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExNewArrayContext newArrayExpression) {
        ExpressionNewArray result = new ExpressionNewArray();

        result.astFilePath = unit.astFilePath;
        result.astLine = newArrayExpression.getStart().getLine();
        result.astColumn = newArrayExpression.getStart().getCharPositionInLine();
        result.owner = owner;

        final String baseType = newArrayExpression.newType().getText();

        for (caffcParser.ExpressionContext expression: newArrayExpression.expression()) {
            result.countExpressions.add(Expression.fromAntlr(unit, result, expression));
        }

        int arraySize = result.countExpressions.size();
        String arrayType = asArray(baseType, arraySize);
        result.symbolSearch = SymbolSearch.ofName(arrayType);

        boolean isPrimitiveArray = TypeName.primitiveType(baseType) != null && arraySize <= 1;

        if (!isPrimitiveArray) {
            result.symbolSearch.generics = new SymbolSearch[]{
                SymbolSearch.ofName(arraySize <= 1 ? baseType : asArray(baseType, arraySize - 1))
            };
        }

        return result;
    }

    private static String asArray(String baseType, int size) {
        StringBuilder result = new StringBuilder(baseType);

        for (int i = 0; i < size; i++) {
            result.append("[]");
        }

        return result.toString();
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
        for (Expression countExpression : countExpressions) {
            countExpression.recurseResolveTypes();
        }

        this.symbol = SymbolResolver.mustResolveSymbol(this, this.symbolSearch);
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();
        BlockVariable resultValue = block.addTempVar(this, this.symbol);

        result.value = resultValue;
        List<AsmValue> dimensions = new ArrayList<>();

        for (Expression countExpression : countExpressions) {
            AsmLinearFormResult linearCountExpression = countExpression.asLinearForm(block);
            result.instructions.addAll(linearCountExpression.instructions);
            dimensions.add(linearCountExpression.value);
        }

        result.instructions.add(new AsmNewArray(resultValue, symbol, dimensions));

        return result;
    }
}
