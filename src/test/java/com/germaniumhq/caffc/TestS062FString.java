package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.error.CancelCompilationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAssertsStr.compileFullCaffcProgram;

public class TestS062FString {
    @BeforeEach
    public void resetCompiler() {
        CaffcCompiler.get().hasErrors = false;
    }

    @Test
    public void testFStringLowersToAdd() {
        String code = compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "test.caffc",
                new TestUnit[]{
                        new TestUnit("test.caffc",
                                """
                                        module main

                                        main() -> i32 {
                                          str user = "alice"
                                          str msg = f"user: {user}"
                                          return 0
                                        }
                                        """)
                });

        CodeAssertsStr.assertCodeContains(code, "caffc_str_add(",
                "f-string should lower to str.add()");
    }

    @Test
    public void testFStringLiteralOnlyIsConstant() {
        String code = compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "test.caffc",
                new TestUnit[]{
                        new TestUnit("test.caffc",
                                """
                                        module main

                                        main() -> i32 {
                                          str msg = f"hello"
                                          return 0
                                        }
                                        """)
                });

        Assertions.assertFalse(code.contains("caffc_str_add("),
                "literal-only f-string should not call add");
        CodeAssertsStr.assertCodeContains(code, "(caffc_str*)&caffc_cstr_",
                "literal-only f-string should emit a string constant");
    }

    @Test
    public void testFStringEscapedBraces() {
        String code = compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "test.caffc",
                new TestUnit[]{
                        new TestUnit("test.caffc",
                                """
                                        module main

                                        main() -> i32 {
                                          str msg = f"user: {{user}}"
                                          return 0
                                        }
                                        """)
                });

        Assertions.assertFalse(code.contains("caffc_str_add("),
                "escaped braces should not interpolate");
    }

    @Test
    public void testFStringDotAndRange() {
        String code = compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "test.caffc",
                new TestUnit[]{
                        new TestUnit("test.caffc",
                                """
                                        module main

                                        class User {
                                          str name
                                        }

                                        main() -> i32 {
                                          User u = new User()
                                          u.name = "bob"
                                          str h = "hello.exe"
                                          str msg = f"{u.name}-{h[1:5]}"
                                          return 0
                                        }
                                        """)
                });

        CodeAssertsStr.assertCodeContains(code, "caffc_str_add(",
                "f-string with multiple parts should call add");
        CodeAssertsStr.assertCodeContains(code, "caffc_str_range(",
                "f-string range interpolation should lower to range()");
    }

    @Test
    public void testFStringRejectsMath() {
        try {
            compileFullCaffcProgram(
                    "caffc/template/c/compilation_unit_c.peb",
                    "test.caffc",
                    new TestUnit[]{
                            new TestUnit("test.caffc",
                                    """
                                            module main

                                            main() -> i32 {
                                              str msg = f"{1 + 2}"
                                              return 0
                                            }
                                            """)
                    });
            throw new AssertionError("Expected fatal error for math in f-string");
        } catch (CancelCompilationException e) {
            Assertions.assertTrue(
                    e.getMessage().contains("f-string") || e.getMessage().contains("invalid"),
                    "Expected f-string error but got: " + e.getMessage());
        }
    }

    @Test
    public void testFStringRejectsNonStr() {
        try {
            compileFullCaffcProgram(
                    "caffc/template/c/compilation_unit_c.peb",
                    "test.caffc",
                    new TestUnit[]{
                            new TestUnit("test.caffc",
                                    """
                                            module main

                                            main() -> i32 {
                                              i32 x = 3
                                              str msg = f"n={x}"
                                              return 0
                                            }
                                            """)
                    });
        } catch (Exception e) {
            // may throw after reporting errors
        }

        Assertions.assertTrue(CaffcCompiler.get().hasErrors,
                "non-str f-string interpolation should be a compilation error");
    }

    @Test
    public void testFStringRejectsFunctionCall() {
        try {
            compileFullCaffcProgram(
                    "caffc/template/c/compilation_unit_c.peb",
                    "test.caffc",
                    new TestUnit[]{
                            new TestUnit("test.caffc",
                                    """
                                            module main

                                            hello() -> str {
                                              return "hi"
                                            }

                                            main() -> i32 {
                                              str msg = f"{hello()}"
                                              return 0
                                            }
                                            """)
                    });
            throw new AssertionError("Expected fatal error for function call in f-string");
        } catch (CancelCompilationException e) {
            Assertions.assertTrue(
                    e.getMessage().contains("f-string") || e.getMessage().contains("invalid"),
                    "Expected f-string error but got: " + e.getMessage());
        }
    }
}
