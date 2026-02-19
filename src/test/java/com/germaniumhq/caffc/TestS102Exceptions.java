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

                      throw new

                      return result
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
            /* if0: */
            if (! _caffc_temp_caffc_bool_1) { goto else0; }
            _caffc_stack_frame_unregister(caffc_null);
            return y;
            else0:
            _caffc_stack_frame_unregister(caffc_null);
            return 0;
            """,
            "generating an if should work");
    }
}
