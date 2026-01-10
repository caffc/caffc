package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclarations;
import com.germaniumhq.caffc.compiler.model.instruction.ControlFlowInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.ForInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.IfInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.ReturnInstruction;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.List;

public interface Statement extends AstItem {
    static List<Statement> fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.StatementContext antlrStatement) {
        if (antlrStatement.nativeBlock() != null) {
            return List.of(NativeBlock.fromAntlr(unit, antlrStatement.nativeBlock(), owner));
        }

        if (antlrStatement.return_() != null) {
            return List.of(ReturnInstruction.fromAntlr(unit, owner, antlrStatement.return_()));
        }

        if (antlrStatement.expression() != null) {
            return List.of(Expression.fromAntlr(unit, owner, antlrStatement.expression()));
        }

        if (antlrStatement.assignExpression() != null) {
            return List.of(AssignExpression.fromAntlr(unit, owner, antlrStatement.assignExpression()));
        }

        if (antlrStatement.ifBlock() != null) {
            return List.of(IfInstruction.fromAntlr(unit, owner, antlrStatement.ifBlock()));
        }

        if (antlrStatement.forBlock() != null) {
            return List.of(ForInstruction.fromAntlr(unit, owner, antlrStatement.forBlock()));
        }

        if (antlrStatement.controlFlow() != null) {
            return List.of(ControlFlowInstruction.fromAntlr(unit, owner, antlrStatement.controlFlow()));
        }

        if (antlrStatement.variableDeclarations() != null) {
            return VariableDeclarations.fromAntlr(unit, owner, antlrStatement.variableDeclarations());
        }

        CaffcCompiler.get().fatal(
                AstItem.fromAntlr(unit.astFilePath, antlrStatement),
                "unknown statement: " + antlrStatement.getText() + " with class " + antlrStatement.getClass().getCanonicalName());

        return null; // not reached
    }

    /**
     * Returs the current statement as a linear form representation.
     * @param block
     * @return
     */
    default AsmLinearFormResult asLinearForm(AsmBlock block) {
        CaffcCompiler.get().fatal(this, "linear form not implemented for " + this.getClass().getCanonicalName());
        return null; // not reached
    }
}
