package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmComment;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmIfZJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

public class IfInstruction implements Statement {
    public AstItem owner;
    public Expression checkExpression;
    public List<Statement> statements = new ArrayList<>();
    public List<Statement> elseStatements = null;
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

        if (ifAntlr.trueBlock != null) {
            for (caffcParser.StatementContext antlrStatement: ifAntlr.trueBlock.statement()) {
                result.statements.addAll(Statement.fromAntlr(unit, result, antlrStatement));
            }
        } else if (ifAntlr.return_() != null) {
            result.statements.add(ReturnInstruction.fromAntlr(unit, result, ifAntlr.return_()));
        } else if (ifAntlr.controlFlow() != null) {
            result.statements.add(ControlFlowInstruction.fromAntlr(unit, result, ifAntlr.controlFlow()));
        } else {
            CaffcCompiler.get().error(result, "unknown statements for if");
        }

        if (ifAntlr.falseBlock != null) {
            result.elseStatements = new ArrayList<>();
            for (caffcParser.StatementContext antlrStatement: ifAntlr.falseBlock.statement()) {
                result.elseStatements.addAll(Statement.fromAntlr(unit, result, antlrStatement));
            }
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

        if (elseStatements != null) {
            for (Statement statement: elseStatements) {
                statement.recurseResolveTypes();
            }
        }
    }

    @Override
    public AsmLinearFormResult asLinearForm(Function function) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        AsmLinearFormResult checkLinearForm = checkExpression.asLinearForm(function);
        result.instructions.addAll(checkLinearForm.instructions);

        int labelIndex = AsmLabel.allocateNumber(this);

        AsmComment ifComment = new AsmComment("if", labelIndex);
        AsmLabel elseLabel = new AsmLabel("else", labelIndex);
        AsmLabel endIfLabel = new AsmLabel("endif", labelIndex);

        result.instructions.add(ifComment);
        result.instructions.add(new AsmIfZJmp(checkLinearForm.value, elseLabel));

        for (Statement statement: statements) {
            result.instructions.addAll(statement.asLinearForm(function).instructions);
        }

        // if we have `else` instructions, we need to skip them now
        if (elseStatements != null) {
            result.instructions.add(new AsmJmp(endIfLabel));
        }

        result.instructions.add(elseLabel);

        if (elseStatements != null) {
            for (Statement statement: elseStatements) {
                result.instructions.addAll(statement.asLinearForm(function).instructions);
            }

            result.instructions.add(endIfLabel);
        }

        return result;
    }
}
