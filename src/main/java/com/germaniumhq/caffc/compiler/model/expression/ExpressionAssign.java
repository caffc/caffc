package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.AstItemCodeRenderer;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;
import com.germaniumhq.caffc.output.filters.FilterCTypeName;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAssign implements Expression {
    public AstItem owner;
    public List<Expression> leftExpressions = new ArrayList<>();
    public Expression right;

    public String astFilePath;
    public int astLine;
    public int astColumn;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExAssignContext assignExpression) {
        ExpressionAssign expression = new ExpressionAssign();

        expression.astFilePath = unit.astFilePath;
        expression.astLine = assignExpression.getStart().getLine();
        expression.astColumn = assignExpression.getStart().getCharPositionInLine();

        expression.owner = owner;

        for (int i = 0; i < assignExpression.expression().size() - 1; i++) {
            expression.leftExpressions.add(
                Expression.fromAntlr(unit, expression, assignExpression.expression(i))
            );
        }
        expression.right = Expression.fromAntlr(unit, expression, assignExpression.rightExpression);

        return expression;
    }

    public static ExpressionAssign fromCode(AstItem owner, Expression left, Expression right) {
        ExpressionAssign result = new ExpressionAssign();

        result.owner = owner;
        result.leftExpressions.add(left);
        result.right = right;

        result.astFilePath = owner.getFilePath();
        result.astLine = owner.getLineNumber();
        result.astColumn = owner.getColumnNumber();

        return result;
    }

    public boolean isIndex() {
        return this.leftExpressions.size() == 1 &&
            this.leftExpressions.get(0) instanceof ExpressionIndexAccess;
    }

    public Expression getLeft() {
        if (this.leftExpressions.size() == 1) {
            return this.leftExpressions.get(0);
        }

        throw new IllegalStateException("multiple expressions exist: " + this.leftExpressions);
    }

    @Override
    public Symbol typeSymbol() {
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

        for (Expression leftExpression: this.leftExpressions) {
            leftExpression.recurseResolveTypes();
        }

        if (this.leftExpressions.size() > 1) {
            String cType = FilterCTypeName.getCType(this.right.typeSymbol().typeName());
            AstItem.findParentOrSelf(this.owner, Function.class)
                .ensureVariableExists(this, cType + "_ret", this.right.typeSymbol());
        }

        // FIXME: check if the type is assignable from right to left
    }

    // #UsedInTemplate("assign.peb")
    public boolean isMultiReturn() {
        return this.leftExpressions.size() > 1;
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
            codeRenderer.field("leftExpressions", this.leftExpressions);
            codeRenderer.field("right", this.right);
        });
    }
}
