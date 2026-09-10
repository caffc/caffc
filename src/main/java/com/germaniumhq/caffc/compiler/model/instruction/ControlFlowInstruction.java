package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ControlFlowInstruction implements Statement {
    public AstItem owner;
    public SourceLocation sourceLocation;

    public String instruction;

    public static ControlFlowInstruction fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ControlFlowContext ctx) {
        ControlFlowInstruction result = new ControlFlowInstruction();

        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, ctx);

        result.instruction = ctx.getText();

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
    }

    public Function getFunction() {
        return this.findAstParent(Function.class);
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();
        boolean isContinue = "continue".equals(instruction);

        AstItem parent = this.getOwner();
        while (parent != null) {
            if (isContinue) {
                AsmLabel continueLabel = continueTarget(parent);
                if (continueLabel != null) {
                    result.instructions.add(new AsmJmp(this.sourceLocation, continueLabel));
                    return result;
                }
            } else {
                AsmLabel breakLabel = breakTarget(parent);
                if (breakLabel != null) {
                    result.instructions.add(new AsmJmp(this.sourceLocation, breakLabel));
                    return result;
                }
            }
            parent = parent.getOwner();
        }

        CaffcCompiler.get().fatal(this,
                isContinue
                        ? "continue outside of loop"
                        : "break outside of loop or switch");
        return result;
    }

    private static AsmLabel continueTarget(AstItem parent) {
        if (parent instanceof ForInstruction forInstruction) {
            return forInstruction.forCheckLabel;
        }
        if (parent instanceof ForInInstruction forInInstruction) {
            return forInInstruction.forCheckLabel;
        }
        if (parent instanceof WhileInstruction whileInstruction) {
            return whileInstruction.whileCheckLabel;
        }
        return null;
    }

    private static AsmLabel breakTarget(AstItem parent) {
        if (parent instanceof SwitchInstruction switchInstruction) {
            return switchInstruction.endLabel;
        }
        if (parent instanceof ForInstruction forInstruction) {
            return forInstruction.forEndLabel;
        }
        if (parent instanceof ForInInstruction forInInstruction) {
            return forInInstruction.forEndLabel;
        }
        if (parent instanceof WhileInstruction whileInstruction) {
            return whileInstruction.whileEndLabel;
        }
        return null;
    }
}
