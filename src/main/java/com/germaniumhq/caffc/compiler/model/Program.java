package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeDefinitionSymbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * A program contains a reference to all the modules loaded. Note that a program
 * must not be fully loaded for the compiler to work, but only the desired
 * modules. At some point, the module definitions should be cached locally,
 * we don't need to always parse caffc code to know the definitions of things.
 */
public class Program implements ModuleProvider, AstItem, Scope {
    public static Program INSTANCE = new Program();

    public Map<String, Module> modules = new HashMap<>();

    /**
     * Contains all the registered types of the programs. The reason for that is that they
     * need to have allocated Type IDs. The Type IDs are what CaffC uses internally to
     * track classes, and see if a class implements an interface.
     */
    public Map<TypeName, TypeDefinitionSymbol> registeredSymbols = new HashMap<>();

    public SourceLocation sourceLocation = new SourceLocation("program", 0, 0);

    // Keeps track where something is defined. We have two kind of mappings kept in sync:
    //
    // name -> file.location:line
    //
    // and
    //
    // file.location -> [names]
    //
    // The rationale is that when we add a new function we can see if we already have it defined.
    // If we do, we can report to the user the locations of where this happens and report this
    // as errors.
    //
    // If a file is being removed, or recompiled, we can clear up the nameToLocation cache
    // faster.
    public Map<String, String> nameToLocation = new HashMap<>(); // name -> file.location:line
    public Map<String, Set<String>> locationToNames = new HashMap<>(); // file.location -> [names]

    private Map<TypeName, Symbol> primitiveSymbols = new HashMap<>();

    private Set<StringConstant> stringConstants = new LinkedHashSet<>();

    /**
     * Counter for the type ids. Whenever a new class/interface is registered, this number gets incremented
     * by one. This will mark the index of the class in our _caffc_types array.
     */
    private int lastTypeId;

    private Program() {
        for (TypeName primitiveTypeName : TypeName.PRIMITIVE_TYPES) {
            primitiveSymbols.put(primitiveTypeName, new TypeSymbol(primitiveTypeName));
        }
    }

    @Override
    public Module getModule(String moduleName) {
        Module result = modules.computeIfAbsent(moduleName, (s) -> new Module(moduleName));
        result.program = this;

        return result;
    }

    /**
     * The program is the toplevel object.
     * @return
     */
    @Override
    public AstItem getOwner() {
        return null;
    }

    /**
     * Register a definition of a type to a specific symbol. A symbol is a link to
     * the full _definition_ of the given type. For now a ClassDefinition, or an
     * InterfaceDefinition.
     *
     * @param typeName
     * @param item
     */
    public void register(TypeName typeName, TypeDefinitionSymbol item) {
        if (registeredSymbols.containsKey(typeName)) {
            // it's a problem if we register it twice, since the type_id generation is going to get borked
            throw new IllegalStateException(String.format("Type %s is already registered", typeName));
        }

        item.setTypeId(this.nextTypeId());

        this.registeredSymbols.put(typeName, item);
    }

    @UsedInTemplate("constants_c.peb")
    public List<TypeDefinitionSymbol> getTypeDefinitionSymbols() {
        List<TypeDefinitionSymbol> result = new ArrayList<>();
        result.addAll(registeredSymbols.values());
        result.sort((o1, o2) -> o1.typeId() - o2.typeId());

        return result;
    }

    public int nextTypeId() {
        return lastTypeId++;
    }

    public TypeDefinitionSymbol getTypeDefinition(TypeName typeName) {
        return registeredSymbols.get(typeName);
    }

    /**
     * Resolve all the type requests
     */
    public void recreateConstants() {
        // empty now, since string constants are already created as constants by each module
        // to ensure parsing is correct.
    }

    @UsedInTemplate("constants.h")
    public Collection<Integer> strStructBytesSizes() {
        TreeSet<Integer> sizes = new TreeSet<>();

        for (StringConstant stringConstant: stringConstants) {
            sizes.add(stringConstant.bytesSize);
        }

        return sizes;
    }

    @Override
    public Symbol resolve(String name) {
        if (modules.containsKey(name)) {
            return modules.get(name);
        }

        TypeName primitive = TypeName.primitiveType(name);
        if (primitive != null) {
            return primitiveSymbols.get(primitive);
        }

        return null;
    }

    public String constantName(StringConstant stringConstant) {
        return stringConstant.name;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    @Override
    public void recurseResolveTypes() {
        throw new IllegalStateException("BUG: recurse resolve types can only start at compilation unit level");
    }

    /**
     * The currently compiled program instance. In case we're running integration tests, we're going
     * to reset this.
     * @return
     */
    public static Program get() {
        return INSTANCE;
    }

    /**
     * When running test, we want a clean new instance of the program.
     * @return
     */
    public static Program reset() {
        INSTANCE = new Program();

        return INSTANCE;
    }

    public Set<StringConstant> getStringConstants() {
        return stringConstants;
    }

    public void addStringConstant(StringConstant stringConstant) {
        this.stringConstants.add(stringConstant);
    }
}
