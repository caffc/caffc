package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestS110TypeAssignability {
    @BeforeEach
    public void setup() {
        CaffcCompiler.get().hasErrors = false;
    }

    @AfterEach
    public void teardown() {
        CaffcCompiler.get().hasErrors = false;
    }

    @Test
    public void classMissingInterfaceMethodShouldError() {
        try {
            CodeAssertsAst.compileCaffcUnitsAst(
                    "a/a.caffc",
                    new TestUnit[] {
                            new TestUnit("a/a.caffc",
                                    """
                                    module caffc

                                    class str {}

                                    interface Greeter {
                                      greet() -> str
                                    }

                                    class A implements Greeter {
                                    }
                                    """)
                    }
            );
        } catch (Exception e) {
            // Expected - later phases may fail after errors are reported
        }

        Assertions.assertTrue(CaffcCompiler.get().hasErrors,
                "missing interface method should be a compilation error");
    }

    @Test
    public void classImplementingInterfaceMethodsShouldSucceed() {
        CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc

                                class str {}

                                interface Greeter {
                                  greet() -> str
                                }

                                class A implements Greeter {
                                  greet() -> str {
                                    return null
                                  }
                                }
                                """)
                }
        );

        Assertions.assertFalse(CaffcCompiler.get().hasErrors);
    }

    @Test
    public void callWithIncompatibleArgumentShouldError() {
        try {
            CodeAssertsAst.compileCaffcUnitsAst(
                    "a/a.caffc",
                    new TestUnit[] {
                            new TestUnit("a/a.caffc",
                                    """
                                    module caffc

                                    takeInt(i32 x) {
                                    }

                                    main() {
                                      takeInt("hello")
                                    }
                                    """)
                    }
            );
        } catch (Exception e) {
            // Expected
        }

        Assertions.assertTrue(CaffcCompiler.get().hasErrors,
                "incompatible call argument should be a compilation error");
    }

    @Test
    public void callWithCompatibleArgumentShouldSucceed() {
        CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc

                                interface obj {}

                                class A implements obj {}

                                takeObj(obj x) {
                                }

                                main() {
                                  A a = new A()
                                  takeObj(a)
                                }
                                """)
                }
        );

        Assertions.assertFalse(CaffcCompiler.get().hasErrors);
    }

    @Test
    public void assignmentWithIncompatibleTypesShouldError() {
        try {
            CodeAssertsAst.compileCaffcUnitsAst(
                    "a/a.caffc",
                    new TestUnit[] {
                            new TestUnit("a/a.caffc",
                                    """
                                    module caffc

                                    class str {}

                                    main() {
                                      i32 x = "hello"
                                    }
                                    """)
                    }
            );
        } catch (Exception e) {
            // Expected
        }

        Assertions.assertTrue(CaffcCompiler.get().hasErrors,
                "incompatible assignment should be a compilation error");
    }

    @Test
    public void assignmentToInterfaceFromImplementorShouldSucceed() {
        CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc

                                interface I {}

                                class A implements I {}

                                main() {
                                  I x = new A()
                                  x = null
                                }
                                """)
                }
        );

        Assertions.assertFalse(CaffcCompiler.get().hasErrors);
    }

    @Test
    public void interfaceMethodWrongParameterTypeShouldError() {
        try {
            CodeAssertsAst.compileCaffcUnitsAst(
                    "a/a.caffc",
                    new TestUnit[] {
                            new TestUnit("a/a.caffc",
                                    """
                                    module caffc

                                    interface I {
                                      f(i32 x)
                                    }

                                    class A implements I {
                                      f(bool x) {
                                      }
                                    }
                                    """)
                    }
            );
        } catch (Exception e) {
            // Expected
        }

        Assertions.assertTrue(CaffcCompiler.get().hasErrors,
                "wrong interface method parameter type should be a compilation error");
    }
}
