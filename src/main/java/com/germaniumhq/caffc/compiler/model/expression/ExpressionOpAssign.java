package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

public class ExpressionOpAssign implements Expression {
    public Expression left;
    public Expression right;
    public String operator;
    public AstItem owner;

    public String astFilePath;
    public int astColumn;
    public int astLine;
    public Symbol symbol;

    public static ExpressionOpAssign fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExOpAssignContext opAssignContext) {
        ExpressionOpAssign result = new ExpressionOpAssign();

        result.astFilePath = unit.astFilePath;
        result.astLine = opAssignContext.getStart().getLine();
        result.astColumn = opAssignContext.getStart().getCharPositionInLine();

        result.owner = owner;
        result.left = Expression.fromAntlr(unit, result, opAssignContext.leftExpression);
        result.right = Expression.fromAntlr(unit, result, opAssignContext.rightExpression);
        result.operator = opAssignContext.getChild(1).getText();

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
        this.left.recurseResolveTypes();
        this.right.recurseResolveTypes();

        this.symbol = new TypeSymbol(TypeName.VOID);
    }
}
