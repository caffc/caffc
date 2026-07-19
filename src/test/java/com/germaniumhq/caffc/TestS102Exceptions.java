package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS102Exceptions {
    @Test
    public void testNormalTryCatchFinallyGeneration() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module main

                    willThrow() -> i32 {
                      i32 result = 0

                      throw new Exception("ded", null)

                      return result
                    }

                    checkValue(i32 y) -> i32 {
                      if (y > 0) {
                        return y
                      } else {
                        return 0
                      }
                    }

                    canThrow() {
                      try {
                        print("code")
                      } catch (Exception e) { // this is basically if (e is Exception) ...
                        print("exception")
                      } finally {
                        print("finally")
                      }
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
            goto endif1;
            else1:
            _caffc_stack_frame_unregister(caffc_null);
            return 0;
            endif1:
            """,
            "generating an if should work");
    }

    @Test
    public void testExceptionPropagationWithoutTryCatch() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module main

                    willThrow() -> void {
                      throw new Exception("error", null)
                    }

                    caller() -> void {
                      willThrow()
                    }

                    main() -> void {
                      caller()
                    }
                    """)
            }
        );

        // Verify that check_exception is called after willThrow() call
        CodeAssertsStr.assertCodeContains(code,
            """
            main_willThrow(); if (_caffc_exception) { goto fnUncaughtException0; };
            """,
            "check_exception should be called after throw-throwing function");

        // Verify that fnUncaughtException0 returns from the function
        CodeAssertsStr.assertCodeContains(code,
            """
            fnUncaughtException0:
            """,
            "exception handler label should exist");
    }

    @Test
    public void testMainHandlesUnhandledException() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module main

                    willThrow() -> void {
                      throw new Exception("uncaught", null)
                    }

                    main() -> void {
                      willThrow()
                    }
                    """)
            }
        );

        // Verify that main checks for exceptions and exits with code 1
        CodeAssertsStr.assertCodeContains(code,
            """
            main_main();
            if (_caffc_exception) {
              mainUnhandledException:
              caffc_str* _caffc_exception_msg = caffc_exception_message((caffc_exception*)_caffc_exception);
              if (_caffc_exception_msg) {
                printf("Uncaught exception: %s\\n", _caffc_exception_msg->_caffc_data);
              }

              /* we want the GC to sweep it */
              _caffc_exception = caffc_null;

              exit(1);
            }
            """,
            "main should check for exceptions and exit(1) on uncaught exception");
    }
}
