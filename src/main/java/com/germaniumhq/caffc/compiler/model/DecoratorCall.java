package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.expression.CallArgument;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionDotAccess;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionFnCall;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionId;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionNewObject;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Parametrized decorator: {@code @decorator(args) function ...}.
 *
 * <p>Always requires parentheses (no bare {@code @decorator}). Lowers to a module
 * global {@code fn<R> name = decorator(args)(bodyLambda)}. Nested
 * {@code @a() @b(x) f(){}} becomes {@code a()(b(x)(body))}.
 */
public final class DecoratorCall implements CompileBlock, AstItem {
    public AstItem owner;
    public SourceLocation sourceLocation;

    /**
     * Factory call expressions from outermost to innermost
     * ({@code @a() @b(x)} → {@code [a(), b(x)]}).
     */
    public List<Expression> decoratorExpressions = new ArrayList<>();

    public Function decoratedFunction;
    public GlobalVariableDeclarations binding;

    private boolean isResolved;

    public static DecoratorCall fromAntlr(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.DecoratorCallContext ctx) {
        DecoratorCall result = new DecoratorCall();
        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, ctx);

        caffcParser.DecoratorCallContext current = ctx;
        while (current != null) {
            result.decoratorExpressions.add(factoryCallFromAntlr(unit, result, current));
            if (current.function() != null) {
                // Do not register as a module function — Function.fromAntlr would.
                result.decoratedFunction = Function.fromAntlr(unit, owner, current.function());
                // Unregister if fromAntlr registered it
                if (result.decoratedFunction.definition.clazz == null) {
                    unit.module.functions.remove(result.decoratedFunction.definition.name);
                }
                break;
            }
            current = current.decoratorCall();
        }

        if (result.decoratedFunction == null) {
            throw new IllegalStateException("decorator without function");
        }

        // Build lambda class for the decorated body
        LambdaSynthesizer.Result lambda = LambdaSynthesizer.synthesizeFromFunction(unit, result.decoratedFunction);

        // Global: fn<R> name = decorator(...(new Lambda())...)
        GlobalVariable global = new GlobalVariable();
        global.owner = unit.module;
        global.module = unit.module.name;
        global.name = result.decoratedFunction.definition.name;
        global.sourceLocation = result.sourceLocation;
        global.typeSymbolSearch = SymbolSearch.ofName("fn");
        global.typeSymbolSearch.generics = new SymbolSearch[]{lambda.returnTypeSearch};

        ExpressionNewObject bodyInstance = new ExpressionNewObject();
        bodyInstance.owner = result;
        bodyInstance.sourceLocation = result.sourceLocation;
        bodyInstance.instantiatedTypeSearch = SymbolSearch.ofName(lambda.clazz.definition.name);

        Expression wrapped = bodyInstance;
        // Apply decorators innermost-first: @a() @b(x) f → a()(b(x)(f))
        for (int i = result.decoratorExpressions.size() - 1; i >= 0; i--) {
            Expression decorator = result.decoratorExpressions.get(i);
            ExpressionFnCall call = new ExpressionFnCall();
            call.owner = result;
            call.sourceLocation = result.sourceLocation;
            call.functionExpression = decorator;
            call.callArguments.add(CallArgument.positional(wrapped));
            wrapped = call;
        }

        com.germaniumhq.caffc.compiler.model.expression.ExpressionAssign assign =
                new com.germaniumhq.caffc.compiler.model.expression.ExpressionAssign();
        assign.owner = global;
        assign.sourceLocation = result.sourceLocation;
        assign.leftExpressions.add(ExpressionId.fromName(null, assign, global.name));
        assign.right = wrapped;
        global.assignExpression = assign;

        unit.module.registerGlobalVariable(global);

        GlobalVariableDeclarations declarations = new GlobalVariableDeclarations();
        declarations.variable = global;
        declarations.owner = unit.module;
        declarations.sourceLocation = result.sourceLocation;
        result.binding = declarations;

        return result;
    }

    private static Expression factoryCallFromAntlr(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.DecoratorCallContext ctx) {
        ExpressionFnCall factoryCall = new ExpressionFnCall();
        factoryCall.owner = owner;
        factoryCall.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, ctx);
        factoryCall.functionExpression = calleeFromAntlr(unit, factoryCall, ctx.decoratorCallee());

        if (ctx.callArgumentList() != null) {
            for (caffcParser.CallArgumentContext argumentContext : ctx.callArgumentList().callArgument()) {
                factoryCall.callArguments.add(CallArgument.fromAntlr(unit, factoryCall, argumentContext));
            }
        }

        return factoryCall;
    }

    private static Expression calleeFromAntlr(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.DecoratorCalleeContext ctx) {
        if (ctx.decoratorCallee() != null) {
            Expression left = calleeFromAntlr(unit, owner, ctx.decoratorCallee());
            ExpressionDotAccess dot = ExpressionDotAccess.fromParts(owner, left, ctx.ID().getText());
            dot.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, ctx);
            return dot;
        }

        return ExpressionId.fromName(unit, owner, ctx.ID().getText());
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

        // Lambda class is already on compileBlocks; resolve binding (decorators + assign).
        if (binding != null) {
            binding.recurseResolveTypes();
        }
    }
}
