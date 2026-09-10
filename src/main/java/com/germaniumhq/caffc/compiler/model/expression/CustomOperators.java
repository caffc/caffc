package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.HasMethods;
import com.germaniumhq.caffc.compiler.model.type.Symbol;

/**
 * Maps operator syntax to optional method overloads on the left operand.
 * Binary: {@code + - | / *} → {@code add}/{@code substract}/{@code pipe}/{@code divide}/{@code multiply}.
 * Compound: {@code += -= |= /= *=} → {@code *All} variants (expected to return {@code _this}).
 */
public final class CustomOperators {
    private CustomOperators() {
    }

    public static String binaryMethodName(String operator) {
        return switch (operator) {
            case "+" -> "add";
            case "-" -> "substract";
            case "|" -> "pipe";
            case "/" -> "divide";
            case "*" -> "multiply";
            default -> null;
        };
    }

    public static String assignMethodName(String operator) {
        return switch (operator) {
            case "+=" -> "addAll";
            case "-=" -> "substractAll";
            case "|=" -> "pipeAll";
            case "/=" -> "divideAll";
            case "*=" -> "multiplyAll";
            default -> null;
        };
    }

    public static HasMethods resolveHasMethods(Symbol expressionSymbol) {
        Symbol type = expressionSymbol;

        while (!(type instanceof HasMethods) && type != null && type.typeSymbol() != type) {
            type = type.typeSymbol();
        }

        if (type instanceof HasMethods hasMethods) {
            return hasMethods;
        }

        return null;
    }

    /**
     * Returns the overload method if the left type exposes it; otherwise {@code null}
     * (caller keeps primitive/C operator codegen).
     */
    public static FunctionDefinition findMethod(Symbol leftType, String methodName) {
        if (methodName == null || leftType == null) {
            return null;
        }

        HasMethods hasMethods = resolveHasMethods(leftType);
        if (hasMethods == null) {
            return null;
        }

        return hasMethods.getFunction(methodName);
    }
}
