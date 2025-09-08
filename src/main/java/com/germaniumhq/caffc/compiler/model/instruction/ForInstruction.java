package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.model.*;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclaration;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclarations;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

public class ForInstruction implements Statement, Scope {
    public AstItem owner;

    public VariableDeclarations variableDeclarations;
    public Expression variableInitializationExpression;
    public Expression checkExpression;
    public Expression incrementExpression;

    public List<Statement> statements = new ArrayList<>();
    public String astFilePath;
    public int astColumn;
    public int astLine;

    public static ForInstruction fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ForBlockContext forAntlr) {
        ForInstruction result = new ForInstruction();

        result.owner = owner;
        result.astFilePath = unit.astFilePath;
        result.astLine = forAntlr.getStart().getLine();
        result.astColumn = forAntlr.getStart().getCharPositionInLine();

        if (forAntlr.variableDeclarations() != null) {
            result.variableDeclarations = VariableDeclarations.fromAntlr(unit, result, forAntlr.variableDeclarations());
        } else {
            result.variableInitializationExpression = Expression.fromAntlr(unit, result, forAntlr.initExpression);
        }

        result.checkExpression = Expression.fromAntlr(unit, result, forAntlr.conditionExpression);
        result.incrementExpression = Expression.fromAntlr(unit, result, forAntlr.incrementExpression);

        for (caffcParser.StatementContext antlrStatement: forAntlr.block().statement()) {
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
        if (this.variableDeclarations != null) {
            this.variableDeclarations.recurseResolveTypes();
        }

        if (this.variableInitializationExpression != null) {
            this.variableInitializationExpression.recurseResolveTypes();
        }

        this.checkExpression.recurseResolveTypes();
        this.incrementExpression.recurseResolveTypes();

        for (Statement statement: statements) {
            statement.recurseResolveTypes();
        }
    }

    @Override
    public Symbol resolve(String name) {
        return null;
    }
}
