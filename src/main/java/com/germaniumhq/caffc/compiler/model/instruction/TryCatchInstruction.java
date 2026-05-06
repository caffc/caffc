package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmAssign;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmComment;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmIfZJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmInstanceOf;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmGlobalExceptionVar;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclaration;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeDefinitionSymbol;
import com.germaniumhq.caffc.generated.caffcParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public final class TryCatchInstruction implements Statement, ExceptionHandler {
    public AstItem owner;
    public SourceLocation sourceLocation;
    public List<Statement> tryStatements = new ArrayList<>();
    public List<CatchBlock> catchBlocks = new ArrayList<>();
    public List<Statement> finallyStatements = new ArrayList<>();

    public AsmLabel catchLabel;
    public AsmLabel finallyLabel;
    public AsmLabel rethrowLabel;
    public AsmLabel tryEnd;

    public static class CatchBlock2 {
        public SymbolSearch exceptionTypeSearch;
        public TypeDefinitionSymbol exceptionType;
        public String variableName;
        public List<Statement> statements = new ArrayList<>();

    }

    public static TryCatchInstruction fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.TryCatchBlockContext ctx) {
        TryCatchInstruction result = new TryCatchInstruction();

        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, ctx);

        // First block is always the try block
        for (caffcParser.StatementContext statementCtx : ctx.block(0).statement()) {
            result.tryStatements.addAll(Statement.fromAntlr(unit, result, statementCtx));
        }

        // Process catch blocks
        for (caffcParser.CatchBlockContext antlrCatchBlock: ctx.catchBlock()) {
            CatchBlock catchBlock = CatchBlock.fromAntlr(unit, result, antlrCatchBlock);
            
            result.catchBlocks.add(catchBlock);
        }

        // Process finally block if present
        if (ctx.FINALLY() != null) {
            for (caffcParser.StatementContext statementCtx : ctx.block(blockIndex).statement()) {
                result.finallyStatements.addAll(Statement.fromAntlr(unit, result, statementCtx));
            }
        }

        if (result.catchBlocks.isEmpty() && result.finallyStatements.isEmpty()) {
            CaffcCompiler.get().error(result.sourceLocation, "No `catch` nor `finally` found for try block.");
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
        // Resolve types in try statements
        for (Statement statement : tryStatements) {
            statement.recurseResolveTypes();
        }

        // Resolve types in catch blocks
        for (CatchBlock catchBlock : catchBlocks) {
            // Resolve the type from the stored SymbolSearch
            Symbol searchedType = SymbolResolver.mustResolveSymbol(this, catchBlock.exceptionTypeSearch);

            if (!(searchedType instanceof TypeDefinitionSymbol)) {
                CaffcCompiler.get().fatal(this, "catch exception type is not a class/interface");
            }

            catchBlock.exceptionType = (TypeDefinitionSymbol) searchedType;

            for (Statement statement : catchBlock.statements) {
                statement.recurseResolveTypes();
            }
        }

        // Resolve types in finally statements
        for (Statement statement : finallyStatements) {
            statement.recurseResolveTypes();
        }
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        int labelIndex = AsmLabel.allocateNumber(this);

        AsmComment tryStart = new AsmComment(null, "try", labelIndex);

        // this.catchLabel/finallyLabel -> get created on demand if we have catch/finally blocks
        // we create them here since the catch needs the finally label for jumps.
        this.catchLabel = !this.catchBlocks.isEmpty() ? null :
            new AsmLabel(null, "catch", labelIndex);
        this.finallyLabel = !this.finallyStatements.isEmpty() ? null :
            new AsmLabel(null, "finally", labelIndex);
        this.rethrowLabel = new AsmLabel(null, "rethrow", labelIndex);
        this.tryEnd = new AsmLabel(null, "tryEnd", labelIndex);

        // add the try block
        result.instructions.add(tryStart);

        for (Statement tryStatement: tryStatements) {
            AsmLinearFormResult tryStatementLinear = tryStatement.asLinearForm(block);
            result.instructions.addAll(tryStatementLinear.instructions);
        }

        // catch: blocks
        if (!this.catchBlocks.isEmpty()) {
            this.catchLabel = new AsmLabel(null, "catch", labelIndex);
            result.instructions.add(catchLabel);

            // catch blocks should jump to the tryEnd: label
            for (CatchBlock catchBlock : this.catchBlocks) {
                this.renderCatchBlock(result, catchBlock);
            }

            // no catch matched, we need to jump to finally/rethrow
            result.instructions.add(new AsmJmp(null,
                this.finallyLabel != null ? this.finallyLabel : this.rethrowLabel));
        }

        // finally: blocks
        if (!this.finallyStatements.isEmpty()) {
            this.finallyLabel = new AsmLabel(null, "finally", labelIndex);
            this.renderFinallyBlock();
        }

        // rethrow: (rethrow if we still got the exception)
        result.instructions.add(this.rethrowLabel);
        this.renderRethrow(result);

        // tryEnd: jump label if a catch handled the exception
        result.instructions.add(this.tryEnd);
        
        return result;
    }

    private void renderCatchBlock(AsmLinearFormResult result, int labelIndex, CatchBlock catchBlock, AsmBlock parentBlock) {
        AsmBlock block = new AsmBlock(parentBlock);
        AsmLabel catchEnd = new AsmLabel(null, "catchEnd", labelIndex);

        VariableDeclaration isExceptionInstanceOf = block.addTempVar(this, TypeSymbol.BOOL);
        block.instructions.add(new AsmInstanceOf(null, isExceptionInstanceOf,
            AsmGlobalExceptionVar.INSTANCE, catchBlock.exceptionType));

        // exception didn't match, we need to check the next exception
        block.instructions.add(new AsmIfZJmp(null, isExceptionInstanceOf, catchEnd));

        // save exception into local variable + clear global exception
        VariableDeclaration exception = block.addTempVar(this, catchBlock.exceptionType);
        block.instructions.add(new AsmAssign(null, exception, AsmGlobalExceptionVar.INSTANCE));
        block.instructions.add(new AsmAssign(null, AsmGlobalExceptionVar.INSTANCE, AsmConstant.NULL));

        // catchEnd: were done with the handling
        block.instructions.add(catchEnd);
    }

    private void renderFinallyBlock(AsmLinearFormResult result, AsmBlock block) {
        // if we don't have a finally block we don't save/restore the global exception
        if (this.finallyStatements.isEmpty()) {
            return;
        }

        VariableDeclaration exceptionCopy = block.addTempVar(this, TypeSymbol.OBJ);
        result.instructions.add(new AsmAssign(null, exceptionCopy, AsmGlobalExceptionVar.INSTANCE));
        result.instructions.add(new AsmAssign(null, AsmGlobalExceptionVar.INSTANCE, AsmConstant.NULL));

        for (Statement statement: this.finallyStatements) {
            AsmLinearFormResult linearFormStatement = statement.asLinearForm(block);
            result.instructions.addAll(linearFormStatement.instructions);
        }

        result.instructions.add(new AsmAssign(null, AsmGlobalExceptionVar.INSTANCE, exceptionCopy));
    }

    /**
     * We still have the exception, and we need to rethrow it further. We'll find the
     * next exception handling target, and just jump there.
     */
    private void renderRethrow(AsmLinearFormResult result) {
        result.instructions.add(new AsmIfZJmp(null, AsmGlobalExceptionVar.INSTANCE, tryEnd)); // FIXME: source location
        ExceptionHandler exceptionHandler = this.findAstParent(ExceptionHandler.class);

        if (exceptionHandler == null) {
            CaffcCompiler.get().fatal(this, "BUG: catch exceptionHandler not found?");
        }

        // FIXME: source location
        result.instructions.add(new AsmJmp(null, exceptionHandler.getExceptionHandlingTargetLabel()));
    }

    @Override
    public AsmLabel getExceptionHandlingTargetLabel() {
        // we have catch blocks
        if (this.catchLabel != null) {
            return this.catchLabel;
        }

        // we have a finally block we still got to execute
        if (this.finallyLabel != null) {
            return this.finallyLabel;
        }

        // our catch didn't catch the exception, and there's no finally block either
        return this.rethrowLabel;
    }
}
