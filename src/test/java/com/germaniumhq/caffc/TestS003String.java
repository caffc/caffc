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

        CodeAsserts.assertCodeContains(code, "x = &caffc_cstr_7509e5bda0c762d2bac7f90d758b5b2263fa01ccbc542ab5e3df163be08e6ca9;",
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
                caffc_str caffc_cstr_7509e5bda0c762d2bac7f90d758b5b2263fa01ccbc542ab5e3df163be08e6ca9 = {
                    /* _caffc_class_header */ &caffc_str_type,
                    /* _caffc_flags */ 0,
                    /* size */ 13,
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
                "extern caffc_str caffc_cstr_ce2cddbe5f7f742d1630540ee17299cd33ff3866e5da666952ebff45fdfaa72a;",
                "string constants aren't generated correctly.");
    }
}
