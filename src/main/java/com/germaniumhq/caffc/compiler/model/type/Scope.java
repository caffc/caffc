package com.germaniumhq.caffc.compiler.model.type;

import com.germaniumhq.caffc.compiler.model.AstItem;

/**
 * A scope is a place for named things to exist. For example a class
 * that lives in a module, or a field in a class, etc. In the code
 * we have a name, let's say `x` and the scope is capable of saying
 * what that name stands for.
 */
public interface Scope extends AstItem {
    /**
     * Resolves the given request into a Symbol. If the Symbol doesn't exist,
     * the function will return null.
     *
     * @param name
     * @return
     */
    Symbol resolve(String name);
}
