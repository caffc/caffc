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
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ExpressionNull implements Expression {
    private Symbol typeSymbol;
    private AstItem owner;
    public SourceLocation sourceLocation;

    public static ExpressionNull fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExNullContext nullExpression) {
        ExpressionNull result = new ExpressionNull();

        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, nullExpression);

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
        // If typeSymbol is not set, return a default object type
        // The actual type will be set during type resolution
        if (typeSymbol == null) {
            return new TypeSymbol(TypeName.OBJ);
        }
        return typeSymbol;
    }

    public void setTypeSymbol(Symbol typeSymbol) {
        this.typeSymbol = typeSymbol;
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
            codeRenderer.field("typeSymbol", this.typeSymbol);
        });
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        result.value = new AsmConstant(this.typeSymbol(), "0");

        return result;
    }
}
