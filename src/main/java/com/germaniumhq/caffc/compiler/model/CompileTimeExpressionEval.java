package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionBoolCompare;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionBoolNot;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionBoolOperation;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionDotAccess;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionFalse;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionFnCall;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionId;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionMath;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionNumber;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionParens;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionString;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionTrue;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionUnaryMinus;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.settings.BuildSettings;
import com.germaniumhq.caffc.compiler.settings.FilesSetting;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Evaluates {@code #case} expressions against {@link BuildSettings} at compile time.
 */
public final class CompileTimeExpressionEval {
    private CompileTimeExpressionEval() {
    }

    public static boolean isTrue(Expression expression, BuildSettings settings) {
        Object value = eval(expression, settings);
        if (!(value instanceof Boolean bool)) {
            CaffcCompiler.get().fatal(expression,
                    "compile-time condition must evaluate to bool, got: " + describe(value));
            return false;
        }
        return bool;
    }

    public static Object eval(Expression expression, BuildSettings settings) {
        if (expression instanceof ExpressionParens parens) {
            return eval(parens.expression, settings);
        }
        if (expression instanceof ExpressionTrue) {
            return Boolean.TRUE;
        }
        if (expression instanceof ExpressionFalse) {
            return Boolean.FALSE;
        }
        if (expression instanceof ExpressionNumber number) {
            return numberValue(number);
        }
        if (expression instanceof ExpressionString string) {
            return string.constant.value;
        }
        if (expression instanceof ExpressionId || expression instanceof ExpressionDotAccess) {
            String path = settingsPath(expression);
            return settings.getCompileTimeValue(path, expression.getSourceLocation());
        }
        if (expression instanceof ExpressionFnCall fnCall) {
            return evalFnCall(fnCall, settings);
        }
        if (expression instanceof ExpressionUnaryMinus unaryMinus) {
            Object inner = eval(unaryMinus.expression, settings);
            if (!(inner instanceof Number n)) {
                CaffcCompiler.get().fatal(expression,
                        "unary `-` requires a number in compile-time expression, got: " + describe(inner));
                return null;
            }
            return negate(n);
        }
        if (expression instanceof ExpressionBoolNot boolNot) {
            Object inner = eval(boolNot.expression, settings);
            if (!(inner instanceof Boolean b)) {
                CaffcCompiler.get().fatal(expression,
                        "`not` / `!` requires a bool in compile-time expression, got: " + describe(inner));
                return null;
            }
            return !b;
        }
        if (expression instanceof ExpressionBoolOperation boolOp) {
            Object left = eval(boolOp.left, settings);
            if (!(left instanceof Boolean leftBool)) {
                CaffcCompiler.get().fatal(expression,
                        "boolean `" + boolOp.operator + "` requires bool operands in compile-time expression");
                return null;
            }
            if ("&&".equals(boolOp.operator) && !leftBool) {
                return Boolean.FALSE;
            }
            if ("||".equals(boolOp.operator) && leftBool) {
                return Boolean.TRUE;
            }
            Object right = eval(boolOp.right, settings);
            if (!(right instanceof Boolean rightBool)) {
                CaffcCompiler.get().fatal(expression,
                        "boolean `" + boolOp.operator + "` requires bool operands in compile-time expression");
                return null;
            }
            return "&&".equals(boolOp.operator) ? (leftBool && rightBool) : (leftBool || rightBool);
        }
        if (expression instanceof ExpressionMath math) {
            return evalMath(math, settings);
        }
        if (expression instanceof ExpressionBoolCompare compare) {
            return evalCompare(compare, settings);
        }

        CaffcCompiler.get().fatal(expression,
                "unsupported expression in compile-time condition: " + expression.getClass().getSimpleName());
        return null;
    }

    private static Object evalFnCall(ExpressionFnCall fnCall, BuildSettings settings) {
        if (!(fnCall.functionExpression instanceof ExpressionDotAccess dot)) {
            CaffcCompiler.get().fatal(fnCall,
                    "unsupported function call in compile-time condition");
            return null;
        }

        Object receiver = eval(dot.leftOfDot, settings);
        String method = dot.rightOfDot;

        if (receiver instanceof FilesSetting files) {
            if (!"contains".equals(method)) {
                CaffcCompiler.get().fatal(fnCall,
                        "unknown method `" + method + "` on files setting (expected contains)");
                return null;
            }
            if (fnCall.parameters.size() != 1) {
                CaffcCompiler.get().fatal(fnCall,
                        "`files.contains` expects exactly one string argument");
                return null;
            }
            Object arg = eval(fnCall.parameters.get(0), settings);
            if (!(arg instanceof String path)) {
                CaffcCompiler.get().fatal(fnCall,
                        "`files.contains` argument must be a string, got: " + describe(arg));
                return null;
            }
            return files.contains(path);
        }

        CaffcCompiler.get().fatal(fnCall,
                "unsupported receiver for compile-time method call: " + describe(receiver));
        return null;
    }

    private static Object evalMath(ExpressionMath math, BuildSettings settings) {
        Object left = eval(math.left, settings);
        Object right = eval(math.right, settings);
        if (!(left instanceof Number leftNum) || !(right instanceof Number rightNum)) {
            CaffcCompiler.get().fatal(math,
                    "math `" + math.operator + "` requires number operands in #case, got: "
                            + describe(left) + " and " + describe(right));
            return null;
        }
        BigDecimal a = toBigDecimal(leftNum);
        BigDecimal b = toBigDecimal(rightNum);
        return switch (math.operator) {
            case "+" -> a.add(b);
            case "-" -> a.subtract(b);
            case "*" -> a.multiply(b);
            case "/" -> {
                if (b.compareTo(BigDecimal.ZERO) == 0) {
                    CaffcCompiler.get().fatal(math, "division by zero in #case");
                }
                yield a.divide(b, MathContext.DECIMAL64);
            }
            case "%" -> {
                CaffcCompiler.get().fatal(math, "`%` is not supported in #case expressions");
                yield null;
            }
            default -> {
                CaffcCompiler.get().fatal(math, "unsupported math operator in #case: " + math.operator);
                yield null;
            }
        };
    }

    private static Object evalCompare(ExpressionBoolCompare compare, BuildSettings settings) {
        Object left = eval(compare.left, settings);
        Object right = eval(compare.right, settings);
        String op = compare.operator;

        boolean leftIsNumber = left instanceof Number;
        boolean rightIsNumber = right instanceof Number;
        boolean leftIsBool = left instanceof Boolean;
        boolean rightIsBool = right instanceof Boolean;

        if (leftIsNumber && rightIsNumber) {
            int cmp = toBigDecimal((Number) left).compareTo(toBigDecimal((Number) right));
            return switch (op) {
                case "==" -> cmp == 0;
                case "!=" -> cmp != 0;
                case "<" -> cmp < 0;
                case "<=" -> cmp <= 0;
                case ">" -> cmp > 0;
                case ">=" -> cmp >= 0;
                default -> {
                    CaffcCompiler.get().fatal(compare, "unsupported comparison in #case: " + op);
                    yield null;
                }
            };
        }

        if (leftIsBool || rightIsBool) {
            if (!leftIsBool || !rightIsBool) {
                CaffcCompiler.get().fatal(compare,
                        "cannot compare bool with non-bool in #case");
                return null;
            }
            if (!"==".equals(op) && !"!=".equals(op)) {
                CaffcCompiler.get().fatal(compare,
                        "bools in #case only support `==` and `!=`, got: " + op);
                return null;
            }
            boolean eq = left.equals(right);
            return "==".equals(op) == eq;
        }

        // Strings and unset settings (null): only == / !=
        if (!"==".equals(op) && !"!=".equals(op)) {
            CaffcCompiler.get().fatal(compare,
                    "strings in #case only support `==` and `!=`, got: " + op);
            return null;
        }
        boolean eq = java.util.Objects.equals(
                left == null ? null : String.valueOf(left),
                right == null ? null : String.valueOf(right));
        return "==".equals(op) == eq;
    }

    /**
     * Builds a dotted settings path from {@code gc} / {@code gc.impl} style expressions.
     */
    static String settingsPath(Expression expression) {
        List<String> parts = new ArrayList<>();
        Expression current = expression;
        while (true) {
            if (current instanceof ExpressionId id) {
                parts.add(0, id.name);
                break;
            }
            if (current instanceof ExpressionDotAccess dot) {
                parts.add(0, dot.rightOfDot);
                current = dot.leftOfDot;
                continue;
            }
            if (current instanceof ExpressionParens parens) {
                current = parens.expression;
                continue;
            }
            CaffcCompiler.get().fatal(expression,
                    "settings path in #case must be a dotted identifier (e.g. gc.impl)");
            return "";
        }
        return String.join(".", parts);
    }

    private static Number numberValue(ExpressionNumber number) {
        if (number.bigIntValue != null) {
            return number.bigIntValue;
        }
        try {
            return new BigDecimal(number.value);
        } catch (NumberFormatException e) {
            CaffcCompiler.get().fatal(number, "invalid number in #case: " + number.value);
            return null;
        }
    }

    private static Number negate(Number n) {
        return toBigDecimal(n).negate();
    }

    private static BigDecimal toBigDecimal(Number n) {
        if (n instanceof BigDecimal bd) {
            return bd;
        }
        if (n instanceof java.math.BigInteger bi) {
            return new BigDecimal(bi);
        }
        if (n instanceof Double || n instanceof Float) {
            return BigDecimal.valueOf(n.doubleValue());
        }
        return BigDecimal.valueOf(n.longValue());
    }

    private static String describe(Object value) {
        if (value == null) {
            return "null";
        }
        return value.getClass().getSimpleName() + "(" + value + ")";
    }
}
