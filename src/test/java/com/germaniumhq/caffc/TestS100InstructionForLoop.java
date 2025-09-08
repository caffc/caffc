package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS100InstructionForLoop {
    @Test
    public void testForLoopGeneration() {
        String code = CodeAsserts.compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
            """
                    module main

                    main() -> i32 {
                      for i32 i = 0; i < 10; i = i + 1 {
                      }
                    }
                    """)
                }
        );

        CodeAsserts.assertCodeContains(code, """
                       i = 0;
                       while (i < 10) {
                       i = i + 1;
                       }
                       """,
                "generating a for loop should work");

        System.out.println(code);
    }

    @Test
    public void testForLoopSeparateVarGeneration() {
        String code = CodeAsserts.compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
                        """
                                module main
            
                                main() -> i32 {
                                  i32 i
                                  for i = 0; i < 10; i = i + 1 {
                                  }
                                }
                                """)
                }
        );

        CodeAsserts.assertCodeContains(code, """
  i = 0;
while (i < 10) {
i = i + 1;
}
                       """,
                "generating a for loop and reusing variables should work");

        System.out.println(code);
    }
}
