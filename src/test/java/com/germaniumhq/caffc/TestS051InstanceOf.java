
package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS051InstanceOf {
    /**
     * Tests if `instanceof` correctly generates the code.
     */
    @Test
    public void testInstanceOfRendering() {
        String code = CodeAssertsStr.compileCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                            module caffc
                            
                            interface I {}
                            
                            class A implements I {
                            }

                            test() {
                              I a = new A()
                              bool b = a instanceof A
                            }
                            """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_caffc_bool_1 = caffc_instanceof_check(a, /* caffc:null:A:OBJECT */
                """,
            "There should be an instanceof check.");
    }
}
