package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmThrow;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ThrowInstruction implements Statement {
    public AstItem owner;
    public SourceLocation sourceLocation;
    public Expression exceptionExpression;

    public static ThrowInstruction fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ThrowStatementContext ctx) {
        ThrowInstruction result = new ThrowInstruction();

        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, ctx);
        result.exceptionExpression = Expression.fromAntlr(unit, result, ctx.expression());

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
        exceptionExpression.recurseResolveTypes();
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        AsmLinearFormResult exceptionLinear = exceptionExpression.asLinearForm(block);
        result.instructions.addAll(exceptionLinear.instructions);

        result.instructions.add(new AsmThrow(this.sourceLocation, exceptionLinear.value));

        return result;
    }
}
