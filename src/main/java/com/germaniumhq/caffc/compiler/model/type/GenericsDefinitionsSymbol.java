package com.germaniumhq.caffc.compiler.model.type;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.GenericDefinition;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This symbol can contain a bunch of generic definitions associated with it.
 * Namely, it's a FunctionDefinition, InterfaceDefinition or a ClassDefinition.
 */
public interface GenericsDefinitionsSymbol extends GenericsSymbol, Symbol {
    /**
     * Return the generics definition with the given index. If there's no
     * such definition, a compilation error is raised.
     */
    GenericDefinition getGenericDefinition(int index);

    /**
     * Returns how many generic types are defined on this symbol.
     * Even if it's possible to have generics, doesn't mean a class or function actually
     * has generics defined.
     */
    int getGenericsDefinitionCount();

    /**
     * Instantiate a copy of this symbol, using the given resolved symbols
     * for the generic definitions of the symbol. This function will check
     * if the resolved symbols are assignable to the definition, create a
     * map with the given generic names, then call the newGenericsCopy()
     * recursively as needed over methods, fields, parameters, etc.
     *
     * @param resolvedGenerics
     * @return
     * @param <T>
     */
    <T extends GenericsDefinitionsSymbol> T instantiateGenerics(List<Symbol> resolvedGenerics);

    /**
     * Instantiates a copy of the given symbol, using resolved generics information. It
     * expects a generics definitions symbol to be able to replace the generics information.
     * @param genericsDefinitionSymbol
     * @param resolvedGenerics
     * @return
     */
    static Symbol instantiateCopy(Symbol genericsDefinitionSymbol, List<Symbol> resolvedGenerics) {
        if (!(genericsDefinitionSymbol instanceof GenericsDefinitionsSymbol)) {
            CaffcCompiler.get().error((AstItem) genericsDefinitionSymbol, String.format(
                    "%s %s does not have any generics definitions",
                    Symbol.typeOfSymbol(genericsDefinitionSymbol),
                    genericsDefinitionSymbol.name()
            ));
            return null; // not reached
        }

        GenericsDefinitionsSymbol instance = (GenericsDefinitionsSymbol) genericsDefinitionSymbol;
        return instance.instantiateGenerics(resolvedGenerics);
    }

    /**
     * Creates the map from the generics name, to the symbol that's associated with.
     * Validates if the symbols are assignable to the generics.
     */
    static Map<String, Symbol> createGenericsSymbolMap(
            GenericsDefinitionsSymbol genericsDefinitionsSymbol,
            List<Symbol> resolvedGenerics) {
        int genericsDefinitionCount = genericsDefinitionsSymbol.getGenericsDefinitionCount();

        if (genericsDefinitionCount == 0 && (resolvedGenerics == null || resolvedGenerics.isEmpty())) {
            return Collections.emptyMap();
        }

        if (resolvedGenerics == null) {
            CaffcCompiler.get().fatal(genericsDefinitionsSymbol, "caffc bug, null resolvedGeneric list");
            return Collections.emptyMap(); // not reached
        }

        if (resolvedGenerics.size() != genericsDefinitionCount) {
            CaffcCompiler.get().error(genericsDefinitionsSymbol, String.format(
                    "%s %s has %d generic arguments. its instantiation had only %d: %s",
                    Symbol.typeOfSymbol(genericsDefinitionsSymbol),
                    genericsDefinitionsSymbol.name(),
                    genericsDefinitionCount,
                    resolvedGenerics.size(),
                    resolvedGenerics
            ));
        }

        Map<String, Symbol> symbolMap = new HashMap<>();
        for (int i = 0; i < genericsDefinitionCount; i++) {
            GenericDefinition genericDefinition = genericsDefinitionsSymbol.getGenericDefinition(i);
            // FIXME: check if the symbols are assignable from one to the other
            symbolMap.put(genericDefinition.name(), resolvedGenerics.get(i));
        }

        return symbolMap;
    }
}
