package com.germaniumhq.caffc.compiler.model.type;

import com.germaniumhq.caffc.compiler.model.ClassDefinition;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.GenericDefinition;
import com.germaniumhq.caffc.compiler.model.InterfaceDefinition;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionNull;

import java.util.Objects;

/**
 * Shared rules for whether a value of one type can be used where another is expected
 * (assignments, call arguments, interface method signatures).
 */
public final class TypeAssignability {
    private TypeAssignability() {
    }

    /**
     * Returns true if a value of {@code source} can be assigned to a location of type {@code target}.
     */
    public static boolean isAssignable(Symbol target, Symbol source) {
        if (target == null || source == null) {
            return false;
        }

        Symbol targetType = underlyingType(target);
        Symbol sourceType = underlyingType(source);

        if (targetType == null || sourceType == null) {
            return false;
        }

        // Untyped {@code null} is represented as a TypeSymbol of obj (see ExpressionNull).
        if (isNullTypeSymbol(sourceType)) {
            DataType dataType = targetType.typeName().dataType;
            return dataType == DataType.OBJECT || dataType == DataType.ARRAY;
        }

        TypeName targetName = targetType.typeName();
        TypeName sourceName = sourceType.typeName();

        if (targetName != null && targetName.equals(sourceName)) {
            return true;
        }

        // obj is the top type for all GC-managed object references.
        if (isObjType(targetType) && sourceName != null && sourceName.dataType == DataType.OBJECT) {
            return true;
        }

        // Integer literals default to i32; existing code freely mixes integer widths.
        if (isIntegerPrimitive(targetName) && isIntegerPrimitive(sourceName)) {
            return true;
        }

        // bool is represented as 0/1 in C; existing code assigns integer literals to bool
        // and bool results to integer locals.
        if (isBoolPrimitive(targetName) && isIntegerPrimitive(sourceName)) {
            return true;
        }
        if (isIntegerPrimitive(targetName) && isBoolPrimitive(sourceName)) {
            return true;
        }

        if (isFloatPrimitive(targetName) && isFloatPrimitive(sourceName)) {
            return true;
        }

        if (sourceType instanceof GenericDefinition sourceGeneric) {
            return isAssignable(targetType, sourceGeneric.typeRestriction);
        }

        if (targetType instanceof GenericDefinition targetGeneric) {
            return isAssignable(targetGeneric.typeRestriction, sourceType);
        }

        if (sourceType instanceof ClassDefinition sourceClass) {
            if (targetType instanceof InterfaceDefinition targetInterface) {
                return sourceClass.isImplementing(targetInterface);
            }
            if (targetType instanceof ClassDefinition) {
                return sameNamedType(targetName, sourceName);
            }
        }

        if (sourceType instanceof InterfaceDefinition sourceInterface) {
            if (targetType instanceof InterfaceDefinition targetInterface) {
                if (targetInterface.isAssignableFrom(sourceInterface)) {
                    return true;
                }
                for (TypeDefinitionSymbol implemented : sourceInterface.getImplementedTypes()) {
                    if (implemented instanceof InterfaceDefinition parent &&
                            targetInterface.isAssignableFrom(parent)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Like {@link #isAssignable(Symbol, Symbol)}, but treats {@link ExpressionNull} as assignable
     * to any object or array type even before its type symbol is specialized.
     */
    public static boolean isExpressionAssignable(Symbol target, Expression sourceExpression) {
        if (sourceExpression instanceof ExpressionNull) {
            if (target == null) {
                return false;
            }
            Symbol targetType = underlyingType(target);
            DataType dataType = targetType.typeName().dataType;
            return dataType == DataType.OBJECT || dataType == DataType.ARRAY;
        }

        return isAssignable(target, sourceExpression.typeSymbol());
    }

    public static String describe(Symbol symbol) {
        if (symbol == null) {
            return "<unknown>";
        }
        Symbol type = underlyingType(symbol);
        if (type == null || type.typeName() == null) {
            return symbol.name();
        }
        return type.typeName().fqdn();
    }

    private static Symbol underlyingType(Symbol symbol) {
        if (symbol == null) {
            return null;
        }

        // Parameters, fields, and variables point at their type via typeSymbol().
        Symbol type = symbol.typeSymbol();
        if (type != null && type != symbol) {
            return type;
        }

        return symbol;
    }

    private static boolean isNullTypeSymbol(Symbol sourceType) {
        return sourceType instanceof TypeSymbol && TypeName.OBJ.equals(sourceType.typeName());
    }

    private static boolean isObjType(Symbol type) {
        if (type == null || type.typeName() == null) {
            return false;
        }
        TypeName typeName = type.typeName();
        return "obj".equals(typeName.name) &&
                (typeName.module == null || "caffc".equals(typeName.module));
    }

    private static boolean isIntegerPrimitive(TypeName typeName) {
        if (typeName == null || typeName.dataType != DataType.PRIMITIVE) {
            return false;
        }
        return typeName == TypeName.U8 || typeName == TypeName.I8 ||
                typeName == TypeName.U16 || typeName == TypeName.I16 ||
                typeName == TypeName.U32 || typeName == TypeName.I32 ||
                typeName == TypeName.U64 || typeName == TypeName.I64 ||
                typeName == TypeName.PTR;
    }

    private static boolean isBoolPrimitive(TypeName typeName) {
        return typeName == TypeName.BOOL;
    }

    private static boolean isFloatPrimitive(TypeName typeName) {
        if (typeName == null || typeName.dataType != DataType.PRIMITIVE) {
            return false;
        }
        return typeName == TypeName.F32 || typeName == TypeName.F64;
    }

    private static boolean sameNamedType(TypeName target, TypeName source) {
        if (target == null || source == null) {
            return false;
        }
        if (target.dataType != source.dataType) {
            return false;
        }
        if (target.module == null || source.module == null) {
            return Objects.equals(target.name, source.name);
        }
        return target.module.equals(source.module) && Objects.equals(target.name, source.name);
    }
}
