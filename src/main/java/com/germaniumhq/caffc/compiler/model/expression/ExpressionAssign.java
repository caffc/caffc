package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

public class ExpressionAssign implements Expression {
    public AstItem owner;
    public Expression left;
    public Expression right;

    public String astFilePath;
    public int astColumn;
    public int astLine;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExAssignContext assignExpression) {
        ExpressionAssign expression = new ExpressionAssign();

        expression.astFilePath = unit.astFilePath;
        expression.astLine = assignExpression.getStart().getLine();
        expression.astColumn = assignExpression.getStart().getCharPositionInLine();

        expression.owner = owner;

        expression.left = Expression.fromAntlr(unit, expression, assignExpression.leftExpression);
        expression.right = Expression.fromAntlr(unit, expression, assignExpression.rightExpression);

        return expression;
    }

    public boolean isIndex() {
        return this.left instanceof ExpressionIndexAccess;
    }

    @Override
    public Symbol getSymbol() {
        return new TypeSymbol(TypeName.VOID);
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
        this.right.recurseResolveTypes();
        this.left.recurseResolveTypes();

        // FIXME: check if the type is assignable from right to left
    }
}
