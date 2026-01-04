package com.germaniumhq.caffc.compiler.model.asm.vars;

import com.germaniumhq.caffc.compiler.model.Field;
import com.germaniumhq.caffc.compiler.model.Struct;
import com.germaniumhq.caffc.compiler.model.type.Symbol;

/**
 * This is a variable that lives inside another variable (i.e. a class, or
 * a struct).
 */
public final class AsmFieldVar implements AsmVar {
    public Symbol type;
    public AsmVar owner;
    public String name;

    public AsmFieldVar(AsmVar owner, Symbol type, String name) {
        this.owner = owner;
        this.type = type;
        this.name = name;
    }

    public static AsmFieldVar fromFieldIndex(AsmVar rightStruct, int index) {
        if (rightStruct == null) {
            throw new IllegalArgumentException("invalid null field owner");
        }

        Struct struct = (Struct) rightStruct.typeSymbol();
        Field field = struct.fields.get(index);

        return new AsmFieldVar(rightStruct, field.typeSymbol, field.name);
    }

    @Override
    public Symbol typeSymbol() {
        return type;
    }
}
