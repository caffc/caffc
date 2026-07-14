package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.AstItemCodeRenderer;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ExpressionFalse implements Expression {
    private AstItem owner;
    public SourceLocation sourceLocation;

    public static ExpressionFalse fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExFalseContext falseExpression) {
        ExpressionFalse result = new ExpressionFalse();

        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, falseExpression);

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
    }

    @Override
    public Symbol typeSymbol() {
        return TypeSymbol.BOOL;
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
        });
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();
        result.value = new AsmConstant(TypeSymbol.BOOL, "0");
        return result;
    }
}
