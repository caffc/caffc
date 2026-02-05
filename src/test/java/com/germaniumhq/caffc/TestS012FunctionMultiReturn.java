package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS012FunctionMultiReturn {
    @Test
    public void testGenerationOfFunctionsWithSingleUnnamedReturn() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                        module main

                        getPoint() -> i32 {
                          return 0
                        }
                        
                        printPoint() {
                          i32 x
                          x = getPoint()
                        }
                        """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
                i32 main_getPoint() {
                """,
            "the return type should be the type for the single unnamed return");

        CodeAssertsStr.assertCodeContains(code,
            """
            _caffc_temp_caffc_i32_1 = main_getPoint();
            x = _caffc_temp_caffc_i32_1;
            """,
            "the call for a single unnamed return should not be unpacked");

    }

    @Test
    public void testGenerationOfFunctionsWithSingleNamedReturn() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                        module main

                        getPoint() -> i32 x {
                          return 0
                        }
                        
                        printPoint() {
                          i32 x
                          x = getPoint()
                        }
                        """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
                caffc_i32 main_getPoint() {
                """,
            "the return type should be still just the type for the single unnamed return," +
                "since the \"name\" of the variable can be thought to be the function names");

        CodeAssertsStr.assertCodeContains(code,
            """
                _caffc_temp_caffc_i32_1 = main_getPoint();
                x = _caffc_temp_caffc_i32_1;
                """,
                "the call for a single named return should not be unpacked");
    }

    @Test
    public void testGenerationOfFunctionsWithMultipleReturnStructs() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                        module main

                        getFile() -> u8 size, str name {
                          name = "a.txt"
                          size = 3
                          return size, name
                        }
                        
                        printFiles() {
                          u8[] sizesArray = new u8[1]
                          str[] namesArray = new str[1]
                          u8 fileSize
                          str fileName
                        
                          sizesArray[0], namesArray[0] = getFile()
                          fileSize, fileName = getFile()
                        }
                        """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
                caffc_getFile_structreturn main_getFile() {
                """,
            "the return type should be a struct for multiple returns");

        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_caffc_getFile_structreturn_1 = main_getFile();
                """,
            "the result of the call should be copied in the structure first");

        CodeAssertsStr.assertCodeContains(code, """
                caffc_u8_arr_set(sizesArray, 0, _caffc_temp_caffc_getFile_structreturn_1.size);
                """,
            "the value x of the struct should be unpacked (primitive array)");

        CodeAssertsStr.assertCodeContains(code, """
                caffc_obj_arr_set(namesArray, 0, _caffc_temp_caffc_getFile_structreturn_1.name);
                """,
            "the value y of the struct should be unpacked (object array)");

        CodeAssertsStr.assertCodeContains(code, """
                fileSize = _caffc_temp_caffc_getFile_structreturn_2.size;
                """,
            "the value x of the struct should be unpacked (primitive)");

        CodeAssertsStr.assertCodeContains(code, """
                fileName = _caffc_temp_caffc_getFile_structreturn_2.name;
                """,
            "the value y of the struct should be unpacked (object)");
    }

    @Test
    public void testGenerationOfFunctionsWithMultipleReturnStructsHeaderGeneration() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/module_h.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                        module main

                        getPoint() -> u8 x, str s {
                          return 3, "yay"
                        }
                        
                        printPoint() {
                          u8[] x = new u8[1]
                          str s
                          x[0], s = getPoint()
                        }
                        """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
                typedef struct {
                  caffc_u8 x;
                  caffc_str* s;
                } caffc_getPoint_structreturn;
                """,
            "the struct type should be generated for multiple returns in the module header");
    }

    @Test
    public void testReturnMultiConstants() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                        module main
                        
                        getOrigin() -> u32 x, u32 y {
                            return 0, 0
                        }
                        """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_caffc_getOrigin_structreturn_1.x = 0;
                _caffc_temp_caffc_getOrigin_structreturn_1.y = 0;
                return _caffc_temp_caffc_getOrigin_structreturn_1;
                """,
            "the return fields should be assigned correctly");
    }
}
