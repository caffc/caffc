package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains all the generic instantiation for a class instantiation
 * or a function. This is different from the declaration of a generics
 * type, since there's no restriction anymore, it's the actual type
 * the class should be instantiated with.
 */
public class GenericInstantiations implements AstItem {
    public AstItem owner;
    public SourceLocation sourceLocation;

    GenericInstantiation[] generics;

    private boolean isResolved;

    public static GenericInstantiations fromAntlr(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.GenericsInstantiationsContext genericsInstantiationContext) {
        if (genericsInstantiationContext == null) {
            return null;
        }

        GenericInstantiations result = new GenericInstantiations();

        result.owner = owner;

        result.sourceLocation = SourceLocation.fromAntlr(unit.sourceLocation.filePath, genericsInstantiationContext);

        if (genericsInstantiationContext instanceof caffcParser.GenericsInstantiationEmptyContext) {
            result.generics = new GenericInstantiation[0];

            return result;
        }

        caffcParser.GenericsInstantiationTypeContext genericsInstantiationTypeContext =
                (caffcParser.GenericsInstantiationTypeContext) genericsInstantiationContext;

        result.generics = new GenericInstantiation[genericsInstantiationTypeContext.typeName().size()];

        for (int i = 0; i < result.generics.length; i++) {
            GenericInstantiation generic = GenericInstantiation.fromAntlr(unit, result, genericsInstantiationTypeContext.typeName(i));
            result.generics[i] = generic;
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
            for (GenericInstantiation genericInstantiation : generics) {
                genericInstantiation.recurseResolveTypes();
            }
        }
    }

    public List<Symbol> getResolvedSymbolList() {
        List<Symbol> result = new ArrayList<>();

        for (GenericInstantiation genericInstantiation : this.generics) {
            result.add(genericInstantiation.type);
        }

        return result;
    }
}
