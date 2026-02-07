package com.germaniumhq.caffc.compiler.optimizer;

import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.optimizer.optimizations.*;

import java.util.ArrayList;
import java.util.List;

public class MultiOptimizations {
    static List<BaseOptimization> optimizations = buildOptimizationList();

    private static List<BaseOptimization> buildOptimizationList() {
        var result = new ArrayList<BaseOptimization>();
        result.add(new MathFoldingOptimization());
        result.add(new BasicBlockOptimization());
        return result;
    }

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
