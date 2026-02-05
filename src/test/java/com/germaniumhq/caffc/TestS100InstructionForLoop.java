package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS100InstructionForLoop {
    @Test
    public void testForLoopGeneration() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
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

        CodeAssertsStr.assertCodeContains(code, """
                    /* forBegin0: */
                    i = 0;
                    forCheck0:
                    _caffc_temp_caffc_bool_1 = i < 10;
                    if (! _caffc_temp_caffc_bool_1) { goto forEnd0; }
                    /* forBlock0: */
                    _caffc_temp_caffc_i32_1 = i + 1;
                    i = _caffc_temp_caffc_i32_1;
                    goto forCheck0;
                    forEnd0:
                    """,
                "generating a for loop should work");
    }

    @Test
    public void testForLoopSeparateVarGeneration() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
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

        CodeAssertsStr.assertCodeContains(code, """
                        /* forBegin0: */
                        i = 0;
                        forCheck0:
                        _caffc_temp_caffc_bool_1 = i < 10;
                        if (! _caffc_temp_caffc_bool_1) { goto forEnd0; }
                        /* forBlock0: */
                        _caffc_temp_caffc_i32_1 = i + 1;
                        i = _caffc_temp_caffc_i32_1;
                        goto forCheck0;
                        forEnd0:
                        """,
                "generating a for loop and reusing variables should work");

        System.out.println(code);
    }
}
