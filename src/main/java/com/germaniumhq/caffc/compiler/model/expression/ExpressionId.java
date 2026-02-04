package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.AstItemCodeRenderer;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.StructReturnVariableDefinition;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmFieldVar;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.generated.caffcParser;

public class ExpressionId implements Expression {
    public String name;
    public AstItem owner;
    public Symbol symbol;
    private Symbol _typeSymbol;

    public String astFilePath;
    public int astColumn;
    public int astLine;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExIdContext idExpression) {
        ExpressionId result = new ExpressionId();

        result.astFilePath = unit.astFilePath;
        result.astLine = idExpression.getStart().getLine();
        result.astColumn = idExpression.getStart().getCharPositionInLine();

        result.owner = owner;
        result.name = idExpression.getText();

        return result;
    }

    public static Expression fromName(CompilationUnit unit, AstItem owner, String name) {
        ExpressionId result = new ExpressionId();

        result.astFilePath = unit.astFilePath;
        result.astLine = owner.getLineNumber();
        result.astColumn = owner.getColumnNumber();

        result.owner = owner;
        result.name = name;

        return result;
    }

    @Override
    public Symbol typeSymbol() {
        return this._typeSymbol;
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
        this.symbol = SymbolResolver.mustResolveSymbol(this, SymbolSearch.ofName(this.name));
        this._typeSymbol = symbol.typeSymbol();
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
            codeRenderer.field("name", this.name);
        });
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        if (this.symbol instanceof StructReturnVariableDefinition structReturnVariableDefinition) {
            AsmLinearFormResult result = new AsmLinearFormResult();

            AsmVar returnType = (AsmVar) this.findAstParent(Function.class).resolve("result");
            result.value = new AsmFieldVar(returnType, this._typeSymbol, structReturnVariableDefinition.name);

            return result;
        }

        if (!(this.symbol instanceof AsmValue)) {
            CaffcCompiler.get().fatal(this, "BUG: ExpressionId is not an AsmValue: " + this.symbol);
        }

        AsmLinearFormResult result = new AsmLinearFormResult();
        result.value = (AsmValue) this.symbol;

        return result;
    }
}
