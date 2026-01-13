package com.germaniumhq.caffc.compiler.model.source;

import com.germaniumhq.caffc.compiler.model.AstItem;

// FIXME: remove if not used
public class DefaultSouceItem implements AstItem {
    public String astFilePath;
    public int astLineNumber;
    public int astColumnNumber;

    public DefaultSouceItem(
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
