package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmCast;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ExpressionCast implements Expression {
    public SymbolSearch castTypeSearch;
    public Symbol castType;
    public Expression expression;

    public AstItem owner;
    public Symbol symbol;

    public SourceLocation sourceLocation;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExCastContext castContext) {
        ExpressionCast result = new ExpressionCast();

        result.sourceLocation = SourceLocation.fromAntlr(unit.sourceLocation.filePath, castContext);

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
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    @Override
    public void recurseResolveTypes() {
        this.expression.recurseResolveTypes();
        castType = SymbolResolver.mustResolveSymbol(this, this.castTypeSearch);
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();
        AsmVar tempVar = block.addTempVar(this, this.castType);

        AsmLinearFormResult expressionLinearForm = expression.asLinearForm(block);
        result.instructions.addAll(expressionLinearForm.instructions);

        result.value = tempVar;
        result.instructions.add(new AsmCast(this.castType, tempVar, expressionLinearForm.value));

        return result;
    }
}
