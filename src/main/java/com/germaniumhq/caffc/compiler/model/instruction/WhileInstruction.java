package com.germaniumhq.caffc.compiler.model.instruction;

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
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

public final class WhileInstruction implements Statement {
    public AstItem owner;
    public Expression checkExpression;
    public List<Statement> statements = new ArrayList<>();
    public SourceLocation sourceLocation;

    public AsmLabel whileCheckLabel;
    public AsmLabel whileEndLabel;

    public static WhileInstruction fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.WhileBlockContext whileAntlr) {
        WhileInstruction result = new WhileInstruction();

        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, whileAntlr);
        result.checkExpression = Expression.fromAntlr(unit, result, whileAntlr.expression());

        for (caffcParser.StatementContext antlrStatement: whileAntlr.block().statement()) {
            result.statements.addAll(Statement.fromAntlr(unit, result, antlrStatement));
        }

        return result;
    }

    @Override
    public AstItem getOwner() {
        return owner;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    @Override
    public void recurseResolveTypes() {
        this.checkExpression.recurseResolveTypes();

        for (Statement statement : statements) {
            statement.recurseResolveTypes();
        }
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        int labelIndex = AsmLabel.allocateNumber(this);

        this.whileCheckLabel = new AsmLabel(null, "whileCheck", labelIndex);
        this.whileEndLabel = new AsmLabel(null, "whileEnd", labelIndex);

        AsmLinearFormResult result = new AsmLinearFormResult();
        AsmBlock whileBlock = new AsmBlock(block);
        result.instructions.add(whileBlock);

        whileBlock.instructions.add(new AsmComment(null, "whileBegin", labelIndex));
        whileBlock.instructions.add(this.whileCheckLabel);

        AsmLinearFormResult checkLinearForm = checkExpression.asLinearForm(whileBlock);
        whileBlock.instructions.addAll(checkLinearForm.instructions);

        whileBlock.instructions.add(new AsmIfZJmp(this.sourceLocation, checkLinearForm.value, this.whileEndLabel));

        whileBlock.instructions.add(new AsmComment(null, "whileBody", labelIndex));

        for (Statement statement : statements) {
            AsmLinearFormResult statementLinear = statement.asLinearForm(whileBlock);
            whileBlock.instructions.addAll(statementLinear.instructions);
        }

        whileBlock.instructions.add(new AsmJmp(this.sourceLocation, this.whileCheckLabel));
        whileBlock.instructions.add(this.whileEndLabel);

        return result;
    }
}
