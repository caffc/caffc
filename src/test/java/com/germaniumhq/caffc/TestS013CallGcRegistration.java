package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS013CallGcRegistration {
    @Test
    public void testGenerationOfFunctionsWithSingleUnnamedReturn() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[]{
                new TestUnit("a/a.caffc",
                    """
                        module main
                        
                        noGcVarFunction() {
                          i32 x
                        }
                        
                        gcVarFunction() {
                          obj x
                        }
                        """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
            arstars
            """);
    }
}
