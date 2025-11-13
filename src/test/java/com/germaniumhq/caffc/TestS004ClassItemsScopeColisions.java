package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestS004ClassItemsScopeColisions {
    @BeforeEach
    public void setup() {
        CaffcCompiler.get().hasErrors = false;
    }

    @AfterEach
    public void teardown() {
        CaffcCompiler.get().hasErrors = false;
    }

    @Test
    public void fieldsAndMethodsShouldNotCollide() {
        CodeAssertsAst.compileCaffcUnitsAst(
            "a/a.caffc", /* compilation unit */
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module caffc

                    class A {
                      i32 x

                      x() {
                      }
                    }
                    """)
            }
        );

        Assertions.assertTrue(CaffcCompiler.get().hasErrors);
    }

    @Test
    public void fieldsShouldNotBeDuplicated() {
        CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
            
                                class A {
                                  i32 x
                                  f32 x
                                }
                                """)
                }
        );

        Assertions.assertTrue(CaffcCompiler.get().hasErrors);
    }

    @Test
    public void fieldsShouldNotBeDuplicated2() {
        CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
            
                                class A {
                                  i32 x, x
                                }
                                """)
                }
        );

        Assertions.assertTrue(CaffcCompiler.get().hasErrors);
    }

    @Test
    public void methodsShouldNotBeDuplicated() {
        CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
            
                                class A {
                                  x() {
                                  }

                                  x(i32 y) {
                                  }
                                }
                                """)
                }
        );

        Assertions.assertTrue(CaffcCompiler.get().hasErrors);
    }

    @Test
    public void methodCanOverloadGlobalFunction() {
        CodeAssertsAst.compileCaffcUnitsAst(
            "a/a.caffc", /* compilation unit */
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module caffc
 
                    x() {
                    }

                    class A {
                      x(i32 y) {
                      }
                    }
                    """)
            }
        );

        Assertions.assertFalse(CaffcCompiler.get().hasErrors);
    }
}
