package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a structure that will be returned for a multi-return call.
 * A struct is itself just a definition, since there's no correspondent
 * `struct` construct in the CaffC language.
 */
public class Struct implements Symbol, AstItem {
    private final AstItem owner;
    private final String name;
    public final LinkedHashMap<String, Symbol> returnTypes;
    private final String[] keyNames;
    private int index;

    public Struct(AstItem owner,
                  String name,
                  LinkedHashMap<String, Symbol> returnTypes) {
        this.owner = owner;
        this.name = name;
        this.returnTypes = returnTypes;
        this.keyNames = new String[returnTypes.size()];

        int index = 0;
        for (Map.Entry<String, Symbol> entry: returnTypes.entrySet()) {
            this.keyNames[index++] = entry.getKey();
        }

        owner.findAstParent(Module.class).structures.put(this.name, this);
    }

    @Override
    public AstItem getOwner() {
        return this.owner;
    }

    @Override
    public String getFilePath() {
        return this.owner.getFilePath();
    }

    @Override
    public int getLineNumber() {
        return this.owner.getLineNumber();
    }

    @Override
    public int getColumnNumber() {
        return this.owner.getColumnNumber();
    }

    @Override
    public void recurseResolveTypes() {
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public TypeName typeName() {
        return TypeName.of(
            "caffc",
            name,
            null,
            DataType.STRUCT
        );
    }

    @Override
    public Symbol typeSymbol() {
        return this;
    }


    @UsedInTemplate("assign.peb")
    public boolean isPrimitive() {
        return this.returnTypes.get(this.keyNames[index]).typeName().isPrimitive();
    }

    @UsedInTemplate("assign.peb")
    public String getKeyName() {
        if (index >= this.keyNames.length) {
            index = 0;
        }

        return this.keyNames[index++];
    }

    public List<String> getGcManagedKeys() {
        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Symbol> entry: returnTypes.entrySet()) {
            DataType dataType = entry.getValue().typeSymbol().typeName().dataType;
            if (dataType == DataType.ARRAY || dataType == DataType.OBJECT) {
                result.add(entry.getKey());
            }
        }

        return result;
    }
}
