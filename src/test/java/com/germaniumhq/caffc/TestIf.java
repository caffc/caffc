package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAssertsStr.assertCodeContains;
import static com.germaniumhq.caffc.CodeAssertsStr.compileCaffcCode;

public class TestIf {
    @Test
    public void testIfStatement() {
        String code = compileCaffcCode(
                "caffc/template/c/compilation_unit_c.peb", /* template         */
                "test.caffc",                              /* compilation unit */
                """
                module main
                
                clip(i32 y) -> i32 {
                    if y > 0 {
                      return y
                    }

                    return 0
                }
                """);

        assertCodeContains(code, "if (y > 0) ",
                "if statements should be generated as such.");
    }
}
