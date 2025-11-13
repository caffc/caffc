package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAssertsStr.assertCodeContains;
import static com.germaniumhq.caffc.CodeAssertsStr.compileCaffcCode;

public class TestExpressionMathMulDiv {
    @Test
    public void testSubStatement() {
        String code = compileCaffcCode(
                "caffc/template/c/compilation_unit_c.peb", /* template         */
                "test.caffc",                              /* compilation unit */
                """
                module main

                compute(i32 y) -> i32 {
                  return y - 3
                }
                """);

        assertCodeContains(code, "return y - 3",
                "operations should be working correctly");
    }
}
