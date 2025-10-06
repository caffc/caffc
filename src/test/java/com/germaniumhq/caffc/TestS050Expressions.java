package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS050Expressions {
    @Test
    public void testExpressionParens() {
        String code = CodeAsserts.compileFullCaffcProgram(
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

        CodeAsserts.assertCodeContains(code, """
                z = (x + y) * (x - y);
                """,
                "parens should translate into the generated code");
    }

    @Test
    public void testCast() {
        String code = CodeAsserts.compileFullCaffcProgram(
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

        CodeAsserts.assertCodeContains(code, "x = (caffc_u32) 3;",
                "primitive casting should translate into the generated code");
        CodeAsserts.assertCodeContains(code, "b = (main_B*) a;",
                "object casting should translate into the generated code");
    }

    @Test
    public void testBoolNot() {
        String code = CodeAsserts.compileFullCaffcProgram(
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

        CodeAsserts.assertCodeContains(code, """
                if (!x) {
                """,
                "not bool should translate into the generated code");
    }

    @Test
    public void testBitNot() {
        String code = CodeAsserts.compileFullCaffcProgram(
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

        CodeAsserts.assertCodeContains(code, """
                y = ~x;
                """,
                "not bits should translate into the generated code");
    }

    @Test
    public void testShift() {
        String code = CodeAsserts.compileFullCaffcProgram(
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

        CodeAsserts.assertCodeContains(code, """
                z = x << y;
                """,
                "left shift should translate into the generated code");
        CodeAsserts.assertCodeContains(code, """
                t = x >> y;
                """,
                "right shift should translate into the generated code");
    }

    @Test
    public void testBitOperations() {
        String code = CodeAsserts.compileFullCaffcProgram(
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

        CodeAsserts.assertCodeContains(code, """
                t = x & y;
                """,
                "bit and should translate into the generated code");
        CodeAsserts.assertCodeContains(code, """
                u = x | y;
                """,
                "bit or should translate into the generated code");
        CodeAsserts.assertCodeContains(code, """
                v = x ^ y;
                """,
                "bit xor should translate into the generated code");
    }


    @Test
    public void testBoolOperations() {
        String code = CodeAsserts.compileFullCaffcProgram(
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

        CodeAsserts.assertCodeContains(code, """
                t = x && y;
                """,
                "bool and should translate into the generated code");
        CodeAsserts.assertCodeContains(code, """
                u = x || y;
                """,
                "bool or should translate into the generated code");
    }

    @Test
    public void testTernaryOperators() {
        String code = CodeAsserts.compileFullCaffcProgram(
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

        CodeAsserts.assertCodeContains(code, """
                y = x == 1 ? 3 : 4;
                """,
                "ternary operators should translate into the generated code");
    }

    @Test
    public void testOperationAssign() {
        //     ('<<='|'>>='|'&='|'|='|'^='|'*='|'/='|'+='|'-=')
        String code = CodeAsserts.compileFullCaffcProgram(
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

        CodeAsserts.assertCodeContains(code, "x <<= y;",
                "shift left assign should translate into the generated code");
        CodeAsserts.assertCodeContains(code, "x >>= y;",
                "shift right assign should translate into the generated code");
        CodeAsserts.assertCodeContains(code, "x &= y;",
                "bit-and assign should translate into the generated code");
        CodeAsserts.assertCodeContains(code, "x |= y;",
                "bit-or assign should translate into the generated code");
        CodeAsserts.assertCodeContains(code, "x ^= y;",
                "bit-xor assign should translate into the generated code");
        CodeAsserts.assertCodeContains(code, "x *= y;",
                "math mul assign should translate into the generated code");
        CodeAsserts.assertCodeContains(code, "x /= y;",
                "math div assign should translate into the generated code");
        CodeAsserts.assertCodeContains(code, "x += y;",
                "math plus assign should translate into the generated code");
        CodeAsserts.assertCodeContains(code, "x -= y;",
                "math minus assign should translate into the generated code");
    }

}
