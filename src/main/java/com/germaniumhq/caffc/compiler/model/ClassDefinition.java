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
import com.germaniumhq.caffc.compiler.model.type.TypeAssignability;
import com.germaniumhq.caffc.compiler.model.type.TypeDefinitionSymbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a class definition in the symbol table, implementing both Symbol and Scope interfaces.
 * It holds information about the class's name, module, type, garbage collection field count,
 * functions, fields, and tags.
 */
public class ClassDefinition implements
    HasMethods,
    GenericsDefinitionsSymbol,
    Scope,
    HasSourceLocation,
    TypeDefinitionSymbol        // this symbol is present in the class definition array
{
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
    private int gcFieldsCount;

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
     * The numerical index that identifies this class in the full program. All
     * classes have their information kept into a class array. This would be
     * the index into the said class array.
     */
    private int typeId;

    /**
     * A flattened list of the implemented type ids for an object.
     */
    private TreeSet<TypeDefinitionSymbol> _implementedTypeIds;

    /**
     * Counts the number of fields that require garbage collection.
     *
     * @return The count of fields with data type OBJECT.
     */
    private int countGcFieldsCount() {
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
            // Keep generics so signature checks see Iterator<DictEntry<K,V>>, not raw Iterator.
            // concreteImplementations registration still walks to the canonical interface.
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

            // Resolve the module's canonical interface so its methods/fields exist; the
            // generics copy above may be a shallow instantiation that still shares them.
            InterfaceDefinition canonical = canonicalInterfaceDefinition(interfaceDefinition);
            canonical.recurseResolveTypes();

            // FIXME: these are internal deps of the module C implementation, probably they shouldn't
            //        be kept together with regular used modules.
            canonical.module.usedModules.add(module);
        }

        ClassDefinition.registerConcreteImplementations(this, this.implementedInterfaces);

        for (FunctionDefinition f: this.functions) {
            f.recurseResolveTypes();
        }

        // Multi-return methods that implement an interface must share the interface's
        // return struct type so virtual dispatch C signatures match.
        for (InterfaceDefinition interfaceDefinition : this.implementedInterfaces) {
            for (FunctionDefinition interfaceFn : interfaceDefinition.functions) {
                if (!(interfaceFn.returnType instanceof Struct)) {
                    continue;
                }
                FunctionDefinition classFn = this.getFunction(interfaceFn.name);
                if (classFn != null && classFn.returnType instanceof Struct) {
                    classFn.returnType = interfaceFn.returnType;
                }
            }
        }

        checkImplementedInterfaceMethods();

        for (Field field: this.fields) {
            field.recurseResolveTypes();
        }

        this.gcFieldsCount = this.countGcFieldsCount();
    }

    /**
     * Ensures every method from claimed interfaces (including parents) exists on this
     * class with a compatible signature.
     */
    private void checkImplementedInterfaceMethods() {
        Set<InterfaceDefinition> interfaces = new HashSet<>();
        Set<InterfaceDefinition> toProcess = new HashSet<>(this.implementedInterfaces);

        while (!toProcess.isEmpty()) {
            InterfaceDefinition current = toProcess.iterator().next();
            toProcess.remove(current);

            // Use the generics instantiation from `implements` (and parent copies), not the
            // canonical raw interface — otherwise Iterator<DictEntry<…>> is checked as Iterator
            // with T erased to obj.
            if (!interfaces.add(current)) {
                continue;
            }

            toProcess.addAll(current.implementedInterfaces);
        }

        Set<String> checkedMethods = new HashSet<>();

        for (InterfaceDefinition interfaceDefinition : interfaces) {
            for (FunctionDefinition interfaceFn : interfaceDefinition.functions) {
                if (!checkedMethods.add(interfaceFn.name)) {
                    continue;
                }

                FunctionDefinition classFn = this.getFunction(interfaceFn.name);
                if (classFn == null) {
                    CaffcCompiler.get().error(this.sourceLocation, String.format(
                            "class '%s' does not implement method '%s' required by interface '%s'",
                            this.name,
                            interfaceFn.name,
                            interfaceDefinition.name));
                    continue;
                }

                checkInterfaceMethodSignature(classFn, interfaceFn, interfaceDefinition);
            }
        }
    }

    private void checkInterfaceMethodSignature(
            FunctionDefinition classFn,
            FunctionDefinition interfaceFn,
            InterfaceDefinition interfaceDefinition) {
        int classStart = parameterStartIndex(classFn);
        int interfaceStart = parameterStartIndex(interfaceFn);
        int classParamCount = classFn.parameters.size() - classStart;
        int interfaceParamCount = interfaceFn.parameters.size() - interfaceStart;

        if (classParamCount != interfaceParamCount) {
            CaffcCompiler.get().error(classFn.sourceLocation, String.format(
                    "method '%s' in class '%s' has %d parameter(s) but interface '%s' requires %d",
                    classFn.name,
                    this.name,
                    classParamCount,
                    interfaceDefinition.name,
                    interfaceParamCount));
            return;
        }

        for (int i = 0; i < interfaceParamCount; i++) {
            Parameter classParam = classFn.parameters.get(classStart + i);
            Parameter interfaceParam = interfaceFn.parameters.get(interfaceStart + i);

            // Parameters must match for a valid implementation (invariant).
            if (!sameImplementationType(classParam.typeSymbol, interfaceParam.typeSymbol)) {
                CaffcCompiler.get().error(classFn.sourceLocation, String.format(
                        "method '%s' in class '%s': parameter '%s' has type '%s' but interface '%s' requires '%s'",
                        classFn.name,
                        this.name,
                        classParam.name,
                        TypeAssignability.describe(classParam.typeSymbol),
                        interfaceDefinition.name,
                        TypeAssignability.describe(interfaceParam.typeSymbol)));
            }
        }

        if (!sameImplementationType(interfaceFn.returnType, classFn.returnType)) {
            CaffcCompiler.get().error(classFn.sourceLocation, String.format(
                    "method '%s' in class '%s': return type '%s' does not match '%s' required by interface '%s'",
                    classFn.name,
                    this.name,
                    TypeAssignability.describe(classFn.returnType),
                    TypeAssignability.describe(interfaceFn.returnType),
                    interfaceDefinition.name));
        }
    }

    private static int parameterStartIndex(FunctionDefinition functionDefinition) {
        if (!functionDefinition.parameters.isEmpty() &&
                "_this".equals(functionDefinition.parameters.get(0).name)) {
            return 1;
        }
        return 0;
    }

    /**
     * Interface implementation parameter/return identity: same named type after
     * generics resolve (not the looser assignment conversions).
     */
    private static boolean sameImplementationType(Symbol classType, Symbol interfaceType) {
        if (classType == null || interfaceType == null) {
            return false;
        }

        TypeName className = classType.typeName();
        TypeName interfaceName = interfaceType.typeName();
        if (className == null || interfaceName == null) {
            return false;
        }

        return Objects.equals(className.module, interfaceName.module) &&
                Objects.equals(className.name, interfaceName.name) &&
                className.dataType == interfaceName.dataType;
    }

    /**
     * Registers {@code classDefinition} on every interface it implements, including
     * parents ({@code Dict} → {@code Collection} → {@code Iterable}).
     *
     * <p>Parent entries on an interface are often generics instantiations
     * ({@code Collection<DictEntry<K,V>>}, {@code Collection<T>}). Codegen emits dispatchers from
     * the module's canonical interface definitions, so registration must use those —
     * otherwise {@code Collection_size} stays empty while {@code Dict_get} works.
     */
    private static void registerConcreteImplementations(ClassDefinition classDefinition, List<InterfaceDefinition> implementedInterfaces) {
        Set<InterfaceDefinition> processedInterfaces = new HashSet<>();
        Set<InterfaceDefinition> toProcess = new HashSet<>(implementedInterfaces);

        while (!toProcess.isEmpty()) {
            InterfaceDefinition currentInterfaceDefinition = toProcess.iterator().next();
            toProcess.remove(currentInterfaceDefinition);

            InterfaceDefinition canonical = canonicalInterfaceDefinition(currentInterfaceDefinition);

            if (processedInterfaces.contains(canonical)) {
                continue;
            }

            processedInterfaces.add(canonical);

            toProcess.addAll(canonical.implementedInterfaces);
            toProcess.removeAll(processedInterfaces);

            canonical.concreteImplementations.add(classDefinition);
        }
    }

    /**
     * Returns the module-scoped interface definition used for codegen, not a
     * generics instantiation copy created during {@code extends} resolve.
     */
    private static InterfaceDefinition canonicalInterfaceDefinition(InterfaceDefinition iface) {
        if (iface.module != null && iface.name != null) {
            InterfaceDefinition fromModule = iface.module.interfaces.get(iface.name);
            if (fromModule != null) {
                return fromModule;
            }
        }
        return iface;
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

        for (InterfaceDefinition implementedInterface : implementedInterfaces) {
            copy.implementedInterfaces.add(implementedInterface.newGenericsCopy(resolvedGenerics));
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
        for (TypeDefinitionSymbol implementedType : getImplementedTypes()) {
            if (implementedType instanceof InterfaceDefinition interfaceDefinition &&
                    definition.isAssignableFrom(interfaceDefinition)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return this.sourceLocation;
    }

    @Override
    public int typeId() {
        return this.typeId;
    }

    @Override
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public int getGcFieldsCount() {
        return this.gcFieldsCount;
    }

    @Override
    public TreeSet<TypeDefinitionSymbol> getImplementedTypes() {
        if (this._implementedTypeIds != null) {
            return this._implementedTypeIds;
        }

        this._implementedTypeIds = new TreeSet<>((o1, o2) -> {
            return o1.typeId() - o2.typeId();
        });

        for (InterfaceDefinition interfaceDefinition : implementedInterfaces) {
            this._implementedTypeIds.add(interfaceDefinition);
            this._implementedTypeIds.addAll(interfaceDefinition.getImplementedTypes());
        }

        return this._implementedTypeIds;
    }
}
