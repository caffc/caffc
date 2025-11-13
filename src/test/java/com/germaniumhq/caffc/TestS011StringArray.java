package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS011StringArray {
    @Test
    public void testStringArrayWrite() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
            """
                    module main

                    main() -> i32 {
                      str[] x = new str[2]

                      x[0] = "abc"
                      x[1] = "cde"

                      return 0
                    }
                    """)
                }
        );

        CodeAssertsStr.assertCodeContains(code, """
                caffc_obj_arr_set((caffc_obj_arr*) x, 0, (caffc_ptr) (caffc_str*)&caffc_cstr_ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad);
                """,
                "assigning string values into a string array should work");

        System.out.println(code);
    }

    @Test
    public void testStringArrayRead() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[]{
                new TestUnit("a/a.caffc", """
                    module main
    
                    main() {
                      str[] x = new str[2]
                      str y = "abc"
    
                      y = x[0]
                    }
                """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, "y = ((caffc_str*) caffc_obj_arr_get((caffc_str_arr*) x, 0));",
                "reading string values from a string array should work");
    }
}
