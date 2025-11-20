package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.AstItemCodeRenderer;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

public class VariableDeclarations implements AstItem, Statement {
    public AstItem owner;
    public SymbolSearch typeSymbolSearch;

    public List<VariableDeclaration> declarations = new ArrayList<>();

    public String astFilePath;
    public int astColumn;
    public int astLine;

    public Symbol typeSymbol;

    public static VariableDeclarations fromAntlr(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.VariableDeclarationsContext variableDeclarationsContext) {
        VariableDeclarations result = new VariableDeclarations();

        result.owner = owner;
        result.astFilePath = unit.astFilePath;
        result.astLine = variableDeclarationsContext.getStart().getLine();
        result.astColumn = variableDeclarationsContext.getStart().getCharPositionInLine();

        for (caffcParser.VariableDeclarationContext variableDeclarationContext: variableDeclarationsContext.variableDeclaration()) {
            result.declarations.add(VariableDeclaration.fromAntlr(unit, result, variableDeclarationContext));
        }

        result.typeSymbolSearch = SymbolSearch.fromAntlr(unit, variableDeclarationsContext.varType());

        Function function = result.findAstParent(Function.class);

        for (VariableDeclaration variableDeclaration: result.declarations) {
            function.registerVariable(variableDeclaration);
        }

        return result;
    }

    @Override
    public AstItem getOwner() {
        return this.owner;
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
        this.typeSymbol = SymbolResolver.resolveInstantiatedSymbol(this, this.typeSymbolSearch);

        for (VariableDeclaration declaration : declarations) {
            declaration.recurseResolveTypes();
        }
    }

    @Override
    public String toString() {
        return "VariableDeclaration{" +
                "typeSymbol=" + typeSymbol +
                '}';
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
            codeRenderer.field("declarations", this.declarations);
        });
    }
}
