package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import org.junit.jupiter.api.Test;

/**
 * This just checks if the code generation works, there's a full integration
 * test to actually check listing of the folder.
 */
public class TestS300LinearFormBasicMath {
    @Test
    public void testBasicArrayListImplementation() {
        CompilationUnit code = CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc",
                true,
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

        System.out.print(code);
    }
}
