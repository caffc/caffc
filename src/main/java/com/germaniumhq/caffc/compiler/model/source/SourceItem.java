package com.germaniumhq.caffc.compiler.model.source;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * SourceItems are objects that are somehow derived from the original CaffC
 * source. For example both `AstItem`s and after the linear form `AsmInstruction`s
 * are source items.
 */
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
        return new DefaultSourceItem(
            filePath,
            antlrParserRuleContext.getStart().getLine(),
            antlrParserRuleContext.getStart().getCharPositionInLine()
        );
    }

    static SourceItem fromFilePath(String file) {
        return new DefaultSourceItem(file, 1, 1);
    }

    static SourceItem fromData(String filePath, int line, int charPositionInLine) {
        return new DefaultSourceItem(filePath, line, charPositionInLine);
    }
}
