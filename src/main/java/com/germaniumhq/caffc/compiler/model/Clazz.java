package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

public class Clazz implements CompileBlock, AstItem, Scope {
    public ClassDefinition definition = new ClassDefinition();
    public List<Function> functions = new ArrayList<>();

    public AstItem owner;
    private boolean isResolved;

    public static Clazz fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ClassDefinitionContext antlrClassDefinition) {
        Clazz clazz = new Clazz();

        clazz.definition.sourceLocation = SourceLocation.fromAntlr(unit.sourceLocation.filePath, antlrClassDefinition);
        clazz.owner = unit;

        clazz.definition.module = unit.module;
        clazz.definition.name = antlrClassDefinition.name().getText();
        clazz.definition.typeName = TypeName.of(clazz.definition.module.name, clazz.definition.name, null, DataType.OBJECT);
        
        caffcParser.TagsContext antlrTags = antlrClassDefinition.tags();
        if (antlrTags != null) {
            for (caffcParser.TagContext antlrTag: antlrTags.tag()) {
                Tag tag = Tag.fromAntlr(unit, owner, antlrTag);
                clazz.definition.tags.put(tag.name, tag);
            }
        }

        caffcParser.GenericsDeclarationsContext antlrGenerics = antlrClassDefinition.genericsDeclarations();
        if (antlrGenerics != null) {
            clazz.definition.generics = GenericDefinitions.fromAntlr(unit, clazz, antlrGenerics);
        }

        if (antlrClassDefinition.interfaceImplementations() != null) {
            for (caffcParser.InterfaceImplementationContext interfaceImplementationContext:
                    antlrClassDefinition.interfaceImplementations().interfaceImplementation()) {
                clazz.definition.implementedInterfacesSearch.add(SymbolSearch.fromAntlr(unit, interfaceImplementationContext));
            }
        }

        if (antlrClassDefinition.fieldDeclaration() != null) {
            for (caffcParser.FieldDeclarationContext antlrFieldDeclaration: antlrClassDefinition.fieldDeclaration()) {
                List<Field> fields = Field.fromAntlr(unit, clazz, antlrFieldDeclaration);

                for (Field field: fields) {
                    SymbolSearch.uniqueSymbolNameCheck(clazz, field.name, field);
                    clazz.definition.fields.add(field);
                }
            }
        }

        if (antlrClassDefinition.function() != null) {
            for (caffcParser.FunctionContext antlrFunction: antlrClassDefinition.function()) {
                Function function = Function.fromAntlr(unit, clazz, antlrFunction);
                function.definition.clazz = clazz.definition;

                if (!function.definition.isStatic) {
                    // we syntheticaly add the `_this` parameter to the function pointing to the class.
                    Parameter thisParameter = new Parameter(function.definition, "_this");
                    thisParameter.sourceLocation = function.getSourceLocation(); // we set the function coordinates

                    function.definition.parameters.add(0, thisParameter);
                    // FIXME: check for existing `_this` parameters.
                }

                SymbolSearch.uniqueSymbolNameCheck(clazz, function.definition.name, function);

                clazz.functions.add(function);
                clazz.definition.functions.add(function.definition);
            }
        }

        unit.module.clazzes.put(clazz.definition.name, clazz.definition);
        unit.module.program.register(
                clazz.definition.typeName,
                clazz.definition
        );

        return clazz;
    }

    /**
     * Called by template to see if there's a `constructor` defined, or a `new` size
     * calculator.
     *
     * @param name
     * @return
     */
    public Function getFunction(String name) {
        if ("new".equals(name)) { // new is an operator, that's the size calculator
            CompilationUnit compilationUnit = this.findAstParent(CompilationUnit.class);
            Function function = compilationUnit.getFunction("_caffc_" + this.definition.name + "_size");
            return function;
        }

        for (Function function : functions) {
            if (function.definition.name.equals(name)) {
                return function;
            }
        }

        return null;
    }

    @Override
    public AstItem getOwner() {
        return owner;
    }

    @Override
    public Symbol resolve(String name) {
        for (Field field : this.definition.fields) {
            if (field.name.equals(name)) {
                return field;
            }
        }

        for (Function function : this.functions) {
            if (function.definition.name.equals(name)) {
                return function.definition;
            }
        }

        if (this.definition.generics != null) {
            return this.definition.generics.getByName(name);
        }

        return null;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return this.definition.sourceLocation;
    }

    @Override
    public void recurseResolveTypes() {
        if (this.isResolved) {
            return;
        }

        this.isResolved = true;

        this.definition.recurseResolveTypes();

        for (Function function: this.functions) {
            function.recurseResolveTypes();
        }
    }
}
