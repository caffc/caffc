package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.type.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FunctionDefinition implements GenericsDefinitionsSymbol, Scope {
    public Symbol clazz;

    public String name;
    public Symbol returnType;
    public SymbolSearch returnTypeSearch;

    public List<Parameter> parameters = new ArrayList<>();
    public String module;
    public Tags tags = new Tags();

    /**
     * Generics defined for the function.
     */
    public GenericDefinitions generics = null;

    // AstItem info is filled by the Function creation
    public AstItem owner;
    public String astFilePath;
    public int astColumn;
    public int astLine;

    private boolean isResolved;

    @Override
    public String name() {
        return name;
    }

    @Override
    public TypeName typeName() {
        TypeName result = TypeName.of(module, name, clazz != null ? clazz.name() : null, DataType.FUNCTION);

        return result;
    }

    /**
     * A function definition has no symbol it points to. Sure, there's the
     * return type, or the parameters, but they're different things. A function
     * _call_ might return the returnType symbol.
     */
    @Override
    public Symbol typeSymbol() {
        return this;
    }

    @Override
    public AstItem getOwner() {
        return owner;
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

        // update the restrictions for the generics first.
        if (generics != null) {
            this.generics.recurseResolveTypes();
        }

        for (Parameter parameter: this.parameters) {
            parameter.recurseResolveTypes();
        }

        if (returnTypeSearch == null) {
            this.returnType = TypeSymbol.VOID;
        } else {
            this.returnType = SymbolResolver.mustResolveSymbol(this, returnTypeSearch);
        }
    }

    @Override
    public <T extends GenericsDefinitionsSymbol> T instantiateGenerics(List<Symbol> resolvedGenerics) {
        Map<String, Symbol> genericsSymbols = GenericsDefinitionsSymbol.createGenericsSymbolMap(
                this, resolvedGenerics);
        return this.newGenericsCopy(genericsSymbols);
    }

    @Override
    public <T extends Symbol> T newGenericsCopy(Map<String, Symbol> resolvedGenerics) {
        // nothing to copy
        if (resolvedGenerics == null || resolvedGenerics.isEmpty()) {
            return (T) this;
        }

        FunctionDefinition newFunctionDefinition = new FunctionDefinition();

        newFunctionDefinition.name = this.name;
        newFunctionDefinition.clazz = this.clazz;
        newFunctionDefinition.returnType = this.returnType;
        newFunctionDefinition.module = this.module;
        newFunctionDefinition.tags = this.tags;

        if (this.returnType instanceof GenericDefinition returnGenericDefinition) {
            Symbol resolvedReturnType = resolvedGenerics.get(returnGenericDefinition.name);
            if (resolvedReturnType != null) {
                newFunctionDefinition.returnType = resolvedReturnType;
            }
        }

        for (Parameter parameter: this.parameters) {
            newFunctionDefinition.parameters.add(parameter.newGenericsCopy(resolvedGenerics));
        }

        return (T) newFunctionDefinition;
    }

    @Override
    public Symbol resolve(String name) {
        for (Parameter parameter: this.parameters) {
            if (parameter.name.equals(name)) {
                return parameter;
            }
        }

        if (generics != null) {
            return generics.getByName(name);
        }

        return null;
    }

    @Override
    public GenericDefinition getGenericDefinition(int index) {
        if (generics == null) {
            CaffcCompiler.get().fatal(this, "function has no generics");
        }

        if (index < 0) {
            CaffcCompiler.get().fatal(this, "index out of bounds");
        }

        if (index >= generics.generics.length) {
            CaffcCompiler.get().fatal(this, "too many generics in the function instantiation");
        }

        return generics.generics[index];
    }

    @Override
    public int getGenericsDefinitionCount() {
        if (generics == null) {
            return 0;
        }

        return generics.generics.length;
    }
}
