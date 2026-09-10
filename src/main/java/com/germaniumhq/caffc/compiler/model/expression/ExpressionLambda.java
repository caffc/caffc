package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.LambdaCapture;
import com.germaniumhq.caffc.compiler.model.LambdaSynthesizer;
import com.germaniumhq.caffc.compiler.model.Parameter;
import com.germaniumhq.caffc.compiler.model.ParameterListParser;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmNew;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeDefinitionSymbol;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Lambda expression: {@code fn(params) -> R { ... }}.
 * Lowers to {@code new _Lambda_...(captures...)} implementing {@code fn<R>}.
 */
public final class ExpressionLambda implements Expression {
    public AstItem owner;
    public SourceLocation sourceLocation;

    public List<Parameter> userParameters = new ArrayList<>();
    public SymbolSearch returnTypeSearch;
    public List<Statement> bodyStatements = new ArrayList<>();

    private LambdaSynthesizer.Result synthesized;
    private final List<Expression> captureArguments = new ArrayList<>();
    private boolean isResolved;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExLambdaContext ctx) {
        ExpressionLambda result = new ExpressionLambda();
        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, ctx);

        // Temporary function owner so parameters/defaults and body statements attach correctly.
        Function parseOwner = new Function();
        parseOwner.owner = owner;
        parseOwner.definition.owner = result;
        parseOwner.definition.module = unit.module.name;
        parseOwner.definition.name = "<lambda>";
        parseOwner.definition.sourceLocation = result.sourceLocation;
        parseOwner.sourceLocationCurlyOpen = result.sourceLocation;
        parseOwner.sourceLocationCurlyClose = result.sourceLocation;

        if (ctx.parameterDefinitions() != null) {
            result.userParameters.addAll(
                    ParameterListParser.fromAntlr(unit, parseOwner.definition, parseOwner, ctx.parameterDefinitions()));
        }

        if (ctx.returnType() != null) {
            parseOwner.definition.antlrFillReturnType(unit, parseOwner, ctx.returnType());
            if (!parseOwner.definition.returnTypeSearches.isEmpty()) {
                result.returnTypeSearch = parseOwner.definition.returnTypeSearches.values().iterator().next();
            } else {
                result.returnTypeSearch = SymbolSearch.ofName("void");
            }
        } else {
            result.returnTypeSearch = SymbolSearch.ofName("void");
        }

        for (caffcParser.FunctionBodyItemContext bodyItem : ctx.functionBlock().functionBodyItem()) {
            if (bodyItem.statement() != null) {
                result.bodyStatements.addAll(Statement.fromAntlr(unit, parseOwner, bodyItem.statement()));
            }
        }

        result.synthesized = LambdaSynthesizer.synthesize(
                unit,
                result.sourceLocation,
                "expr",
                parseOwner,
                result.userParameters,
                result.returnTypeSearch,
                result.bodyStatements);

        // Capture args are read at the lambda expression site (enclosing scope).
        for (LambdaCapture capture : result.synthesized.captures) {
            result.captureArguments.add(ExpressionId.fromName(unit, result, capture.name));
        }

        // Move parse-time locals onto the synthetic call function
        for (LocalVariable localVariable : parseOwner._variables.values()) {
            if (result.synthesized.callFunction._variables.containsKey(localVariable.name)) {
                continue;
            }
            localVariable.owner = result.synthesized.callFunction;
            result.synthesized.callFunction.registerVariable(localVariable);
        }

        return result;
    }

    @Override
    public Symbol typeSymbol() {
        return synthesized.clazz.definition;
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

        for (Expression captureArgument : captureArguments) {
            captureArgument.recurseResolveTypes();
        }

        // Ensure the synthetic class (and its call method) are fully resolved.
        synthesized.clazz.recurseResolveTypes();
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        List<AsmValue> parameterValues = new ArrayList<>();
        for (Expression captureArgument : captureArguments) {
            AsmLinearFormResult captureLinear = captureArgument.asLinearForm(block);
            result.instructions.addAll(captureLinear.instructions);
            parameterValues.add(captureLinear.value);
        }

        TypeDefinitionSymbol type = synthesized.clazz.definition;
        AsmNew asmNew = new AsmNew(
                sourceLocation,
                type,
                parameterValues.toArray(new AsmValue[0]));
        asmNew.result = block.addTempVar(this, type);
        result.value = asmNew.result;
        result.instructions.add(asmNew);

        return result;
    }
}
