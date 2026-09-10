package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.HasMethods;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmCall;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmComment;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmIfZJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.expression.LocalVariable;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

public final class ForInInstruction implements Statement, Scope {
    public AstItem owner;

    public SymbolSearch itemTypeSearch;
    public String variableName;
    public Expression iterableExpression;
    public List<Statement> statements = new ArrayList<>();
    public SourceLocation sourceLocation;

    public Symbol iterableTypeSymbol;
    public Symbol iteratorTypeSymbol;
    public Symbol itemTypeSymbol;
    public LocalVariable iteratorVariableDecl;
    public LocalVariable itemVariableDecl;

    public AsmLabel forCheckLabel;
    public AsmLabel forEndLabel;

    public static ForInInstruction fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ForBlockContext forAntlr) {
        ForInInstruction result = new ForInInstruction();

        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, forAntlr);

        if (forAntlr.typeName() != null) {
            result.itemTypeSearch = SymbolSearch.fromAntlr(unit, forAntlr.typeName());
        }
        result.variableName = forAntlr.variableName.getText();

        result.iterableExpression = Expression.fromAntlr(unit, result, forAntlr.expression(0));

        for (caffcParser.StatementContext antlrStatement: forAntlr.block().statement()) {
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
        this.iterableExpression.recurseResolveTypes();

        iterableTypeSymbol = this.iterableExpression.typeSymbol();

        if (!(iterableTypeSymbol instanceof HasMethods)) {
            CaffcCompiler.get().fatal(this, "for-in requires an iterable type (type with newIterator() method), got: " +
                    (itemTypeSearch != null ? itemTypeSearch : iterableTypeSymbol));
        }

        HasMethods iterableClass = (HasMethods) iterableTypeSymbol;
        FunctionDefinition newIterator = iterableClass.getFunction("newIterator");

        if (newIterator == null) {
            CaffcCompiler.get().fatal(this, "no `newIterator` method defined for " + iterableTypeSymbol);
        }

        iteratorTypeSymbol = newIterator.returnType;

        if (!(iteratorTypeSymbol instanceof HasMethods)) {
            CaffcCompiler.get().fatal(this, "iterator must be a HasMethods type, got: " + iteratorTypeSymbol);
        }

        HasMethods iteratorClass = (HasMethods) iteratorTypeSymbol;
        FunctionDefinition next = iteratorClass.getFunction("next");

        if (next == null) {
            CaffcCompiler.get().fatal(this, "no `next` method defined on iterator " + iteratorTypeSymbol);
        }

        itemTypeSymbol = next.returnType;

        if (itemTypeSearch != null) {
            Symbol resolvedType = SymbolResolver.resolveInstantiatedSymbol(this, itemTypeSearch);
            if (resolvedType != null) {
                itemTypeSymbol = resolvedType;
            }
        }

        Function fn = this.findAstParent(Function.class);
        this.iteratorVariableDecl = fn.ensureVariableExists(this, variableName + "_iter", iteratorTypeSymbol);
        this.itemVariableDecl = fn.ensureVariableExists(this, variableName, itemTypeSymbol);

        for (Statement statement : statements) {
            statement.recurseResolveTypes();
        }
    }

    @Override
    public Symbol resolve(String name) {
        return null;
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        int labelIndex = AsmLabel.allocateNumber(this);

        this.forCheckLabel = new AsmLabel(null, "forCheck", labelIndex);
        this.forEndLabel = new AsmLabel(null, "forEnd", labelIndex);

        AsmLinearFormResult result = new AsmLinearFormResult();
        AsmBlock forBlock = new AsmBlock(block);
        result.instructions.add(forBlock);

        forBlock.instructions.add(new AsmComment(null, "forInBegin", labelIndex));

        // variable declarations
        AsmLinearFormResult iterVarLinear = iteratorVariableDecl.asLinearForm(forBlock);
        forBlock.instructions.addAll(iterVarLinear.instructions);

        // Call newIterator on iterable to create iterator
        HasMethods iterableClass = (HasMethods) iterableTypeSymbol;
        FunctionDefinition newIterator = iterableClass.getFunction("newIterator");

        AsmLinearFormResult iterableLinear = this.iterableExpression.asLinearForm(forBlock);
        forBlock.instructions.addAll(iterableLinear.instructions);

        AsmLabel exceptionLabel = this.findAstParent(ExceptionHandler.class).getExceptionHandlingTargetLabel();
        AsmCall newIteratorCall = new AsmCall(this.sourceLocation, exceptionLabel, newIterator, iterableLinear.value);
        newIteratorCall.result = iteratorVariableDecl;
        forBlock.instructions.add(newIteratorCall);

        // Declare the item variable
        AsmLinearFormResult itemVarLinear = itemVariableDecl.asLinearForm(forBlock);
        forBlock.instructions.addAll(itemVarLinear.instructions);

        forBlock.instructions.add(this.forCheckLabel);

        // hasNext check
        HasMethods iteratorClass = (HasMethods) iteratorTypeSymbol;
        FunctionDefinition hasNext = iteratorClass.getFunction("hasNext");

        AsmCall hasNextCall = new AsmCall(this.sourceLocation, exceptionLabel, hasNext, iteratorVariableDecl);
        AsmValue hasNextResult = block.addTempVar(this, hasNext.returnType);
        hasNextCall.result = hasNextResult;
        forBlock.instructions.add(hasNextCall);

        forBlock.instructions.add(new AsmIfZJmp(this.sourceLocation, hasNextResult, this.forEndLabel));

        forBlock.instructions.add(new AsmComment(null, "forInBody", labelIndex));

        // next() call to get the item
        FunctionDefinition next = iteratorClass.getFunction("next");
        AsmCall nextCall = new AsmCall(this.sourceLocation, exceptionLabel, next, iteratorVariableDecl);
        nextCall.result = itemVariableDecl;
        forBlock.instructions.add(nextCall);

        // statements
        for (Statement statement : statements) {
            AsmLinearFormResult statementLinear = statement.asLinearForm(forBlock);
            forBlock.instructions.addAll(statementLinear.instructions);
        }

        // continue the loop
        forBlock.instructions.add(new AsmJmp(this.sourceLocation, this.forCheckLabel));

        // exit the loop
        forBlock.instructions.add(this.forEndLabel);

        return result;
    }
}
