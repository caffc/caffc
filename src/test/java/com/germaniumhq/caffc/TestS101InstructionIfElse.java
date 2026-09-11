package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import org.junit.jupiter.api.Assertions;
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
/* if1: */
if (! _caffc_temp_caffc_bool_1) { goto else1; }
_caffc_stack_frame_unregister(caffc_null);
return y;
else1:
_caffc_stack_frame_unregister(caffc_null);
return 0;
fnUncaughtException0:
_caffc_stack_frame_unregister(caffc_null);
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
                /* if1: */
                if (! _caffc_temp_caffc_bool_1) { goto else1; }
                x = 4;
                goto endif1;
                else1:
                x = 5;
                endif1:
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
                /* forBegin1: */
                i = 0;
                forCheck1:
                _caffc_temp_caffc_bool_1 = i < 3;
                if (! _caffc_temp_caffc_bool_1) { goto forEnd1; }
                /* forBlock1: */
                _caffc_temp_caffc_bool_2 = i == 1;
                /* if2: */
                if (! _caffc_temp_caffc_bool_2) { goto else2; }
                goto forCheck1;
                else2:
                _caffc_temp_caffc_bool_2 = i == 2;
                /* if3: */
                if (! _caffc_temp_caffc_bool_2) { goto else3; }
                goto forCheck1;
                else3:
                i = i + 1;
                goto forCheck1;
                forEnd1:
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
                /* forBegin1: */
                i = 0;
                forCheck1:
                _caffc_temp_caffc_bool_1 = i < 3;
                if (! _caffc_temp_caffc_bool_1) { goto forEnd1; }
                /* forBlock1: */
                _caffc_temp_caffc_bool_2 = i == 1;
                /* if2: */
                if (! _caffc_temp_caffc_bool_2) { goto else2; }
                goto forEnd1;
                else2:
                _caffc_temp_caffc_bool_2 = i == 2;
                /* if3: */
                if (! _caffc_temp_caffc_bool_2) { goto else3; }
                goto forEnd1;
                else3:
                i = i + 1;
                goto forCheck1;
                forEnd1:
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
/* forBegin1: */
i = 0;
forCheck1:
_caffc_temp_caffc_bool_1 = i < 3;
if (! _caffc_temp_caffc_bool_1) { goto forEnd1; }
/* forBlock1: */
_caffc_temp_caffc_bool_2 = i == 1;
/* if2: */
if (! _caffc_temp_caffc_bool_2) { goto else2; }
_caffc_stack_frame_unregister(caffc_null);
return;
else2:
_caffc_temp_caffc_bool_2 = i == 2;
/* if3: */
if (! _caffc_temp_caffc_bool_2) { goto else3; }
_caffc_stack_frame_unregister(caffc_null);
return;
else3:
i = i + 1;
goto forCheck1;
forEnd1:
            """,
            "generating an if that has a simple return should work");
    }


    @Test
    public void testBoolNotHasABooleanType() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                            module main
        
                            test() {
                              str x
                              if (not x) {}
                            }
                            """)
            }
        );

        CodeAssertsStr.assertCodeContains(code,
            """
            _caffc_temp_caffc_bool_1 = !x;
            """,
            "`not` on an object should return a boolean type, not the original object type");
    }

    @Test
    public void nestedIfElseIsRejected() {
        CaffcCompiler.get().hasErrors = false;
        try {
            CodeAssertsStr.compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
                        """
                        module main

                        test(i32 x) -> i32 {
                          if x > 0 {
                            if x > 10 {
                              return 1
                            }
                            return 2
                          }
                          return 0
                        }
                        """)
                });
        } catch (Exception ignored) {
        }

        Assertions.assertTrue(CaffcCompiler.get().hasErrors,
                "nested if/else should be a compilation error");
    }

    @Test
    public void ifInsideSwitchIsAllowed() {
        CaffcCompiler.get().hasErrors = false;
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module main

                    test(i32 x) -> i32 {
                      i32 result = 0
                      switch {
                        case x > 0: {
                          if x > 10 {
                            result = 1
                          }
                        }
                        default: {
                          result = 2
                        }
                      }
                      return result
                    }
                    """)
            });

        Assertions.assertFalse(CaffcCompiler.get().hasErrors,
                "if inside switch should be allowed");
        CodeAssertsStr.assertCodeContains(code,
            "/* switchBegin",
            "if inside switch should still generate a switch");
    }
}
