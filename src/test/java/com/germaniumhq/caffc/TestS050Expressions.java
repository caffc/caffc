package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestS050Expressions {
    @Test
    public void testExpressionParens() {
        String code = CodeAssertsStr.compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                        module main

                                        main() -> i32 {
                                          i32 x, y, z

                                          z = (x + y) * (x - y)

                                          return 0
                                        }
                                        """)
                }
        );

        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_caffc_i32_1 = x + y;
                _caffc_temp_caffc_i32_2 = x - y;
                _caffc_temp_caffc_i32_3 = _caffc_temp_caffc_i32_1 * _caffc_temp_caffc_i32_2;
                z = _caffc_temp_caffc_i32_3;
                """,
                "parens should translate into the generated code");
    }

    @Test
    public void testCast() {
        String code = CodeAssertsStr.compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                        module main

                                        interface A {}
                                        class B implements A {}

                                        main() -> i32 {
                                          // primitive cast
                                          u32 x = (u32) 3_u64

                                          // objects cast
                                          A a = new B()
                                          B b = (B) a
                                        }
                                        """)
                }
        );

        CodeAssertsStr.assertCodeContains(code, "_caffc_temp_caffc_u32_1 = (caffc_u32) 3;",
                "primitive casting should translate into the generated code");
        CodeAssertsStr.assertCodeContains(code, "_caffc_temp_main_B_2 = (main_B*) a;",
                "object casting should translate into the generated code");
    }

    @Test
    public void testBoolNot() {
        String code = CodeAssertsStr.compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                        module main

                                        main() -> i32 {
                                          bool x = 0

                                          if not x {
                                            return 1
                                          }

                                          return 0
                                        }
                                        """)
                }
        );

        CodeAssertsStr.assertCodeContains(code, """
x = 0;
_caffc_temp_caffc_bool_1 = !x;
/* if0: */
if (! _caffc_temp_caffc_bool_1) { goto else0; }
_caffc_stack_frame_unregister(caffc_null);
return 1;
else0:
_caffc_stack_frame_unregister(caffc_null);
return 0;
                """,
                "not bool should translate into the generated code");
    }

    @Test
    public void testBitNot() {
        String code = CodeAssertsStr.compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                        module main

                                        main() -> i32 {
                                          u32 x = 1
                                          u32 y = ~x

                                          return 0
                                        }
                                        """)
                }
        );

        CodeAssertsStr.assertCodeContains(code, """
                x = 1;
                _caffc_temp_caffc_u32_1 = ~x;
                y = _caffc_temp_caffc_u32_1;
                """,
                "not bits should translate into the generated code");
    }

    @Test
    public void testShift() {
        String code = CodeAssertsStr.compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                        module main

                                        main() -> i32 {
                                          u32 x = 1
                                          u32 y = 1

                                          u32 z = x << y
                                          u32 t = x >> y

                                          return 0
                                        }
                                        """)
                }
        );

        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_caffc_u32_1 = x << y;
                z = _caffc_temp_caffc_u32_1;
                """,
                "left shift should translate into the generated code");
        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_caffc_u32_2 = x >> y;
                t = _caffc_temp_caffc_u32_2;
                """,
                "right shift should translate into the generated code");
    }

    @Test
    public void testBitOperations() {
        String code = CodeAssertsStr.compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                        module main

                                        main() -> i32 {
                                          u32 x = 1
                                          u32 y = 2

                                          u32 t = x & y
                                          u32 u = x | y
                                          u32 v = x ^ y

                                          return 0
                                        }
                                        """)
                }
        );

        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_caffc_u32_1 = x & y;
                t = _caffc_temp_caffc_u32_1;
                """,
                "bit and should translate into the generated code");
        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_caffc_u32_2 = x | y;
                u = _caffc_temp_caffc_u32_2;
                """,
                "bit or should translate into the generated code");
        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_caffc_u32_3 = x ^ y;
                v = _caffc_temp_caffc_u32_3;
                """,
                "bit xor should translate into the generated code");
    }


    @Test
    public void testBoolOperations() {
        String code = CodeAssertsStr.compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                        module main

                                        main() -> i32 {
                                          bool x = 1
                                          bool y = 0

                                          u32 t = x and y
                                          u32 u = x or y

                                          return 0
                                        }
                                        """)
                }
        );

        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_caffc_bool_1 = x && y;
                t = _caffc_temp_caffc_bool_1;
                """,
                "bool and should translate into the generated code");
        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_caffc_bool_2 = x || y;
                u = _caffc_temp_caffc_bool_2;
                """,
                "bool or should translate into the generated code");
    }

    @Test
    public void testTernaryOperators() {
        String code = CodeAssertsStr.compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                        module main

                                        main() -> i32 {
                                          u32 x = 1
                                          u32 y = x == 1 ? 3 : 4

                                          return 0
                                        }
                                        """)
                }
        );

        CodeAssertsStr.assertCodeContains(code, """
/* ternaryStart0: */
_caffc_temp_caffc_bool_1 = x == 1;
if (! _caffc_temp_caffc_bool_1) { goto ternaryElse0; }
_caffc_temp_caffc_i32_1 = 3;
goto ternaryEnd0;
ternaryElse0:
_caffc_temp_caffc_i32_1 = 4;
ternaryEnd0:
y = _caffc_temp_caffc_i32_1;
_caffc_stack_frame_unregister(caffc_null);
return 0;
                """,
                "ternary operators should translate into the generated code");
    }

    @Test
    public void testTernaryOperatorsEmptyTrue() {
        String code = CodeAssertsStr.compileCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                            module main

                            class A {}

                            main() -> i32 {
                              A a
                              A b = new A()
                              A c = a ?: b

                              return 0
                            }
                            """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
                /* ternaryStart0: */
                if (! a) { goto ternaryElse0; }
                _caffc_temp_main_A_2 = a;
                goto ternaryEnd0;
                ternaryElse0:
                _caffc_temp_main_A_2 = b;
                ternaryEnd0:
                c = _caffc_temp_main_A_2;
                """,
            "ternary operators should translate into the generated code");
    }


    @Test
    public void testOperationAssign() {
        //     ('<<='|'>>='|'&='|'|='|'^='|'*='|'/='|'+='|'-=')
        String code = CodeAssertsStr.compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                        module main

                                        main() -> i32 {
                                          u32 x = 1
                                          u32 y = 2
                                          
                                          x <<= y
                                          x >>= y
                                          x &= y
                                          x |= y
                                          x ^= y
                                          x *= y
                                          x /= y
                                          x += y
                                          x -= y

                                          return 0
                                        }
                                        """)
                }
        );

        CodeAssertsStr.assertCodeContains(code, "x = x << y;",
                "shift left assign should translate into the generated code");
        CodeAssertsStr.assertCodeContains(code, "x = x >> y;",
                "shift right assign should translate into the generated code");
        CodeAssertsStr.assertCodeContains(code, "x = x & y;",
                "bit-and assign should translate into the generated code");
        CodeAssertsStr.assertCodeContains(code, "x = x | y;",
                "bit-or assign should translate into the generated code");
        CodeAssertsStr.assertCodeContains(code, "x = x ^ y;",
                "bit-xor assign should translate into the generated code");
        CodeAssertsStr.assertCodeContains(code, "x = x * y;",
                "math mul assign should translate into the generated code");
        CodeAssertsStr.assertCodeContains(code, "x = x / y;",
             "math div assign should translate into the generated code");
        CodeAssertsStr.assertCodeContains(code, "x = x + y;",
             "math plus assign should translate into the generated code");
        CodeAssertsStr.assertCodeContains(code, "x = x - y;",
             "math minus assign should translate into the generated code");
    }

    @Test
    public void testCharLiteral() {
       String code = CodeAssertsStr.compileCaffcProgram(
               "caffc/template/c/compilation_unit_c.peb",
               "a/a.caffc",
               new TestUnit[] {
                       new TestUnit("a/a.caffc",
                               """
                                       module main
    
                                       main() -> i32 {
                                         u32 x = 'a'
                                         u32 y = '\\n'
                                         u32 z = '\\x32'
                                         u32 unicode_char = '✅'
    
                                         return 0
                                       }
                                       """)
               }
       );

       CodeAssertsStr.assertCodeContains(code, "x = 97;",
               "character 'a' should translate to ASCII value 97");
       CodeAssertsStr.assertCodeContains(code, "y = 10;",
               "escaped newline '\\n' should translate to ASCII value 10");
       CodeAssertsStr.assertCodeContains(code, "z = 50;",
               "hex escape '\\x32' should translate to value 50 (0x32)");
       CodeAssertsStr.assertCodeContains(code, "unicode_char = 14851205;",
           "character '✅' should translate to value 14851205, its bytes: 0xe29c85");
    }

    @Test
    public void testCharLiteralMultipleCharactersFails() {
       // Test that character literals with multiple characters are rejected
       // We expect a runtime exception with the error message
       try {
           String code = CodeAssertsStr.compileCaffcProgram(
                   "caffc/template/c/compilation_unit_c.peb",
                   "a/a.caffc",
                   new TestUnit[] {
                           new TestUnit("a/a.caffc",
                                   """
                                           module main
    
                                           main() -> i32 {
                                             u32 x = 'aa'
                                             return 0
                                           }
                                           """)
                   }
           );
           fail("Should have thrown exception for multiple characters in char literal");
       } catch (RuntimeException e) {
           assertTrue(e.getMessage().contains("Errors found in compilation"), "Expected compilation error but got: " + e.getMessage());
       }
    }

    @Test
    public void testCharLiteralHexEscapeTooShortFails() {
       // Test that hex escape sequences with too few hex digits are rejected
       // The lexer rejects incomplete escape sequences with ERROR level messages
       try {
           String code = CodeAssertsStr.compileCaffcProgram(
                   "caffc/template/c/compilation_unit_c.peb",
                   "a/a.caffc",
                   new TestUnit[] {
                           new TestUnit("a/a.caffc",
                                   """
                                           module main
    
                                           main() -> i32 {
                                             u32 x = '\\x3'
                                             return 0
                                           }
                                           """)
                   }
           );
           fail("Should have thrown exception for hex escape with too few digits");
       } catch (RuntimeException e) {
           assertTrue(e.getMessage().contains("Errors found in compilation"), "Expected compilation error but got: " + e.getMessage());
       }
    }

    @Test
    public void testCharHexEscapeTooManyBytesFails() {
        // Test that hex escape sequences with too few hex digits are rejected
        // The lexer rejects incomplete escape sequences with ERROR level messages
        try {
            String code = CodeAssertsStr.compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
                        """
                                module main

                                main() -> i32 {
                                  u32 x = '\\xe2\\x9c\\x85\\x85\\x85'
                                  return 0
                                }
                                """)
                }
            );
            fail("Should have thrown exception for hex escape with too many bytes in character");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("too many characters in escape"),
                "Expected too many characters in escape but got: " + e.getMessage());
        }
    }

       @Test
       public void testCharLiteralOctalEscapeTooShortFails() {
           // Test that octal escape sequences with too few octal digits are rejected
           // The lexer rejects incomplete escape sequences with ERROR level messages
           try {
               String code = CodeAssertsStr.compileCaffcProgram(
                       "caffc/template/c/compilation_unit_c.peb",
                       "a/a.caffc",
                       new TestUnit[] {
                               new TestUnit("a/a.caffc",
                                       """
                                               module main

                                               main() -> i32 {
                                                 u32 x = '\\03'
                                                 return 0
                                               }
                                               """)
                       }
               );
               fail("Should have thrown exception for octal escape with too few digits");
           } catch (RuntimeException e) {
               assertTrue(e.getMessage().contains("Errors found in compilation"), "Expected compilation error but got: " + e.getMessage());
           }
       }
  }

