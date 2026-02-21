package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.source.HasSourceLocation;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.GenericsDefinitionsSymbol;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeName;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Represents a class definition in the symbol table, implementing both Symbol and Scope interfaces.
 * It holds information about the class's name, module, type, garbage collection field count,
 * functions, fields, and tags.
 */
public class ClassDefinition implements HasMethods, GenericsDefinitionsSymbol, Scope, HasSourceLocation {
    /**
     * The name of the class.
     */
    public String name;

    /**
     * The module to which this class belongs.
     */
    public Module module;

    /**
     * The type of the class.
     */
    public TypeName typeName;

    /**
     * The count of fields that require garbage collection.
     */
    public int gcFieldsCount;

    /**
     * Only applicable for arrays, it points to the symbol definition
     * of the contained item. It can be either the class, interface,
     * or the primitive type symbol.
     */
    public Symbol childDefinition;

    /**
     * A list of function definitions defined within this class.
     */
    public List<FunctionDefinition> functions = new ArrayList<>();

    /**
     * A list? of interfaces implemented by this class.
     */
    public List<SymbolSearch> implementedInterfacesSearch = new ArrayList<>();
    public List<InterfaceDefinition> implementedInterfaces = new ArrayList<>();

    /**
     * A list of fields defined within this class.
     */
    public List<Field> fields = new ArrayList<>();

    /**
     * Generics defined for the class.
     */
    public GenericDefinitions generics = null;

    /**
     * Tags associated with this class.
     */
    public Tags tags = new Tags();

    /**
     * Source location is filled in the AST parsing.
     */
    public SourceLocation sourceLocation;

    /**
     * Is the class resolved or not - did the recurseResolveTypes already
     * happened? The rationale is that items can have circular dependencies
     * so we don't want to call the resolving multiple times when descending
     * in the resolve calls.
     */
    private boolean isResolved;

    /**
     * Counts the number of fields that require garbage collection.
     *
     * @return The count of fields with data type OBJECT.
     */
    public int countGcFieldsCount() {
        int i = 0;

        for (Field f : fields) {
            if (f.typeName().dataType == DataType.OBJECT || f.typeName().dataType == DataType.ARRAY) {
                i++;
            }
        }

        return i;
    }

    /**
     * Returns the name of the class.
     *
     * @return The name of the class.
     */
    @Override
    public String name() {
        return name;
    }

    /**
     * Returns the type of the class.
     *
     * @return The type of the class.
     */
    @Override
    public TypeName typeName() {
        return typeName;
    }

    /**
     * The class definition has no pointing type symbol.
     * @return _this
     */
    @Override
    public Symbol typeSymbol() {
        return this;
    }

    /**
     * Resolves a symbol by name within the scope of this class.
     * It first searches through the functions and then through the fields.
     *
     * @param name The name of the symbol to resolve.
     * @return The resolved symbol if found, otherwise null.
     */
    @Override
    public Symbol resolve(String name) {
        for (FunctionDefinition f : functions) {
            if (f.name.equals(name)) {
                return f;
            }
        }

        for (Field f : fields) {
            if (f.name.equals(name)) {
                return f;
            }
        }

        if (this.generics != null) {
            return this.generics.getByName(name);
        }

        if ("_this".equals(name)) {
            return this;
        }

        return null;
    }

    /**
     * Retrieves a function definition by name.
     *
     * @param name The name of the function to retrieve.
     * @return The function definition if found, otherwise null.
     */
    public FunctionDefinition getFunction(String name) {
        for (FunctionDefinition f : functions) {
            if (f.name.equals(name)) {
                return f;
            }
        }

        return null;
    }

    @Override
    public AstItem getOwner() {
        return module;
    }

    @Override
    public void recurseResolveTypes() {
        if (this.isResolved) {
            return;
        }

        this.isResolved = true;

        if (generics != null) {
            this.generics.recurseResolveTypes();
        }

        for (SymbolSearch symbolSearch : implementedInterfacesSearch) {
            Symbol implementedSymbol = SymbolResolver.mustResolveSymbol(this, symbolSearch);

            if (!(implementedSymbol instanceof InterfaceDefinition)) {
                CaffcCompiler.get().fatal(this.sourceLocation, String.format(
                    "%s is not an interface but a %s",
                    symbolSearch,
                    implementedSymbol
                ));
            }

            InterfaceDefinition interfaceDefinition = (InterfaceDefinition) implementedSymbol;
            implementedInterfaces.add(interfaceDefinition);

            interfaceDefinition.recurseResolveTypes();
        }

        ClassDefinition.registerConcreteImplementations(this, this.implementedInterfaces);

        for (FunctionDefinition f: this.functions) {
            f.recurseResolveTypes();
        }

        for (Field field: this.fields) {
            field.recurseResolveTypes();
        }

        this.gcFieldsCount = this.countGcFieldsCount();
    }

    private static void registerConcreteImplementations(ClassDefinition classDefinition, List<InterfaceDefinition> implementedInterfaces) {
        Set<InterfaceDefinition> processedInterfaces = new HashSet<>();
        Set<InterfaceDefinition> toProcess = new HashSet<>(implementedInterfaces);

        while (!toProcess.isEmpty()) {
            InterfaceDefinition currentInterfaceDefinition = toProcess.iterator().next();
            toProcess.remove(currentInterfaceDefinition);

            if (processedInterfaces.contains(currentInterfaceDefinition)) {
                continue;
            }

            processedInterfaces.add(currentInterfaceDefinition);

            toProcess.addAll(currentInterfaceDefinition.implementedInterfaces);
            toProcess.removeAll(processedInterfaces);

            assert currentInterfaceDefinition != null;
            currentInterfaceDefinition.concreteImplementations.add(classDefinition);
        }
    }

    /**
     * Creates a copy of the definition, replacing all the generics definitions
     * with the actual types being passed in the generics given.
     *
     * The resolvedType is a type that's from a symbol after full parsing of the
     * SymbolResolver.
     */
    @Override
    public <T extends Symbol> T newGenericsCopy(Map<String, Symbol> resolvedGenerics) {
        ClassDefinition copy = new ClassDefinition();

        copy.name = this.name;
        copy.module = this.module;

        copy.sourceLocation = this.sourceLocation;
        copy.gcFieldsCount = this.gcFieldsCount;

        copy.typeName = this.typeName;

        for (FunctionDefinition f : functions) {
            copy.functions.add(f.newGenericsCopy(resolvedGenerics));
        }

        for (Field f : fields) {
            copy.fields.add(f.newGenericsCopy(resolvedGenerics));
        }

        copy.tags = this.tags;
        copy.isResolved = this.isResolved;

        return (T) copy;
    }

    @Override
    public GenericDefinition getGenericDefinition(int index) {
        if (generics == null) {
            CaffcCompiler.get().fatal(this.sourceLocation, "class has no generics");
        }

        if (index < 0) {
            CaffcCompiler.get().fatal(this.sourceLocation, "index out of bounds");
        }

        if (index >= generics.generics.length) {
            CaffcCompiler.get().fatal(this.sourceLocation, "too many generics in the class instantiation");
        }

        return generics.generics[index];
    }

    @Override
    public int getGenericsDefinitionCount() {
        if (generics == null) {
            return 0;
        }

        return generics.generics.length;
    }

    @Override
    public <T extends GenericsDefinitionsSymbol> T instantiateGenerics(List<Symbol> resolvedGenerics) {
        Map<String, Symbol> genericsSymbols = GenericsDefinitionsSymbol.createGenericsSymbolMap(
                this, resolvedGenerics);
        return this.newGenericsCopy(genericsSymbols);
    }

    public ClassDefinition copyDefinition() {
        ClassDefinition result = new ClassDefinition();

        result.typeName = this.typeName;
        result.name = this.name;
        result.module = this.module;
        result.sourceLocation = this.sourceLocation;

        result.generics = this.generics;
        result.tags = this.tags;
        result.fields = this.fields;
        result.functions = this.functions;

        return result;
    }

    @Override
    public String toString() {
        return "ClassDefinition{" +
                "type=" + typeName +
                ", name='" + name + '\'' +
                ", generics=" + generics +
                '}';
    }

    public boolean isImplementing(InterfaceDefinition definition) {
        for (InterfaceDefinition interfaceDefinition : implementedInterfaces) {
            if (definition.isAssignableFrom(interfaceDefinition)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return this.sourceLocation;
    }
}
