package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.GenericsDefinitionsSymbol;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a structure that will be allocated on the stack. Structs
 * are currently only returned for a multi-return call.
 * A struct is itself just a definition, since there's no correspondent
 * `struct` construct in the CaffC language yet.
 */
public class Struct implements GenericsDefinitionsSymbol, Scope {
    private AstItem owner;
    private String name;
    private String module;

    public String astFilePath;
    public int astColumn;
    public int astLine;

    /**
     * A list of fields defined within this class.
     */
    public List<Field> fields = new ArrayList<>();

    /**
     * Generics defined for the struct.
     */
    public GenericDefinitions generics = null;

    public static Struct fromDefinition(AstItem owner, String module, String name, LinkedHashMap<String, Symbol> keyNames) {
        Struct struct = new Struct();

        struct.owner = owner;
        struct.module = module;
        struct.name = name;
        struct.fields = new ArrayList<>(keyNames.size());
        for (Map.Entry<String, Symbol> entry: keyNames.entrySet()) {
            struct.fields.add(Field.fromDefinition(struct, entry.getValue(), entry.getKey()));
        }

        return struct;
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

    public List<Field> getGcManagedFields() {
        List<Field> result = new ArrayList<>();

        for (Field field: this.fields) {
            DataType dataType = field.typeSymbol().typeName().dataType;
            if (dataType == DataType.ARRAY || dataType == DataType.OBJECT) {
                result.add(field);
            }
        }

        return result;
    }

    @Override
    public GenericDefinition getGenericDefinition(int index) {
        return generics.generics[index];
    }

    @Override
    public int getGenericsDefinitionCount() {
        return generics.generics.length;
    }

    @Override
    public <T extends GenericsDefinitionsSymbol> T instantiateGenerics(List<Symbol> resolvedGenerics) {
        Map<String, Symbol> genericsSymbols = GenericsDefinitionsSymbol.createGenericsSymbolMap(
            this, resolvedGenerics);

        return this.newGenericsCopy(genericsSymbols);
    }

    @Override
    public <T extends Symbol> T newGenericsCopy(Map<String, Symbol> resolvedGenerics) {
        Struct copy = new Struct();

        copy.owner = this.owner;
        copy.module = this.module;
        copy.name = this.name;

        copy.astColumn = this.astColumn;
        copy.astLine = this.astLine;
        copy.astFilePath = this.astFilePath;

        for (Field f : fields) {
            copy.fields.add(f.newGenericsCopy(resolvedGenerics));
        }

        return (T) copy;
    }

    @Override
    public Symbol resolve(String name) {
        for (Field f : fields) {
            if (f.name.equals(name)) {
                return f;
            }
        }

        if (this.generics != null) {
            return this.generics.getByName(name);
        }

        return null;
    }
}
