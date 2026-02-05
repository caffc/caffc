package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmComment;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmIfZJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

public final class IfInstruction implements Statement {
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
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        int labelIndex = AsmLabel.allocateNumber(this);

        AsmLinearFormResult result = new AsmLinearFormResult();

        AsmBlock forBlock = new AsmBlock(block);
        result.instructions.add(new AsmComment("ifBlock", labelIndex));
        result.instructions.add(forBlock);

        AsmLinearFormResult checkLinearForm = checkExpression.asLinearForm(forBlock);
        forBlock.instructions.addAll(checkLinearForm.instructions);

        AsmComment ifComment = new AsmComment("if", labelIndex);
        AsmLabel elseLabel = new AsmLabel("else", labelIndex);
        AsmLabel endIfLabel = new AsmLabel("endif", labelIndex);

        forBlock.instructions.add(ifComment);
        forBlock.instructions.add(new AsmIfZJmp(checkLinearForm.value, elseLabel));

        AsmBlock ifTrueBlock = new AsmBlock(forBlock);
        forBlock.instructions.add(ifTrueBlock);

        for (Statement statement: statements) {
            ifTrueBlock.instructions.addAll(statement.asLinearForm(ifTrueBlock).instructions);
        }

        // if we have `else` instructions, we need to skip them now
        if (elseStatements != null) {
            ifTrueBlock.instructions.add(new AsmJmp(endIfLabel));
        }

        forBlock.instructions.add(elseLabel);

        if (elseStatements != null) {
            AsmBlock ifFalseBlock = new AsmBlock(forBlock);
            forBlock.instructions.add(ifFalseBlock);

            for (Statement statement: elseStatements) {
                ifFalseBlock.instructions.addAll(statement.asLinearForm(ifFalseBlock).instructions);
            }

            forBlock.instructions.add(endIfLabel);
        }

        return result;
    }
}
