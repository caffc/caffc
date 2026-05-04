package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmAssign;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmIfZJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmInstanceOf;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmZeroClear;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmGlobalExceptionVar;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeDefinitionSymbol;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

public final class TryCatchInstruction implements Statement {
    public AstItem owner;
    public SourceLocation sourceLocation;
    public List<Statement> tryStatements = new ArrayList<>();
    public List<CatchBlock> catchBlocks = new ArrayList<>();
    public List<Statement> finallyStatements = new ArrayList<>();

    public static class CatchBlock {
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
        int blockIndex = 1;
        for (int i = 0; i < ctx.CATCH().size(); i++) {
            CatchBlock catchBlock = new CatchBlock();
            
            catchBlock.exceptionTypeSearch = SymbolSearch.fromAntlr(unit, ctx.classType(i));
            catchBlock.variableName = ctx.ID(i).getText();
            
            for (caffcParser.StatementContext statementCtx : ctx.block(blockIndex).statement()) {
                catchBlock.statements.addAll(Statement.fromAntlr(unit, result, statementCtx));
            }
            
            result.catchBlocks.add(catchBlock);
            blockIndex++;
        }

        // Process finally block if present
        if (ctx.FINALLY() != null) {
            for (caffcParser.StatementContext statementCtx : ctx.block(blockIndex).statement()) {
                result.finallyStatements.addAll(Statement.fromAntlr(unit, result, statementCtx));
            }
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
        // Try/catch/finally implementation in linear form:
        // 1. Save current exception
        // 2. Clear exception
        // 3. Execute try block
        // 4. Jump over catch blocks
        // 5. Catch blocks check exception type
        // 6. Execute finally block
        // 7. Restore exception
        
        AsmLinearFormResult result = new AsmLinearFormResult();
        
        // Generate unique labels
        int labelIndex = AsmLabel.allocateNumber(this);
        AsmLabel tryEndLabel = new AsmLabel(this.sourceLocation, "try_end", labelIndex);
        AsmLabel finallyLabel = new AsmLabel(this.sourceLocation, "finally", labelIndex);
        AsmLabel finallyEndLabel = new AsmLabel(this.sourceLocation, "finally_end", labelIndex);
        
        // Save current exception
        AsmVar savedExceptionVar = block.addTempVar(this, TypeSymbol.OBJ);
        result.instructions.add(new AsmAssign(this.sourceLocation, savedExceptionVar, AsmGlobalExceptionVar.INSTANCE));
        
        // Clear exception
        result.instructions.add(new AsmZeroClear(this.sourceLocation, AsmGlobalExceptionVar.INSTANCE));
        
      // Add try block with exception checking after each statement
        AsmLabel tryExceptionHandler = new AsmLabel(this.sourceLocation, "try_exception_" + labelIndex, AsmLabel.allocateNumber(this));
        for (Statement statement : tryStatements) {
            result.instructions.addAll(statement.asLinearForm(block).instructions);
            // Add exception check after each statement
            // If exception == NULL, continue to next statement
            // If exception != NULL, jump to try's local exception handler
            AsmLabel nextStatementLabel = new AsmLabel(this.sourceLocation, "next_stmt_" + labelIndex, AsmLabel.allocateNumber(this));
            result.instructions.add(new AsmIfZJmp(this.sourceLocation, AsmGlobalExceptionVar.INSTANCE, nextStatementLabel));
            result.instructions.add(new AsmJmp(this.sourceLocation, tryExceptionHandler));
            result.instructions.add(nextStatementLabel);
        }
        
        // Jump over catch blocks to finally
        result.instructions.add(new AsmJmp(this.sourceLocation, finallyLabel));
        
        // Try's local exception handler - restore exception and rethrow to function's handler
        result.instructions.add(tryExceptionHandler);
        result.instructions.add(new AsmAssign(this.sourceLocation, AsmGlobalExceptionVar.INSTANCE, savedExceptionVar));
        result.instructions.add(new AsmJmp(this.sourceLocation, new AsmLabel(this.sourceLocation, "exception_handler", AsmLabel.allocateNumber(this))));
        
        // Add catch blocks
        for (int i = 0; i < catchBlocks.size(); i++) {
            CatchBlock catchBlock = catchBlocks.get(i);
            AsmLabel catchLabel = new AsmLabel(this.sourceLocation, "catch_" + catchBlock.variableName, labelIndex);
            AsmLabel nextCatchLabel = new AsmLabel(this.sourceLocation, "next_catch_" + i, labelIndex);
            
            result.instructions.add(catchLabel);
            
            // Check if exception is not null
            result.instructions.add(new AsmIfZJmp(this.sourceLocation, AsmGlobalExceptionVar.INSTANCE, nextCatchLabel));
            
            // Check instanceof
            AsmVar instanceofResult = block.addTempVar(this, TypeSymbol.BOOL);
            result.instructions.add(new AsmInstanceOf(
                this.sourceLocation,
                instanceofResult,
                AsmGlobalExceptionVar.INSTANCE,
                catchBlock.exceptionType
            ));
            
            // If instanceof fails, jump to next catch
            result.instructions.add(new AsmIfZJmp(this.sourceLocation, instanceofResult, nextCatchLabel));
            
            // Clear exception
            result.instructions.add(new AsmZeroClear(this.sourceLocation, AsmGlobalExceptionVar.INSTANCE));
            
            // Execute catch block statements
            for (Statement statement : catchBlock.statements) {
                result.instructions.addAll(statement.asLinearForm(block).instructions);
            }
            
            // Jump to finally
            result.instructions.add(new AsmJmp(this.sourceLocation, finallyLabel));
            
            // Next catch label
            result.instructions.add(nextCatchLabel);
        }
        
        // No catch block matched - restore exception and rethrow
        result.instructions.add(new AsmAssign(this.sourceLocation, AsmGlobalExceptionVar.INSTANCE, savedExceptionVar));
        result.instructions.add(new AsmJmp(this.sourceLocation, new AsmLabel(this.sourceLocation, "exception_handler", AsmLabel.allocateNumber(this))));
        
        // Finally block
        result.instructions.add(finallyLabel);
        
        for (Statement statement : finallyStatements) {
            result.instructions.addAll(statement.asLinearForm(block).instructions);
        }
        
        // Restore exception
        result.instructions.add(new AsmAssign(this.sourceLocation, AsmGlobalExceptionVar.INSTANCE, savedExceptionVar));
        
        // End
        result.instructions.add(finallyEndLabel);
        
        return result;
    }
 }
