package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.AstItemCodeRenderer;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmAssign;
import com.germaniumhq.caffc.compiler.model.asm.opc.Block;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

public final class VariableDeclaration implements AstItem, Symbol, AsmVar, Statement {
    public String name;
    public ExpressionAssign assignExpression;

    public AstItem owner;

    public SymbolSearch typeSymbolSearch;
    public Symbol typeSymbol;

    public String astFilePath;
    public int astColumn;
    public int astLine;

    public boolean isResolved;

    public static VariableDeclaration fromAntlr(
        CompilationUnit unit,
        AstItem owner,
        SymbolSearch symbolSearch,
        caffcParser.VariableDeclarationContext variableDeclarationContext) {
        VariableDeclaration result = new VariableDeclaration();

        result.owner = owner;
        result.astFilePath = unit.astFilePath;
        result.astLine = variableDeclarationContext.getStart().getLine();
        result.astColumn = variableDeclarationContext.getStart().getCharPositionInLine();

        result.typeSymbolSearch = symbolSearch;
        result.name = variableDeclarationContext.ID().getText();

        caffcParser.ExpressionContext expressionContext = variableDeclarationContext.expression();

        if (expressionContext != null) {
            result.assignExpression = new ExpressionAssign();

            result.assignExpression.astFilePath = result.getFilePath();
            result.assignExpression.astLine = result.getLineNumber();
            result.assignExpression.astColumn = result.getColumnNumber();

            result.assignExpression.owner = result;
            result.assignExpression.leftExpressions.add(
                ExpressionId.fromName(unit, result.assignExpression, result.name)
            );

            result.assignExpression.right = Expression.fromAntlr(unit, result.assignExpression, expressionContext);
        }

        return result;
    }

    public static VariableDeclaration fromEnsure(AstItem owner, Symbol typeSymbol, String name) {
        VariableDeclaration result = new VariableDeclaration();

        result.name = name;
        result.typeSymbol = typeSymbol;
        result.owner = owner;
        result.astFilePath = owner.getFilePath();
        result.astLine = owner.getLineNumber();
        result.astColumn = owner.getColumnNumber();

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
        this.typeSymbol = SymbolResolver.resolveInstantiatedSymbol(this, this.typeSymbolSearch);

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
        return this.typeSymbol().typeName();
    }

    @Override
    public Symbol typeSymbol() {
        return this.typeSymbol;
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
            codeRenderer.field("typeSymbol", this.typeSymbol);
            codeRenderer.field("name", this.name);
            codeRenderer.field("expression", this.assignExpression);
        });
    }

    @Override
    public AsmLinearFormResult asLinearForm(Block block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        if (this.assignExpression != null) {
            AsmLinearFormResult right = this.assignExpression.right.asLinearForm(block);
            result.instructions.addAll(right.instructions);

            result.instructions.add(new AsmAssign(this, right.value));
        } else {
            result.instructions.add(new AsmAssign(this, new AsmConstant(this.typeSymbol, "0")));
        }

        return result;
    }
}
