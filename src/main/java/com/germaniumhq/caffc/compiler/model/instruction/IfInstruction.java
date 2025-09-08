package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.model.*;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

public class IfInstruction implements Statement {
    public AstItem owner;
    public Expression checkExpression;
    public List<Statement> statements = new ArrayList<>();
    public String astFilePath;
    public int astColumn;
    public int astLine;

    public static IfInstruction fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.IfBlockContext ifAntlr) {
        IfInstruction result = new IfInstruction();

        result.owner = owner;
        result.astFilePath = unit.astFilePath;
        result.astLine = ifAntlr.getStart().getLine();
        result.astColumn = ifAntlr.getStart().getCharPositionInLine();
        result.checkExpression = Expression.fromAntlr(unit, result, ifAntlr.expression());

        for (caffcParser.StatementContext antlrStatement: ifAntlr.block(0).statement()) {
            result.statements.add(Statement.fromAntlr(unit, result, antlrStatement));
        }

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
        this.checkExpression.recurseResolveTypes();

        for (Statement statement: statements) {
            statement.recurseResolveTypes();
        }
    }
}
