package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAssertsStr.assertCodeContains;
import static com.germaniumhq.caffc.CodeAssertsStr.compileFullCaffcProgram;

public class TestS064FnCallable {
    @Test
    public void objectImplementingFnIsCallable() {
        String code = compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            new TestUnit[] {
                new TestUnit("test.caffc",
                    """
                    module main

                    use caffc.collection

                    class Adder implements fn<i32> {
                      call(... obj[] args, Dict<str, obj> kw) -> i32 {
                        return args.size() + kw.size()
                      }
                    }

                    main() -> i32 {
                      Adder a = new Adder()
                      return a(null, null, extra=null)
                    }
                    """)
            });

        assertCodeContains(code, "Adder_call(",
                "obj(...) on a class implementing fn should lower to call()");
    }

    @Test
    public void fnInterfaceTypedValueIsCallable() {
        String code = compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            new TestUnit[] {
                new TestUnit("test.caffc",
                    """
                    module main

                    use caffc.collection

                    class Constant implements fn<i32> {
                      call(... obj[] args, Dict<str, obj> kw) -> i32 {
                        return 7
                      }
                    }

                    main() -> i32 {
                      fn<i32> f = new Constant()
                      return f()
                    }
                    """)
            });

        assertCodeContains(code, "fn_call(",
                "calling through an fn-typed variable should use interface dispatch");
        assertCodeContains(code, "caffc_EMPTY_KWARGS",
                "fn() with no kwargs should pass the shared empty kwargs dict");
    }
}
