package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.generated.caffcParser;

public class ControlFlowInstruction implements Statement {
    public AstItem owner;
    public String astFilePath;

    public int astColumn;
    public int astLine;

    public String instruction;

    public static ControlFlowInstruction fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ControlFlowContext ctx) {
        ControlFlowInstruction result = new ControlFlowInstruction();

        result.owner = owner;
        result.astFilePath = unit.astFilePath;
        result.astLine = ctx.getStart().getLine();
        result.astColumn = ctx.getStart().getCharPositionInLine();

        result.instruction = ctx.getText();

        return result;
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
    }

    public Function getFunction() {
        return this.findAstParent(Function.class);
    }
}
