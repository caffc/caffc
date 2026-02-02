package com.germaniumhq.caffc.compiler.model.type;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.ClassDefinition;
import com.germaniumhq.caffc.compiler.model.Field;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.Parameter;

/**
 * A symbol has a name and a type. It's what we get when we resolve something in a scope.
 * In some cases (function, class, module), the name of the symbol is the same as the
 * type, with the type one specifying also the module, its data type.
 *
 * For local variables, parameters, or class fields, the name is different since it
 * represents the local name the field has, while the type represents the type of the
 * parameter itself. i.e. type could be anything from `i32` to
 * `module=main, name=x, clazz=MainApp, data_type=FUNCTION` for the same name `x`.
 *
 * ```
 * i32 x
 * a = x + b // resolving x here
 * ```
 *
 * vs
 *
 * ```
 * app = new MainApp()
 * app.x() // resolving x here
 * ```
 */
public interface Symbol extends AstItem {
    /**
     * The name of the symbol.
     *
     * 1. In case this is a Parameter, Field, or Variable, the name is
     * literally the name of the thing.
     * 2. If the symbol itself is a ClassDefinition, or FunctionDefinition, etc,
     * the name is the fqdn name of the thing. If it's a generics
     * declaration, it's the name of the generic. If it's a generics
     * instantiation, then the name is "generic:type_name"
     */
    String name();

    /**
     * The type name of the type symbol where this symbol points to.
     */
    TypeName typeName();

    /**
     * The actual type of this symbol. This is in turn a symbol due to
     * the way they're being resolved in the AST.
     */
    Symbol typeSymbol();

    static String typeOfSymbol(Symbol item) {
        if (item instanceof Function fd) {
            item = fd.definition;
        }

        if (item instanceof ClassDefinition) {
            return "class";
        } else if (item instanceof FunctionDefinition fd && fd.clazz != null) {
            return "method";
        } else if (item instanceof FunctionDefinition) {
            return "function";
        } else if (item instanceof Field) {
            return "field";
        } else if (item instanceof Parameter) {
            return "parameter";
        }

        return "symbol";
    }

    static String debugInfo(Symbol symbol) {
        return String.format("%s %s defined at %s:%d:%d",
            typeOfSymbol(symbol),
            symbol.name(),
            symbol.getFilePath(),
            symbol.getLineNumber(),
            symbol.getColumnNumber()
        );
    }
}
