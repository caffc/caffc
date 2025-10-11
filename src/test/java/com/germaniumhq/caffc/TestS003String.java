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

        CodeAsserts.assertCodeContains(code, "x = (caffc_str*)&caffc_cstr_7509e5bda0c762d2bac7f90d758b5b2263fa01ccbc542ab5e3df163be08e6ca9;",
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
                caffc_str_13 caffc_cstr_7509e5bda0c762d2bac7f90d758b5b2263fa01ccbc542ab5e3df163be08e6ca9 = {
                    /* _caffc_class_header */ &caffc_str_type,
                    /* _caffc_flags */ 0,
                    /* size */ 13,
                    { 0x68, 0x65, 0x6c, 0x6c, 0x6f, 0x20, 0x77, 0x6f, 0x72, 0x6c, 0x64, 0x21, 0x00 }
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
                "extern caffc_str_12 caffc_cstr_ce2cddbe5f7f742d1630540ee17299cd33ff3866e5da666952ebff45fdfaa72a;",
                "string constants aren't generated correctly.");

      CodeAsserts.assertCodeContains(code,
        """
          typedef struct {
              caffc_class_header* _caffc_class_header;
              caffc_u8 _caffc_flags; /* FIXME: pack into the header pointer */
              caffc_i32 _byte_length;
              caffc_u8 _caffc_data[12];
          } caffc_str_12;
          """,
        "string constants should have their corresponding struct defined per each byte-size" +
                    " so C can allocate them at compile time.");

    }
}
