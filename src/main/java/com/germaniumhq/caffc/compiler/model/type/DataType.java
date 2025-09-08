package com.germaniumhq.caffc.compiler.model.type;

public enum DataType {
    PRIMITIVE,
    ARRAY, // managed by the GC
    OBJECT, // managed by the GC
    FUNCTION,
    MODULE,
}
