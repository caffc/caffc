package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.ClassDefinition;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.InterfaceDefinition;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmAssign;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBoolOperation;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmCall;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmComment;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmIfZJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionNull;
import com.germaniumhq.caffc.compiler.model.expression.LocalVariable;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code switch} statement. Two forms:
 * <ul>
 *   <li>Boolean: {@code switch { case cond: { ... } default: { ... } }} — if/else-if/else</li>
 *   <li>Value: {@code switch x { case 3: { ... } default: { ... } }} — match on value</li>
 * </ul>
 * Value switches on objects require {@code HasEquals}. Null switch values are safe;
 * {@code case null:} matches when the switched value is null.
 */
public final class SwitchInstruction implements Statement {
    public AstItem owner;
    public SourceLocation sourceLocation;

    /** Null for the boolean form. */
    public Expression switchExpression;
    public List<SwitchCase> cases = new ArrayList<>();

    public LocalVariable switchValueVar;
    public AsmLabel endLabel;

    private FunctionDefinition equalsFunction;

    public static SwitchInstruction fromAntlr(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.SwitchBlockContext switchAntlr) {
        SwitchInstruction result = new SwitchInstruction();
        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, switchAntlr);

        if (switchAntlr.expression() != null) {
            result.switchExpression = Expression.fromAntlr(unit, result, switchAntlr.expression());
        }

        for (caffcParser.SwitchBranchContext branch : switchAntlr.switchBranch()) {
            result.cases.add(SwitchCase.fromAntlr(unit, result, branch));
        }

        if (result.cases.isEmpty()) {
            CaffcCompiler.get().error(result, "switch must have at least one case or default branch");
        }

        int defaultCount = 0;
        for (SwitchCase switchCase : result.cases) {
            if (switchCase.isDefault) {
                defaultCount++;
            }
        }
        if (defaultCount > 1) {
            CaffcCompiler.get().error(result, "switch can have at most one default branch");
        }

        return result;
    }

    public boolean isValueSwitch() {
        return switchExpression != null;
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
        if (switchExpression != null) {
            switchExpression.recurseResolveTypes();
            Symbol switchType = switchExpression.typeSymbol();

            if (switchType.typeName().dataType == DataType.OBJECT) {
                equalsFunction = resolveEqualsFunction(switchType);
                if (equalsFunction == null) {
                    CaffcCompiler.get().error(switchExpression,
                            "switch on objects requires HasEquals, got: " + switchType.typeName().fqdn());
                }
            }

            Function fn = this.findAstParent(Function.class);
            this.switchValueVar = fn.ensureVariableExists(
                    this,
                    "_caffc_switch_" + this.sourceLocation.lineNumber + "_" + this.sourceLocation.columnNumber,
                    switchType);
        }

        for (SwitchCase switchCase : cases) {
            switchCase.recurseResolveTypes();

            if (!switchCase.isDefault && !isValueSwitch()) {
                Symbol caseType = switchCase.caseExpression.typeSymbol();
                if (!TypeSymbol.BOOL.equals(caseType)) {
                    CaffcCompiler.get().error(switchCase.caseExpression,
                            "boolean switch case must be a bool expression, got: " +
                                    (caseType.typeName() != null ? caseType.typeName().fqdn() : caseType));
                }
            }

            if (!switchCase.isDefault && isValueSwitch() && switchCase.caseExpression instanceof ExpressionNull) {
                ((ExpressionNull) switchCase.caseExpression).setTypeSymbol(switchExpression.typeSymbol());
            }
        }
    }

    private FunctionDefinition resolveEqualsFunction(Symbol switchType) {
        InterfaceDefinition hasEquals = SymbolResolver.mustResolveSymbol(
                this, SymbolSearch.ofName("HasEquals"));

        if (switchType instanceof ClassDefinition classDefinition) {
            if (!classDefinition.isImplementing(hasEquals)) {
                return null;
            }
            FunctionDefinition equals = classDefinition.getFunction("equals");
            if (equals != null) {
                return equals;
            }
            return hasEquals.getFunction("equals");
        }

        if (switchType instanceof InterfaceDefinition interfaceDefinition) {
            if (hasEquals.isAssignableFrom(interfaceDefinition)) {
                return hasEquals.getFunction("equals");
            }
            for (var implemented : interfaceDefinition.getImplementedTypes()) {
                if (implemented instanceof InterfaceDefinition parent &&
                        hasEquals.isAssignableFrom(parent)) {
                    return hasEquals.getFunction("equals");
                }
            }
        }

        return null;
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        int labelIndex = AsmLabel.allocateNumber(this);
        this.endLabel = new AsmLabel(null, "switchEnd", labelIndex);

        AsmLinearFormResult result = new AsmLinearFormResult();
        AsmBlock switchBlock = new AsmBlock(block);
        result.instructions.add(switchBlock);

        switchBlock.instructions.add(new AsmComment(null, "switchBegin", labelIndex));

        AsmValue switchValue = null;
        if (isValueSwitch()) {
            AsmLinearFormResult switchLinear = switchExpression.asLinearForm(switchBlock);
            switchBlock.instructions.addAll(switchLinear.instructions);
            switchBlock.instructions.add(new AsmAssign(this.sourceLocation, switchValueVar, switchLinear.value));
            switchValue = switchValueVar;
        }

        List<SwitchCase> nonDefaultCases = new ArrayList<>();
        SwitchCase defaultCase = null;
        for (SwitchCase switchCase : cases) {
            if (switchCase.isDefault) {
                defaultCase = switchCase;
            } else {
                nonDefaultCases.add(switchCase);
            }
        }

        for (int i = 0; i < nonDefaultCases.size(); i++) {
            SwitchCase switchCase = nonDefaultCases.get(i);
            AsmLabel nextLabel = new AsmLabel(null, "switchNext", AsmLabel.allocateNumber(this));

            AsmValue matchValue;
            if (isValueSwitch()) {
                matchValue = emitValueMatch(switchBlock, switchValue, switchCase, nextLabel);
            } else {
                AsmLinearFormResult caseLinear = switchCase.caseExpression.asLinearForm(switchBlock);
                switchBlock.instructions.addAll(caseLinear.instructions);
                matchValue = caseLinear.value;
            }

            switchBlock.instructions.add(new AsmIfZJmp(this.sourceLocation, matchValue, nextLabel));

            emitCaseBody(switchBlock, switchCase);
            switchBlock.instructions.add(new AsmJmp(this.sourceLocation, this.endLabel));
            switchBlock.instructions.add(nextLabel);
        }

        if (defaultCase != null) {
            switchBlock.instructions.add(new AsmComment(null, "switchDefault", labelIndex));
            emitCaseBody(switchBlock, defaultCase);
        }

        switchBlock.instructions.add(this.endLabel);
        return result;
    }

    /**
     * Emits comparison of the switch value against a case. For object cases that are not
     * {@code null}, skips {@code equals} when the switch value is null (jumps to nextLabel).
     */
    private AsmValue emitValueMatch(
            AsmBlock switchBlock,
            AsmValue switchValue,
            SwitchCase switchCase,
            AsmLabel nextLabel) {
        DataType dataType = switchExpression.typeSymbol().typeName().dataType;

        if (switchCase.caseExpression instanceof ExpressionNull) {
            AsmVar resultVar = switchBlock.addTempVar(this, TypeSymbol.BOOL);
            switchBlock.instructions.add(new AsmBoolOperation(
                    this.sourceLocation,
                    resultVar,
                    "==",
                    switchValue,
                    new AsmConstant(switchExpression.typeSymbol(), null)));
            return resultVar;
        }

        AsmLinearFormResult caseLinear = switchCase.caseExpression.asLinearForm(switchBlock);
        switchBlock.instructions.addAll(caseLinear.instructions);

        if (dataType == DataType.OBJECT) {
            // if (!switchValue) goto next — do not call equals on null
            switchBlock.instructions.add(new AsmIfZJmp(this.sourceLocation, switchValue, nextLabel));

            AsmLabel exceptionLabel = this.findAstParent(ExceptionHandler.class).getExceptionHandlingTargetLabel();
            AsmCall equalsCall = new AsmCall(
                    this.sourceLocation,
                    exceptionLabel,
                    equalsFunction,
                    switchValue,
                    caseLinear.value);
            AsmVar equalsResult = switchBlock.addTempVar(this, TypeSymbol.BOOL);
            equalsCall.result = equalsResult;
            switchBlock.instructions.add(equalsCall);
            return equalsResult;
        }

        // primitives and arrays: ==
        AsmVar resultVar = switchBlock.addTempVar(this, TypeSymbol.BOOL);
        switchBlock.instructions.add(new AsmBoolOperation(
                this.sourceLocation,
                resultVar,
                "==",
                switchValue,
                caseLinear.value));
        return resultVar;
    }

    private void emitCaseBody(AsmBlock switchBlock, SwitchCase switchCase) {
        AsmBlock caseBlock = new AsmBlock(switchBlock);
        switchBlock.instructions.add(caseBlock);

        for (Statement statement : switchCase.statements) {
            caseBlock.instructions.addAll(statement.asLinearForm(caseBlock).instructions);
        }
    }
}
