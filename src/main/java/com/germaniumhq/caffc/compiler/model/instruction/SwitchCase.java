package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

/**
 * A single {@code case} or {@code default} branch inside a {@link SwitchInstruction}.
 */
public final class SwitchCase implements AstItem {
    public AstItem owner;
    public SourceLocation sourceLocation;

    /** Null when this is the {@code default} branch. */
    public Expression caseExpression;
    public boolean isDefault;
    public List<Statement> statements = new ArrayList<>();

    public static SwitchCase fromAntlr(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.SwitchBranchContext branch) {
        SwitchCase result = new SwitchCase();
        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, branch);

        if (branch.DEFAULT() != null) {
            result.isDefault = true;
        } else {
            result.caseExpression = Expression.fromAntlr(unit, result, branch.expression());
        }

        parseBody(unit, result, branch.switchCaseBody());
        return result;
    }

    private static void parseBody(
            CompilationUnit unit,
            SwitchCase result,
            caffcParser.SwitchCaseBodyContext body) {
        if (body.block() != null) {
            for (caffcParser.StatementContext antlrStatement : body.block().statement()) {
                result.statements.addAll(Statement.fromAntlr(unit, result, antlrStatement));
            }
        } else if (body.return_() != null) {
            result.statements.add(ReturnInstruction.fromAntlr(unit, result, body.return_()));
        } else if (body.controlFlow() != null) {
            result.statements.add(ControlFlowInstruction.fromAntlr(unit, result, body.controlFlow()));
        } else {
            CaffcCompiler.get().error(result, "unknown statements for switch case");
        }
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
        if (caseExpression != null) {
            caseExpression.recurseResolveTypes();
        }

        for (Statement statement : statements) {
            statement.recurseResolveTypes();
        }
    }
}
