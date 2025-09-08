package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.type.GenericsDefinitionsSymbol;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a class definition in the symbol table, implementing both Symbol and Scope interfaces.
 * It holds information about the class's name, module, type, garbage collection field count,
 * functions, fields, and tags.
 */
public class InterfaceDefinition implements GenericsDefinitionsSymbol, Scope {
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
     * A list of function definitions defined within this class.
     */
    public List<FunctionDefinition> functions = new ArrayList<>();

    /**
     * A list? of interfaces implemented by this class.
     */
    public List<SymbolSearch> implementedInterfacesSearch = new ArrayList<>();
    public List<InterfaceDefinition> implementedInterfaces = new ArrayList<>();
    public List<ClassDefinition> concreteImplementations = new ArrayList<>();

    /**
     * Generics defined for the class.
     */
    public GenericDefinitions generics = null;

    /**
     * Tags associated with this class.
     */
    public Tags tags = new Tags();

    // AstItem info is filled by the class parsing
    public String astFilePath;
    public int astColumn;
    public int astLine;

    private boolean isResolved;

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
        if (this.isResolved) {
            return;
        }

        this.isResolved = true;

        if (generics != null) {
            this.generics.recurseResolveTypes();
        }

        for (SymbolSearch symbolSearch : implementedInterfacesSearch) {
            InterfaceDefinition interfaceDefinition = SymbolResolver.mustResolveSymbol(this, symbolSearch);
            implementedInterfaces.add(interfaceDefinition);

            interfaceDefinition.recurseResolveTypes();
        }

        for (FunctionDefinition f: this.functions) {
            f.recurseResolveTypes();
        }
    }

    @Override
    public <T extends GenericsDefinitionsSymbol> T instantiateGenerics(List<Symbol> resolvedGenerics) {
        Map<String, Symbol> genericsSymbols = GenericsDefinitionsSymbol.createGenericsSymbolMap(
                this, resolvedGenerics);
        return this.newGenericsCopy(genericsSymbols);
    }

    @Override
    public <T extends Symbol> T newGenericsCopy(Map<String, Symbol> resolvedGenerics) {
        InterfaceDefinition copy = new InterfaceDefinition();

        copy.name = this.name;
        copy.module = this.module;

        copy.astColumn = this.astColumn;
        copy.astLine = this.astLine;
        copy.astFilePath = this.astFilePath;

        copy.typeName = this.typeName;

        for (FunctionDefinition f : functions) {
            copy.functions.add(f.newGenericsCopy(resolvedGenerics));
        }

        copy.tags = this.tags;

        return (T) copy;
    }

    @Override
    public GenericDefinition getGenericDefinition(int index) {
        if (generics == null) {
            CaffcCompiler.get().fatal(this, "intreface has no generics");
        }

        if (index < 0) {
            CaffcCompiler.get().fatal(this, "index out of bounds");
        }

        if (index >= generics.generics.length) {
            CaffcCompiler.get().fatal(this, "too many generics in the interface instantiation");
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

    public InterfaceDefinition copyDefinition() {
        InterfaceDefinition result = new InterfaceDefinition();

        result.typeName = this.typeName;
        result.name = this.name;
        result.module = this.module;
        result.astColumn = this.astColumn;
        result.astLine = this.astLine;
        result.astFilePath = this.astFilePath;

        result.generics = this.generics;
        result.tags = this.tags;
        result.functions = this.functions;

        return result;
    }

    @Override
    public String toString() {
        return "InterfaceDefinition{" +
                "type=" + typeName +
                ", name='" + name + '\'' +
                ", generics=" + generics +
                '}';
    }

    public boolean isAssignableFrom(InterfaceDefinition interfaceDefinition) {
        if (interfaceDefinition == null) {
            return false;
        }

        if (!this.module.name.equals(interfaceDefinition.module.name)) {
            return false;
        }

        if (!this.name.equals(interfaceDefinition.name)) {
            return false;
        }

        return true;
    }
}