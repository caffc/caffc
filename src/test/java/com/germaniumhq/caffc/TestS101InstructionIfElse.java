package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS101InstructionIfElse {
    @Test
    public void testIfGeneration() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module main

                    clip(i32 y) -> i32 {
                        if y > 0 {
                          return y
                        }
    
                        return 0
                    }
                    """)
            }
        );

        CodeAssertsStr.assertCodeContains(code,
            """
            _caffc_temp_caffc_bool_1 = y > 0;
            /* if0: */
            if (! _caffc_temp_caffc_bool_1) { goto else0; }
            return y;
            else0:
            return 0;
            """,
            "generating an if should work");
    }

    @Test
    public void testIfElseGeneration() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
            """
                    module main

                    test() -> i32 {
                      i32 x = 0
                      
                      if x == 3 {
                        x = 4
                      } else {
                        x = 5
                      }
                    }
                    """)
                }
        );

        CodeAssertsStr.assertCodeContains(code,
                """
                x = 0;
                _caffc_temp_caffc_bool_1 = x == 3;
                /* if0: */
                if (! _caffc_temp_caffc_bool_1) { goto else0; }
                x = 4;
                goto endif0;
                else0:
                x = 5;
                endif0:
                """,
                "generating an if/else should work");
    }

    @Test
    public void testIfContinueGeneration() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                        module main
                    
                                        test(){
                                          for i32 i = 0; i < 3; i += 1 {
                                            if i == 1 continue
                                            if i == 2 {
                                              continue
                                            }
                                          }
                                        }
                                        """)
                }
        );

        CodeAssertsStr.assertCodeContains(code,
                """
                /* forBegin0: */
                i = 0;
                forCheck0:
                _caffc_temp_caffc_bool_1 = i < 3;
                if (! _caffc_temp_caffc_bool_1) { goto forEnd0; }
                /* forBlock0: */
                _caffc_temp_caffc_bool_2 = i == 1;
                /* if1: */
                if (! _caffc_temp_caffc_bool_2) { goto else1; }
                goto forCheck0;
                else1:
                _caffc_temp_caffc_bool_2 = i == 2;
                /* if2: */
                if (! _caffc_temp_caffc_bool_2) { goto else2; }
                goto forCheck0;
                else2:
                i = i + 1;
                goto forCheck0;
                forEnd0:
                """,
                "generating an if that has a simple continue should work");
    }

    @Test
    public void testIfBreakGeneration() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                        module main
                    
                                        test(){
                                          for i32 i = 0; i < 3; i += 1 {
                                            if i == 1 break
                                            if i == 2 {
                                              break
                                            }
                                          }
                                        }
                                        """)
                }
        );

        CodeAssertsStr.assertCodeContains(code,
                """
                /* forBegin0: */
                i = 0;
                forCheck0:
                _caffc_temp_caffc_bool_1 = i < 3;
                if (! _caffc_temp_caffc_bool_1) { goto forEnd0; }
                /* forBlock0: */
                _caffc_temp_caffc_bool_2 = i == 1;
                /* if1: */
                if (! _caffc_temp_caffc_bool_2) { goto else1; }
                goto forEnd0;
                else1:
                _caffc_temp_caffc_bool_2 = i == 2;
                /* if2: */
                if (! _caffc_temp_caffc_bool_2) { goto else2; }
                goto forEnd0;
                else2:
                i = i + 1;
                goto forCheck0;
                forEnd0:
                """,
                "generating an if that has a simple break should work");
    }

    @Test
    public void testIfReturnGeneration() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                        module main
                    
                                        test(){
                                          for i32 i = 0; i < 3; i += 1 {
                                            if i == 1 return
                                            if i == 2 {
                                              return
                                            }
                                          }
                                        }
                                        """)
                }
        );

        CodeAssertsStr.assertCodeContains(code,
            """
            /* forBegin0: */
            i = 0;
            forCheck0:
            _caffc_temp_caffc_bool_1 = i < 3;
            if (! _caffc_temp_caffc_bool_1) { goto forEnd0; }
            /* forBlock0: */
            _caffc_temp_caffc_bool_2 = i == 1;
            /* if1: */
            if (! _caffc_temp_caffc_bool_2) { goto else1; }
            return;
            else1:
            _caffc_temp_caffc_bool_2 = i == 2;
            /* if2: */
            if (! _caffc_temp_caffc_bool_2) { goto else2; }
            return;
            else2:
            i = i + 1;
            goto forCheck0;
            forEnd0:
            """,
            "generating an if that has a simple return should work");
    }
}
