package com.germaniumhq.caffc.compiler.model.type;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.ClassDefinition;
import com.germaniumhq.caffc.compiler.model.Clazz;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Program;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.generated.caffcParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Arrays;

/**
 * A symbol resolve request is the information needed to fetch a
 * symbol from one of its parent scopes.
 *
 * It also includes the eventual Type instantiation needed for
 * generics.
 *
 * It will be resolved by recursively asking scopes about it,
 * from the bottom up.
 */
public class SymbolSearch {
    /**
     * The name of the symbol to be resolved. Can be either a variable or
     * a class name (excluding its type information)
     */
    public String name;

    /**
     * Instantiated generics. These are also at this stage just symbols
     * to be resolved.
     */
    public SymbolSearch[] generics;

    public static SymbolSearch ofName(String name) {
        SymbolSearch result = new SymbolSearch();
        result.name = name;

        if (name.contains("<")) {
            throw new IllegalArgumentException("Name `" + name + "`contains '<', generics args must be filled in generics.");
        }

        return result;
    }

    /**
     * Constructs a SymbolSearch from an Antlr ParserRuleContext.
     * @param unit
     * @param antlrParserRuleContext
     * @return
     */
    public static SymbolSearch fromAntlr(CompilationUnit unit, ParserRuleContext antlrParserRuleContext) {
        if (antlrParserRuleContext == null) {
            SymbolSearch symbolSearch = new SymbolSearch();
            symbolSearch.name = "void";

            return symbolSearch;
        }

        ParserRuleContext context = antlrParserRuleContext;

        if (context instanceof caffcParser.ExNewObjectContext exNewObjectContext) {
            return SymbolSearch.ofName(exNewObjectContext.newType().getText());
        }

        if (context instanceof caffcParser.NewTypeClassContext newTypeClassContext) {
            SymbolSearch symbolSearch = SymbolSearch.ofName(newTypeClassContext.classType().fqdn().getText());
            symbolSearch.generics = fromGenericsInstantiation(unit, newTypeClassContext.classType().genericsInstantiations());

            return symbolSearch;
        }

        if (context instanceof caffcParser.InterfaceImplementationContext interfaceImplementationContext) {
            SymbolSearch symbolSearch = SymbolSearch.ofName(interfaceImplementationContext.fqdn().getText());
            symbolSearch.generics = fromGenericsInstantiation(unit, interfaceImplementationContext.genericsInstantiations());

            return symbolSearch;
        }

        if (context instanceof caffcParser.TypeClassContext classContext) {
            SymbolSearch symbolSearch = SymbolSearch.ofName(classContext.classType().fqdn().getText());
            symbolSearch.generics = fromGenericsInstantiation(unit, classContext.classType().genericsInstantiations());

            return symbolSearch;
        }

        if (context instanceof caffcParser.VarTypeContext varTypeContext) {
            return fromTypeContext(unit, varTypeContext.typeName());
        }

        if (context instanceof caffcParser.TypeNameContext typeContext) {
            return fromTypeContext(unit, typeContext);
        }

        CaffcCompiler.get().fatal(
                SourceLocation.fromAntlr(unit.sourceLocation.filePath, antlrParserRuleContext),
                "unsupported expression: " + antlrParserRuleContext.getText() +
                        " of type " + antlrParserRuleContext.getClass().getSimpleName());

        return null; // not reached
    }

    public static SymbolSearch fromTypeContext(CompilationUnit unit, caffcParser.TypeNameContext typeContext) {
        if (typeContext instanceof caffcParser.TypeClassContext classContext) {
            SymbolSearch symbolSearch = SymbolSearch.ofName(classContext.classType().fqdn().getText());
            symbolSearch.generics = fromGenericsInstantiation(unit, classContext.classType().genericsInstantiations());

            return symbolSearch;
        } else if (typeContext instanceof caffcParser.TypeArrayContext classContext) {
            return SymbolSearch.ofName(classContext.getText());
        } else if (typeContext instanceof caffcParser.TypePrimitiveContext primitiveContext) {
            return SymbolSearch.ofName(primitiveContext.getText());
        }

        CaffcCompiler.get().fatal(
                SourceLocation.fromAntlr(unit.sourceLocation.filePath, typeContext),
                "unsupported vartype expression: " + typeContext.getText() +
                        " of type " + typeContext.getClass().getSimpleName());
        return null; // not reached
    }

    private static SymbolSearch[] fromGenericsInstantiation(CompilationUnit unit, caffcParser.GenericsInstantiationsContext genericsInstantiationContext) {
        if (genericsInstantiationContext == null) {
            return null;
        }

        if (genericsInstantiationContext instanceof caffcParser.GenericsInstantiationEmptyContext) {
            return null;
        }

        caffcParser.GenericsInstantiationTypeContext genericsInstantiationTypeContext =
                (caffcParser.GenericsInstantiationTypeContext) genericsInstantiationContext;

        SymbolSearch[] result = new SymbolSearch[genericsInstantiationTypeContext.typeName().size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = SymbolSearch.fromAntlr(unit, genericsInstantiationTypeContext.typeName(i));
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(name);

        if (this.generics != null) {
            result.append("<");
            result.append(String.join(",", Arrays.stream(this.generics).map(it -> it.toString()).toList()));
            result.append(">");
        }

        return result.toString();
    }

    /**
     * Checks if the given symbol name is not already defined in one of the parent scopes.
     * If it is, we add an error to the compilation.
     *
     * @param scope
     * @param name
     * @param astItem
     */
    public static void uniqueSymbolNameCheck(Scope scope, String name, AstItem astItem) {
        if (scope == null || name == null) {
            return;
        }

        while (scope != null) {
            Symbol existing = scope.resolve(name);

            if (existing != null) {
                String message = String.format("redeclared %s `%s`. it shadows previously declared %s at %s",
                        Symbol.typeOfSymbol((Symbol) astItem),
                        name,
                        Symbol.typeOfSymbol(existing),
                        CaffcCompiler.fileLocation((AstItem) existing));

                CaffcCompiler.get().error(astItem, message);
                break;
            }

            if (scope instanceof ClassDefinition || scope instanceof Clazz) {
                // everything in an object is accessed via `_this`, so there
                // aren't any collisions possible anymore.
                return;
            }

            if (scope instanceof Program) {
                // top scope, here's where we break the loop
                return;
            }

            scope = scope.findAstParent(Scope.class);
        }
    }
}
