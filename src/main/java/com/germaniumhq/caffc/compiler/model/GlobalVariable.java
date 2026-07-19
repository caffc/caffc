package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionAssign;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionId;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

/**
 * GlobalVariable for a module are - similar to the LocalVariable statements. However, they
 * won't get added into a compilation unit directly, instead they'll be added to the
 * module init function. The module init function is a synthetic function created by
 * CaffC that holds the initialization of all these variables.
 */
public class GlobalVariable implements CompileBlock, Statement, Symbol, AsmVar, AstItem {
    public String name;
    public ExpressionAssign assignExpression;

    public AstItem owner;
    public SourceLocation sourceLocation;

    public SymbolSearch typeSymbolSearch;
    public Symbol typeSymbol;

    private boolean isResolved;

    public static GlobalVariable fromAntlr(
        CompilationUnit unit,
        Module module,
        SymbolSearch symbolSearch,
        caffcParser.VariableDeclarationContext variableDeclarationContext) {

        GlobalVariable result = new GlobalVariable();

        result.owner = module;
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, variableDeclarationContext);
        result.typeSymbolSearch = symbolSearch;
        result.name = variableDeclarationContext.ID().getText();

        caffcParser.ExpressionContext expressionContext = variableDeclarationContext.expression();

        if (expressionContext != null) {
            result.assignExpression = new ExpressionAssign();

            result.assignExpression.sourceLocation = result.getSourceLocation();
            result.assignExpression.owner = result;
            result.assignExpression.leftExpressions.add(
                ExpressionId.fromName(unit, result.assignExpression, result.name)
            );

            result.assignExpression.right = Expression.fromAntlr(unit, result.assignExpression, expressionContext);
        }

        return result;
    }

    @Override
    public AstItem getOwner() {
        return owner;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    @Override
    public void recurseResolveTypes() {
        if (this.isResolved) {
            return;
        }

        this.isResolved = true;
        this.typeSymbol = SymbolResolver.mustResolveSymbol(this, this.typeSymbolSearch);

        if (this.assignExpression != null) {
            this.assignExpression.recurseResolveTypes();
        }
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        if (this.assignExpression == null) {
            return AsmLinearFormResult.EMPTY;
        }

        AsmLinearFormResult result = this.assignExpression.asLinearForm(block);
        return result;
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
    public String toString() {
        return "GlobalVariable{" +
            "typeSymbol=" + typeSymbol +
            ", name='" + name + '\'' +
            '}';
    }
}
