package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.*;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

/**
 * A string constant.
 */
public class ExpressionString implements Expression {
    public AstItem owner;
    public String constant;

    public String astFilePath;
    public int astColumn;
    public int astLine;

    public TypeSymbol symbol;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExStringContext stringExpression) {
        ExpressionString result = new ExpressionString();

        result.owner = owner;
        result.astFilePath = unit.astFilePath;
        result.astLine = stringExpression.getStart().getLine();
        result.astColumn = stringExpression.getStart().getCharPositionInLine();
        result.constant = stringExpression.STRING().getText();
        result.symbol = new TypeSymbol(TypeName.STR);

        unit.module.registerConstant(result.constant);

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
    }

}
