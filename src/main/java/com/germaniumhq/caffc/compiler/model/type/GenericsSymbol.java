package com.germaniumhq.caffc.compiler.model.type;

import com.germaniumhq.caffc.compiler.model.AstItem;

import java.util.Map;

/**
 * This represents a symbol that has generics that need replacing. Note that
 * the symbol itself may or may not contain the generics definition. For example
 * a Field doesn't have a definition, however its type can refer to the class
 * definition's generics. Similarly with Parameters for functions.
 *
 * All symbols that have generics, must exist in the code (aren't primitive types)
 * therefore they're AstItems as well.
 */
public interface GenericsSymbol extends Symbol, AstItem {
    /**
     * Makes a copy of the current AstItem type, to another one, where the
     * generics are replaced with the concrete values from the type. All
     * fields, returns and parameters of the class/function definitions will
     * be replaced with the values specified in the resolvedType.
     *
     * @param <T>
     * @param resolvedGenerics
     * @return
     */
    <T extends Symbol> T newGenericsCopy(Map<String, Symbol> resolvedGenerics);
}
