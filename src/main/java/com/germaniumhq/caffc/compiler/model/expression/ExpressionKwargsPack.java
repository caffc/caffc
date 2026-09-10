package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.HasMethods;
import com.germaniumhq.caffc.compiler.model.Module;
import com.germaniumhq.caffc.compiler.model.StringConstant;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmCall;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmNew;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.instruction.ExceptionHandler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeDefinitionSymbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds a temporary {@code HashDict} and {@code put}s the given key/value pairs.
 * Used to materialize kwargs before a call.
 */
public final class ExpressionKwargsPack implements Expression {
    public AstItem owner;
    public Symbol dictParamType;
    public List<String> keys = new ArrayList<>();
    public List<Expression> values = new ArrayList<>();
    public SourceLocation sourceLocation;

    private Symbol concreteDictType;
    private List<StringConstant> keyConstants = new ArrayList<>();
    private boolean isResolved;

    public static ExpressionKwargsPack of(
            AstItem owner,
            SourceLocation location,
            Symbol dictParamType,
            List<String> keys,
            List<Expression> values) {
        ExpressionKwargsPack result = new ExpressionKwargsPack();
        result.owner = owner;
        result.sourceLocation = location;
        result.dictParamType = dictParamType;
        result.keys.addAll(keys);
        result.values.addAll(values);
        return result;
    }

    @Override
    public Symbol typeSymbol() {
        return dictParamType;
    }

    @Override
    public AstItem getOwner() {
        return owner;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    @Override
    public void recurseResolveTypes() {
        if (isResolved) {
            return;
        }
        isResolved = true;

        for (Expression value : values) {
            value.recurseResolveTypes();
        }

        concreteDictType = resolveConcreteDictType();

        Module module = this.findAstParent(Module.class);
        keyConstants.clear();
        for (String key : keys) {
            StringConstant constant = StringConstant.newStringConstant(sourceLocation, key);
            module.registerConstant(constant);
            keyConstants.add(constant);
        }
    }

    private Symbol resolveConcreteDictType() {
        TypeName typeName = dictParamType.typeName();
        String name = typeName.name;

        if ("HashDict".equals(name)) {
            return dictParamType;
        }

        if ("Dict".equals(name) || "dict".equals(name)) {
            SymbolSearch search = SymbolSearch.ofName("HashDict");
            if (typeName.generics != null && typeName.generics.length > 0) {
                search.generics = new SymbolSearch[typeName.generics.length];
                for (int i = 0; i < typeName.generics.length; i++) {
                    search.generics[i] = SymbolSearch.ofName(typeName.generics[i].name);
                }
            }
            return SymbolResolver.mustResolveSymbol(this, search);
        }

        CaffcCompiler.get().fatal(this,
                "kwargs parameter must be Dict or HashDict, got: " + name);
        return null;
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        int capacity = Math.max(8, keys.size() * 2);
        AsmConstant capacityConstant = new AsmConstant(TypeSymbol.I32, Integer.toString(capacity));

        TypeDefinitionSymbol instantiatedType = (TypeDefinitionSymbol) concreteDictType;
        AsmNew asmNew = new AsmNew(sourceLocation, instantiatedType, new AsmValue[]{capacityConstant});
        AsmVar dictVar = block.addTempVar(this, dictParamType);
        asmNew.result = dictVar;
        result.instructions.add(asmNew);
        result.value = dictVar;

        if (keys.isEmpty()) {
            return result;
        }

        HasMethods dictClass = (HasMethods) concreteDictType;
        FunctionDefinition put = dictClass.getFunction("put");
        if (put == null) {
            CaffcCompiler.get().fatal(this, "kwargs dict type has no put() method: " + concreteDictType);
        }

        AsmLabel exceptionLabel = ExceptionHandler.resolveExceptionLabel(this, block);

        for (int i = 0; i < keys.size(); i++) {
            AsmConstant keyConstant = new AsmConstant(TypeSymbol.STR, keyConstants.get(i).name);

            AsmLinearFormResult valueLinear = values.get(i).asLinearForm(block);
            result.instructions.addAll(valueLinear.instructions);

            result.instructions.add(new AsmCall(
                    sourceLocation,
                    exceptionLabel,
                    put,
                    dictVar,
                    keyConstant,
                    valueLinear.value
            ));
        }

        return result;
    }
}
