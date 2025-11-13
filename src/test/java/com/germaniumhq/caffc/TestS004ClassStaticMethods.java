package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAssertsStr.assertCodeContains;
import static com.germaniumhq.caffc.CodeAssertsStr.compileCaffcProgram;

public class TestS004ClassStaticMethods {
    @Test
    public void testStaticClassFunctions() {
        String code = compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb", /* template         */
                "test.caffc",                              /* compilation unit */
                new TestUnit[] {
                        new TestUnit("test.caffc",
                                """
                module caffc

                class str {}

                class A {
                  str _name

                  constructor(str name) {
                    _this._name = name
                  }
                
                  static newA() -> A {
                    return new A("yay")
                  }
                  
                  getName() -> str {
                    return _this._name
                  }
                }
                
                createA() {
                  A a = A.newA()
                }
                """)}
        );

        assertCodeContains(code, "caffc_A* caffc_A_newA() {",
                "static methods shouldn't have the _this parameter");
        assertCodeContains(code, "caffc_str* caffc_A_getName(caffc_A* _this) {",
                "non-static methods should have the _this parameter");
        assertCodeContains(code, "a = caffc_A_newA();",
                "calls of static methods should be possible");
    }

    @Test
    public void testStaticMethodsOutsideClassesShouldFailCompiling() {
        CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc

                                static x() -> i32 {
                                  return 0
                                }
                                """)
                }
        );

        Assertions.assertTrue(CaffcCompiler.get().hasErrors,
                "it shouldn't be possible to have a static methhod outside a class");
    }

}
