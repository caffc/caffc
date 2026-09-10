package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAssertsStr.assertCodeContains;
import static com.germaniumhq.caffc.CodeAssertsStr.compileCaffcCode;
import static com.germaniumhq.caffc.CodeAssertsStr.compileFullCaffcProgram;

public class TestS060DefaultNamedVarargs {
    @Test
    public void defaultParameterInlinedBeforeCall() {
        String code = compileCaffcCode(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            """
            module main

            hello(i32 a, i32 b=7) {
            }

            main() {
              hello(3)
            }
            """);

        assertCodeContains(code, "main_hello(3, 7);",
                "missing default literal should be passed at the call site");
    }

    @Test
    public void namedArgumentSkipsDefault() {
        String code = compileCaffcCode(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            """
            module main

            hello(i32 a=3, i32 b=7) {
            }

            main() {
              hello(b=9)
            }
            """);

        assertCodeContains(code, "main_hello(3, 9);",
                "named b=9 should fill a from its default");
    }

    @Test
    public void defaultMethodCallInlinedBeforeCall() {
        String code = compileCaffcCode(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            """
            module main

            defaultB() -> i32 {
              return 7
            }

            hello(i32 a, i32 b=defaultB()) {
            }

            main() {
              hello(3)
            }
            """);

        assertCodeContains(code,
            """
            _caffc_temp_caffc_i32_1 = main_defaultB();
            main_hello(3, _caffc_temp_caffc_i32_1);
            """,
            "default that is a call should be evaluated into a temp before the outer call");
    }

    @Test
    public void bothNamedArguments() {
        String code = compileCaffcCode(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            """
            module main

            hello(i32 a=3, i32 b=7) {
            }

            main() {
              hello(a=1, b=2)
            }
            """);

        assertCodeContains(code, "main_hello(1, 2);",
                "fully named call should pass values directly");
    }

    @Test
    public void explicitVarargsPacksExtras() {
        String code = compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            new TestUnit[] {
                new TestUnit("test.caffc",
                    """
                    module main

                    hello(i32 a ... obj[] rest) {
                    }

                    main() {
                      hello(1, null, null)
                    }
                    """)
            });

        assertCodeContains(code, "main_hello(1,",
                "varargs call should still invoke hello");
        assertCodeContains(code, "_newa(",
                "extra varargs should be packed into a new array");
        assertCodeContains(code, "_set(",
                "packed varargs elements should be assigned via set()");
    }

    @Test
    public void autoTrailingArrayExactArityPassesThrough() {
        String code = compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            new TestUnit[] {
                new TestUnit("test.caffc",
                    """
                    module main

                    hello(i32 a, obj[] rest) {
                    }

                    main() {
                      obj[] xs = new obj[0]
                      hello(1, xs)
                    }
                    """)
            });

        assertCodeContains(code, "main_hello(1, xs);",
                "exact arity with trailing array should pass the array through");
    }

    @Test
    public void autoTrailingArrayPacksExtras() {
        String code = compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            new TestUnit[] {
                new TestUnit("test.caffc",
                    """
                    module main

                    hello(i32 a, obj[] rest) {
                    }

                    main() {
                      hello(1, null, null)
                    }
                    """)
            });

        assertCodeContains(code, "_newa(",
                "extra positionals for trailing array should pack into a new array");
    }

    @Test
    public void kwargsPackUnknownNames() {
        String code = compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            new TestUnit[] {
                new TestUnit("test.caffc",
                    """
                    module main

                    use caffc.collection

                    hello(i32 a ... obj[] rest, Dict<str, obj> kw) {
                    }

                    main() {
                      hello(1, extra=null)
                    }
                    """)
            });

        assertCodeContains(code, "HashDict",
                "unknown named args should be packed into a HashDict");
        assertCodeContains(code, "_put(",
                "kwargs entries should be inserted via put()");
    }

    @Test
    public void kwargsDictParameterCanUseInheritedCollectionMethods() {
        String code = compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            new TestUnit[] {
                new TestUnit("test.caffc",
                    """
                    module main

                    use caffc.collection

                    hello(i32 a ... obj[] rest, Dict<str, obj> kw) -> i32 {
                      return kw.size() + rest.size()
                    }

                    main() {
                      hello(1, extra=null)
                    }
                    """)
            });

        assertCodeContains(code, "Collection_size(",
                "Dict<str, obj> should resolve inherited Collection.size()");
    }

    @Test
    public void defaultObjectExpressionReplicatedPerCall() {
        String code = compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            new TestUnit[] {
                new TestUnit("test.caffc",
                    """
                    module main

                    class Box {
                      constructor() {
                      }
                    }

                    hello(Box b=new Box()) {
                    }

                    main() {
                      hello()
                      hello()
                    }
                    """)
            });

        int first = code.indexOf("Box_new(");
        int second = code.indexOf("Box_new(", first + 1);
        if (first < 0 || second < 0) {
            System.out.println(code);
            throw new AssertionError("expected new Box() default to be replicated at each call site");
        }
    }

    @Test
    public void emptyKwargsUsesSharedGlobal() {
        String code = compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            new TestUnit[] {
                new TestUnit("test.caffc",
                    """
                    module main

                    use caffc.collection

                    hello(i32 a ... obj[] rest, Dict<str, obj> kw) {
                    }

                    main() {
                      hello(1)
                    }
                    """)
            });

        assertCodeContains(code, "caffc_EMPTY_KWARGS",
                "calls with no keyword args should pass the shared empty kwargs dict");
    }
}
