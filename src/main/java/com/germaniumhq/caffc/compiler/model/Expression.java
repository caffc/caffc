package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionBitNot;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionBitOperation;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionBoolCompare;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionBoolNot;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionBoolOperation;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionCast;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionDotAccess;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionFnCall;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionId;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionIndexAccess;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionMath;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionNewArray;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionNewObject;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionNumber;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionParens;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionShift;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionString;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionTernary;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionUnaryMinus;
import com.germaniumhq.caffc.compiler.model.source.SourceItem;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

/**
 * A language expression.
 */
public interface Expression extends Statement {
    /**
     * The Type that will be returned by the evaluation of the expression.
     * This typeSymbol is a reference to the actual type (i.e. ClassDefinition)
     * and not a reference to variables or fields.
     */
    Symbol typeSymbol();

    static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExpressionContext expression) {
        // keep in sync with CaffcPebblesExtension and caffc.g4
        if (expression instanceof caffcParser.ExNumberContext numberExpression) {
            return ExpressionNumber.fromAntlr(unit, owner, numberExpression);
        }

        if (expression instanceof caffcParser.ExStringContext stringExpression) {
            return ExpressionString.fromAntlr(unit, owner, stringExpression);
        }

        if (expression instanceof caffcParser.ExIdContext idExpression) {
            return ExpressionId.fromAntlr(unit, owner, idExpression);
        }

        if (expression instanceof caffcParser.ExDotAccessContext dotAccessExpression) {
            return ExpressionDotAccess.fromAntlr(unit, owner, dotAccessExpression);
        }

        if (expression instanceof caffcParser.ExNewObjectContext newObjectExpression) {
            return ExpressionNewObject.fromAntlr(unit, owner, newObjectExpression);
        }

        if (expression instanceof caffcParser.ExNewArrayContext newArrayExpression) {
            return ExpressionNewArray.fromAntlr(unit, owner, newArrayExpression);
        }

        if (expression instanceof caffcParser.ExFnCallContext fnCallExpression) {
            return ExpressionFnCall.fromAntlr(unit, owner, fnCallExpression);
        }

        if (expression instanceof caffcParser.ExIndexAccessContext indexAccessExpression) {
            return ExpressionIndexAccess.fromAntlr(unit, owner, indexAccessExpression);
        }

        if (expression instanceof caffcParser.ExCastContext castContext) {
            return ExpressionCast.fromAntlr(unit, owner, castContext);
        }

        if (expression instanceof caffcParser.ExParensContext parensContext) {
            return ExpressionParens.fromAntlr(unit, owner, parensContext);
        }

        if (expression instanceof caffcParser.ExBoolNotContext boolNotContext) {
            return ExpressionBoolNot.fromAntlr(unit, owner, boolNotContext);
        }

        if (expression instanceof caffcParser.ExBitNotContext bitNotContext) {
            return ExpressionBitNot.fromAntlr(unit, owner, bitNotContext);
        }

        if (expression instanceof caffcParser.ExUnaryMinusContext unaryMinusContext) {
            return ExpressionUnaryMinus.fromAntlr(unit, owner, unaryMinusContext);
        }

        if (expression instanceof caffcParser.ExMulModContext mulModContext) {
            return ExpressionMath.fromAntlrMulMod(unit, owner, mulModContext);
        }

        if (expression instanceof caffcParser.ExDivContext divContext) {
            return ExpressionMath.fromAntlrDiv(unit, owner, divContext);
        }

        if (expression instanceof caffcParser.ExAddSubContext addSubContext) {
            return ExpressionMath.fromAntlr(unit, owner, addSubContext);
        }

        if (expression instanceof caffcParser.ExShiftContext shiftContext) {
            return ExpressionShift.fromAntlr(unit, owner, shiftContext);
        }

        if (expression instanceof caffcParser.ExLtLteGtGteContext ltLteGtGteContext) {
            return ExpressionBoolCompare.fromAntlr2(unit, owner, ltLteGtGteContext);
        }

        if (expression instanceof caffcParser.ExEqNeqContext eqNeqContext) {
            return ExpressionBoolCompare.fromAntlr(unit, owner, eqNeqContext);
        }

        // FIXME: these shouldn't allow chaining bit operations of different types without parens
        //        so: a | b | c <-- fine
        //            a & b & c <-- fine
        //            a | b ^ c <-- not fine
        if (expression instanceof caffcParser.ExBitAndContext bitAndContext) {
            return ExpressionBitOperation.fromAntlr(unit, owner, bitAndContext);
        }

        if (expression instanceof caffcParser.ExBitXorContext bitXorContext) {
            return ExpressionBitOperation.fromAntlr(unit, owner, bitXorContext);
        }

        if (expression instanceof caffcParser.ExBitOrContext bitOrContext) {
            return ExpressionBitOperation.fromAntlr(unit, owner, bitOrContext);
        }

        if (expression instanceof caffcParser.ExBoolAndContext exBoolAndContext) {
            return ExpressionBoolOperation.fromAntlr(unit, owner, exBoolAndContext);
        }

        if (expression instanceof caffcParser.ExBoolOrContext exBoolOrContext) {
            return ExpressionBoolOperation.fromAntlr(unit, owner, exBoolOrContext);
        }

        if (expression instanceof caffcParser.ExTernaryContext exTernaryContext) {
            return ExpressionTernary.fromAntlr(unit, owner, exTernaryContext);
        }

        if (expression == null) {
            CaffcCompiler.get().fatal(
                    owner,
                    "null expression passed from owner");
        }

        CaffcCompiler.get().fatal(
                SourceItem.fromAntlr(unit.astFilePath, expression),
                "unsupported expression: " + expression.getText());

        return null; // not reached
    }
}
