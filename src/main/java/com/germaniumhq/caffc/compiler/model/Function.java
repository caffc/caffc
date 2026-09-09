package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmInstruction;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmReturn;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmZeroClear;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.expression.LocalVariable;
import com.germaniumhq.caffc.compiler.model.instruction.ExceptionHandler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
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
public class Function implements CompileBlock, Scope, Statement, Symbol, ExceptionHandler {
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
    public Map<String, LocalVariable> _variables = new LinkedHashMap<>();

    private boolean isResolved;

    private ArrayList<LocalVariable> objVariablesCache;
    private ArrayList<Parameter> objParametersCache;
    private ArrayList<StructReturnVariableDefinition> objStructVariables;

    public StringConstant stringConstantName;

    /**
     * The current number used when allocating labels. This is so we keep track of
     * AsmInstructions when serializing complex AST structures. It's easier to see
     * `if1:`, `else1:,` `endif1:` labels, to know they are coming from the same
     * instruction versus `if1:`, `else2:`, `endif3:`, especially when they would be
     * nested those numbers would be jumping all around.
     */
    public int labelIndex;

    /**
     * We keep these source locations to have a place where to anchor the cursor
     * when the instructions we're stepping over are defined in the GC wiring.
     */
    public SourceLocation sourceLocationCurlyOpen;
    public SourceLocation sourceLocationCurlyClose;

    /**
     * AsmLabel to jump to if there's an exception thrown, and we need to do a fast return
     */
    private AsmLabel uncaughtExceptionLabel;

    public static Function fromAntlr(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.FunctionContext ctx) {
        Function function = new Function();

        // for the function location, we get the name, so we jump over potential tags or decorators attached
        function.definition.sourceLocation = SourceLocation.fromAntlrToken(
            unit.sourceLocation.filePath, ctx.ID().getSymbol());

        function.sourceLocationCurlyOpen = SourceLocation.fromAntlrToken(
            unit.sourceLocation.filePath,
            ctx.functionBlock().CURLY_OPEN().getSymbol()
        );
        function.sourceLocationCurlyClose = SourceLocation.fromAntlrToken(
            unit.sourceLocation.filePath,
            ctx.functionBlock().CURLY_CLOSE().getSymbol()
        );

        function.owner = owner;
        function.definition.module = unit.module.name;

      // if the function has parameters, add them
        caffcParser.ParameterDefinitionsContext parameterDefinitions = ctx.parameterDefinitions();

        if (parameterDefinitions != null) {
            for (caffcParser.ParameterDefinitionContext parameter : parameterDefinitions.parameterDefinition()) {
                function.definition.parameters.add(Parameter.fromAntlr(unit, function.definition, parameter));
            }
        }

        // read the return values and add them as parameters if needed
        List<LocalVariable> variableDefinitions =
            function.definition.antlrFillReturnType(unit, function, ctx.returnType());
        for (LocalVariable variableDefinition: variableDefinitions) {
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

        for (caffcParser.FunctionBodyItemContext bodyItem : ctx.functionBlock().functionBodyItem()) {
            if (bodyItem.sharpSwitchMethod() != null) {
                function.statements.addAll(
                        SharpSwitch.expandMethod(unit, function, bodyItem.sharpSwitchMethod()));
            } else if (bodyItem.sharpIfdefMethod() != null) {
                function.statements.addAll(
                        SharpIfdef.expandMethod(unit, function, bodyItem.sharpIfdefMethod()));
            } else {
                function.statements.addAll(Statement.fromAntlr(unit, function, bodyItem.statement()));
            }
        }

        // we register the function in the module only if it's a true global function, otherwise they
        // will get loaded from the class.
        if (function.definition.clazz == null && !Module.UNIT_INIT.equals(function.definition.name)) {
            unit.module.functions.put(function.definition.name, function.definition);
        }

        function.stringConstantName = StringConstant.newStringConstant(function.getSourceLocation(), function.definition.name);
        function.findAstParent(Module.class).registerConstant(function.stringConstantName);

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

        if (this.definition.generics != null) {
            Symbol generic = this.definition.generics.getByName(name);
            if (generic != null) {
                return generic;
            }
        }

        LocalVariable localVariable = this._variables.get(name);
        if (localVariable != null) {
            return localVariable;
        }

        return null;
    }

public void registerVariable(LocalVariable localVariable) {
        Symbol existing = this.resolve(localVariable.name);

        if (existing != null) {
            CaffcCompiler.get().error(localVariable,
                    "variable " + localVariable.name + " shadows " +
                        Symbol.debugInfo(existing));
        }

        this._variables.put(localVariable.name, localVariable);
    }

    /**
     * Ensures a variable is defined in the variable scope with the given type. The variable
     * might be reused in a different context. For example `i32 i` in multiple `for` iterations.
     * Or a multi-return struct that will get reused for multiple invocations.
     * <p>
     * This will create a LocalVariable with the given name and type. If the type is
     * conflicting, an error will be raised.
     *
     * @return
     */
    public LocalVariable ensureVariableExists(AstItem owner, String name, Symbol resolvedType) {
        Symbol existing = this.resolve(name);

        if (existing != null && !(existing instanceof LocalVariable)) {
            CaffcCompiler.get().error(owner,
                String.format("conflicting types: %s with type %s attempts to shadow %s %s " +
                        "that's not a variable declaration",
                    name, resolvedType, Symbol.typeOfSymbol(existing), name));
        }

        LocalVariable existingVariable = (LocalVariable) existing;

        if (existingVariable != null &&
            !existingVariable.typeSymbol().equals(resolvedType.typeSymbol())) {
            CaffcCompiler.get().error(owner,
                "conflicting types for variable " + name +
                    ": existing " + existing + ", requested: " + resolvedType);
        }

        if (existingVariable != null) {
            return existingVariable;
        }

        existingVariable = LocalVariable.fromEnsure(owner, resolvedType, name);
        this._variables.put(name, existingVariable);

        // this is a synthetic variable created after the resolving is already done
        existingVariable.isResolved = true;

        return existingVariable;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return definition.sourceLocation;
    }

@Override
    public void recurseResolveTypes() {
        if (this.isResolved) {
            return;
        }

        this.isResolved = true;
        this.definition.recurseResolveTypes();

        // Check for type name collisions BEFORE resolving variable types,
        // because resolving may fail if a variable shadows its type name
        checkVariableNameCollisions();

        for (LocalVariable localVariable : this._variables.values()) {
            localVariable.recurseResolveTypes();
        }

        for (Statement statement: this.statements) {
            statement.recurseResolveTypes();
        }
    }

  private void checkVariableNameCollisions() {
        // Only check within the same module - cross-module names don't conflict
        Module ownModule = Program.get().modules.get(this.definition.module);
        if (ownModule == null) return;

        // Check parameters for name collisions within the same module
        for (Parameter parameter : this.definition.parameters) {
            Symbol collision = ownModule.resolveWithAnyName(parameter.name);
            if (collision != null) {
                CaffcCompiler.get().fatal(parameter,
                        "parameter " + parameter.name + " shadows " +
                            Symbol.typeOfSymbol(collision) + " " + collision.name() +
                            " defined at " + CaffcCompiler.fileLocation(collision));
            }
        }

        // Check local variables for name collisions within the same module
        for (LocalVariable localVariable : this._variables.values()) {
            Symbol collision = ownModule.resolveWithAnyName(localVariable.name);
            if (collision != null) {
                CaffcCompiler.get().fatal(localVariable,
                        "variable " + localVariable.name + " shadows " +
                            Symbol.typeOfSymbol(collision) + " " + collision.name() +
                            " defined at " + CaffcCompiler.fileLocation(collision));
            }
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
    public Collection<LocalVariable> objVariables() {
        if (objVariablesCache != null) {
            return objVariablesCache;
        }

        objVariablesCache = new ArrayList<>();

        for (LocalVariable localVariable : this._variables.values()) {
            if (localVariable.typeName().dataType == DataType.OBJECT ||
                    localVariable.typeName().dataType == DataType.ARRAY) {
                objVariablesCache.add(localVariable);
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

        for (LocalVariable localVariable : this._variables.values()) {
            if (localVariable.typeName().dataType == DataType.STRUCT) {
                Struct struct = (Struct) localVariable.typeSymbol;
                for (Field field: struct.getGcManagedFields()) {
                    objStructVariables.add(new StructReturnVariableDefinition(
                        localVariable,
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
    public Collection<LocalVariable> variables() {
        return this._variables.values();
    }

    public int gcVariableCount() {
        return
            this.objVariables().size()
            + this.objParameters().size()
            + this.objStructVariables().size()
            ;
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        int index = AsmLabel.allocateNumber(this);
        this.uncaughtExceptionLabel = new AsmLabel(this.getSourceLocation(), "fnUncaughtException", index);

        for (Statement statement: this.statements) {
            result.instructions.addAll(statement.asLinearForm(block).instructions);
        }

        result.instructions.add(this.uncaughtExceptionLabel);

        // value will be ignored at this stage
        if (TypeName.VOID.equals(this.definition.returnType.typeName())) {
            result.instructions.add(new AsmReturn(null, this, null));
        } else if (this.definition.isMultiReturn()) {
            AsmVar structVar = block.addTempVar(this, this.definition.returnType);
            result.instructions.add(new AsmZeroClear(null, structVar));
            result.instructions.add(new AsmReturn(null, this, structVar));
        } else {
            AsmValue defaultValue = null;
            if (this.definition.returnType.typeName().dataType != DataType.PRIMITIVE) {
                defaultValue = AsmConstant.NULL;
            } else {
                defaultValue = new AsmConstant(null, "0");
            }
            result.instructions.add(new AsmReturn(null, this, defaultValue));
        }

        return result;
    }

    @Override
    public AsmLabel getExceptionHandlingTargetLabel() {
        return uncaughtExceptionLabel;
    }
}
