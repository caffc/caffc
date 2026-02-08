package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;

import java.util.*;

/**
 * All the things that exist in a module are defined here. Like
 * this we can create the forward definitions when generating
 * header C files for the modules.
 */
public class Module implements AstItem, Scope, Symbol {
    public Program program;
    public String name;

    public Map<String, FunctionDefinition> functions = new LinkedHashMap<>();
    public Map<String, ClassDefinition> clazzes = new LinkedHashMap<>();
    public Map<String, InterfaceDefinition> interfaces = new LinkedHashMap<>();
    public Map<String, Struct> structures = new LinkedHashMap<>();
    public Set<Module> usedModules = new LinkedHashSet<>();
    public Set<String> stringConstants = new LinkedHashSet<>();

    public Map<TypeName, Integer> moduleArrays = new LinkedHashMap<>();

    public String astFilePath;
    public int astColumn;
    public int astLine;

    public Module(String moduleName) {
        this.name = moduleName;
    }

    public Collection<FunctionDefinition> functionDefinitions() {
        return functions.values();
    }

    public Object getByName(String name) {
        return functions.get(name);
    }

    public Collection<ClassDefinition> classDefinitions() {
        return clazzes.values();
    }

    public Collection<InterfaceDefinition> interfaceDefinitions() {
        return interfaces.values();
    }

    public Collection<Struct> structDefinitions() {
        return structures.values();
    }

    @Override
    public AstItem getOwner() {
        return program;
    }

    @Override
    public Symbol resolve(String name) {
        name = name.replace("[]", "_arr");

        if (functions.containsKey(name)) {
            return functions.get(name);
        }

        if (clazzes.containsKey(name)) {
            return clazzes.get(name);
        }

        if (interfaces.containsKey(name)) {
            return interfaces.get(name);
        }

        return null;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public TypeName typeName() {
        return TypeName.of(this.name, "", null, DataType.MODULE);
    }

    @Override
    public Symbol typeSymbol() {
        return this;
    }

    public TypeName registerArray(TypeName symbolTypeName, int dimensions) {
        moduleArrays.compute(symbolTypeName, (k, v) -> {
            if (v == null) {
                return dimensions;
            }

            return v >= dimensions ? v : dimensions;
        });

        return TypeName.arrayOf(symbolTypeName, dimensions);
    }

    public List<TypeName> getRegisteredArrays() {
        List<TypeName> result = new ArrayList<>();

        for (Map.Entry<TypeName, Integer> entry : moduleArrays.entrySet()) {
            for (int i = 1; i <= entry.getValue(); i++) {
                result.add(TypeName.arrayOf(entry.getKey(), i));
            }
        }

        return result;
    }

    public void registerConstant(String constant) {
        this.stringConstants.add(constant);
    }

    @Override
    public String getFilePath() {
        return astFilePath;
    }

    @Override
    public int getLineNumber() {
        return astLine;
    }

    @Override
    public int getColumnNumber() {
        return astColumn;
    }

    @Override
    public void recurseResolveTypes() {
        throw new IllegalStateException("BUG: recurse resolve types can only start at compilation unit level");
    }

    /**
     * Ensures the array is registered, and its definition exists.
     * @return
     */
    public Symbol ensureArray(Symbol symbol, int arrayDimensions) {
        while (symbol instanceof GenericDefinition genericDefinition) {
            symbol = genericDefinition.typeRestriction;
        }

        if (!(symbol instanceof ClassDefinition) &&
                !(symbol instanceof TypeSymbol) &&
                !(symbol instanceof InterfaceDefinition)) {
            CaffcCompiler.get().fatal((AstItem) symbol, "base type of array is not a class definition: " + symbol);
        }

        if (arrayDimensions <= 0) {
            CaffcCompiler.get().fatal((AstItem) symbol, "ensure array created with <0 array dimensions: " + arrayDimensions);
        }

        Symbol containedSymbol = symbol;
        String uniDimensionArrayName = symbol.typeName().isPrimitive() ?
            symbol.typeName().name + "_arr" :
            "obj_arr";
        ClassDefinition result = null;

        for (int i = 1; i <= arrayDimensions; i++) {
            // SymbolResolver.resolveSymbol(this, SymbolSearch.ofName("obj_arr"));
            // the symbol might be a generics definition, so we need the type of it
            TypeName arrayTypeName = this.registerArray(symbol.typeName(), i);
            String searchArrayName = i == 1 ? uniDimensionArrayName : "obj_arr";
            ClassDefinition arrayClassDefinition = Program.get()
                .getModule("caffc").clazzes.get(
                    searchArrayName
                );

            if (arrayClassDefinition == null) {
                CaffcCompiler.get().fatal((AstItem) symbol, "unable to find " +
                    searchArrayName
                    + ". missing caffc sources?");
            }

            result = arrayClassDefinition.copyDefinition();

            result.childDefinition = containedSymbol;
            containedSymbol = result;
            result.typeName = arrayTypeName;
        }

        return result;
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        throw new IllegalStateException("BUG: render ast can only be used on modules");
    }

    public void registerStruct(Struct struct) {
        this.structures.compute(struct.name(), (key, oldValue) -> {
            if (oldValue != null) {
                throw new IllegalArgumentException("struct " + struct.name() + " already exists");
            }

            return struct;
        });
    }
}
