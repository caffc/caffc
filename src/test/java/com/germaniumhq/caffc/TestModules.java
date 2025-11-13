package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestModules {
    @Test
    public void testModuleUse() {
        String code = CodeAssertsStr.compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb", /* template         */
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
                            """
                            module a

                            use b as c

                            main() {
                              c.x(3)
                            }
                            """),
                        new TestUnit("b/b.caffc",
                            """
                            module b
                            
                            x(i32 a) {
                            }
                            """),
                }
        );

        CodeAssertsStr.assertCodeContains(code, "b_x(3);");
    }

    @Test
    public void testModuleUseModuleHeaderGen() {
        String code = CodeAssertsStr.compileCaffcProgram(
                "caffc/template/c/module_h.peb", /* template         */
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module a
                                
                                use b as c
                                
                                main() {
                                  c.x(3)
                                }
                                """),
                        new TestUnit("b/b.caffc",
                                """
                                module b
                                
                                x() {
                                }
                                """),
                }
        );

        CodeAssertsStr.assertCodeContains(code, "#include \"module_b.h\"",
                "the b module should be imported, since we have a module a -> module b dependency");
    }

    @Test
    public void testModuleCaffcIsAvailableByDefault() {
        String code = CodeAssertsStr.compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb", /* template         */
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("caffc/o.caffc",
                                """
                                module caffc

                                class CaffcObj {
                                  i32 x
                                }
                                """),
                        new TestUnit("a/a.caffc",
                                """
                                module a
                                
                                main() {
                                  new CaffcObj().x = 4
                                }
                                """),
                }
        );

        CodeAssertsStr.assertCodeContains(code, " caffc_CaffcObj_new()->x = 4;",
                "the caffc.CaffcObj should be instantiated, since caffc is used by default");
    }
}
