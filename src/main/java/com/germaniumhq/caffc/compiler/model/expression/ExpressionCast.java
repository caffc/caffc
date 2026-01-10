package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.BlockVariable;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmCast;
import com.germaniumhq.caffc.compiler.model.asm.opc.Block;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.generated.caffcParser;

public class ExpressionCast implements Expression {
    public SymbolSearch castTypeSearch;
    public Symbol castType;
    public Expression expression;

    public AstItem owner;
    public Symbol symbol;

    public String astFilePath;
    public int astColumn;
    public int astLine;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExCastContext castContext) {
        ExpressionCast result = new ExpressionCast();

        result.astFilePath = unit.astFilePath;
        result.astLine = castContext.getStart().getLine();
        result.astColumn = castContext.getStart().getCharPositionInLine();

        result.owner = owner;
        result.expression = Expression.fromAntlr(unit, result, castContext.expression());
        result.castTypeSearch = SymbolSearch.fromTypeContext(unit, castContext.typeName());

        return result;
    }

    @Override
    public Symbol typeSymbol() {
        return castType;
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
        this.expression.recurseResolveTypes();
        castType = SymbolResolver.mustResolveSymbol(this, this.castTypeSearch);
    }

    @Override
    public AsmLinearFormResult asLinearForm(Block block) {
        AsmLinearFormResult result = new AsmLinearFormResult();
        BlockVariable tempVar = block.addTempVar(this, this.castType);

        AsmLinearFormResult expressionLinearForm = expression.asLinearForm(block);
        result.instructions.addAll(expressionLinearForm.instructions);

        result.value = tempVar;
        result.instructions.add(new AsmCast(this.castType, tempVar, expressionLinearForm.value));

        return result;
    }
}
