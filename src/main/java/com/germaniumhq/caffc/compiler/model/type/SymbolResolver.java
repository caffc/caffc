package com.germaniumhq.caffc.compiler.model.type;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.*;
import com.germaniumhq.caffc.compiler.model.Module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SymbolResolver {
    /**
     * Resolves a symbol used in an instantiation context. The idea is to give it an owner:
     * that is, where in our AST tree is the search being performed from, and a name. When
     * the symbol is found, it is returned to the caller.
     *
     * Initially the idea was to have the resolving of the generics + instantiation of the
     * concrete type from it (i.e. make a copy of the Function/ClassDefinition, and replace
     * the GenericDefinition reference to the actual type symbol that's being used in the
     * current instantiation). This worked well for `new X<..>` where the generics are tied
     * to the new instantiation, but didn't work well for function calls, since they can
     * be expressions. This is why the instantiation has to be moved in the actual expression
     * that does the operation, namely the ExpressionNewObject and ExpressionFnCall.
     *
     */
    public static <T extends Symbol> T resolveInstantiatedSymbol(
            AstItem owner, SymbolSearch symbolSearch) {

        if (symbolSearch == null) {
            throw new IllegalArgumentException("symbol searched cannot be null");
        }

        // We search for the exact item of the object. If the item is an array,
        // we'll create its type later, right now we only want to find the item,
        // to validate if it exists. so if the symbol search is str[][], we want
        // to know first if the type itself, in this case `str`, exists. This
        // also holds true for generics, if we need to search for `T[][]`, we start
        // with only `T`.
        // FIXME: implement better, not just blind replace
        String searchedName = symbolSearch.name.replace("[]", "");
        int arrayDimensions = (symbolSearch.name.length() - searchedName.length()) / 2;

        Symbol result = caffcResolveFqdnName(owner, searchedName);

        if (result == null) {
            return null;
        }

        // If the item is an array, we ensure the array exists. The array we
        // retrieve here is still _without_ its generics applied. We'll apply
        // the generics in the next step. However, if it is an array, we'll get
        // back the implementation for the array: obj_arr, or u8_arr, etc.
        if (arrayDimensions > 0) {
            result = ensureArrayInModule(result, arrayDimensions);
        }

        // for any symbol that we found, we need to go and resolve it as well, since it
        // can happen that the compilation unit of that symbol wasn't resolved yet.
        // this would cause issues, since the function definitions for example would
        // be with unresolved types.
        if (! (result instanceof Module)) {
            result.recurseResolveTypes();
        }

        // Now we just need to resolve the generics if that's the case. We'll
        // replace the generics with the actual types we resolved here.
        if (symbolSearch.generics != null) {
            List<Symbol> resolvedGenerics = SymbolResolver.resolveAllSymbols(owner, symbolSearch.generics);
            result = GenericsDefinitionsSymbol.instantiateCopy(result, resolvedGenerics);
        }

        return (T) result;
    }

    private static List<Symbol> resolveAllSymbols(AstItem owner, SymbolSearch[] generics) {
        List<Symbol> result = new ArrayList<>();

        for (SymbolSearch symbolSearch: generics) {
            result.add(SymbolResolver.mustResolveSymbol(owner, symbolSearch));
        }

        return result;
    }

    /**
     * Ensures the array definition is registered in the module that owns the class.
     * It returns the array symbol.
     *
     * @param result
     * @param arrayDimensions
     * @return
     */
    private static Symbol ensureArrayInModule(Symbol result, int arrayDimensions) {
        Module module = Program.get().getModule(result.typeName().module);
        return module.ensureArray(result, arrayDimensions);
    }

    /**
     * Resolves a single str name (no generics), searching all the nested scopes. A FQDN search is performed.
     * In case the name is not available in the current module, it will search also the default modules.
     *
     * @param owner
     * @param name
     * @return
     * @param <T>
     */
    private static <T extends Symbol> T caffcResolveFqdnName(AstItem owner, String name) {
        TypeName primitiveTypeName = TypeName.primitiveType(name);
        if (primitiveTypeName != null) {
            return (T) new TypeSymbol(primitiveTypeName);
        }

        Scope scope = owner instanceof Scope ? (Scope) owner : owner.findAstParent(Scope.class);

        T result = fqdnResolve(name, scope);

        if (result != null) {
            return result;
        }

        // if we cannot resolve the symbol in the current scope, we search it in the parent caffc scope
        Program program = Program.get();
        Module caffcModule = program.getModule("caffc");

        result = fqdnResolve(name, caffcModule);

        if (result == null) {
            // not fatal
            CaffcCompiler.get().error(owner, "cannot resolve symbol " + name);
        }

        return result;
    }

    /**
     * Does a fqdn search for the given name across scopes. It iterates over all the parts of the
     * name, trying to get the scopes above if the current scope doesn't know about the next name.
     * @param symbolName
     * @param scope
     * @return
     * @param <T>
     */
    private static <T extends Symbol> T fqdnResolve(String symbolName, Scope scope) {
        String[] nameParts = symbolName.split("\\.");

        Symbol result = null;

        for (int i = 0; i < nameParts.length; i++) {
            String name = nameParts[i];

            while (scope != null) {
                Symbol symbol = scope.resolve(name);

                if (symbol != null) {
                    result = symbol;

                    if (symbol instanceof Scope scope1) {
                        scope = scope1;
                    }

                    break; // => we advance to the next name part
                }

                scope = scope.findAstParent(Scope.class);
            }

            if (scope == null) {
                return null;
            }
        }

        if (result != null) {
            return (T) result;
        }

        return null;
    }

    /**
     * Resolve the symbol or die with error.
     * @param owner
     * @param symbolSearch
     * @return
     * @param <T>
     */
    public static <T extends Symbol> T mustResolveSymbol(AstItem owner, SymbolSearch symbolSearch) {
        T result = resolveInstantiatedSymbol(owner, symbolSearch);

        if (result == null) {
            CaffcCompiler.get().fatal(owner, "cannot resolve " + symbolSearch);
            throw new IllegalStateException("not reached");
        }

        return result;
    }
}
