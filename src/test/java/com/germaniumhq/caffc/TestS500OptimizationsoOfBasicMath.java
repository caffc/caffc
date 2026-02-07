package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.optimizer.MultiOptimizations;
import org.junit.jupiter.api.Test;

public class TestS500OptimizationsoOfBasicMath {
    @Test
    public void testSimpleOptimizations() {
        CompilationUnit astOps = CodeAssertsAst.compileCaffcUnitsAst(
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
        module main
        
        a() -> i32 {
          i32 result
          
          result = 5 * 7 + 3
          
          return result
        }
        """)
            }
        );

        MultiOptimizations.optimize(astOps);

        String astString = AstUtil.astToString(astOps);
        System.out.println(astString);
    }
}
