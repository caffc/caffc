package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.expression.CallArgument;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionArrayPack;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionAssign;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionBitNot;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionBitOperation;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionBoolCompare;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionBoolNot;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionBoolOperation;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionCast;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionDotAccess;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionFString;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionFnCall;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionId;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionIndexAccess;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionInstanceOf;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionKwargsPack;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionLambda;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionMath;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionNewArray;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionNewObject;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionOpAssign;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionParens;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionRangeAccess;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionShift;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionTernary;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionUnaryMinus;
import com.germaniumhq.caffc.compiler.model.expression.LocalVariable;
import com.germaniumhq.caffc.compiler.model.instruction.CatchBlock;
import com.germaniumhq.caffc.compiler.model.instruction.ForInInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.ForInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.IfInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.ReturnInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.SwitchCase;
import com.germaniumhq.caffc.compiler.model.instruction.SwitchInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.ThrowInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.TryCatchInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.WhileInstruction;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Detects enclosing locals/params used inside a lambda body and rewrites them to
 * {@code _this.field} accesses on the synthetic capture class.
 */
public final class LambdaCapture {
    public final String name;
    public final SymbolSearch typeSearch;
    final SourceLocationRef location;

    private LambdaCapture(String name, SymbolSearch typeSearch, SourceLocationRef location) {
        this.name = name;
        this.typeSearch = typeSearch;
        this.location = location;
    }

    /**
     * Lightweight holder so we don't require SourceLocation in every call site.
     */
    record SourceLocationRef(com.germaniumhq.caffc.compiler.model.source.SourceLocation sourceLocation) {
    }

    static List<LambdaCapture> collectAndRewrite(
            Function bindingFunction,
            Function callFunction,
            List<Statement> bodyStatements) {
        Map<String, LambdaCapture> captures = new LinkedHashMap<>();
        for (int i = 0; i < bodyStatements.size(); i++) {
            bodyStatements.set(i, rewriteStatement(bindingFunction, callFunction, bodyStatements.get(i), captures));
        }
        return new ArrayList<>(captures.values());
    }

    private static Statement rewriteStatement(
            Function bindingFunction,
            Function callFunction,
            Statement statement,
            Map<String, LambdaCapture> captures) {
        if (statement instanceof Expression expression) {
            return rewriteExpression(bindingFunction, callFunction, expression, captures);
        }
        if (statement instanceof LocalVariable localVariable) {
            if (localVariable.assignExpression != null) {
                localVariable.assignExpression = (ExpressionAssign) rewriteExpression(
                        bindingFunction, callFunction, localVariable.assignExpression, captures);
            }
            return localVariable;
        }
        if (statement instanceof ReturnInstruction returnInstruction) {
            for (ReturnInstruction.NamedReturn namedReturn : returnInstruction.returns) {
                if (namedReturn.value != null) {
                    namedReturn.value = rewriteExpression(
                            bindingFunction, callFunction, namedReturn.value, captures);
                }
            }
            return returnInstruction;
        }
        if (statement instanceof IfInstruction ifInstruction) {
            ifInstruction.checkExpression = rewriteExpression(
                    bindingFunction, callFunction, ifInstruction.checkExpression, captures);
            rewriteStatementList(bindingFunction, callFunction, ifInstruction.statements, captures);
            if (ifInstruction.elseStatements != null) {
                rewriteStatementList(bindingFunction, callFunction, ifInstruction.elseStatements, captures);
            }
            return ifInstruction;
        }
        if (statement instanceof WhileInstruction whileInstruction) {
            whileInstruction.checkExpression = rewriteExpression(
                    bindingFunction, callFunction, whileInstruction.checkExpression, captures);
            rewriteStatementList(bindingFunction, callFunction, whileInstruction.statements, captures);
            return whileInstruction;
        }
        if (statement instanceof ForInstruction forInstruction) {
            if (forInstruction.variableInitializationExpression != null) {
                forInstruction.variableInitializationExpression = rewriteExpression(
                        bindingFunction, callFunction, forInstruction.variableInitializationExpression, captures);
            }
            if (forInstruction.localVariables != null) {
                for (LocalVariable localVariable : forInstruction.localVariables) {
                    if (localVariable.assignExpression != null) {
                        localVariable.assignExpression = (ExpressionAssign) rewriteExpression(
                                bindingFunction, callFunction, localVariable.assignExpression, captures);
                    }
                }
            }
            forInstruction.checkExpression = rewriteExpression(
                    bindingFunction, callFunction, forInstruction.checkExpression, captures);
            forInstruction.incrementExpression = rewriteExpression(
                    bindingFunction, callFunction, forInstruction.incrementExpression, captures);
            rewriteStatementList(bindingFunction, callFunction, forInstruction.statements, captures);
            return forInstruction;
        }
        if (statement instanceof ForInInstruction forInInstruction) {
            forInInstruction.iterableExpression = rewriteExpression(
                    bindingFunction, callFunction, forInInstruction.iterableExpression, captures);
            rewriteStatementList(bindingFunction, callFunction, forInInstruction.statements, captures);
            return forInInstruction;
        }
        if (statement instanceof SwitchInstruction switchInstruction) {
            if (switchInstruction.switchExpression != null) {
                switchInstruction.switchExpression = rewriteExpression(
                        bindingFunction, callFunction, switchInstruction.switchExpression, captures);
            }
            for (SwitchCase switchCase : switchInstruction.cases) {
                if (switchCase.caseExpression != null) {
                    switchCase.caseExpression = rewriteExpression(
                            bindingFunction, callFunction, switchCase.caseExpression, captures);
                }
                rewriteStatementList(bindingFunction, callFunction, switchCase.statements, captures);
            }
            return switchInstruction;
        }
        if (statement instanceof TryCatchInstruction tryCatchInstruction) {
            rewriteStatementList(bindingFunction, callFunction, tryCatchInstruction.tryStatements, captures);
            for (CatchBlock catchBlock : tryCatchInstruction.catchBlocks) {
                rewriteStatementList(bindingFunction, callFunction, catchBlock.getStatements(), captures);
            }
            rewriteStatementList(bindingFunction, callFunction, tryCatchInstruction.finallyStatements, captures);
            return tryCatchInstruction;
        }
        if (statement instanceof ThrowInstruction throwInstruction) {
            throwInstruction.exceptionExpression = rewriteExpression(
                    bindingFunction, callFunction, throwInstruction.exceptionExpression, captures);
            return throwInstruction;
        }
        return statement;
    }

    private static void rewriteStatementList(
            Function bindingFunction,
            Function callFunction,
            List<Statement> statements,
            Map<String, LambdaCapture> captures) {
        if (statements == null) {
            return;
        }
        for (int i = 0; i < statements.size(); i++) {
            statements.set(i, rewriteStatement(bindingFunction, callFunction, statements.get(i), captures));
        }
    }

    private static Expression rewriteExpression(
            Function bindingFunction,
            Function callFunction,
            Expression expression,
            Map<String, LambdaCapture> captures) {
        if (expression == null) {
            return null;
        }

        if (expression instanceof ExpressionId expressionId) {
            return maybeCapture(bindingFunction, callFunction, expressionId, captures);
        }
        if (expression instanceof ExpressionAssign assign) {
            for (int i = 0; i < assign.leftExpressions.size(); i++) {
                assign.leftExpressions.set(i, rewriteExpression(
                        bindingFunction, callFunction, assign.leftExpressions.get(i), captures));
            }
            assign.right = rewriteExpression(bindingFunction, callFunction, assign.right, captures);
            return assign;
        }
        if (expression instanceof ExpressionOpAssign opAssign) {
            opAssign.left = rewriteExpression(bindingFunction, callFunction, opAssign.left, captures);
            opAssign.right = rewriteExpression(bindingFunction, callFunction, opAssign.right, captures);
            return opAssign;
        }
        if (expression instanceof ExpressionMath math) {
            math.left = rewriteExpression(bindingFunction, callFunction, math.left, captures);
            math.right = rewriteExpression(bindingFunction, callFunction, math.right, captures);
            return math;
        }
        if (expression instanceof ExpressionBitOperation bit) {
            bit.left = rewriteExpression(bindingFunction, callFunction, bit.left, captures);
            bit.right = rewriteExpression(bindingFunction, callFunction, bit.right, captures);
            return bit;
        }
        if (expression instanceof ExpressionShift shift) {
            shift.left = rewriteExpression(bindingFunction, callFunction, shift.left, captures);
            shift.right = rewriteExpression(bindingFunction, callFunction, shift.right, captures);
            return shift;
        }
        if (expression instanceof ExpressionBoolOperation boolOp) {
            boolOp.left = rewriteExpression(bindingFunction, callFunction, boolOp.left, captures);
            boolOp.right = rewriteExpression(bindingFunction, callFunction, boolOp.right, captures);
            return boolOp;
        }
        if (expression instanceof ExpressionBoolCompare compare) {
            compare.left = rewriteExpression(bindingFunction, callFunction, compare.left, captures);
            compare.right = rewriteExpression(bindingFunction, callFunction, compare.right, captures);
            return compare;
        }
        if (expression instanceof ExpressionBoolNot boolNot) {
            boolNot.expression = rewriteExpression(bindingFunction, callFunction, boolNot.expression, captures);
            return boolNot;
        }
        if (expression instanceof ExpressionBitNot bitNot) {
            bitNot.expression = rewriteExpression(bindingFunction, callFunction, bitNot.expression, captures);
            return bitNot;
        }
        if (expression instanceof ExpressionUnaryMinus unaryMinus) {
            unaryMinus.expression = rewriteExpression(bindingFunction, callFunction, unaryMinus.expression, captures);
            return unaryMinus;
        }
        if (expression instanceof ExpressionParens parens) {
            parens.expression = rewriteExpression(bindingFunction, callFunction, parens.expression, captures);
            return parens;
        }
        if (expression instanceof ExpressionCast cast) {
            cast.expression = rewriteExpression(bindingFunction, callFunction, cast.expression, captures);
            return cast;
        }
        if (expression instanceof ExpressionTernary ternary) {
            ternary.checkExpression = rewriteExpression(bindingFunction, callFunction, ternary.checkExpression, captures);
            ternary.trueExpression = rewriteExpression(bindingFunction, callFunction, ternary.trueExpression, captures);
            ternary.falseExpression = rewriteExpression(bindingFunction, callFunction, ternary.falseExpression, captures);
            return ternary;
        }
        if (expression instanceof ExpressionDotAccess dotAccess) {
            dotAccess.leftOfDot = rewriteExpression(bindingFunction, callFunction, dotAccess.leftOfDot, captures);
            return dotAccess;
        }
        if (expression instanceof ExpressionIndexAccess indexAccess) {
            indexAccess.expression = rewriteExpression(bindingFunction, callFunction, indexAccess.expression, captures);
            indexAccess.index = rewriteExpression(bindingFunction, callFunction, indexAccess.index, captures);
            return indexAccess;
        }
        if (expression instanceof ExpressionRangeAccess rangeAccess) {
            rangeAccess.expression = rewriteExpression(bindingFunction, callFunction, rangeAccess.expression, captures);
            if (rangeAccess.start != null) {
                rangeAccess.start = rewriteExpression(bindingFunction, callFunction, rangeAccess.start, captures);
            }
            if (rangeAccess.end != null) {
                rangeAccess.end = rewriteExpression(bindingFunction, callFunction, rangeAccess.end, captures);
            }
            return rangeAccess;
        }
        if (expression instanceof ExpressionFnCall fnCall) {
            fnCall.functionExpression = rewriteExpression(bindingFunction, callFunction, fnCall.functionExpression, captures);
            for (CallArgument argument : fnCall.callArguments) {
                argument.value = rewriteExpression(bindingFunction, callFunction, argument.value, captures);
            }
            return fnCall;
        }
        if (expression instanceof ExpressionNewObject newObject) {
            for (CallArgument argument : newObject.callArguments) {
                argument.value = rewriteExpression(bindingFunction, callFunction, argument.value, captures);
            }
            return newObject;
        }
        if (expression instanceof ExpressionNewArray newArray) {
            for (int i = 0; i < newArray.countExpressions.size(); i++) {
                newArray.countExpressions.set(i, rewriteExpression(
                        bindingFunction, callFunction, newArray.countExpressions.get(i), captures));
            }
            return newArray;
        }
        if (expression instanceof ExpressionInstanceOf instanceOf) {
            instanceOf.checkedObject = rewriteExpression(
                    bindingFunction, callFunction, instanceOf.checkedObject, captures);
            return instanceOf;
        }
        if (expression instanceof ExpressionArrayPack arrayPack) {
            for (int i = 0; i < arrayPack.elements.size(); i++) {
                arrayPack.elements.set(i, rewriteExpression(
                        bindingFunction, callFunction, arrayPack.elements.get(i), captures));
            }
            return arrayPack;
        }
        if (expression instanceof ExpressionKwargsPack kwargsPack) {
            for (int i = 0; i < kwargsPack.values.size(); i++) {
                kwargsPack.values.set(i, rewriteExpression(
                        bindingFunction, callFunction, kwargsPack.values.get(i), captures));
            }
            return kwargsPack;
        }
        if (expression instanceof ExpressionFString fString) {
            List<ExpressionFString.Part> rewritten = new ArrayList<>();
            for (ExpressionFString.Part part : fString.parts) {
                if (part instanceof ExpressionFString.Part.Interpolation interpolation) {
                    rewritten.add(new ExpressionFString.Part.Interpolation(
                            rewriteExpression(bindingFunction, callFunction, interpolation.expression(), captures)));
                } else {
                    rewritten.add(part);
                }
            }
            fString.parts = rewritten;
            return fString;
        }
        if (expression instanceof ExpressionLambda) {
            // Nested lambdas already ran their own capture analysis at parse time.
            return expression;
        }
        return expression;
    }

    private static Expression maybeCapture(
            Function bindingFunction,
            Function callFunction,
            ExpressionId expressionId,
            Map<String, LambdaCapture> captures) {
        if ("_".equals(expressionId.name) || "_this".equals(expressionId.name)) {
            return expressionId;
        }

        if (bindingFunction.resolve(expressionId.name) != null) {
            return expressionId;
        }

        Symbol enclosing = findEnclosingLocalOrParam(bindingFunction, expressionId.name);
        if (enclosing == null) {
            return expressionId;
        }

        SymbolSearch typeSearch = typeSearchOf(enclosing);
        if (typeSearch == null) {
            return expressionId;
        }

        captures.computeIfAbsent(expressionId.name, name -> new LambdaCapture(
                name,
                typeSearch,
                new SourceLocationRef(expressionId.sourceLocation)));

        // Own `_this` on the synthetic call so resolution does not depend on reparenting.
        ExpressionDotAccess access = ExpressionDotAccess.fromParts(
                expressionId.owner,
                ExpressionId.fromName(null, callFunction, "_this"),
                expressionId.name);
        access.sourceLocation = expressionId.sourceLocation;
        return access;
    }

    private static Symbol findEnclosingLocalOrParam(Function bindingFunction, String name) {
        AstItem owner = bindingFunction.getOwner();
        if (owner == null) {
            return null;
        }

        Scope scope = owner instanceof Scope s ? s : owner.findAstParent(Scope.class);
        while (scope != null) {
            if (scope instanceof Function function) {
                Symbol symbol = function.resolve(name);
                if (isCapturable(symbol)) {
                    return symbol;
                }
            }
            if (scope instanceof CompilationUnit || scope instanceof Module) {
                return null;
            }
            scope = scope.findAstParent(Scope.class);
        }
        return null;
    }

    private static boolean isCapturable(Symbol symbol) {
        if (symbol instanceof LocalVariable) {
            return true;
        }
        if (symbol instanceof Parameter parameter) {
            return !"_this".equals(parameter.name);
        }
        return false;
    }

    private static SymbolSearch typeSearchOf(Symbol symbol) {
        if (symbol instanceof LocalVariable localVariable) {
            return localVariable.typeSymbolSearch;
        }
        if (symbol instanceof Parameter parameter) {
            return parameter.getSymbolSearch();
        }
        return null;
    }
}
