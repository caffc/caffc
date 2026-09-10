package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeName;

/**
 * Auto-box / unbox helpers for {@code fn.call(... obj[] args)} packing.
 */
public final class PrimitiveBoxing {
    private PrimitiveBoxing() {
    }

    /**
     * Maps a primitive type name ({@code i32}) to its boxing class ({@code I32}).
     * Returns {@code null} when the type is not a boxed primitive.
     */
    public static String boxClassName(String primitiveName) {
        if (primitiveName == null) {
            return null;
        }
        return switch (primitiveName) {
            case "i8" -> "I8";
            case "u8" -> "U8";
            case "i16" -> "I16";
            case "u16" -> "U16";
            case "i32" -> "I32";
            case "u32" -> "U32";
            case "i64" -> "I64";
            case "u64" -> "U64";
            case "f32" -> "F32";
            case "f64" -> "F64";
            case "bool" -> null;
            default -> null;
        };
    }

    public static boolean isObjType(Symbol symbol) {
        if (symbol == null || symbol.typeName() == null) {
            return false;
        }
        TypeName typeName = symbol.typeName();
        return "obj".equals(typeName.name) &&
                (typeName.module == null || "caffc".equals(typeName.module));
    }

    public static boolean isPrimitive(Symbol symbol) {
        return symbol != null
                && symbol.typeName() != null
                && symbol.typeName().dataType == DataType.PRIMITIVE
                && !TypeName.VOID.equals(symbol.typeName())
                && !TypeName.PTR.equals(symbol.typeName());
    }

    public static boolean isPrimitiveName(String name) {
        return boxClassName(name) != null || "bool".equals(name) || "ptr".equals(name);
    }

    /**
     * If {@code elementType} is {@code obj} and {@code arg} is a boxable primitive,
     * wrap it in {@code new BoxType(arg)}; otherwise return {@code arg}.
     */
    public static Expression boxIfNeeded(AstItem owner, Expression arg, Symbol elementType) {
        if (arg == null || !isObjType(elementType) || !isPrimitive(arg.typeSymbol())) {
            return arg;
        }

        String boxName = boxClassName(arg.typeSymbol().typeName().name);
        if (boxName == null) {
            return arg;
        }

        ExpressionNewObject boxed = new ExpressionNewObject();
        boxed.owner = owner;
        boxed.sourceLocation = arg.getSourceLocation() != null
                ? arg.getSourceLocation()
                : owner.getSourceLocation();
        boxed.instantiatedTypeSearch = SymbolSearch.ofName(boxName);
        boxed.callArguments.add(CallArgument.positional(arg));
        boxed.recurseResolveTypes();
        return boxed;
    }

    /**
     * Build an expression that reads {@code args[index]} and converts it to the user parameter type.
     */
    public static Expression unpackArg(
            AstItem owner,
            Expression argsExpression,
            int index,
            SymbolSearch targetTypeSearch) {
        ExpressionNumber indexLit = ExpressionNumber.fromLiteral(owner, Integer.toString(index));
        ExpressionIndexAccess indexed = ExpressionIndexAccess.fromParts(owner, argsExpression, indexLit);

        if (targetTypeSearch == null) {
            return indexed;
        }

        String boxName = boxClassName(targetTypeSearch.name);
        if (boxName != null) {
            ExpressionCast cast = ExpressionCast.of(owner, SymbolSearch.ofName(boxName), indexed);
            return ExpressionDotAccess.fromParts(owner, cast, "value");
        }

        return ExpressionCast.of(owner, targetTypeSearch, indexed);
    }
}
