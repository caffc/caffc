package com.germaniumhq.caffc.compiler.optimizer;

import com.germaniumhq.caffc.compiler.model.BlockVariable;
import com.germaniumhq.caffc.compiler.model.Clazz;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.CompileBlock;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.NativeBlock;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmInstruction;
import com.germaniumhq.caffc.compiler.model.asm.opc.IfJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.Jmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.Label;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionAssign;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionDotAccess;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionId;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionMath;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionNumber;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionString;
import com.germaniumhq.caffc.compiler.model.instruction.Block;
import com.germaniumhq.caffc.compiler.model.instruction.IfInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.ReturnInstruction;

/**
 * This will transform the AST into a list of OPS. The OPS are still nodes
 * from AST, but flattened.
 */
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

    private static void breakDownStatement(AsmBlock block, Statement statement) {
        if (statement instanceof NativeBlock nativeBlock) {
            block.add(nativeBlock);
            return;
        }

        if (statement instanceof ReturnInstruction) {
            block.addStatement(statement);
            return;
        }

        if (statement instanceof ExpressionAssign assignExpression) {
            breakDownAssignExpression(block, assignExpression);
            return;
        }

        if (statement instanceof IfInstruction ifInstruction) {
            AsmInstruction ifCondition = extractIntoBlockTempVariable(block, ifInstruction.checkExpression);

            Label ifLabel = new Label();
            IfJmp ifJump = new IfJmp(ifCondition, ifLabel);
            Label endIfLabel = new Label();
            Jmp goToEnd = new Jmp(endIfLabel);

            block.add(ifJump);
            block.add(ifInstruction.statements);
            block.add(goToEnd);
            block.add(ifLabel);
            block.add(ifInstruction.elseStatements);
            block.add(endIfLabel);
            return;
        }
    }

    private static void breakDownAssignExpression(Block block, ExpressionAssign assignExpression) {
        breakDownExpression(block, assignExpression.right);
        for (Expression expression: assignExpression.leftExpressions) {
            breakDownExpression(block, expression);
        }

        block.addStatement(assignExpression);
    }

    private static void breakDownExpression(Block block, Expression expression) {
        if (expression instanceof ExpressionMath expressionMath) {
            if (!isSimpleExpression(expressionMath.left)) {
                expressionMath.left = extractIntoBlockTempVariable(block, expressionMath.left);
            }

            if (!isSimpleExpression(expressionMath.right)) {
                expressionMath.right = extractIntoBlockTempVariable(block, expressionMath.right);
            }
        }
    }

    private static BlockVariable extractIntoBlockTempVariable(Block block, Expression expression) {
        BlockVariable variable = block.addTempVariableFor(expression);

        breakDownExpression(block, expression);
        ExpressionAssign expressionAssign = ExpressionAssign.fromCode(expression.getOwner(), variable, expression);

        block.addStatement(expressionAssign);
        return variable;
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
