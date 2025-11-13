package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAssertsStr.assertCodeContains;
import static com.germaniumhq.caffc.CodeAssertsStr.compileCaffcProgram;

/**
 * Checks if functions, including across different modules can be
 * resolved.
 */
public class TestS002ResolveFunctionNamesNoGenerics {
    @Test
    public void testResolveFunctionNamesSameCompilationUnit() {
        String code = compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb", /* template         */
                "a.caffc",                              /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a.caffc",
                                """
                module a
                
                main(i32 i) {
                  pain()
                }

                pain() {
                }
                """)}
        );

        assertCodeContains(code, "a_pain();",
                "the pain() call should be resolved in the same unit");
    }

    @Test
    public void testResolveFunctionNamesSameModule() {
        String code = compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb", /* template         */
                "a1.caffc",                              /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a1.caffc",
                """
                module a
                
                main(i32 i) {
                  pain()
                }
                """),
                        new TestUnit("a2.caffc",
                """
                module a
                
                pain() {
                }
                """),
                }
        );

        assertCodeContains(code, "a_pain();",
                "the pain() call should be resolved in the same module");
    }

    @Test
    public void testResolveFunctionNamesDifferentModule() {
        String code = compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb", /* template         */
                "a.caffc",                              /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a.caffc",
                                """
                                module a
                                
                                use b
                                
                                main(i32 i) {
                                  b.pain()
                                }
                                """),
                        new TestUnit("b.caffc",
                                """
                                module b
                                
                                pain() {
                                }
                                """),
                }
        );

        assertCodeContains(code, "b_pain();",
                "the pain() call should be resolved in the other module");
    }
}
