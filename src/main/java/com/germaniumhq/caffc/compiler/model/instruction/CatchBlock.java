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
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmGlobalExceptionVar;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclaration;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeDefinitionSymbol;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

public class CatchBlock implements Statement, ExceptionHandler, Scope {
    public TryCatchInstruction owner;
    public SourceLocation sourceLocation;

    private SymbolSearch exceptionTypeSearch;
    private TypeDefinitionSymbol exceptionType;

    private String variableName;
    private List<Statement> statements = new ArrayList<>();

    public static CatchBlock fromAntlr(CompilationUnit unit, TryCatchInstruction owner, caffcParser.CatchBlockContext ctx) {
        CatchBlock result = new CatchBlock();

        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, ctx);

        result.exceptionTypeSearch = SymbolSearch.fromAntlr(unit, ctx.classType());
        result.variableName = ctx.ID().getText();

        for (caffcParser.StatementContext statementCtx : ctx.block().statement()) {
            result.statements.addAll(Statement.fromAntlr(unit, result, statementCtx));
        }

        return result;
    }

    @Override
    public AsmLabel getExceptionHandlingTargetLabel() {
        // Here is a bit tricky. When we're in the catch block there's no active exception, since we've already
        // copied it into our local variable. Now we need to either run the `finally` block if it exists, or to
        // jump to the next exception handling _skipping_ the current try/catch parent.
        if (owner.finallyLabel != null) {
            return owner.finallyLabel;
        }

        ExceptionHandler exceptionHandler = owner.findAstParent(ExceptionHandler.class);

        return exceptionHandler.getExceptionHandlingTargetLabel();
    }

    @Override
    public Symbol resolve(String name) {
        return null;
    }

    @Override
    public AstItem getOwner() {
        return owner;
    }

    @Override
    public void recurseResolveTypes() {
        Symbol foundSymbol = SymbolResolver.mustResolveSymbol(this, this.exceptionTypeSearch);

        if (!(foundSymbol instanceof TypeDefinitionSymbol)) {
            CaffcCompiler.get().error(this, "Exception type is not an actual object type, instead was: " + foundSymbol.typeName());
            return;
        }

        this.exceptionType = (TypeDefinitionSymbol) foundSymbol;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        AsmLabel catchEnd = new AsmLabel(null, "catchEnd", labelIndex);

        VariableDeclaration isExceptionInstanceOf = block.addTempVar(this, TypeSymbol.BOOL);
        result.instructions.add(new AsmInstanceOf(null, isExceptionInstanceOf,
            AsmGlobalExceptionVar.INSTANCE, this.exceptionType));

        // exception didn't match, we need to check the next exception
        result.instructions.add(new AsmIfZJmp(null, isExceptionInstanceOf, catchEnd));

        // save exception into local variable + clear global exception
        VariableDeclaration exception = block.addTempVar(this, this.exceptionType);
        result.instructions.add(new AsmAssign(null, exception, AsmGlobalExceptionVar.INSTANCE));
        result.instructions.add(new AsmAssign(null, AsmGlobalExceptionVar.INSTANCE, AsmConstant.NULL));

        // catchEnd: were done with the handling
        result.instructions.add(catchEnd);

        return result;
    }
}
