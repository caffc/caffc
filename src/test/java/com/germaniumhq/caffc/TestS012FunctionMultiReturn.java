package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS012FunctionMultiReturn {
    @Test
    public void testGenerationOfFunctionsWithSingleUnnamedReturn() {
        String code = CodeAsserts.compileFullCaffcProgram(
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

        CodeAsserts.assertCodeContains(code, """
                i32 main_getPoint() {
                """,
            "the return type should be the type for the single unnamed return");

        CodeAsserts.assertCodeContains(code,
            "x = main_getPoint();",
            "the call for a single unnamed return should not be unpacked");

    }

    @Test
    public void testGenerationOfFunctionsWithSingleNamedReturn() {
        String code = CodeAsserts.compileFullCaffcProgram(
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

        CodeAsserts.assertCodeContains(code, """
                i32 main_getPoint() {
                """,
            "the return type should be still just the type for the single unnamed return," +
                "since the \"name\" of the variable can be thought to be the function names");

        CodeAsserts.assertCodeContains(code,
                "x = main_getPoint();",
                "the call for a single named return should not be unpacked");
    }

    @Test
    public void testGenerationOfFunctionsWithMultipleReturnStructs() {
        String code = CodeAsserts.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                        module main

                        getFile() -> i32 size, str name {
                          name = "a.txt"
                          size = 3
                          return size, name
                        }
                        
                        printFiles() {
                          i32[] sizesArray = new i32[1]
                          str[] namesArray = new str[1]
                          i32 fileSize
                          str fileName
                        
                          sizesArray[0], namesArray[0] = getFile()
                          fileSize, fileName = getFile()
                        }
                        """)
            }
        );

        CodeAsserts.assertCodeContains(code, """
                caffc_getFile_structreturn main_getFile() {
                """,
            "the return type should be a struct for multiple returns");

        CodeAsserts.assertCodeContains(code, """
                caffc_getFile_structreturn_ret = main_getFile();
                """,
            "the result of the call should be copied in the structure first");

        CodeAsserts.assertCodeContains(code, """
                caffc_i32_arr_set(sizesArray, 0, caffc_getFile_structreturn_ret.size);
                """,
            "the value x of the struct should be unpacked (primitive array)");

        CodeAsserts.assertCodeContains(code, """
                caffc_obj_arr_set((caffc_obj_arr*) namesArray, 0, (caffc_ptr) caffc_getFile_structreturn_ret.name);
                """,
            "the value y of the struct should be unpacked (object array)");

        CodeAsserts.assertCodeContains(code, """
                fileSize = caffc_getFile_structreturn_ret.size;
                """,
            "the value x of the struct should be unpacked (primitive)");

        CodeAsserts.assertCodeContains(code, """
                fileName = caffc_getFile_structreturn_ret.name;
                """,
            "the value y of the struct should be unpacked (object)");
    }

    @Test
    public void testGenerationOfFunctionsWithMultipleReturnStructsHeaderGeneration() {
        String code = CodeAsserts.compileFullCaffcProgram(
            "caffc/template/c/module_h.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                        module main

                        getPoint() -> i32 x, str s {
                          return 3, "yay"
                        }
                        
                        printPoint() {
                          i32[] x = new i32[1]
                          str s
                          x[0], s = getPoint()
                        }
                        """)
            }
        );

        CodeAsserts.assertCodeContains(code, """
                typedef struct {
                  caffc_i32 x;
                  caffc_str* s;
                } caffc_getPoint_structreturn;
                """,
            "the struct type should be generated for multiple returns in the module header");
    }
}
