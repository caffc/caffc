package com.germaniumhq.caffc.compiler.model;

// FIXME: remove if not used
public class DefaultAstItem implements AstItem {
    public String astFilePath;
    public int astLineNumber;
    public int astColumnNumber;

    public DefaultAstItem(
            String astFilePath,
            int astLineNumber,
            int astColumnNumber) {
        this.astFilePath = astFilePath;
        this.astLineNumber = astLineNumber;
        this.astColumnNumber = astColumnNumber;
    }

    @Override
    public AstItem getOwner() {
        return null;
    }

    @Override
    public String getFilePath() {
        return astFilePath;
    }

    @Override
    public int getLineNumber() {
        return astLineNumber;
    }

    @Override
    public int getColumnNumber() {
        return astColumnNumber;
    }

    @Override
    public void recurseResolveTypes() {
    }
}
