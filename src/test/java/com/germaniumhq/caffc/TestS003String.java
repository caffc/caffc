package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAsserts.compileFullCaffcProgram;

public class TestS003String {
    @Test
    public void testStringCreation() {
        String code = compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb", /* template         */
                "test.caffc",                              /* compilation unit */
                new TestUnit[]{
                        new TestUnit("test.caffc",
                                """
                                        module main
                                        
                                        print_string(str x) {
                                          native {
                                            printf("%s", x._caffc_data);
                                          }
                                        }
                                        
                                        main() -> i32 {
                                          str x = "hello world!"
                                          print_string(x)
                                          return 0
                                        }
                                        """)
                });

        CodeAsserts.assertCodeContains(code, "x = &caffc_cstr_6292db02441c2d7b7ab6960d0bff928b834ec69a20f13626e8ccbd39ef95a6f8;",
                "string constants aren't generated correctly.");
    }

    @Test
    public void testStringConstantsImplCreation() {
        String code = compileFullCaffcProgram(
                "caffc/template/c/constants_c.peb", /* template         */
                "test.caffc",                              /* compilation unit */
                new TestUnit[]{
                        new TestUnit("test.caffc",
                                """
                                        module main
                                        
                                        print_string(str x) {
                                          native {
                                            printf("%s", x._caffc_data);
                                          }
                                        }
                                        
                                        main() -> i32 {
                                          str x = "hello world!"
                                          print_string(x)
                                          return 0
                                        }
                                        """)
                });

        CodeAsserts.assertCodeContains(code,
                """
                caffc_str caffc_cstr_6292db02441c2d7b7ab6960d0bff928b834ec69a20f13626e8ccbd39ef95a6f8 = {
                    /* _caffc_class_header */ &caffc_str_type,
                    /* _caffc_flags */ 0,
                    /* size */ 15,
                    "hello world!",
                };
                """,
                "string constants aren't generated correctly.");
    }

    @Test
    public void testStringConstantsHeaderCreation() {
        String code = compileFullCaffcProgram(
                "caffc/template/c/constants_h.peb", /* template         */
                "test.caffc",                              /* compilation unit */
                new TestUnit[]{
                        new TestUnit("test.caffc",
                                """
                                        module main
                                        
                                        print_string(str x) {
                                          native {
                                            printf("%s", x._caffc_data);
                                          }
                                        }
                                        
                                        main() -> i32 {
                                          str x = "hello world!"
                                          print_string(x)
                                          return 0
                                        }
                                        """)
                });

        CodeAsserts.assertCodeContains(code,
                "caffc_str caffc_cstr_6292db02441c2d7b7ab6960d0bff928b834ec69a20f13626e8ccbd39ef95a6f8;",
                "string constants aren't generated correctly.");
    }
}
