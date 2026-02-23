package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.asm.opc.AsmInstruction;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.generated.caffcParser;

final public class NativeBlock implements CompileBlock, Statement, AsmInstruction {
    public AstItem owner;
    public String text;

    public SourceLocation sourceLocation;

    public static NativeBlock fromAntlr(CompilationUnit unit, caffcParser.NativeBlockContext ctx, AstItem owner) {
        NativeBlock result = new NativeBlock();

        result.sourceLocation = SourceLocation.fromAntlr(unit.sourceLocation.filePath, ctx);

        result.owner = owner;
        String nativeText = ctx.NATIVE().getText();
        result.text = nativeText.substring(nativeText.indexOf('{') + 1, nativeText.length() - 1);

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
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        result.instructions.add(this);

        return result;
    }
}
