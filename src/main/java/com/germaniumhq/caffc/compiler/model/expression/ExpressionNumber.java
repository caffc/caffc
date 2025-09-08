package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.*;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

public class ExpressionNumber implements Expression {
    public Symbol type;
    public String value;
    private AstItem owner;
    public String astFilePath;
    public int astColumn;
    public int astLine;

    public static ExpressionNumber fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExNumberContext numberExpression) {
        ExpressionNumber result = new ExpressionNumber();

        result.owner = owner;
        result.astFilePath = unit.astFilePath;
        result.astLine = numberExpression.getStart().getLine();
        result.astColumn = numberExpression.getStart().getCharPositionInLine();
        result.type = new TypeSymbol(TypeName.I32); // FIXME: multiple types?
        result.value = numberExpression.getText();

        return result;
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

    @Override
    public Symbol getSymbol() {
        return type;
    }
}
