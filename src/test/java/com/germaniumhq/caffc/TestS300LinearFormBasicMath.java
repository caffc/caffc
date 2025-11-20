package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import org.junit.jupiter.api.Test;

public class TestS300LinearFormBasicMath {
    @Test
    public void testBasicLinearForm() {
        CompilationUnit astOps = CodeAssertsAst.compileCaffcUnitsAst(
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

        String astString = AstUtil.astToString(astOps);
        System.out.println(astString);
    }
}
