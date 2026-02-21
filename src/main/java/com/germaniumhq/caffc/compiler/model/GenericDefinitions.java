package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains all the generic definitions for a class or a function.
 */
public class GenericDefinitions implements AstItem {
    public AstItem owner;
    public SourceLocation sourceLocation;

    Map<String, GenericDefinition> definitions = new HashMap<>();
    GenericDefinition[] generics;

    private boolean isResolved;

    public static GenericDefinitions fromAntlr(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.GenericsDeclarationsContext genericsDeclarationsContext) {
        GenericDefinitions result = new GenericDefinitions();

        result.owner = owner;

        result.sourceLocation = SourceLocation.fromAntlr(unit.sourceLocation.filePath, genericsDeclarationsContext);

        int genericCount = genericsDeclarationsContext.genericDeclaration().size();
        int genericIndex = 0;
        result.generics = new GenericDefinition[genericCount];

        for (caffcParser.GenericDeclarationContext genericDeclaration: genericsDeclarationsContext.genericDeclaration()) {
            GenericDefinition generic = GenericDefinition.fromAntlr(unit, result, genericDeclaration);

            result.generics[genericIndex++] = generic;
            result.definitions.put(generic.name, generic);
        }

        return result;
    }

    @Override
    public AstItem getOwner() {
        return this.owner;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    @Override
    public void recurseResolveTypes() {
        if (this.isResolved) {
            return;
        }

        this.isResolved = true;

        if (generics != null) {
            for (GenericDefinition genericDefinition : generics) {
                genericDefinition.recurseResolveTypes();
            }
        }
    }

    public GenericDefinition getByName(String name) {
        return this.definitions.get(name);
    }
}
