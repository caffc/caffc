package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclaration;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.GenericsDefinitionsSymbol;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FunctionDefinition implements GenericsDefinitionsSymbol, Scope {
    public Symbol clazz;
    public boolean isStatic;

    public String name;
    public LinkedHashMap<String, Symbol> returnTypes;

    /**
     * Contains a mapping of the type searches for the variables declared as return.
     * The mapping is from the name of the variable to the symbol search.
     */
    public LinkedHashMap<String, SymbolSearch> returnTypeSearches = new LinkedHashMap<>();
    public String[] returnNames;

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

    public Symbol returnType;

    public List<VariableDeclaration> antlrFillReturnType(
        CompilationUnit unit,
        AstItem owner,
        caffcParser.ReturnTypeContext returnTypeContext) {

        List<VariableDeclaration> result = new ArrayList<>();

        if (returnTypeContext == null || returnTypeContext.VOID() != null) {
            // NOTHING on purpose, function is void
        } else if (returnTypeContext.typeName() != null) {
            // this returns a single primitive type, no need to create a structure for it
            this.returnTypeSearches.put("", SymbolSearch.fromAntlr(unit, returnTypeContext.typeName()));
        } else if (returnTypeContext.namedTypeTuple() != null) {
            caffcParser.NamedTypeTupleContext namedTypeTupleContext = returnTypeContext.namedTypeTuple();
            for (int i = 0; i < namedTypeTupleContext.typeName().size(); i++) {
                SymbolSearch symbolSearch = SymbolSearch.fromAntlr(unit, namedTypeTupleContext.typeName(i));
                String variableName = namedTypeTupleContext.ID(i).getText();

                this.returnTypeSearches.put(
                    variableName,
                    symbolSearch
                );

                result.add(VariableDeclaration.fromReturn(owner, symbolSearch, variableName));
            }
        } else {
            CaffcCompiler.get().fatal(owner, "unsupported function return type");
            // not reached
        }

        returnNames = this.returnTypeSearches.keySet().toArray(new String[0]);

        return result;
    }

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

        if (this.returnTypeSearches != null) {
            this.returnTypes = new LinkedHashMap<>();
            for (Map.Entry<String, SymbolSearch> entry: this.returnTypeSearches.entrySet()) {
                Symbol resolvedSymbol = SymbolResolver.mustResolveSymbol(this, entry.getValue());
                this.returnTypes.put(entry.getKey(), resolvedSymbol);
            }
        } else {
            this.returnTypes = new LinkedHashMap<>();
        }

        this.returnType = this.createReturnTypeSymbol();
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
            codeRenderer.field("isStatic", isStatic);
            codeRenderer.field("name", name);
            codeRenderer.field("parameters", parameters);
            codeRenderer.field("returnTypes", returnTypes);
        });
    }

    private Symbol createReturnTypeSymbol() {
        if (this.returnTypes == null || this.returnTypes.isEmpty()) {
            return TypeSymbol.VOID;
        }

        if (this.returnTypes.size() == 1) {
            return this.returnTypes.values().iterator().next();
        }

        return Struct.fromDefinition(owner, module, this.name + "_structreturn", this.returnTypes);
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

        newFunctionDefinition.clazz = this.clazz;
        newFunctionDefinition.isStatic = this.isStatic;

        newFunctionDefinition.name = this.name;
        newFunctionDefinition.returnTypes = new LinkedHashMap<>();
        newFunctionDefinition.module = this.module;
        newFunctionDefinition.tags = this.tags;

        newFunctionDefinition.returnNames = this.returnNames;

        for (Map.Entry<String, Symbol> entry: this.returnTypes.entrySet()) {
            if (!(entry.getValue() instanceof GenericDefinition)) {
                newFunctionDefinition.returnTypes.put(entry.getKey(), entry.getValue());
                continue;
            }

            GenericDefinition returnGenericDefinition = (GenericDefinition) entry.getValue();

            Symbol resolvedReturnType = resolvedGenerics.get(returnGenericDefinition.name);
            if (resolvedReturnType == null) {
                newFunctionDefinition.returnTypes.put(entry.getKey(), entry.getValue());
                continue;
            }

            newFunctionDefinition.returnTypes.put(entry.getKey(), resolvedReturnType);
        }

        for (Parameter parameter: this.parameters) {
            newFunctionDefinition.parameters.add(parameter.newGenericsCopy(resolvedGenerics));
        }

        newFunctionDefinition.returnType = newFunctionDefinition.createReturnTypeSymbol();

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

    public boolean isNamedReturn() {
        return !this.returnTypeSearches.isEmpty() &&
            !"".equals(this.returnTypeSearches.keySet().iterator().next());
    }

    public boolean isMultiReturn() {
        return this.returnTypes.size() > 1;
    }

    public boolean isReturnEmpty() {
        return this.returnTypes.isEmpty();
    }

    public boolean isVoid() {
        return isReturnEmpty() || this.returnTypes.size() == 1 &&
            TypeSymbol.VOID.equals(this.returnTypes.values().iterator().next());
    }

    public String getReturnName(int i) {
        return this.returnNames[i];
    }
}
