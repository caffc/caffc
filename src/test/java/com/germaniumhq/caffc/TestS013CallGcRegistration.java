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
            _caffc_stack_frame_register((caffc_str*)&caffc_cstr_b039205e869bac191d95d26e278e6b336b652c110dfaa2a044639f0c2759d14e, caffc_null, 0);
            """);

        CodeAssertsStr.assertCodeContains(code, """
            _caffc_stack_frame_register((caffc_str*)&caffc_cstr_13b99dc167c251ccb9b8537249cbdaeb5796d6a1390a589f5c95d355a9c5eb69, _caffc_locals, 1);
            """);
    }
}
