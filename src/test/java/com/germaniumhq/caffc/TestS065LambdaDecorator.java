package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAssertsStr.assertCodeContains;
import static com.germaniumhq.caffc.CodeAssertsStr.compileFullCaffcProgram;

public class TestS065LambdaDecorator {
    @Test
    public void lambdaExpressionLowersToFnClass() {
        String code = compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            new TestUnit[] {
                new TestUnit("test.caffc",
                    """
                    module main

                    main() -> i32 {
                      fn<i32> f = fn() -> i32 {
                        return 7
                      }
                      return f()
                    }
                    """)
            });

        assertCodeContains(code, "_Lambda_",
                "lambda should synthesize a class");
        assertCodeContains(code, "_new(",
                "lambda expression should instantiate the synthetic class");
        assertCodeContains(code, "fn_call(",
                "calling the lambda through fn<i32> should use interface dispatch");
    }

    @Test
    public void lambdaWithTypedParamUnpacksArgs() {
        String code = compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            new TestUnit[] {
                new TestUnit("test.caffc",
                    """
                    module main

                    main() -> i32 {
                      fn<i32> add1 = fn(i32 x) -> i32 {
                        return x + 1
                      }
                      return add1(41)
                    }
                    """)
            });

        assertCodeContains(code, "I32_new(",
                "primitive args to fn.call should auto-box");
        assertCodeContains(code, "->value",
                "lambda call should unbox typed primitive params");
    }

    @Test
    public void decoratorWrapsFunctionAsFnGlobal() {
        String code = compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            new TestUnit[] {
                new TestUnit("test.caffc",
                    """
                    module main

                    use caffc.collection

                    class Identity implements fn<fn<i32>> {
                      call(... obj[] args, Dict<str, obj> kw) -> fn<i32> {
                        return (fn<i32>) args.get(0)
                      }
                    }

                    identity() -> fn<fn<i32>> {
                      return new Identity()
                    }

                    @identity()
                    answer() -> i32 {
                      return 42
                    }

                    main() -> i32 {
                      return answer()
                    }
                    """)
            });

        assertCodeContains(code, "main_answer",
                "decorated name should remain callable as answer()");
        assertCodeContains(code, "caffc_fn_call(main_answer",
                "decorated function should be invoked as an fn global");
        assertCodeContains(code, "Identity_call(",
                "decorator should be applied to the body lambda");
    }

    @Test
    public void parametrizedDecoratorPassesFactoryArgs() {
        String code = compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            new TestUnit[] {
                new TestUnit("test.caffc",
                    """
                    module main

                    use caffc.collection

                    class AddN implements fn<fn<i32>> {
                      i32 n

                      constructor(i32 n) {
                        _this.n = n
                      }

                      call(... obj[] args, Dict<str, obj> kw) -> fn<i32> {
                        fn<i32> inner = (fn<i32>) args.get(0)
                        i32 amount = _this.n
                        return fn() -> i32 {
                          return inner() + amount
                        }
                      }
                    }

                    addN(i32 n) -> fn<fn<i32>> {
                      return new AddN(n)
                    }

                    @addN(10)
                    answer() -> i32 {
                      return 32
                    }

                    main() -> i32 {
                      return answer()
                    }
                    """)
            });

        assertCodeContains(code, "main_addN(",
                "parametrized decorator should call the factory with config args");
        assertCodeContains(code, "AddN_call(",
                "factory result should wrap the body lambda");
        assertCodeContains(code, "caffc_fn_call(main_answer",
                "decorated function should remain an fn global");
    }

    @Test
    public void lambdaCapturesEnclosingLocal() {
        String code = compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            new TestUnit[] {
                new TestUnit("test.caffc",
                    """
                    module main

                    main() -> i32 {
                      i32 n = 40
                      fn<i32> f = fn() -> i32 {
                        return n + 2
                      }
                      return f()
                    }
                    """)
            });

        assertCodeContains(code, "constructor(",
                "capturing lambda should synthesize a constructor");
        assertCodeContains(code, "_new(",
                "lambda expression should pass captures into _new");
    }

    @Test
    public void lambdaCaptureAssignmentIsError() {
        CaffcCompiler.get().hasErrors = false;
        try {
            compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "test.caffc",
                new TestUnit[] {
                    new TestUnit("test.caffc",
                        """
                        module main

                        main() -> i32 {
                          i32 n = 1
                          fn<i32> f = fn() -> i32 {
                            n = 2
                            return n
                          }
                          return f()
                        }
                        """)
                });
        } catch (Exception ignored) {
            // later phases may fail after the error is reported
        }

        Assertions.assertTrue(CaffcCompiler.get().hasErrors,
                "assignment to a captured variable should be a compilation error");
    }

    @Test
    public void lambdaCaptureOpAssignIsError() {
        CaffcCompiler.get().hasErrors = false;
        try {
            compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "test.caffc",
                new TestUnit[] {
                    new TestUnit("test.caffc",
                        """
                        module main

                        main() -> i32 {
                          i32 n = 1
                          fn<i32> f = fn() -> i32 {
                            n += 1
                            return n
                          }
                          return f()
                        }
                        """)
                });
        } catch (Exception ignored) {
        }

        Assertions.assertTrue(CaffcCompiler.get().hasErrors,
                "op-assign to a captured variable should be a compilation error");
    }
}
