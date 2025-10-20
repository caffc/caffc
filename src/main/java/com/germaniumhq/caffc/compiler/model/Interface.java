package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

public class Interface implements CompileBlock, AstItem, Scope {
    public InterfaceDefinition definition = new InterfaceDefinition();
    public AstItem owner;

    private boolean isResolved;

    public static Interface fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.InterfaceDefinitionContext antlrInterfaceDefinition) {
        Interface _interface = new Interface();

        _interface.definition.astFilePath = unit.astFilePath;
        _interface.definition.astLine = antlrInterfaceDefinition.getStart().getLine();
        _interface.definition.astColumn = antlrInterfaceDefinition.getStart().getCharPositionInLine();

        _interface.owner = unit;

        _interface.definition.module = unit.module;
        _interface.definition.name = antlrInterfaceDefinition.name().getText();
        _interface.definition.typeName = TypeName.of(_interface.definition.module.name, _interface.definition.name, null, DataType.OBJECT);
        
        caffcParser.TagsContext antlrTags = antlrInterfaceDefinition.tags();
        if (antlrTags != null) {
            for (caffcParser.TagContext antlrTag: antlrTags.tag()) {
                Tag tag = Tag.fromAntlr(unit, owner, antlrTag);
                _interface.definition.tags.put(tag.name, tag);
            }
        }

        caffcParser.GenericsDeclarationsContext antlrGenerics = antlrInterfaceDefinition.genericsDeclarations();
        if (antlrGenerics != null) {
            _interface.definition.generics = GenericDefinitions.fromAntlr(unit, _interface, antlrGenerics);
        }

        if (antlrInterfaceDefinition.interfaceImplementations() != null) {
            for (caffcParser.InterfaceImplementationContext interfaceImplementationContext:
                    antlrInterfaceDefinition.interfaceImplementations().interfaceImplementation()) {
                _interface.definition.implementedInterfacesSearch.add(SymbolSearch.fromAntlr(unit, interfaceImplementationContext));
            }
        }

        if (antlrInterfaceDefinition.interfaceStatements() != null) {
            for (caffcParser.FunctionDeclarationContext antlrFunction: antlrInterfaceDefinition.interfaceStatements().functionDeclaration()) {
                FunctionDefinition functionDefinition = fromInterfaceDefinition(unit, _interface, antlrFunction);
                functionDefinition.clazz = _interface.definition;

                // we syntheticaly add the `_this` parameter to the function pointing to the class.
                Parameter thisParameter = new Parameter(functionDefinition, "_this");
                thisParameter.astFilePath = functionDefinition.getFilePath(); // we set the function coordinates
                thisParameter.astLine = functionDefinition.getLineNumber();
                thisParameter.astColumn = functionDefinition.getColumnNumber();

                functionDefinition.parameters.add(0, thisParameter);
                // FIXME: check for existing `_this` parameters.

                SymbolSearch.uniqueSymbolNameCheck(_interface, functionDefinition.name, functionDefinition);

                _interface.definition.functions.add(functionDefinition);
            }
        }


        // register the interface into the module
        unit.module.interfaces.put(_interface.definition.name, _interface.definition);
        unit.module.program.register(
                _interface.definition.typeName,
                _interface.definition
        );

        return _interface;
    }

    static FunctionDefinition fromInterfaceDefinition(
            CompilationUnit unit,
            Interface owner,
            caffcParser.FunctionDeclarationContext ctx) {
        FunctionDefinition functionDefinition = new FunctionDefinition();

        functionDefinition.astFilePath = unit.astFilePath;
        functionDefinition.astLine = ctx.getStart().getLine();
        functionDefinition.astColumn = ctx.getStart().getCharPositionInLine();

        functionDefinition.owner = owner;
        functionDefinition.clazz = owner.definition;
        functionDefinition.module = unit.module.name;
        functionDefinition.antlrFillReturnType(unit, owner, ctx.returnType());
        functionDefinition.name = ctx.ID().getText();

        caffcParser.TagsContext antlrTags = ctx.tags();

        if (antlrTags != null) {
            for (caffcParser.TagContext antlrTag: antlrTags.tag()) {
                Tag tag = Tag.fromAntlr(unit, owner, antlrTag);
                functionDefinition.tags.put(tag.name, tag);
            }
        }

        caffcParser.GenericsDeclarationsContext antlrGenerics = ctx.genericsDeclarations();
        if (antlrGenerics != null) {
            functionDefinition.generics = GenericDefinitions.fromAntlr(unit, functionDefinition, antlrGenerics);
        }

        // if the functionDefinition has parameters, add them
        caffcParser.ParameterDefinitionsContext parameterDefinitionsContext = ctx.parameterDefinitions();

        if (parameterDefinitionsContext != null) {
            for (caffcParser.ParameterDefinitionContext parameter: parameterDefinitionsContext.parameterDefinition()) {
                functionDefinition.parameters.add(Parameter.fromAntlr(unit, functionDefinition, parameter));
            }
        }

        return functionDefinition;
    }

    @Override
    public AstItem getOwner() {
        return owner;
    }

    @Override
    public Symbol resolve(String name) {
        return this.definition.resolve(name);
    }

    @Override
    public String getFilePath() {
        return this.definition.astFilePath;
    }

    @Override
    public int getLineNumber() {
        return this.definition.astLine;
    }

    @Override
    public int getColumnNumber() {
        return this.definition.astColumn;
    }

    @Override
    public void recurseResolveTypes() {
        if (this.isResolved) {
            return;
        }

        this.isResolved = true;

        this.definition.recurseResolveTypes();
    }
}
