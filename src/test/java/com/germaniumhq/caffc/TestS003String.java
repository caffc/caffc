package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAssertsStr.compileFullCaffcProgram;

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

        CodeAssertsStr.assertCodeContains(code, """
                x = (caffc_str*)&caffc_cstr_7509e5bda0c762d2bac7f90d758b5b2263fa01ccbc542ab5e3df163be08e6ca9;
                """,
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

        CodeAssertsStr.assertCodeContains(code,
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

        CodeAssertsStr.assertCodeContains(code,
                "extern caffc_str_12 caffc_cstr_ce2cddbe5f7f742d1630540ee17299cd33ff3866e5da666952ebff45fdfaa72a;",
                "string constants aren't generated correctly.");

      CodeAssertsStr.assertCodeContains(code,
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

    @Test
    public void testStringEscapeCharacterSet() {
        String code = compileFullCaffcProgram(
            "caffc/template/c/constants_c.peb", /* template         */
            "test.caffc",                              /* compilation unit */
            new TestUnit[]{
                new TestUnit("test.caffc",
                    """
                            module main
                            
                            main() -> i32 {
                              str x = "string escapes: \\a\\b\\e\\f\\n\\r\\t\\v\\\\'\\"\\?\\033\\x44"
                              return 0
                            }
                            """)
            });

        CodeAssertsStr.assertCodeContains(code,
            "{ 0x73, 0x74, 0x72, 0x69, 0x6e, 0x67, 0x20, 0x65, 0x73, 0x63, 0x61, 0x70, 0x65, 0x73, 0x3a, 0x20, 0x07, 0x08, 0x1b, 0x0c, 0x0a, 0x0d, 0x09, 0x0b, 0x5c, 0x27, 0x22, 0x3f, 0x1b, 0x44, 0x00 }",
            "string escapes aren't processed correctly.");
    }

    /**
     * We check to see if escapes that appear after an emoji are correctly
     * escaped. \\033 should be still the octal for 0x1b.
     */
    @Test
    public void testStringEscapeColorsNotShowingAfterEmoji() {
        String code = compileFullCaffcProgram(
            "caffc/template/c/constants_c.peb", /* template         */
            "test.caffc",                              /* compilation unit */
            new TestUnit[]{
                new TestUnit("test.caffc",
                    """
                            module main
                            
                            main() -> i32 {
                              str x = "✅ \\033[2mtest passed\\033[0m"
                              return 0
                            }
                            """)
            });

        CodeAssertsStr.assertCodeContains(code,
            "{ 0xe2, 0x9c, 0x85, 0x20, 0x1b, 0x5b, 0x32, 0x6d, 0x74, 0x65, 0x73, 0x74, 0x20, 0x70, 0x61, 0x73, 0x73, 0x65, 0x64, 0x1b, 0x5b, 0x30, 0x6d, 0x00 }",
            "string escapes aren't processed correctly.");
    }
}
