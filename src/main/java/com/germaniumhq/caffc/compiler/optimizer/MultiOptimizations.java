package com.germaniumhq.caffc.compiler.optimizer;

import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.optimizer.optimizations.BaseOptimization;
import com.germaniumhq.caffc.compiler.optimizer.optimizations.BasicBlockOptimization;
import com.germaniumhq.caffc.compiler.optimizer.optimizations.MathFoldingOptimization;

import java.util.Arrays;
import java.util.List;

public final class MultiOptimizations {
    static List<BaseOptimization> optimizations = Arrays.asList(
        new MathFoldingOptimization(),
        new BasicBlockOptimization()
    );

    public static boolean optimize(CompilationUnit astOps) {
        boolean foundOptimization = false;

        var foundOnce = false;

        do {
            foundOnce = applyOptimizationsOnce(astOps);
            foundOptimization |= foundOnce;
        } while (foundOnce);

        return foundOptimization;
    }

    private static boolean applyOptimizationsOnce(CompilationUnit astOps) {
        var foundOptimization = false;

        for (BaseOptimization optimization : optimizations) {
            for (var compileBlock : astOps.getCompileBlocks()) {
                if (compileBlock instanceof Function function) {
                    foundOptimization |= optimization.optimize(function);
                }
            }
        }

        return foundOptimization;
    }
}
