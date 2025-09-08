package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.ClassDefinition;
import com.germaniumhq.caffc.compiler.model.Clazz;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.CompileBlock;
import com.germaniumhq.caffc.compiler.model.Field;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.Interface;
import com.germaniumhq.caffc.compiler.model.Parameter;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclaration;
import com.germaniumhq.caffc.compiler.model.type.Symbol;

/**
 * Traverses the AST trying to find an item by its nested name. The
 * caffc model AST isn't storing children mapped from the Antlr 1-to-1,
 * however its items can contain children. This class is aware on what
 * children belong to what AST item and is able to descend and find
 * them recursively. For example in the following code:
 *
 * class A {
 *    b(i32 x) {
 *      i32 y = x
 *    }
 * }
 *
 * the expression `A.b.x` will return the parameter definition by going
 * inside the CompilationUnit and there finding first the `A` that
 * happens to be a Clazz, then finding `b` _inside_ that class that is a
 * Function, after it finds `x` that's a parameter of that function.
 */
public class AstUtil {
    public static <T extends AstItem> T findAstItem(CompilationUnit root, String name) {
        String[] tokens = name.split("\\.");
        AstItem result = root;
        AstItem previousItem = root;

        for (String token: tokens) {
            previousItem = result;

            if (result instanceof CompilationUnit cu) {
                result = findCompilationUnitField(cu, token);
            } else if (result instanceof Clazz c) {
                result = findClazzField(c, token);
            } else if (result instanceof Function f) {
                result = findFunctionField(f, token);
            } else if (result instanceof VariableDeclaration vd) {
                result = findVariableDeclarationField(vd, token);
            } else {
                throw new IllegalArgumentException("unsupported AstItem in chain for " + token + ": " + result);
            }

            if (result == null) {
                throw new IllegalArgumentException("AstItem not found in parent: " + previousItem + ".`" + token + "` ?");
            }
        }

        return (T) result;
    }

    private static AstItem findCompilationUnitField(CompilationUnit result, String token) {
        for (CompileBlock compileBlock: result.compileBlocks) {
            if (compileBlock instanceof Function f) {
                if (f.definition.name.equals(token)) {
                    return f;
                }
            }

            if (compileBlock instanceof Clazz c) {
                if (c.definition.name.equals(token)) {
                    return c;
                }
            }

            if (compileBlock instanceof Interface i) {
                if (i.definition.name.equals(token)) {
                    return i;
                }
            }
        }

        return null;
    }

    private static AstItem findClazzField(Clazz c, String token) {
        for (Field field: c.definition.fields) {
            if (field.name.equals(token)) {
                return field;
            }
        }

        for (Function function: c.functions) {
            if (function.definition.name.equals(token)) {
                return function;
            }
        }

        return null;
    }

    private static AstItem findFunctionField(Function f, String token) {
        if (token.startsWith("[") && token.endsWith("]")) {
            int statementIndex = Integer.parseInt(token.substring(1, token.length() - 1));

            if (statementIndex < 0 || statementIndex >= f.statements.size()) {
                throw new IllegalArgumentException("No such statement in function `" + f.definition.name +  "()`: " + token);
            }

            return f.statements.get(statementIndex);
        }

        for (Parameter parameter: f.definition.parameters) {
            if (parameter.name.equals(token)) {
                return parameter;
            }
        }

        VariableDeclaration variableDeclaration = f._variables.get(token);
        if (variableDeclaration != null) {
            return variableDeclaration;
        }

        if (f.definition.generics != null) {
            return f.definition.generics.getByName(token);
        }

        return null;
    }

    private static AstItem findVariableDeclarationField(VariableDeclaration c, String token) {
        Symbol typeSymbol = c.typeSymbol();

        if (typeSymbol instanceof ClassDefinition classDefinition) {
            return (AstItem) classDefinition.resolve(token);
        }

        return (AstItem) typeSymbol;
    }


}
