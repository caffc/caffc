package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.HasMethods;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmNew;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeDefinitionSymbol;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

public final class ExpressionNewObject implements Expression {
    public AstItem owner;
    public List<CallArgument> callArguments = new ArrayList<>();
    public List<Expression> parameters = new ArrayList<>();

    public SourceLocation sourceLocation;

    public TypeDefinitionSymbol instantiatedType;
    private SymbolSearch instantiatedTypeSearch;

    private boolean isResolved;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExNewObjectContext newObject) {
        ExpressionNewObject result = new ExpressionNewObject();

        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, newObject);
        result.instantiatedTypeSearch = SymbolSearch.fromAntlr(unit, newObject.newType());

        if (newObject.callArgumentList() != null) {
            for (caffcParser.CallArgumentContext argumentContext : newObject.callArgumentList().callArgument()) {
                result.callArguments.add(CallArgument.fromAntlr(unit, result, argumentContext));
            }
        }

        return result;
    }

    @Override
    public Symbol typeSymbol() {
        return instantiatedType;
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

        for (CallArgument argument : callArguments) {
            argument.value.recurseResolveTypes();
        }

        this.instantiatedType = SymbolResolver.mustResolveSymbol(this, this.instantiatedTypeSearch);

        if (!(instantiatedType instanceof HasMethods hasMethods)) {
            if (!callArguments.isEmpty()) {
                CaffcCompiler.get().fatal(this,
                        "type " + instantiatedType + " cannot take constructor arguments");
            }
            return;
        }

        FunctionDefinition constructor = hasMethods.getFunction("constructor");
        if (constructor == null) {
            if (!callArguments.isEmpty()) {
                CaffcCompiler.get().fatal(this,
                        "type " + instantiatedType.name() + " has no constructor, but arguments were given");
            }
            return;
        }

        constructor.recurseResolveTypes();

        this.parameters = FunctionCallBinder.bindConstructor(
                this,
                this,
                constructor,
                callArguments);
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        List<AsmLinearFormResult> linearParameters = new ArrayList<>();
        for (Expression parameter : this.parameters) {
            linearParameters.add(parameter.asLinearForm(block));
        }

        AsmValue[] callParameters = new AsmValue[linearParameters.size()];
        for (int i = 0; i < linearParameters.size(); i++) {
            AsmLinearFormResult linearParameter = linearParameters.get(i);

            callParameters[i] = linearParameter.value;

            result.instructions.addAll(linearParameter.instructions);
        }

        AsmNew asmNew = new AsmNew(this.sourceLocation, instantiatedType, callParameters);
        asmNew.result = block.addTempVar(this, instantiatedType);

        result.value = asmNew.result;
        result.instructions.add(asmNew);

        return result;
    }
}
