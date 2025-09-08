package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.generated.caffcParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains all the generic definitions for a class or a function.
 */
public class GenericDefinitions implements AstItem {
    public AstItem owner;
    public String astFilePath;
    public int astColumn;
    public int astLine;

    Map<String, GenericDefinition> definitions = new HashMap<>();
    GenericDefinition[] generics;

    private boolean isResolved;

    public static GenericDefinitions fromAntlr(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.GenericsDeclarationsContext genericsDeclarationsContext) {
        GenericDefinitions result = new GenericDefinitions();

        result.owner = owner;

        result.astFilePath = unit.astFilePath;
        result.astLine = genericsDeclarationsContext.getStart().getLine();
        result.astColumn = genericsDeclarationsContext.getStart().getCharPositionInLine();

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
    public String getFilePath() {
        return this.astFilePath;
    }

    @Override
    public int getLineNumber() {
        return this.astLine;
    }

    @Override
    public int getColumnNumber() {
        return this.astColumn;
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
