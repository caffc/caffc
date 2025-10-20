package com.germaniumhq.caffc.compiler.model;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * An item in the AST. Every item in our AST implements this, and allows picking where it's
 * defined. A program is the top "AST" item, and its owner is null.
 */
public interface AstItem {
    /**
     * Get the owner of the current AST item. The top program has null as parent.
     */
    AstItem getOwner();

    /**
     * In what file is the current AST item defined?
     */
    String getFilePath();

    /**
     * At what line number is the current AST item defined?
     */
    int getLineNumber();

    /**
     * At what column number is the current AST item defined?
     */
    int getColumnNumber();

    /**
     * Recursively resolves the types from the current item. This has the
     * advantage of the type _knowing_ the order in which to call the resolving
     * for their children.
     */
    void recurseResolveTypes();

    default <T extends AstItem> T findAstParent(Class<T> clazz) {
        AstItem parentOwner = this.getOwner();

        while (parentOwner != null) {
            if (clazz.isAssignableFrom(parentOwner.getClass())) {
                return (T) parentOwner;
            }

            parentOwner = parentOwner.getOwner();
        }

        return null;
    }

    static AstItem fromAntlr(String filePath, ParserRuleContext antlrParserRuleContext) {
        return new DefaultAstItem(
                filePath,
                antlrParserRuleContext.getStart().getLine(),
                antlrParserRuleContext.getStart().getCharPositionInLine()
        );
    }

    static <T extends AstItem> T findParentOrSelf(AstItem owner, Class<T> clazz) {
        if (clazz.isAssignableFrom(owner.getClass())) {
            return (T) owner;
        }

        return owner.findAstParent(clazz);
    }

    static AstItem fromFilePath(String file) {
        return new DefaultAstItem(file, 1, 1);
    }

    static AstItem fromData(String filePath, int line, int charPositionInLine) {
        return new DefaultAstItem(filePath, line, charPositionInLine);
    }

    static String debugInfo(AstItem owner) {
        return owner.getFilePath() + ":" + owner.getLineNumber() + ":" + owner.getColumnNumber();
    }
}
