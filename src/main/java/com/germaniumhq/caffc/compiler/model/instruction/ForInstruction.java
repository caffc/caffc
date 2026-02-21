package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.AssignExpression;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmComment;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmIfZJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclaration;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclarations;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

public final class ForInstruction implements Statement, Scope {
    public AstItem owner;

    public List<VariableDeclaration> variableDeclarations;
    public Expression variableInitializationExpression;
    public Expression checkExpression;
    public Expression incrementExpression;

    public List<Statement> statements = new ArrayList<>();
    public SourceLocation sourceLocation;

    public AsmLabel forCheckLabel;
    public AsmLabel forEndLabel;

    public static ForInstruction fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ForBlockContext forAntlr) {
        ForInstruction result = new ForInstruction();

        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlr(unit.sourceLocation.filePath, forAntlr);

        if (forAntlr.variableDeclarations() != null) {
            result.variableDeclarations =
                VariableDeclarations.fromAntlr(unit, result, forAntlr.variableDeclarations());
        } else {
            result.variableInitializationExpression = AssignExpression.fromAntlr(unit, result, forAntlr.initExpression);
        }

        result.checkExpression = Expression.fromAntlr(unit, result, forAntlr.conditionExpression);

        if (forAntlr.incrementExpression != null) {
            result.incrementExpression = Expression.fromAntlr(unit, result, forAntlr.incrementExpression);
        } else if (forAntlr.incrementAssignExpression != null) {
            result.incrementExpression = AssignExpression.fromAntlr(unit, result, forAntlr.incrementAssignExpression);
        }

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
        if (this.variableDeclarations != null) {
            for (VariableDeclaration variableDeclaration: this.variableDeclarations) {
                variableDeclaration.recurseResolveTypes();
            }
        }

        if (this.variableInitializationExpression != null) {
            this.variableInitializationExpression.recurseResolveTypes();
        }

        this.checkExpression.recurseResolveTypes();
        this.incrementExpression.recurseResolveTypes();

        for (Statement statement: statements) {
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

        this.forCheckLabel = new AsmLabel("forCheck", labelIndex);
        this.forEndLabel = new AsmLabel("forEnd", labelIndex);

        AsmLinearFormResult result = new AsmLinearFormResult();
        AsmBlock forBlock = new AsmBlock(block);
        result.instructions.add(forBlock);

        forBlock.instructions.add(new AsmComment("forBegin", labelIndex));

        // variable declarations
        if (this.variableDeclarations != null) {
            for (var variableDeclaration: this.variableDeclarations) {
                AsmLinearFormResult variableDeclarationLinear = variableDeclaration.asLinearForm(forBlock);
                forBlock.instructions.addAll(variableDeclarationLinear.instructions);
            }
        }

        if (this.variableInitializationExpression != null) {
            AsmLinearFormResult variableInitializationLinear =
                this.variableInitializationExpression.asLinearForm(forBlock);
            forBlock.instructions.addAll(variableInitializationLinear.instructions);
        }

        forBlock.instructions.add(this.forCheckLabel);

        // check
        AsmLinearFormResult checkLinear = this.checkExpression.asLinearForm(forBlock);
        forBlock.instructions.addAll(checkLinear.instructions);

        forBlock.instructions.add(new AsmIfZJmp(checkLinear.value, this.forEndLabel));

        forBlock.instructions.add(new AsmComment("forBlock", labelIndex));

        // statements
        for (Statement statement: statements) {
            AsmLinearFormResult statementLinear = statement.asLinearForm(forBlock);
            forBlock.instructions.addAll(statementLinear.instructions);
        }

        // increment
        AsmLinearFormResult incrementLinear = this.incrementExpression.asLinearForm(forBlock);
        forBlock.instructions.addAll(incrementLinear.instructions);

        // continue the loop
        forBlock.instructions.add(new AsmJmp(this.forCheckLabel));

        // exit the loop
        forBlock.instructions.add(this.forEndLabel);

        return result;
    }
}
