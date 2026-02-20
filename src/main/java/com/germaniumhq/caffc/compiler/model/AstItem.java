package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.source.SourceItem;

/**
 * An item in the AST. Every item in our AST implements this.
 * A program is the top "AST" item, and its owner is null.
 */
public interface AstItem extends SourceItem {
    /**
     * Get the owner of the current AST item. The top program has null as parent.
     */
    AstItem getOwner();

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

    static <T extends AstItem> T findParentOrSelf(AstItem owner, Class<T> clazz) {
        if (clazz.isAssignableFrom(owner.getClass())) {
            return (T) owner;
        }

        return owner.findAstParent(clazz);
    }

    static String debugInfo(AstItem owner) {
        return owner.getFilePath() + ":" + owner.getLineNumber() + ":" + owner.getColumnNumber();
    }

    /**
     * Renders the current node using the given code renderer.
     */
    default void renderAst(AstItemCodeRenderer codeRenderer) {
        throw new UnsupportedOperationException("Not supported yet for: " + this.getClass().getCanonicalName());
    }
}
