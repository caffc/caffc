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

        assertCodeContains(code, "errors |= caffc_check((caffc_str*)&caffc_cstr_4c1dc1f491eb4e4e3e5e34a36df4fd84932bb09a01b248edbcb1581d16ce4dba, 1, i8size);",
                "calling functions with multiple parameters should work fine");
        assertCodeContains(code, "caffc_u32 caffc_check(caffc_str* typeName, caffc_u32 expectedSize, caffc_u32 actualSize) {",
                "declaring functions with multiple parameters should work fine");
    }
}
