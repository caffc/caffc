package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAssertsStr.assertCodeContains;
import static com.germaniumhq.caffc.CodeAssertsStr.compileCaffcProgram;

public class TestS001Instructions {
    @Test
    public void basicSanityCheckForInstructionRendering() {
        String code = compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb", /* template         */
                "test.caffc",                              /* compilation unit */
                new TestUnit[] {
                        new TestUnit("test.caffc",
                                """
                module caffc
                
                check() -> u32 {
                  native {
                    printf("disable optimizations");
                  }
                  u32 x = 5 + 7 * 3
                  return 0
                }
                """)}
        );

        assertCodeContains(code, """
                _caffc_temp_caffc_i32_1 = 7 * 3;
                _caffc_temp_caffc_i32_2 = 5 + _caffc_temp_caffc_i32_1;
                x = _caffc_temp_caffc_i32_2;
                """,
                "calling functions with multiple parameters should work fine");
    }
}
