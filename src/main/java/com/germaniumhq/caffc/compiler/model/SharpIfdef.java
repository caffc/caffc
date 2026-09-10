package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.settings.BuildSettings;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Expands compile-time {@code #ifdef} / {@code #else} into the winning branch's
 * AST items. Inactive branches are never type-resolved or codegen'd.
 */
public final class SharpIfdef {
    private SharpIfdef() {
    }

    public static List<CompileBlock> expandUnit(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.SharpIfdefUnitContext ctx) {
        BuildSettings settings = Program.get().buildSettings;
        Expression condition = Expression.fromAntlr(unit, owner, ctx.expression());
        if (CompileTimeExpressionEval.isTrue(condition, settings)) {
            return parsePlainBlocks(unit, owner, ctx.thenBlocks);
        }
        if (ctx.ELSE() != null) {
            return parsePlainBlocks(unit, owner, ctx.elseBlocks);
        }
        return List.of();
    }

    public static List<Statement> expandMethod(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.SharpIfdefMethodContext ctx) {
        BuildSettings settings = Program.get().buildSettings;
        Expression condition = Expression.fromAntlr(unit, owner, ctx.expression());
        if (CompileTimeExpressionEval.isTrue(condition, settings)) {
            return parseStatements(unit, owner, ctx.thenStatements);
        }
        if (ctx.ELSE() != null) {
            return parseStatements(unit, owner, ctx.elseStatements);
        }
        return List.of();
    }

    private static List<CompileBlock> parsePlainBlocks(
            CompilationUnit unit,
            AstItem owner,
            List<caffcParser.CompileBlockPlainContext> contexts) {
        List<CompileBlock> result = new ArrayList<>();
        for (caffcParser.CompileBlockPlainContext plain : contexts) {
            result.add(CompileBlock.fromPlain(unit, owner, plain));
        }
        return result;
    }

    private static List<Statement> parseStatements(
            CompilationUnit unit,
            AstItem owner,
            List<caffcParser.StatementContext> contexts) {
        List<Statement> result = new ArrayList<>();
        for (caffcParser.StatementContext statement : contexts) {
            result.addAll(Statement.fromAntlr(unit, owner, statement));
        }
        return result;
    }
}
