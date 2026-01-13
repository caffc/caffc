package com.germaniumhq.caffc.compiler.model.source;

import org.antlr.v4.runtime.ParserRuleContext;

public interface SourceItem {
    /**
     * In what file is the current item defined?
     */
    String getFilePath();

    /**
     * At what line number is the current item defined?
     */
    int getLineNumber();

    /**
     * At what column number is the current item defined?
     */
    int getColumnNumber();

    static SourceItem fromAntlr(String filePath, ParserRuleContext antlrParserRuleContext) {
        return new DefaultSouceItem(
            filePath,
            antlrParserRuleContext.getStart().getLine(),
            antlrParserRuleContext.getStart().getCharPositionInLine()
        );
    }

    static SourceItem fromFilePath(String file) {
        return new DefaultSouceItem(file, 1, 1);
    }

    static SourceItem fromData(String filePath, int line, int charPositionInLine) {
        return new DefaultSouceItem(filePath, line, charPositionInLine);
    }
}
