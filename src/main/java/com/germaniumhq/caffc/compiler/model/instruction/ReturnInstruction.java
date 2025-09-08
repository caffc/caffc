package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.model.*;
import com.germaniumhq.caffc.generated.caffcParser;

public class ReturnInstruction implements Statement {
    public Expression value;
    public AstItem owner;
    public String astFilePath;

    public int astColumn;
    public int astLine;

    public static ReturnInstruction fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ReturnContext ctx) {
        ReturnInstruction result = new ReturnInstruction();

        result.owner = owner;
        result.astFilePath = unit.astFilePath;
        result.astLine = ctx.getStart().getLine();
        result.astColumn = ctx.getStart().getCharPositionInLine();

        if (ctx.expression() != null) {
            result.value = Expression.fromAntlr(unit, result, ctx.expression());
        }

        return result;
    }

    public Expression getValue() {
        return value;
    }

    @Override
    public AstItem getOwner() {
        return owner;
    }

    @Override
    public String getFilePath() {
        return astFilePath;
    }

    @Override
    public int getLineNumber() {
        return astLine;
    }

    @Override
    public int getColumnNumber() {
        return astColumn;
    }

    @Override
    public void recurseResolveTypes() {
        value.recurseResolveTypes();
    }

    public Function getFunction() {
        return this.findAstParent(Function.class);
    }
}
