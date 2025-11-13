package com.germaniumhq.caffc.compiler.optimizer;

import com.germaniumhq.caffc.compiler.model.BlockVariable;
import com.germaniumhq.caffc.compiler.model.Clazz;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.CompileBlock;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.NativeBlock;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionAssign;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionDotAccess;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionId;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionMath;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionNumber;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionString;
import com.germaniumhq.caffc.compiler.model.instruction.Block;
import com.germaniumhq.caffc.compiler.model.instruction.ReturnInstruction;

public class LinearFormOptimizer {
    /**
     * Recursively traverses each method, and flattens it, so at the end, each
     * function is a linear list of instructions.
     */
    public static void convertAstToLinearForm(CompilationUnit compilationUnit) {
        for (CompileBlock compileBlock: compilationUnit.compileBlocks) {
            if (compileBlock instanceof Function function) {
                convertStatementsToLinearForm(function);
                continue;
            }

            if (compileBlock instanceof Clazz clazz) {
                for (Function function: clazz.functions) {
                    convertStatementsToLinearForm(function);
                }
                continue;
            }
        }
    }

    private static void convertStatementsToLinearForm(Function function) {
        for (int i = 0; i < function.statements.size(); i++) {
            Statement statement = function.statements.get(i);

            Block block = Block.createFrom(statement);
            breakDownStatement(block, statement);

            function.statements.set(i, block);
        }
    }

    private static void breakDownStatement(Block block, Statement statement) {
        if (statement instanceof NativeBlock) {
            block.addStatement(statement);
            return;
        }

        if (statement instanceof ReturnInstruction) {
            block.addStatement(statement);
            return;
        }

        if (statement instanceof ExpressionAssign assignExpression) {
            breakDownExpression(block, assignExpression.right);
            for (Expression expression: assignExpression.leftExpressions) {
                breakDownExpression(block, expression);
            }

            block.addStatement(statement);
        }
    }

    private static void breakDownExpression(Block block, Expression expression) {
        if (expression instanceof ExpressionMath expressionMath) {
            if (!isSimpleExpression(expressionMath.left)) {
                BlockVariable variable = block.addTempVariable(expressionMath.left, expressionMath.left.typeSymbol());

                breakDownExpression(block, expressionMath.left);
                ExpressionAssign a1 = ExpressionAssign.fromCode(expressionMath, variable, expressionMath.left);

                block.addStatement(a1);
                expressionMath.left = variable;
            }

            if (!isSimpleExpression(expressionMath.right)) {
                BlockVariable variable = block.addTempVariable(expressionMath.right, expressionMath.right.typeSymbol());

                breakDownExpression(block, expressionMath.right);
                ExpressionAssign a1 = ExpressionAssign.fromCode(expressionMath, variable, expressionMath.right);

                block.addStatement(a1);

                expressionMath.right = variable;
            }
        }
    }

    private static boolean isSimpleExpression(Expression expression) {
        if (expression instanceof ExpressionNumber numberExpression) {
            return true;
        }

        if (expression instanceof ExpressionString stringExpression) {
            return true;
        }

        if (expression instanceof ExpressionId idExpression) {
            return true;
        }

        if (expression instanceof ExpressionDotAccess dotAccessExpression) {
            return true;
        }

        return false;
    }
}
