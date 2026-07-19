package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.output.filters.FilterCTypeName;

import java.util.ArrayList;
import java.util.Collection;
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
     * Ensures the `module_init` function exists, and initializes all
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
     * If there's already a `module_init` function in the current module,
     * the GlobalVariable statements will be prepended. If not, a custom
     * fake compilation unit will be created.
     */
    public static void createModuleInit(Module module, Set<CompilationUnit> compilationUnits) {
        List<GlobalVariable> globalVariables = new ArrayList<>();

        // we find all the variables to see if there's anything to be done
        for (CompilationUnit compilationUnit: compilationUnits) {
            // FIXME: Create a map? String -> List<CompilationUnit>
            if (compilationUnit.module != module) {
                continue;
            }

            // we don't care about the `use` statements anymore of the module, since
            // the compilation units are already resolved, and each compilation unit
            // when generated #includes the module header, that in turn has all deps
            // correctly included
            for (CompileBlock compileBlock: compilationUnit.compileBlocks) {
                if (compileBlock instanceof GlobalVariableDeclarations globalVariable) {
                    globalVariables.add(globalVariable.variable);
                }
            }
        }

        if (globalVariables.isEmpty()) {
            // we don't need to augment/create the `module_init()` since we have no globals
            return;
        }

        Function moduleInitFunction = getOrCreateModuleInitFunction(module, compilationUnits);

        // we need to reparent the global variables to the `module_init` function.
        // the reason is for try/catch blocks, so exceptions hook in the module_init's
        // unhandled exception label
        for (GlobalVariable globalVariable: globalVariables) {
            globalVariable.owner = moduleInitFunction;
        }

        // prepend the global variables
        List<Statement> statements = new ArrayList<>(globalVariables);
        statements.addAll(moduleInitFunction.statements);
        moduleInitFunction.statements = statements;
    }

    private static Function getOrCreateModuleInitFunction(
            Module module, Set<CompilationUnit> compilationUnits) {
        // search for an existing `module_init` function
        for (CompilationUnit compilationUnit: compilationUnits) {
            if (compilationUnit.module != module) {
                continue;
            }

            for (CompileBlock compileBlock: compilationUnit.compileBlocks) {
                if (compileBlock instanceof Function function) {
                    if ("module_init".equals(function.name())) {
                        return function;
                    }
                }
            }
        }

        // we don't have an existing `module_init`, we need to create a
        // synthetic one
        CompilationUnit compilationUnit = new CompilationUnit();
        compilationUnit.module = module;
        compilationUnit.isResolved = true;
        compilationUnit.sourceLocation = SourceLocation.fromFilePath(
            FilterCTypeName.getCType(module.typeName()) +
            "module_init.caffc");
        compilationUnits.add(compilationUnit);

        Function moduleInitFunction = new Function();
        moduleInitFunction.owner = compilationUnit;
        moduleInitFunction.definition.name = "module_init";
        moduleInitFunction.definition.module = module.name;

        compilationUnit.compileBlocks.add(moduleInitFunction);
        module.functions.put(
            moduleInitFunction.definition.name,
            moduleInitFunction.definition);

        moduleInitFunction.stringConstantName = StringConstant.newStringConstant(
            moduleInitFunction.getSourceLocation(), moduleInitFunction.definition.name);
        module.registerConstant(moduleInitFunction.stringConstantName);

        return moduleInitFunction;
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
