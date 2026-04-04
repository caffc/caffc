
package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertFalse(CaffcCompiler.get().hasWarnings);
    }

    /**
     * Tests if `not instanceof` correctly generates the code.
     */
    @Test
    public void testXNotInstanceOfYRendering() {
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
                              bool b = a not instanceof A
                            }
                            """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_caffc_bool_1 = caffc_instanceof_check(a, /* caffc:null:A:OBJECT */ 1);
                _caffc_temp_caffc_bool_1 = !_caffc_temp_caffc_bool_1;
                """,
            "There should be an instanceof check.");
        assertFalse(CaffcCompiler.get().hasWarnings);
    }


    /**
     * Tests if `not X instanceof Y` correctly generates the code. The form
     * while allowed, would create a warning to get the programmer to
     * write normal, readable, code.
     */
    @Test
    public void testNotXInstanceYOfRendering() {
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
                              bool b = not a instanceof A
                            }
                            """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_caffc_bool_1 = caffc_instanceof_check(a, /* caffc:null:A:OBJECT */ 1);
                _caffc_temp_caffc_bool_2 = !_caffc_temp_caffc_bool_1;
                """,
            "There should be an instanceof check.");
        assertTrue(CaffcCompiler.get().hasWarnings);
    }
}
