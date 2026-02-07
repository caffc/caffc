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
        result.add(new OptimizeSimpleBinaryOperations());
        result.add(new BasicBlockOptimization());
        return result;
    }

    public static void optimize(CompilationUnit astOps) {
        var foundOptimization = false;
        for(BaseOptimization optimization : optimizations){
            for (var compileBlock : astOps.getCompileBlocks()){
                if (compileBlock instanceof Function function) {
                    foundOptimization |= optimization.optimize(function);
                }
            }
        }
        if (foundOptimization) {
            System.out.println("Optimizations applied");
        }
    }
}
