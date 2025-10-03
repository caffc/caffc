package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS101InstructionContinue {
    @Test
    public void testContinueGeneration() {
        String code = CodeAsserts.compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
            """
                    module main

                    main() -> i32 {
                      for i32 i = 0; i < 10; i = i + 1 {
                        if i == 5 continue
                        print(i)
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
}
