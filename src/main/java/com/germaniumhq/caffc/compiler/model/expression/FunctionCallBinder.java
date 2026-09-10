package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.ClassDefinition;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.Parameter;
import com.germaniumhq.caffc.compiler.model.source.HasSourceLocation;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeAssignability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Binds call-site arguments (positional + named) onto a {@link FunctionDefinition},
 * filling defaults, packing varargs into a trailing array, and packing unknown
 * named args into a trailing kwargs dict — Python-style.
 *
 * <p>Trailing-array auto-varargs (no explicit {@code ...}): exact arity passes the
 * last positional through; extra positionals are packed. Explicit {@code ...} always
 * packs leftover positionals into a new array (pass an existing array by name).
 */
public final class FunctionCallBinder {
    private FunctionCallBinder() {
    }

    /**
     * Bind arguments for a normal function/method call. Instance methods must pass
     * the receiver; it is prepended as {@code _this}.
     */
    public static List<Expression> bind(
            AstItem owner,
            HasSourceLocation location,
            FunctionDefinition functionDefinition,
            Expression thisReceiver,
            List<CallArgument> callArguments) {
        return bind(owner, location, functionDefinition, thisReceiver, callArguments, true);
    }

    /**
     * Bind arguments for {@code new T(...)} against the constructor signature.
     * The synthetic {@code _this} parameter is not included in the result (the
     * generated {@code _new} supplies it).
     */
    public static List<Expression> bindConstructor(
            AstItem owner,
            HasSourceLocation location,
            FunctionDefinition constructor,
            List<CallArgument> callArguments) {
        return bind(owner, location, constructor, null, callArguments, false);
    }

    private static List<Expression> bind(
            AstItem owner,
            HasSourceLocation location,
            FunctionDefinition functionDefinition,
            Expression thisReceiver,
            List<CallArgument> callArguments,
            boolean emitThis) {

        List<Parameter> params = functionDefinition.parameters;
        int startIndex = 0;
        if (!params.isEmpty() && "_this".equals(params.get(0).name)) {
            startIndex = 1;
        }

        int varargsIndex = findVarargsIndex(params, startIndex);
        int kwargsIndex = findKwargsIndex(params, startIndex, varargsIndex);
        boolean explicitVarargs = varargsIndex >= 0 && params.get(varargsIndex).isExplicitVarargs();

        List<Expression> positionals = new ArrayList<>();
        Map<String, CallArgument> named = new HashMap<>();
        boolean seenNamed = false;

        for (CallArgument arg : callArguments) {
            if (arg.isNamed()) {
                seenNamed = true;
                if (named.containsKey(arg.name)) {
                    CaffcCompiler.get().fatal(arg.sourceLocation,
                            "duplicate named argument: " + arg.name);
                }
                named.put(arg.name, arg);
            } else {
                if (seenNamed) {
                    CaffcCompiler.get().fatal(arg.sourceLocation,
                            "positional argument after named argument");
                }
                positionals.add(arg.value);
            }
        }

        int regularEnd = varargsIndex >= 0 ? varargsIndex
                : (kwargsIndex >= 0 ? kwargsIndex : params.size());

        List<Expression> bound = new ArrayList<>();
        if (startIndex == 1 && emitThis) {
            if (thisReceiver == null) {
                CaffcCompiler.get().fatal(location,
                        "missing receiver for instance method " + functionDefinition.name);
            }
            bound.add(thisReceiver);
        }

        int positionalIndex = 0;
        Set<String> usedNames = new HashSet<>();

        for (int i = startIndex; i < regularEnd; i++) {
            Parameter param = params.get(i);
            Expression value;

            if (named.containsKey(param.name)) {
                value = named.get(param.name).value;
                usedNames.add(param.name);
            } else if (positionalIndex < positionals.size()) {
                value = positionals.get(positionalIndex++);
            } else if (param.defaultExpression != null) {
                value = param.defaultExpression;
            } else {
                CaffcCompiler.get().fatal(location,
                        "missing argument for parameter '" + param.name + "' in call to " +
                                functionDefinition.name);
                return bound;
            }

            bound.add(value);
        }

        List<Expression> leftoverPositionals = new ArrayList<>();
        while (positionalIndex < positionals.size()) {
            leftoverPositionals.add(positionals.get(positionalIndex++));
        }

        if (varargsIndex >= 0) {
            Parameter varargsParam = params.get(varargsIndex);
            Expression varargsValue = bindVarargs(
                    owner,
                    location,
                    varargsParam,
                    leftoverPositionals,
                    named,
                    usedNames,
                    explicitVarargs);
            leftoverPositionals = List.of();
            bound.add(varargsValue);
        } else if (!leftoverPositionals.isEmpty()) {
            CaffcCompiler.get().fatal(location,
                    "too many positional arguments for " + functionDefinition.name);
        }

        if (kwargsIndex >= 0) {
            Parameter kwargsParam = params.get(kwargsIndex);
            bound.add(bindKwargs(owner, location, kwargsParam, named, usedNames));
        } else {
            for (Map.Entry<String, CallArgument> entry : named.entrySet()) {
                if (!usedNames.contains(entry.getKey())) {
                    CaffcCompiler.get().fatal(entry.getValue().sourceLocation,
                            "unexpected keyword argument '" + entry.getKey() + "'");
                }
            }
        }

        int expected = emitThis ? params.size() : params.size() - startIndex;
        if (bound.size() != expected) {
            CaffcCompiler.get().fatal(location,
                    "internal error: bound " + bound.size() + " args but expected " +
                            expected + " for " + functionDefinition.name);
        }

        checkArgumentAssignability(location, functionDefinition, bound, emitThis ? 0 : startIndex);

        return bound;
    }

    /**
     * After arity/name binding succeeds, ensure each argument is assignable to its parameter.
     *
     * @param boundOffset index into {@code params} corresponding to {@code bound.get(0)}
     *                    (0 when {@code _this} is included; {@code startIndex} for constructors)
     */
    private static void checkArgumentAssignability(
            HasSourceLocation location,
            FunctionDefinition functionDefinition,
            List<Expression> bound,
            int boundOffset) {
        List<Parameter> params = functionDefinition.parameters;

        for (int i = 0; i < bound.size(); i++) {
            Parameter param = params.get(boundOffset + i);
            Expression arg = bound.get(i);

            if ("_this".equals(param.name)) {
                continue;
            }

            if (!TypeAssignability.isExpressionAssignable(param.typeSymbol, arg)) {
                CaffcCompiler.get().error(arg.getSourceLocation() != null ? arg.getSourceLocation() : location.getSourceLocation(),
                        String.format(
                                "argument for parameter '%s' in call to '%s' has type '%s' which is not assignable to '%s'",
                                param.name,
                                functionDefinition.name,
                                TypeAssignability.describe(arg.typeSymbol()),
                                TypeAssignability.describe(param.typeSymbol)));
            }
        }
    }

    private static Expression bindVarargs(
            AstItem owner,
            HasSourceLocation location,
            Parameter varargsParam,
            List<Expression> leftoverPositionals,
            Map<String, CallArgument> named,
            Set<String> usedNames,
            boolean explicitVarargs) {

        if (named.containsKey(varargsParam.name)) {
            if (!leftoverPositionals.isEmpty()) {
                CaffcCompiler.get().fatal(location,
                        "cannot pass both varargs elements and named argument '" +
                                varargsParam.name + "'");
            }
            usedNames.add(varargsParam.name);
            return named.get(varargsParam.name).value;
        }

        if (explicitVarargs) {
            // Python *args: every leftover positional becomes an element
            if (!leftoverPositionals.isEmpty()) {
                List<Expression> boxed = boxElements(owner, leftoverPositionals, varargsParam.typeSymbol);
                Expression pack = ExpressionArrayPack.of(
                        owner, location.getSourceLocation(), varargsParam.typeSymbol, boxed);
                pack.recurseResolveTypes();
                return pack;
            }
            if (varargsParam.defaultExpression != null) {
                return varargsParam.defaultExpression;
            }
            Expression empty = ExpressionArrayPack.of(
                    owner, location.getSourceLocation(), varargsParam.typeSymbol, List.of());
            empty.recurseResolveTypes();
            return empty;
        }

        // Auto trailing-array: exact one leftover → pass through; several → pack
        if (leftoverPositionals.isEmpty()) {
            if (varargsParam.defaultExpression != null) {
                return varargsParam.defaultExpression;
            }
            Expression empty = ExpressionArrayPack.of(
                    owner, location.getSourceLocation(), varargsParam.typeSymbol, List.of());
            empty.recurseResolveTypes();
            return empty;
        }
        if (leftoverPositionals.size() == 1) {
            Symbol elementType = arrayElementType(varargsParam.typeSymbol);
            return PrimitiveBoxing.boxIfNeeded(owner, leftoverPositionals.get(0), elementType);
        }

        List<Expression> boxed = boxElements(owner, leftoverPositionals, varargsParam.typeSymbol);
        Expression pack = ExpressionArrayPack.of(
                owner, location.getSourceLocation(), varargsParam.typeSymbol, boxed);
        pack.recurseResolveTypes();
        return pack;
    }

    private static List<Expression> boxElements(AstItem owner, List<Expression> elements, Symbol arrayType) {
        Symbol elementType = arrayElementType(arrayType);
        List<Expression> boxed = new ArrayList<>();
        for (Expression element : elements) {
            boxed.add(PrimitiveBoxing.boxIfNeeded(owner, element, elementType));
        }
        return boxed;
    }

    private static Symbol arrayElementType(Symbol arrayType) {
        if (arrayType instanceof com.germaniumhq.caffc.compiler.model.ClassDefinition arrayClass) {
            return arrayClass.childDefinition;
        }
        return null;
    }

    private static Expression bindKwargs(
            AstItem owner,
            HasSourceLocation location,
            Parameter kwargsParam,
            Map<String, CallArgument> named,
            Set<String> usedNames) {

        if (named.containsKey(kwargsParam.name)) {
            for (Map.Entry<String, CallArgument> entry : named.entrySet()) {
                if (!usedNames.contains(entry.getKey()) && !entry.getKey().equals(kwargsParam.name)) {
                    CaffcCompiler.get().fatal(entry.getValue().sourceLocation,
                            "unexpected keyword argument '" + entry.getKey() + "' when kwargs " +
                                    "are passed explicitly as '" + kwargsParam.name + "'");
                }
            }
            usedNames.add(kwargsParam.name);
            return named.get(kwargsParam.name).value;
        }

        List<String> kwKeys = new ArrayList<>();
        List<Expression> kwValues = new ArrayList<>();
        for (Map.Entry<String, CallArgument> entry : named.entrySet()) {
            if (usedNames.contains(entry.getKey())) {
                continue;
            }
            kwKeys.add(entry.getKey());
            kwValues.add(entry.getValue().value);
            usedNames.add(entry.getKey());
        }

        if (kwKeys.isEmpty()) {
            if (kwargsParam.defaultExpression != null) {
                return kwargsParam.defaultExpression;
            }
            // Shared empty readonly dict — avoid allocating a new HashDict per call.
            Expression emptyKwargs = ExpressionId.fromName(null, owner, "EMPTY_KWARGS");
            emptyKwargs.recurseResolveTypes();
            return emptyKwargs;
        }

        Expression kwargsValue = ExpressionKwargsPack.of(
                owner,
                location.getSourceLocation(),
                kwargsParam.typeSymbol,
                kwKeys,
                kwValues);
        kwargsValue.recurseResolveTypes();
        return kwargsValue;
    }

    /**
     * Index of the varargs array parameter, or -1.
     * Prefer an explicit {@code ...} section / {@code name...}; otherwise the trailing
     * array (or second-to-last when followed by a kwargs dict).
     */
    static int findVarargsIndex(List<Parameter> params, int startIndex) {
        int explicit = -1;
        for (int i = startIndex; i < params.size(); i++) {
            Parameter p = params.get(i);
            if (p.isVarargsMarked || p.isVarargsSection) {
                explicit = i;
                break;
            }
        }

        if (explicit >= 0) {
            if (!isArrayType(params.get(explicit).typeSymbol)) {
                CaffcCompiler.get().error(params.get(explicit),
                        "varargs parameter '" + params.get(explicit).name + "' must be an array");
            }
            return explicit;
        }

        if (params.size() - startIndex >= 2) {
            int maybeArr = params.size() - 2;
            int maybeKw = params.size() - 1;
            if (isArrayType(params.get(maybeArr).typeSymbol) && isDictType(params.get(maybeKw).typeSymbol)) {
                return maybeArr;
            }
        }

        if (params.size() > startIndex) {
            int last = params.size() - 1;
            if (isArrayType(params.get(last).typeSymbol)) {
                return last;
            }
        }

        return -1;
    }

    static int findKwargsIndex(List<Parameter> params, int startIndex, int varargsIndex) {
        if (varargsIndex >= 0 && varargsIndex + 1 < params.size()) {
            Parameter maybeKw = params.get(varargsIndex + 1);
            if (isDictType(maybeKw.typeSymbol)) {
                if (varargsIndex + 1 != params.size() - 1) {
                    CaffcCompiler.get().error(maybeKw,
                            "kwargs parameter must be the last parameter");
                }
                return varargsIndex + 1;
            }
        }

        if (params.size() - startIndex >= 2) {
            int maybeArr = params.size() - 2;
            int maybeKw = params.size() - 1;
            if (isArrayType(params.get(maybeArr).typeSymbol) && isDictType(params.get(maybeKw).typeSymbol)) {
                return maybeKw;
            }
        }

        return -1;
    }

    static boolean isArrayType(Symbol symbol) {
        return symbol != null && symbol.typeName().dataType == DataType.ARRAY;
    }

    static boolean isDictType(Symbol symbol) {
        if (symbol == null) {
            return false;
        }
        String name = symbol.typeName().name;
        return "Dict".equals(name) || "dict".equals(name) || "HashDict".equals(name);
    }
}
