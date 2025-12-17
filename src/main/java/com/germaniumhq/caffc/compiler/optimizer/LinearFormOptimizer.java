package com.germaniumhq.caffc.compiler.optimizer;

import com.germaniumhq.caffc.compiler.model.Clazz;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.CompileBlock;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.asm.opc.Block;

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

        }
    }
}
