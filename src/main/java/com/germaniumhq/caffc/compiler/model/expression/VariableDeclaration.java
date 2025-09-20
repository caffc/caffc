package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

public class VariableDeclaration implements AstItem, Symbol {
    public String name;
    public ExpressionAssign assignExpression;

    public VariableDeclarations owner;
    public String astFilePath;
    public int astColumn;
    public int astLine;

    private boolean isResolved;

    public static VariableDeclaration fromAntlr(CompilationUnit unit, VariableDeclarations owner, caffcParser.VariableDeclarationContext variableDeclarationContext) {
        VariableDeclaration result = new VariableDeclaration();

        result.owner = owner;
        result.astFilePath = unit.astFilePath;
        result.astLine = variableDeclarationContext.getStart().getLine();
        result.astColumn = variableDeclarationContext.getStart().getCharPositionInLine();

        result.name = variableDeclarationContext.ID().getText();
        caffcParser.ExpressionContext expressionContext = variableDeclarationContext.expression();

        if (expressionContext != null) {
            result.assignExpression = new ExpressionAssign();

            result.assignExpression.astFilePath = result.getFilePath();
            result.assignExpression.astLine = result.getLineNumber();
            result.assignExpression.astColumn = result.getColumnNumber();

            result.assignExpression.owner = result;
            result.assignExpression.left = ExpressionId.fromName(unit, result.assignExpression, result.name);
            result.assignExpression.right = Expression.fromAntlr(unit, result.assignExpression, expressionContext);
        }

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
        if (this.isResolved) {
            return;
        }

        this.isResolved = true;

        if (this.assignExpression != null) {
            this.assignExpression.recurseResolveTypes();
        }
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public TypeName typeName() {
        return this.owner.typeSymbol.typeName();
    }

    @Override
    public Symbol typeSymbol() {
        return this.owner.typeSymbol;
    }
}
