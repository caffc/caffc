package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.instruction.InitUnitBlock;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.output.filters.FilterCTypeName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * All the things that exist in a module are defined here.
 * This way we can create the forward definitions when generating
 * header C files for the modules.
 */
public class Module implements AstItem, Scope, Symbol {
    public Program program;
    public String name;

    public Map<String, FunctionDefinition> functions = new LinkedHashMap<>();
    public Map<String, ClassDefinition> clazzes = new LinkedHashMap<>();
    public Map<String, InterfaceDefinition> interfaces = new LinkedHashMap<>();
    public Map<String, Struct> structures = new LinkedHashMap<>();
    public Map<String, GlobalVariable> globalVariables = new LinkedHashMap<>();
    public Set<Module> usedModules = new LinkedHashSet<>();
    public Set<StringConstant> stringConstants = new LinkedHashSet<>();

    public Map<TypeName, Integer> moduleArrays = new LinkedHashMap<>();

    public SourceLocation sourceLocation = new SourceLocation("", 0, 0);

    public Module(String moduleName) {
        this.name = moduleName;
    }

    /**
     * Ensures the `init_module` function exists, and initializes all
     * the global variables. If a function already exists for this module,
     * this function will be reused.
     *
     * The GlobalVariables must be moved _after_ the `recurseResolveTypes`.
     * The rationale is the following, if we have a compilation unit such as:
     *
     * module main
     * use my_users as user
     * user.User x = user.defaultUser("root") // this is global
     *
     * Resolving `user` is actually bound to the compilation unit.
     *
     * The synthetic compilation unit that we create, must have all the
     * `usedModules`
     *
     * If there's already an `init_module` function in the current module,
     * the GlobalVariable statements will be prepended. If not, a custom
     * fake compilation unit will be created.
     *
     * Each compilation unit may also define an optional {@code init_unit()}
     * function. Those bodies are appended after the original {@code init_module}
     * code (globals, then user {@code init_module}, then each {@code init_unit}),
     * and the {@code init_unit} functions themselves are deleted.
     */
    public static void createInitModule(Module module, Set<CompilationUnit> compilationUnits) {
        List<GlobalVariable> globalVariables = new ArrayList<>();
        List<Function> initUnits = new ArrayList<>();

        // we don't care about the `use` statements anymore of the module, since
        // the compilation units are already resolved, and each compilation unit
        // when generated #includes the module header, that in turn has all deps
        // correctly included
        // we need to find:
        // 1. the global vars we have to init in the init_module()
        // 2. the init_unit() functions we need to inline in the init_module()
        for (CompilationUnit compilationUnit: compilationUnits) {
            Function initUnit = null;
            for (CompileBlock compileBlock: compilationUnit.compileBlocks) {
                // 1. global vars
                if (compileBlock instanceof GlobalVariableDeclarations globalVariable) {
                    globalVariables.add(globalVariable.variable);
                }

                // 2. init_unit() calls
                if (compileBlock instanceof Function function &&
                        "init_unit".equals(function.name()) &&
                        function.definition.clazz == null) {
                    if (initUnit != null) {
                        CaffcCompiler.get().fatal(function,
                                "compilation unit already has an init_unit() function");
                    }

                    initUnit = function;
                    initUnits.add(initUnit);
                }
            }
        }

        if (globalVariables.isEmpty() && initUnits.isEmpty()) {
            // we don't need to augment/create the `init_module()` since we have
            // no globals and no init_unit functions
            return;
        }

        Function initModuleFunction = getOrCreateInitModuleFunction(module, compilationUnits);

        // we need to reparent the global variables to the `init_module` function.
        // the reason is for try/catch blocks, so exceptions hook in the init_module's
        // unhandled exception label
        for (GlobalVariable globalVariable: globalVariables) {
            globalVariable.owner = initModuleFunction;
        }

        // prepend the global variables, keep existing init_module body, then
        // append each init_unit body
        List<Statement> statements = new ArrayList<>(globalVariables);
        statements.addAll(initModuleFunction.statements);

        for (Function initUnit: initUnits) {
            validateInitUnitSignature(initUnit);
            statements.add(InitUnitBlock.fromInitUnit(initModuleFunction, initUnit));

            CompilationUnit unit = (CompilationUnit) initUnit.owner;
            unit.compileBlocks.remove(initUnit);
        }

        module.functions.remove("init_unit");
        initModuleFunction.statements = statements;
    }

    private static void validateInitUnitSignature(Function initUnit) {
        if (!initUnit.definition.parameters.isEmpty()) {
            CaffcCompiler.get().fatal(initUnit,
                    "init_unit() cannot have parameters; it is inlined into init_module");
        }

        if (!initUnit.definition.isVoid()) {
            CaffcCompiler.get().fatal(initUnit,
                    "init_unit() cannot return a value; it is inlined into init_module");
        }
    }

    private static Function getOrCreateInitModuleFunction(
            Module module, Set<CompilationUnit> compilationUnits) {
        // search for an existing `init_module` function
        for (CompilationUnit compilationUnit: compilationUnits) {
            if (compilationUnit.module != module) {
                continue;
            }

            for (CompileBlock compileBlock: compilationUnit.compileBlocks) {
                if (compileBlock instanceof Function function) {
                    if ("init_module".equals(function.name())) {
                        return function;
                    }
                }
            }
        }

        // we don't have an existing `init_module`, we need to create a
        // synthetic one
        CompilationUnit compilationUnit = new CompilationUnit();
        compilationUnit.module = module;
        compilationUnit.isResolved = true;
        compilationUnit.sourceLocation = SourceLocation.fromFilePath(
            FilterCTypeName.getCType(module.typeName()) +
            "init_module.caffc");
        compilationUnits.add(compilationUnit);

        Function initModuleFunction = new Function();
        initModuleFunction.owner = compilationUnit;
        initModuleFunction.definition.name = "init_module";
        initModuleFunction.definition.module = module.name;

        compilationUnit.compileBlocks.add(initModuleFunction);
        module.functions.put(
            initModuleFunction.definition.name,
            initModuleFunction.definition);

        initModuleFunction.stringConstantName = StringConstant.newStringConstant(
            initModuleFunction.getSourceLocation(), initModuleFunction.definition.name);
        module.registerConstant(initModuleFunction.stringConstantName);

        return initModuleFunction;
    }

    public Collection<FunctionDefinition> functionDefinitions() {
        return functions.values();
    }

    public Object getByName(String name) {
        return functions.get(name);
    }

    public boolean hasFunction(String name) {
        return functions.containsKey(name);
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

        if (globalVariables.containsKey(name)) {
            return globalVariables.get(name);
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

    public void registerConstant(StringConstant constant) {
        this.stringConstants.add(constant);
    }

 /**
      * Checks if the given name collides with a type name (class, interface, or struct)
      * defined in this module. Returns the first colliding type symbol found, or null if no collision.
      */
     public Symbol resolveWithType(String name) {
         if (clazzes.containsKey(name)) {
             return clazzes.get(name);
         }
         if (interfaces.containsKey(name)) {
             return interfaces.get(name);
         }
         if (structures.containsKey(name)) {
             return structures.get(name);
         }
         return null;
     }

    /**
     * Checks if the given name collides with any symbol (type or function)
     * defined in this module. Returns the first colliding symbol found, or null if no collision.
     */
    public Symbol resolveWithAnyName(String name) {
        if (clazzes.containsKey(name)) {
            return clazzes.get(name);
        }
        if (interfaces.containsKey(name)) {
            return interfaces.get(name);
        }
        if (structures.containsKey(name)) {
            return structures.get(name);
        }
        if (functions.containsKey(name)) {
            return functions.get(name);
        }
        if (globalVariables.containsKey(name)) {
            return globalVariables.get(name);
        }
        return null;
    }

    /**
     * Checks if the given name collides with a type name defined in any registered module.
     * Returns the first colliding type symbol found, or null if no collision.
     */
    public static Symbol resolveWithTypeAllModules(String name) {
        Program program = Program.get();
        if (program == null) {
            return null;
        }

        for (Module module : program.modules.values()) {
            Symbol collision = module.resolveWithType(name);
            if (collision != null) {
                return collision;
            }
        }
        return null;
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

    public void registerGlobalVariable(GlobalVariable globalVariable) {
        this.globalVariables.compute(globalVariable.name, (key, oldValue) -> {
            if (oldValue != null) {
                CaffcCompiler.get().fatal(globalVariable,
                    "global variable " + globalVariable.name + " is already declared");
                return oldValue;
            }

            return globalVariable;
        });
    }
}
