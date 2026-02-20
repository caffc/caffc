package com.germaniumhq.caffc.compiler.model.source;

public class SourceItem {
    public String astFilePath;
    public int astLineNumber;
    public int astColumnNumber;

    public SourceItem(
            String astFilePath,
            int astLineNumber,
            int astColumnNumber) {
        this.astFilePath = astFilePath;
        this.astLineNumber = astLineNumber;
        this.astColumnNumber = astColumnNumber;
    }

    public String getFilePath() {
        return astFilePath;
    }

    public int getLineNumber() {
        return astLineNumber;
    }

    public int getColumnNumber() {
        return astColumnNumber;
    }
}
