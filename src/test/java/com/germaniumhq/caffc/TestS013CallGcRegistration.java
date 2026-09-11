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
            _caffc_stack_frame_register((caffc_str*)&caffc_cstr_b039205e869bac19,
              caffc_null, 0);
            """);

        CodeAssertsStr.assertCodeContains(code, """
            _caffc_stack_frame_register((caffc_str*)&caffc_cstr_13b99dc167c251cc,
              _caffc_locals, 1);
            """);
    }
}
