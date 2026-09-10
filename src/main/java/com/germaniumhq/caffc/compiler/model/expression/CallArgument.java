package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.generated.caffcParser;

/**
 * A single call-site argument: either positional ({@code expr}) or named ({@code name=expr}).
 */
public final class CallArgument {
    public String name; // null when positional
    public Expression value;
    public SourceLocation sourceLocation;

    public static CallArgument fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.CallArgumentContext ctx) {
        CallArgument result = new CallArgument();
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, ctx);

        // Named form is exactly: ID '=' expression (3 children). Do not use ctx.ID()
        // alone — a positional `foo` expression also contains an ID token.
        if (ctx.getChildCount() == 3 && "=".equals(ctx.getChild(1).getText())) {
            result.name = ctx.getChild(0).getText();
            result.value = Expression.fromAntlr(unit, owner, ctx.expression());
        } else {
            result.name = null;
            result.value = Expression.fromAntlr(unit, owner, ctx.expression());
        }

        return result;
    }

    public boolean isNamed() {
        return name != null;
    }
}
