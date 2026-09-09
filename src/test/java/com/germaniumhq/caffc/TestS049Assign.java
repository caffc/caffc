
package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS049Assign {
    /**
     * Tests a multi-array assign, that will destructure the return struct.
     */
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
                caffc_multi_structreturn _caffc_temp_caffc_multi_structreturn_1 = {0, 0};
                """,
            "there should be a local variable declaration for the struct response");

        CodeAssertsStr.assertCodeContains(code, """
_caffc_temp_caffc_multi_structreturn_1 = caffc_multi(); if (_caffc_exception) { goto fnUncaughtException0; };
x = _caffc_temp_caffc_multi_structreturn_1.x;
caffc_u8_arr_set(y, 0, _caffc_temp_caffc_multi_structreturn_1.y);
                """,
            "multi assign should be deconstructed from the struct response");
    }

    /**
     * Blank identifier {@code _} discards multi-return slots (Go-style).
     */
    @Test
    public void testAssignMultiIgnoredVariables() {
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
                            
                            multi() -> i32 x, u8 y, i32 z {
                              return 0, 0, 0
                            }

                            main() -> i32 {
                              i32 x

                              x, _, _ = multi()

                              return 0
                            }
                            """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
_caffc_temp_caffc_multi_structreturn_1 = caffc_multi(); if (_caffc_exception) { goto fnUncaughtException0; };
x = _caffc_temp_caffc_multi_structreturn_1.x;
                """,
            "kept unpack targets should still be assigned from the struct");

        CodeAssertsStr.assertCodeNotContains(code,
            "= _caffc_temp_caffc_multi_structreturn_1.y",
            "discarded primitive unpack slots must not be read into a local");

        CodeAssertsStr.assertCodeNotContains(code,
            "= _caffc_temp_caffc_multi_structreturn_1.z",
            "discarded primitive unpack slots must not be read into a local");
    }

    /**
     * Discarded object/array multi-return slots must still be zero-cleared for GC.
     */
    @Test
    public void testAssignMultiIgnoredObjectStillGcCleared() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                            module main
                            
                            getFile() -> u8 size, str name {
                              return 3, "a.txt"
                            }

                            main() -> i32 {
                              u8 size

                              size, _ = getFile()

                              return 0
                            }
                            """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
size = _caffc_temp_caffc_getFile_structreturn_1.size;
_caffc_temp_caffc_getFile_structreturn_1.name = caffc_null;
                """,
            "discarded object unpack slots must be zero-cleared without assigning to a local");
    }

    /**
     * Tests regular assignment of a single variable.
     */
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
            u8array = caffc_null;
            caffc_u8_arr_set(u8array, 0, 3);
            """,
            "multi assign should be deconstructed from the struct response");
    }
}
