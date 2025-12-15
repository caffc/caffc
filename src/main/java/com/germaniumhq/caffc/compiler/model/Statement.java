package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.instruction.Block;
import com.germaniumhq.caffc.compiler.model.instruction.ControlFlowInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.ForInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.IfInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.ReturnInstruction;
import com.germaniumhq.caffc.generated.caffcParser;

public interface Statement extends AstItem {
    static Statement fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.StatementContext antlrStatement) {
        if (antlrStatement.nativeBlock() != null) {
            return NativeBlock.fromAntlr(unit, antlrStatement.nativeBlock(), owner);
        }

        if (antlrStatement.return_() != null) {
            return ReturnInstruction.fromAntlr(unit, owner, antlrStatement.return_());
        }

        if (antlrStatement.expression() != null) {
            return Expression.fromAntlr(unit, owner, antlrStatement.expression());
        }

        if (antlrStatement.assignExpression() != null) {
            return AssignExpression.fromAntlr(unit, owner, antlrStatement.assignExpression());
        }

        if (antlrStatement.ifBlock() != null) {
            return IfInstruction.fromAntlr(unit, owner, antlrStatement.ifBlock());
        }

        if (antlrStatement.forBlock() != null) {
            return ForInstruction.fromAntlr(unit, owner, antlrStatement.forBlock());
        }

        if (antlrStatement.controlFlow() != null) {
            return ControlFlowInstruction.fromAntlr(unit, owner, antlrStatement.controlFlow());
        }

        CaffcCompiler.get().fatal(
                AstItem.fromAntlr(unit.astFilePath, antlrStatement),
                "unknown statement: " + antlrStatement.getText());

        return null; // not reached
    }

    /**
     * Returs the current statement as a linear form representation.
     * @param block
     * @return
     */
    default AsmLinearFormResult asLinearForm(Block block) {
        throw new IllegalStateException("linear form not implemented for " + this.getClass().getCanonicalName());
    }
}
