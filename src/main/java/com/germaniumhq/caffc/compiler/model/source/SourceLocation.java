package com.germaniumhq.caffc.compiler.model.source;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * A source location. This is used to keep track of debug information.
 */
public class SourceLocation {
    public String filePath;
    public int lineNumber;
    public int columnNumber;

    public SourceLocation(
            String filePath,
            int lineNumber,
            int columnNumber) {
        this.filePath = filePath;
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    public static SourceLocation fromAntlr(String filePath, ParserRuleContext antlrParserRuleContext) {
        return new SourceLocation(
            filePath,
            antlrParserRuleContext.getStart().getLine(),
            antlrParserRuleContext.getStart().getCharPositionInLine()
        );
    }

    public static SourceLocation fromFilePath(String filePath) {
        return new SourceLocation(filePath, 0, 0);
    }

    public static SourceLocation fromData(String filePath, int lineNumber, int columnNumber) {
        return new SourceLocation(filePath, lineNumber, columnNumber);
    }

    public String getFilePath() {
        return filePath;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }
}
