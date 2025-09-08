package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS011InstantiateAndCall {
    @Test
    public void testInstantiationCallOfAnAnonymousObject() {
        String code = CodeAsserts.compileCaffcProgram(
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

        CodeAsserts.assertCodeContains(code, """
                a_A_hello(a_A_new(), 3);
                """,
                "the new call should go via the dynamic size function defined for _obj_a");

        System.out.println(code);
    }
}