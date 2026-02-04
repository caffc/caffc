package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS011InstantiateAndCall {
    @Test
    public void testInstantiationCallOfAnAnonymousObject() {
        String code = CodeAssertsStr.compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
                        """
                        module a

                        class A {
                          hello(i32 x) {
                          }
                        }

                        main() {
                          new A().hello(3)
                        }
                        """)
                }
        );

        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_a_A_1 = a_A_new();
                a_A_hello(_caffc_temp_a_A_1, 3);
                """,
                "the new call should go via the dynamic size function defined for _obj_a");
    }
}
