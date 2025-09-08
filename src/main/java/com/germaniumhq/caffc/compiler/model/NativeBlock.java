package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.generated.caffcParser;

public class NativeBlock implements CompileBlock, Statement {
    public AstItem owner;
    public String text;

    public String astFilePath;
    public int astColumn;
    public int astLine;

    public static NativeBlock fromAntlr(CompilationUnit unit, caffcParser.NativeBlockContext ctx, AstItem owner) {
        NativeBlock result = new NativeBlock();

        result.astFilePath = unit.astFilePath;
        result.astLine = ctx.getStart().getLine();
        result.astColumn = ctx.getStart().getCharPositionInLine();

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
    }
}
