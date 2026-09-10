package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.generated.caffcParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses {@link caffcParser.ParameterDefinitionsContext}, including a bare {@code ...}
 * separator and per-parameter trailing {@code ...}.
 */
public final class ParameterListParser {
    private ParameterListParser() {
    }

    public static List<Parameter> fromAntlr(
            CompilationUnit unit,
            FunctionDefinition owner,
            AstItem defaultExpressionOwner,
            caffcParser.ParameterDefinitionsContext ctx) {
        List<Parameter> result = new ArrayList<>();
        if (ctx == null) {
            return result;
        }

        boolean afterEllipsis = false;
        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree child = ctx.getChild(i);
            if (child instanceof TerminalNode terminal &&
                    terminal.getSymbol().getType() == caffcParser.ELLIPSIS) {
                afterEllipsis = true;
                continue;
            }
            if (child instanceof caffcParser.ParameterDefinitionContext parameterCtx) {
                Parameter parameter = Parameter.fromAntlr(
                        unit, owner, defaultExpressionOwner, parameterCtx);
                if (afterEllipsis) {
                    parameter.isVarargsSection = true;
                }
                if (parameterCtx.ELLIPSIS() != null) {
                    parameter.isVarargsMarked = true;
                    afterEllipsis = true;
                }
                result.add(parameter);
            }
        }

        return result;
    }
}
