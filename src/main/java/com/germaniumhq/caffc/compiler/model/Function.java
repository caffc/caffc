package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclaration;
import com.germaniumhq.caffc.compiler.model.type.*;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.*;

/**
 * A function in caffc.
 */
public class Function implements CompileBlock, Scope, Symbol {
    public AstItem owner;
    public FunctionDefinition definition = new FunctionDefinition();
    public List<Statement> statements = new ArrayList<>();
    public Map<String, VariableDeclaration> _variables = new LinkedHashMap<>();

    private boolean isResolved;

    private ArrayList<VariableDeclaration> objVariablesCache;
    private ArrayList<Parameter> objParametersCache;

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
        function.definition.returnTypeSearch = SymbolSearch.fromAntlr(unit, ctx.returnType());
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

        // if the function has parameters, add them
        caffcParser.ParameterDefinitionsContext parameterDefinitions = ctx.parameterDefinitions();

        if (parameterDefinitions != null) {
            for (caffcParser.ParameterDefinitionContext parameter : parameterDefinitions.parameterDefinition()) {
                function.definition.parameters.add(Parameter.fromAntlr(unit, function.definition, parameter));
            }
        }

        for (caffcParser.StatementContext antlrStatement: ctx.block().statement()) {
            function.statements.add(Statement.fromAntlr(unit, function, antlrStatement));
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
        // FIXME: registering _anything_ should check if it shadows things from the outside context
        Symbol existing = this.resolve(variableDeclaration.name);

        if (existing != null) {
            CaffcCompiler.get().error(variableDeclaration,
                    "variable " + variableDeclaration.name + " shadows " + existing);
        }

        this._variables.put(variableDeclaration.name, variableDeclaration);
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

        for (Statement statement: this.statements) {
            statement.recurseResolveTypes();
        }
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
            if (variableDeclaration.owner.typeSymbol.typeName().dataType == DataType.OBJECT ||
                    variableDeclaration.owner.typeSymbol.typeName().dataType == DataType.ARRAY) {
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

    public Collection<VariableDeclaration> variables() {
        return this._variables.values();
    }

    public int gcVariableCount() {
        return this.objVariables().size() + this.objParameters().size();
    }
}
