package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS500OptimizationsoOfBasicMath {
    @Test
    public void testSimpleOptimizations() {
        String code = CodeAssertsStr.compileCaffcProgramWithOptimizations(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
        module main
        
        test() -> i32 {
          i32 result
        
          result = 5 * 7 + 3
        
          return result
        }
        """)
            }
        );

        System.out.println(code);
    }
}
