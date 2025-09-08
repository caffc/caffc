package com.germaniumhq.caffc.compiler.model.type;

import java.util.Arrays;
import java.util.Objects;

/**
 * Type name of something. Can be any of:
 *
 * - a primitive type
 * - an interface (with generics)
 * - an array of something
 *
 * The generics array in the `TypeName` is always a specific type. If it's an `is`
 * restriction in the type, then the actual restriction replaces it for types
 * that are still "generic", but need to have their code generated. The TypeName
 * will be changed from the FunctionDefinition/ClassDefinition, and the only
 * reason why the generics[] information is also here is to have an easy way
 * to check if something is assignable to something else.
 */
public class TypeName {
    public String module;
    public String functionClass; // only applies to functions if they're owned by a class
    public String name;
    public TypeName[] generics;
    public DataType dataType;

    // Primitive and default types. Primitive types are present in the caffc module only so we know in what
    // module to generate the array definitions.
    public final static TypeName PTR = new TypeName("caffc", "ptr", DataType.PRIMITIVE);
    public final static TypeName U8 = new TypeName("caffc", "u8", DataType.PRIMITIVE);
    public final static TypeName I8 = new TypeName("caffc", "i8", DataType.PRIMITIVE);
    public final static TypeName U16 = new TypeName("caffc", "u16", DataType.PRIMITIVE);
    public final static TypeName I16 = new TypeName("caffc", "i16", DataType.PRIMITIVE);
    public final static TypeName U32 = new TypeName("caffc", "u32", DataType.PRIMITIVE);
    public final static TypeName I32 = new TypeName("caffc", "i32", DataType.PRIMITIVE);
    public final static TypeName U64 = new TypeName("caffc", "u64", DataType.PRIMITIVE);
    public final static TypeName I64 = new TypeName("caffc", "i64", DataType.PRIMITIVE);
    public final static TypeName F32 = new TypeName("caffc", "f32", DataType.PRIMITIVE);
    public final static TypeName F64 = new TypeName("caffc", "f64", DataType.PRIMITIVE);
    public final static TypeName BOOL = new TypeName("caffc", "bool", DataType.PRIMITIVE);
    public final static TypeName VOID = new TypeName("caffc", "void", DataType.PRIMITIVE);
    public final static TypeName STR = new TypeName("caffc", "str", DataType.OBJECT);
    public final static TypeName OBJ = new TypeName("caffc", "obj", DataType.OBJECT);

    public final static TypeName[] PRIMITIVE_TYPES = new TypeName[]{
       PTR, U8, I8, U16, I16, U32, I32, U64, I64, F32, F64, BOOL
    };

    private TypeName() {
        // used only for copies
    }

    private TypeName(String module, String name, DataType dataType) {
        this.module = module;
        this.name = name;
        this.dataType = dataType;
    }

    /**
     * Creates a Type object based on the provided parameters.
     *
     * @param module   The module name associated with the type.
     * @param name     The name of the type, which can include array notation (e.g., "int[]").
     * @param clazz    The class name associated with the type.
     * @param dataType The data type of the type, which can be overridden if the name contains array notation.
     * @return A Type object representing the specified type.
     */
    public static TypeName of(String module, String name, String clazz, DataType dataType) {
        TypeName result = primitiveType(name);

        if (result != null) {
            return result;
        }

        if (name.contains("[]")) {
            dataType = DataType.ARRAY;
        }

        result = new TypeName(module, name, dataType);
        result.functionClass = clazz;

        return result;
    }

    /**
     * Returns the primitive type corresponding to the given name.
     *
     * @param name the name of the primitive type
     * @return the primitive type if found, otherwise null
     */
    public static TypeName primitiveType(String name) {
        if (PTR.name.equals(name)) {
            return PTR;
        } else if (U8.name.equals(name)) {
            return U8;
        } else if (I8.name.equals(name)) {
            return I8;
        } else if (U16.name.equals(name)) {
            return U16;
        } else if (I16.name.equals(name)) {
            return I16;
        } else if (U32.name.equals(name)) {
            return U32;
        } else if (I32.name.equals(name)) {
            return I32;
        } else if (U64.name.equals(name)) {
            return U64;
        } else if (I64.name.equals(name)) {
            return I64;
        } else if (F32.name.equals(name)) {
            return F32;
        } else if (F64.name.equals(name)) {
            return F64;
        } else if (BOOL.name.equals(name)) {
            return BOOL;
        } else if (VOID.name.equals(name)) {
            return VOID;
        }

        return null;
    }

    /**
     * Creates an array type with the specified number of dimensions.
     *
     * @param typeName       The base type of the array.
     * @param dimensions The number of dimensions for the array.
     * @return A new Type representing the array with the specified dimensions.
     */
    public static TypeName arrayOf(TypeName typeName, int dimensions) {
        if (dimensions < 0) {
            throw new IllegalArgumentException("dimensions cannot be negative");
        }

        if (dimensions == 0) {
            return typeName;
        }

        StringBuilder stringDimensions = new StringBuilder();

        for (int i = 0; i < dimensions; i++) {
            stringDimensions.append("[]");
        }

        TypeName result = TypeName.of(
                typeName.module != null ? typeName.module : "caffc",
                typeName.name + stringDimensions,
                typeName.functionClass,
                DataType.ARRAY);

        result.generics = typeName.generics;

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TypeName typeName = (TypeName) o;
        return Objects.equals(module, typeName.module) &&
                Objects.equals(functionClass, typeName.functionClass) &&
                Objects.equals(name, typeName.name) &&
                Arrays.equals(generics, typeName.generics) &&
                dataType == typeName.dataType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(module, functionClass, name, dataType, Arrays.hashCode(generics));
    }

    public boolean isPrimitive() {
        return this.dataType == DataType.PRIMITIVE;
    }

    /**
     * Generates the fully qualified domain name (FQDN) based on the data type.
     * If the data type is PRIMITIVE, it returns the name directly.
     * Otherwise, it constructs the FQDN by appending the module, function class (if present), and name.
     *
     * @return the fully qualified domain name as a String
     */
    public String fqdn() {
        if (this.dataType == DataType.PRIMITIVE) {
            return this.name;
        }

        StringBuilder result = new StringBuilder();

        result.append(module).append(".");

        if (this.functionClass != null && !this.functionClass.isEmpty()) {
            result.append(this.functionClass).append(".");
        }

        result.append(name);

        return result.toString();
    }

    /**
     * Copies the properties from another Type object to this object.
     *
     * @param symbolTypeName the Type object from which to copy properties
     */
    public void copyFrom(TypeName symbolTypeName) {
        this.module = symbolTypeName.module;
        this.functionClass = symbolTypeName.functionClass;
        this.name = symbolTypeName.name;
        this.dataType = symbolTypeName.dataType;
        this.generics = symbolTypeName.generics != null ? Arrays.copyOf(symbolTypeName.generics, symbolTypeName.generics.length) : null;
    }

    public static TypeName copyOf(TypeName symbolTypeName) {
        TypeName result = new TypeName(symbolTypeName.module, symbolTypeName.name, symbolTypeName.dataType);
        result.copyFrom(symbolTypeName);

        return result;
    }

    public int arrayDimensions() {
        int initialLength = this.name.length();
        int noArrayLength = this.name.replace("[]", "").length();

        return (initialLength - noArrayLength) / 2;
    }

    /**
     * Determines the type of the child element if the current type represents an array.
     *
     * @return The type of the child element if the current type is an array, otherwise null.
     */
    public TypeName childType() {
        if (!this.name.endsWith("[]")) {
            return null;
        }

        String childTypeName = this.name.substring(0, this.name.length() - 2);

        TypeName result = primitiveType(childTypeName);

        if (result != null) {
            return result;
        }

        result = new TypeName();
        result.copyFrom(this);
        result.name = childTypeName;
        result.generics = this.generics;

        if (!result.name.contains("[]")) {
            result.dataType = DataType.OBJECT;
        }

        return result;
    }

    @Override
    public String toString() {
        return module + ":" + functionClass + ":" + name + ":" + dataType;
    }
}
