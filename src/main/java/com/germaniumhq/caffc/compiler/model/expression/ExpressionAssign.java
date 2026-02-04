package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.AstItemCodeRenderer;
import com.germaniumhq.caffc.compiler.model.ClassDefinition;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmAssign;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmCall;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmZeroClear;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmFieldVar;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;
import com.germaniumhq.caffc.output.filters.FilterCTypeName;

import java.util.ArrayList;
import java.util.List;

/**
 * Assign a variable, or multiple variables to some expression.
 *
 */
public final class ExpressionAssign implements Expression {
    public AstItem owner;
    public List<Expression> leftExpressions = new ArrayList<>();
    public Expression right;

    public String astFilePath;
    public int astLine;
    public int astColumn;

    private boolean isResolved;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExAssignContext assignExpression) {
        ExpressionAssign expression = new ExpressionAssign();

        expression.astFilePath = unit.astFilePath;
        expression.astLine = assignExpression.getStart().getLine();
        expression.astColumn = assignExpression.getStart().getCharPositionInLine();

        expression.owner = owner;

        for (int i = 0; i < assignExpression.expression().size() - 1; i++) {
            expression.leftExpressions.add(
                Expression.fromAntlr(unit, expression, assignExpression.expression(i))
            );
        }
        expression.right = Expression.fromAntlr(unit, expression, assignExpression.rightExpression);

        return expression;
    }

    public static ExpressionAssign fromCode(AstItem owner, Expression left, Expression right) {
        ExpressionAssign result = new ExpressionAssign();

        result.owner = owner;
        result.leftExpressions.add(left);
        result.right = right;

        result.astFilePath = owner.getFilePath();
        result.astLine = owner.getLineNumber();
        result.astColumn = owner.getColumnNumber();

        return result;
    }

    public boolean isIndex() {
        return this.leftExpressions.size() == 1 &&
            this.leftExpressions.get(0) instanceof ExpressionIndexAccess;
    }

    public Expression getLeft() {
        if (this.leftExpressions.size() == 1) {
            return this.leftExpressions.get(0);
        }

        throw new IllegalStateException("multiple expressions exist: " + this.leftExpressions);
    }

    @Override
    public Symbol typeSymbol() {
        return new TypeSymbol(TypeName.VOID);
    }

    @Override
    public AstItem getOwner() {
        return owner;
    }

    @Override
    public String getFilePath() {
        return astFilePath;
    }

    @Override
    public int getLineNumber() {
        return astLine;
    }

    @Override
    public int getColumnNumber() {
        return astColumn;
    }

    @Override
    public void recurseResolveTypes() {
        if (isResolved) {
            return;
        }

        isResolved = true;

        this.right.recurseResolveTypes();

        for (Expression leftExpression: this.leftExpressions) {
            leftExpression.recurseResolveTypes();
        }

        if (this.leftExpressions.size() > 1) {
            String cType = FilterCTypeName.getCType(this.right.typeSymbol().typeName());
            AstItem.findParentOrSelf(this.owner, Function.class)
                .ensureVariableExists(this, cType + "_ret", this.right.typeSymbol());
        }

        // FIXME: check if the type is assignable from right to left
    }

    // #UsedInTemplate("assign.peb")
    public boolean isMultiReturn() {
        return this.leftExpressions.size() > 1;
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
            codeRenderer.field("leftExpressions", this.leftExpressions);
            codeRenderer.field("right", this.right);
        });
    }

    @Override
    public AsmLinearFormResult asLinearForm(Function function) {
        // this is an indexed assign, i.e.: arr[i] = 3
        // we need to compute each expression (`arr`, `i` and `3`), then create the
        // setter call for the array.
        if (this.isIndex()) {
            return getAsmLinearIndexAssign(function);
        }

        // this is a multi assign, i.e.: x, arr[i] = someCall()
        // someCall() returns a `struct` that needs destructured in the individual expressions
        // individual expressions can in turn also be simple assigns or indexed assigns
        if (this.isMultiReturn()) {
            return getAsmLinearMultiReturnAssign(function);
        }

        // this is a normal assign, i.e. a = 3
        // we just need to compute the expression and do the assign.
        return getAsmLinearSimpleAssign(function);
    }

    /**
     * Serialize as instructions an indexed assign. We need to make sure the
     * expressions are evaluated in the right order as well. (right to left)
     */
    private AsmLinearFormResult getAsmLinearIndexAssign(Function function) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        ExpressionIndexAccess indexAccess = (ExpressionIndexAccess) this.getLeft();

        AsmLinearFormResult right = this.right.asLinearForm(function);
        AsmLinearFormResult leftIndex = indexAccess.index.asLinearForm(function);
        AsmLinearFormResult leftExpression = indexAccess.expression.asLinearForm(function);

        ClassDefinition leftTypeSymbol = (ClassDefinition) indexAccess.expression.typeSymbol();

        result.instructions.addAll(right.instructions);
        result.instructions.addAll(leftIndex.instructions);
        result.instructions.addAll(leftExpression.instructions);

        // array call
        result.instructions.add(new AsmCall(
            leftTypeSymbol.getFunction("set"),
            leftExpression.value, // _this
            leftIndex.value,      // index
            right.value           // value
        ));

        return result;
    }

    private AsmLinearFormResult getAsmLinearSimpleAssign(Function function) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        AsmLinearFormResult right = this.right.asLinearForm(function);
        AsmLinearFormResult left = this.leftExpressions.get(0).asLinearForm(function);

        result.instructions.addAll(right.instructions);
        result.instructions.addAll(left.instructions);

        result.instructions.add(new AsmAssign((AsmVar) left.value, right.value));

        return result;
    }

    /**
     * Serialize a struct deconstruction assignment.
     */
    private AsmLinearFormResult getAsmLinearMultiReturnAssign(Function function) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        // this right expression since it's a multi-return holds a struct now.
        AsmLinearFormResult right = this.right.asLinearForm(function);
        result.instructions.addAll(right.instructions);
        AsmVar rightStruct = (AsmVar) right.value;

        for (int i = 0; i < this.leftExpressions.size(); i++) {
            Expression left = this.leftExpressions.get(i);
            AsmVar rightAsmVar = AsmFieldVar.fromFieldIndex(rightStruct, i);

            if ((left instanceof ExpressionIndexAccess)) {
                ExpressionIndexAccess indexAccess = (ExpressionIndexAccess) left;

                AsmLinearFormResult leftIndex = indexAccess.index.asLinearForm(function);
                AsmLinearFormResult leftExpression = indexAccess.expression.asLinearForm(function);

                Symbol arrayDefinition = indexAccess.expression.typeSymbol();
                FunctionDefinition setFunction = ((ClassDefinition) arrayDefinition).getFunction("set");

                result.instructions.addAll(leftIndex.instructions);
                result.instructions.addAll(leftExpression.instructions);

                // this is basically: arr_set(leftExpr, leftIndex, rightAsmVar)
                result.instructions.add(new AsmCall(
                    setFunction,
                    leftExpression.value, // _this
                    leftIndex.value,      // index
                    rightAsmVar           // value
                ));
            } else {
                AsmLinearFormResult leftLinear = left.asLinearForm(function);
                result.instructions.addAll(leftLinear.instructions);
                result.instructions.add(new AsmAssign((AsmVar) leftLinear.value, rightAsmVar));
            }

            // after the assignment in the individual variables is done, we don't want the
            // GC to think these values are still used.
            DataType dataType = rightAsmVar.typeSymbol().typeName().dataType;
            if (dataType == DataType.ARRAY || dataType == DataType.OBJECT) {
                result.instructions.add(new AsmZeroClear(rightAsmVar));
            }
        }

        return result;
    }
}
