package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmInstruction;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclaration;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A function in caffc.
 */
public class Function implements CompileBlock, Scope, Symbol {
    public AstItem owner;
    public FunctionDefinition definition = new FunctionDefinition();

    /**
     * Statements represent the actual instructions in AST format. They
     * will be later converted to AsmInstruction instances.
     */
    public List<Statement> statements = new ArrayList<>();

    /**
     * Instructions are what the code actually does, in an ASM-like format
     * (also known as linear form)
     */
    public List<AsmInstruction> instructions = new ArrayList<>();

    /**
     * These are normal variables the user has declared.
     */
    public Map<String, VariableDeclaration> _variables = new LinkedHashMap<>();

    private boolean isResolved;

    private ArrayList<VariableDeclaration> objVariablesCache;
    private ArrayList<Parameter> objParametersCache;
    private ArrayList<StructReturnVariableDefinition> objStructVariables;

    /**
     * The current number used when allocating labels. This is so we keep track of
     * AsmInstructions when serializing complex AST structures. It's easier to see
     * `if1:`, `else1:,` `endif1:` labels, to know they are coming from the same
     * instruction versus `if1:`, `else2:`, `endif3:`, especially when they would be
     * nested those numbers would be jumping all around.
     */
    public int labelIndex;

    public static Function fromAntlr(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.FunctionContext ctx) {
        Function function = new Function();

        function.definition.astFilePath = unit.astFilePath;
        function.definition.astLine = ctx.getStart().getLine();
        function.definition.astColumn = ctx.getStart().getCharPositionInLine();

        function.owner = owner;
        function.definition.module = unit.module.name;

        if (ctx.ID() == null) {
            CaffcCompiler.get().fatal(function, "null function ID");
        }

        // if the function has parameters, add them
        caffcParser.ParameterDefinitionsContext parameterDefinitions = ctx.parameterDefinitions();

        if (parameterDefinitions != null) {
            for (caffcParser.ParameterDefinitionContext parameter : parameterDefinitions.parameterDefinition()) {
                function.definition.parameters.add(Parameter.fromAntlr(unit, function.definition, parameter));
            }
        }

        // read the return values and add them as parameters if needed
        List<VariableDeclaration> variableDefinitions =
            function.definition.antlrFillReturnType(unit, function, ctx.returnType());
        for (VariableDeclaration variableDefinition: variableDefinitions) {
            function._variables.put(variableDefinition.name, variableDefinition);
        }

        function.definition.name = ctx.ID().getText();

        if (ctx.STATIC() != null) {
            function.definition.isStatic = true;
        }

        caffcParser.TagsContext antlrTags = ctx.tags();

        if (antlrTags != null) {
            for (caffcParser.TagContext antlrTag: antlrTags.tag()) {
                Tag tag = Tag.fromAntlr(unit, owner, antlrTag);
                function.definition.tags.put(tag.name, tag);
            }
        }

        if (owner instanceof Clazz clazz) {
            function.definition.clazz = clazz.definition;
        }

        if (function.definition.isStatic && function.definition.clazz == null) {
            CaffcCompiler.get().error(function.definition, String.format(
                    "static function '%s' is not a method of a class",
                    function.definition.name));
        }

        if (function.definition.clazz != null) {
            function.definition.owner = (AstItem) function.definition.clazz;
        } else {
            function.definition.owner = function.owner;
        }

        caffcParser.GenericsDeclarationsContext antlrGenerics = ctx.genericsDeclarations();
        if (antlrGenerics != null) {
            function.definition.generics = GenericDefinitions.fromAntlr(unit, function, antlrGenerics);
        }

        for (caffcParser.StatementContext antlrStatement: ctx.block().statement()) {
            function.statements.addAll(Statement.fromAntlr(unit, function, antlrStatement));
        }

        // we register the function in the module only if it's a global function, otherwise they
        // will get loaded from the class.
        if (function.definition.clazz == null) {
            unit.module.functions.put(function.definition.name, function.definition);
            unit.module.program.register(
                    TypeName.of(
                            unit.module.name,
                            function.definition.name,
                            function.definition.clazz != null ? function.definition.clazz.name() : null,
                            DataType.FUNCTION),
                    function.definition
            );
        }

        return function;
    }

    @Override
    public AstItem getOwner() {
        return owner;
    }

    /**
     * We need to see the values defined in this.
     */
    @Override
    public Symbol resolve(String name) {
        for (Parameter parameter: this.definition.parameters) {
            if (Objects.equals(parameter.name, name)) {
                return parameter;
            }
        }

        VariableDeclaration variableDeclaration = this._variables.get(name);
        if (variableDeclaration != null) {
            return variableDeclaration;
        }

        if (this.definition.generics != null) {
            return this.definition.generics.getByName(name);
        }

        return null;
    }

    public void registerVariable(VariableDeclaration variableDeclaration) {
        Symbol existing = this.resolve(variableDeclaration.name);

        if (existing != null) {
            CaffcCompiler.get().error(variableDeclaration,
                    "variable " + variableDeclaration.name + " shadows " +
                        Symbol.debugInfo(existing));
        }

        this._variables.put(variableDeclaration.name, variableDeclaration);
    }

    /**
     * Ensures a variable is defined in the variable scope with the given type. The variable
     * might be reused in a different context. For example `i32 i` in multiple `for` iterations.
     * Or a multi-return struct that will get reused for multiple invocations.
     * <p>
     * This will create a VariableDeclaration with the given name and type. If the type is
     * conflicting, an error will be raised.
     *
     * @return
     */
    public VariableDeclaration ensureVariableExists(AstItem owner, String name, Symbol resolvedType) {
        Symbol existing = this.resolve(name);

        if (existing != null && !(existing instanceof VariableDeclaration)) {
            CaffcCompiler.get().error(owner,
                String.format("conflicting types: %s with type %s attempts to shadow %s %s " +
                        "that's not a variable declaration",
                    name, resolvedType, Symbol.typeOfSymbol(existing), name));
        }

        VariableDeclaration existingVariable = (VariableDeclaration) existing;

        if (existingVariable != null &&
            !existingVariable.typeSymbol().equals(resolvedType.typeSymbol())) {
            CaffcCompiler.get().error(owner,
                "conflicting types for variable " + name +
                    ": existing " + existing + ", requested: " + resolvedType);
        }

        if (existingVariable != null) {
            return existingVariable;
        }

        existingVariable = VariableDeclaration.fromEnsure(owner, resolvedType, name);
        this._variables.put(name, existingVariable);

        // this is a synthetic variable created after the resolving is already done
        existingVariable.isResolved = true;

        return existingVariable;
    }

    @Override
    public String getFilePath() {
        return definition.astFilePath;
    }

    @Override
    public int getLineNumber() {
        return definition.astLine;
    }

    @Override
    public int getColumnNumber() {
        return definition.astColumn;
    }

    @Override
    public void recurseResolveTypes() {
        if (this.isResolved) {
            return;
        }

        this.isResolved = true;
        this.definition.recurseResolveTypes();

        for (VariableDeclaration variableDeclaration: this._variables.values()) {
            variableDeclaration.recurseResolveTypes();
        }

        for (Statement statement: this.statements) {
            statement.recurseResolveTypes();
        }
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
            codeRenderer.field("definition", definition);
            codeRenderer.field("statements", statements);
        });
    }

    @Override
    public String name() {
        return this.definition.name;
    }

    @Override
    public TypeName typeName() {
        return this.definition.typeName();
    }

    @Override
    public Symbol typeSymbol() {
        return this.definition.typeSymbol();
    }

    // used for templating, returns only the variables that are objects or arrays
    public Collection<VariableDeclaration> objVariables() {
        if (objVariablesCache != null) {
            return objVariablesCache;
        }

        objVariablesCache = new ArrayList<>();

        for (VariableDeclaration variableDeclaration: this._variables.values()) {
            if (variableDeclaration.typeName().dataType == DataType.OBJECT ||
                    variableDeclaration.typeName().dataType == DataType.ARRAY) {
                objVariablesCache.add(variableDeclaration);
            }
        }

        return objVariablesCache;
    }

    public Collection<Parameter> objParameters() {
        if (objParametersCache != null) {
            return objParametersCache;
        }

        objParametersCache = new ArrayList<>();

        for (Parameter parameter: this.definition.parameters) {
            if (parameter.typeSymbol.typeName().dataType == DataType.OBJECT ||
                    parameter.typeSymbol.typeName().dataType == DataType.ARRAY) {
                objParametersCache.add(parameter);
            }
        }

        return objParametersCache;
    }

    public Collection<StructReturnVariableDefinition> objStructVariables() {
        if (objStructVariables != null) {
            return objStructVariables;
        }

        objStructVariables = new ArrayList<>();

        for (VariableDeclaration variableDeclaration: this._variables.values()) {
            if (variableDeclaration.typeName().dataType == DataType.STRUCT) {
                Struct struct = (Struct) variableDeclaration.typeSymbol;
                for (Field field: struct.getGcManagedFields()) {
                    objStructVariables.add(new StructReturnVariableDefinition(
                        variableDeclaration,
                        field.name,
                        field.typeSymbol
                    ));
                }
            }
        }

        return objStructVariables;
    }


    @UsedInTemplate("ge_stack_frame_register.peb")
    public Collection<Object> objParametersAndVariables() {
        List<Object> result = new ArrayList<>();

        result.addAll(objParameters());
        result.addAll(objVariables());
        result.addAll(objStructVariables());

        return result;
    }

    @UsedInTemplate("ge_stack_frame_register.peb")
    public Collection<VariableDeclaration> variables() {
        return this._variables.values();
    }

    public int gcVariableCount() {
        return
            this.objVariables().size()
            + this.objParameters().size()
            + this.objStructVariables().size()
            ;
    }
}
