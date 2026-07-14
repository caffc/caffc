package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestS004VariableShadowingTypeNames {
    @BeforeEach
    public void setup() {
        CaffcCompiler.get().hasErrors = false;
    }

    @AfterEach
    public void teardown() {
        CaffcCompiler.get().hasErrors = false;
    }

    @Test
    public void variableShouldNotShadowClass() {
        try {
            CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
                        """
                        module caffc

                        class MyClass {
                        }

                        test() {
                          MyClass MyClass = new MyClass()
                        }
                        """)
                }
            );
        } catch (Exception e) {
            // Expected - compiler may crash after reporting errors
        }

        Assertions.assertTrue(CaffcCompiler.get().hasErrors);
    }

    @Test
    public void variableShouldNotShadowInterface() {
        try {
            CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
                        """
                        module caffc

                        interface MyInterface {
                        }

                        test() {
                          MyInterface MyInterface = null
                        }
                        """)
                }
            );
        } catch (Exception e) {
            // Expected - compiler may crash after reporting errors
        }

        Assertions.assertTrue(CaffcCompiler.get().hasErrors);
    }

    @Test
    public void parameterShouldNotShadowClass() {
        try {
            CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
                        """
                        module caffc

                        class MyClass {
                        }

                        test(MyClass MyClass) {
                        }
                        """)
                }
            );
        } catch (Exception e) {
            // Expected - compiler may crash after reporting errors
        }

        Assertions.assertTrue(CaffcCompiler.get().hasErrors);
    }

    @Test
    public void parameterShouldNotShadowInterface() {
        try {
            CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
                        """
                        module caffc

                        interface MyInterface {
                        }

                        test(MyInterface MyInterface) {
                        }
                        """)
                }
            );
        } catch (Exception e) {
            // Expected - compiler fails after reporting error
        }

        Assertions.assertTrue(CaffcCompiler.get().hasErrors);
    }

    @Test
    public void variableShouldNotShadowFunction() {
        try {
            CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
                        """
                        module caffc

                        myFunc() {
                        }

                        test() {
                          myFunc myFunc = null
                        }
                        """)
                }
            );
        } catch (Exception e) {
            // Expected - compiler may crash after reporting errors
        }

        Assertions.assertTrue(CaffcCompiler.get().hasErrors);
    }

    @Test
    public void parameterShouldNotShadowFunction() {
        try {
            CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
                        """
                        module caffc

                        myFunc() {
                        }

                        test(myFunc myFunc) {
                        }
                        """)
                }
            );
        } catch (Exception e) {
            // Expected - compiler fails after reporting error
        }

        Assertions.assertTrue(CaffcCompiler.get().hasErrors);
    }
}
