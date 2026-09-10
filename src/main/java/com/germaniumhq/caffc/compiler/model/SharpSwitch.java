package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.settings.BuildSettings;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Expands compile-time {@code #switch} / {@code #case} / {@code #default} into the
 * winning branch's AST items. Inactive branches are never type-resolved or codegen'd.
 */
public final class SharpSwitch {
    private SharpSwitch() {
    }

    public static List<CompileBlock> expandUnit(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.SharpSwitchUnitContext ctx) {
        BuildSettings settings = Program.get().buildSettings;
        List<caffcParser.CompileBlockPlainContext> defaultBody = null;

        for (caffcParser.SharpCaseUnitContext branch : ctx.sharpCaseUnit()) {
            if (branch.DEFAULT() != null) {
                if (defaultBody != null) {
                    CaffcCompiler.get().fatal(
                            SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, branch),
                            "#switch can have at most one #default");
                }
                defaultBody = branch.compileBlockPlain();
                continue;
            }

            Expression condition = Expression.fromAntlr(unit, owner, branch.expression());
            if (CompileTimeExpressionEval.isTrue(condition, settings)) {
                return parsePlainBlocks(unit, owner, branch.compileBlockPlain());
            }
        }

        if (defaultBody != null) {
            return parsePlainBlocks(unit, owner, defaultBody);
        }

        // No matching case and no default: contribute nothing.
        return List.of();
    }

    public static List<Statement> expandMethod(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.SharpSwitchMethodContext ctx) {
        BuildSettings settings = Program.get().buildSettings;
        List<caffcParser.StatementContext> defaultBody = null;

        for (caffcParser.SharpCaseMethodContext branch : ctx.sharpCaseMethod()) {
            if (branch.DEFAULT() != null) {
                if (defaultBody != null) {
                    CaffcCompiler.get().fatal(
                            SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, branch),
                            "#switch can have at most one #default");
                }
                defaultBody = branch.statement();
                continue;
            }

            Expression condition = Expression.fromAntlr(unit, owner, branch.expression());
            if (CompileTimeExpressionEval.isTrue(condition, settings)) {
                return parseStatements(unit, owner, branch.statement());
            }
        }

        if (defaultBody != null) {
            return parseStatements(unit, owner, defaultBody);
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
