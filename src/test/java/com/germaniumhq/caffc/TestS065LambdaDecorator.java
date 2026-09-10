package com.germaniumhq.caffc;

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

                    Identity identity = new Identity()

                    @identity
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
}
