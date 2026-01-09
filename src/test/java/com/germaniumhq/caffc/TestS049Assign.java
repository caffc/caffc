
package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS049Assign {
    @Test
    public void testAssignMulti() {
        String code = CodeAssertsStr.compileCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                            module caffc
                            
                            class u8_arr {
                              set(i32 index, u8 value) {}
                            }
                            
                            multi() -> i32 x, u8 y {
                              return 0, 0
                            }

                            main() -> i32 {
                              i32 x
                              u8[] y

                              x, y[0] = multi()

                              return 0
                            }
                            """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
                caffc_multi_structreturn _caffc_temp_caffc_multi_structreturn_1;
                """,
            "there should be a local variable declaration for the struct response");

        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_caffc_multi_structreturn_1 = caffc_multi();
                x = _caffc_temp_caffc_multi_structreturn_1.x;
                caffc_u8_arr_set(y, 0, _caffc_temp_caffc_multi_structreturn_1.y);
                """,
            "multi assign should be deconstructed from the struct response");
    }

    @Test
    public void testAssignSimple() {
        String code = CodeAssertsStr.compileCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                            module caffc
                            
                            class u8_arr {
                              set(i32 index, u8 value) {}
                            }
                            
                            main() -> i32 {
                              i32 value
                              u8[] u8array

                              u8array[0] = 3
                              value = 5

                              return 0
                            }
                            """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
            u8array = 0;
            caffc_u8_arr_set(u8array, 0, 3);
            """,
            "multi assign should be deconstructed from the struct response");
    }
}
