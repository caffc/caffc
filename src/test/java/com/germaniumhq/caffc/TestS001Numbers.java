package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAsserts.assertCodeContains;
import static com.germaniumhq.caffc.CodeAsserts.compileCaffcProgram;

public class TestS001Numbers {
    @Test
    public void testNumberConstants() {
        String code = compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb", /* template         */
                "test.caffc",                              /* compilation unit */
                new TestUnit[] {
                        new TestUnit("test.caffc",
                                """
                module main
        
                main(i32 i) {
                  i16 x = -33
                  u16 y = 33
                  i64 z = 33_i64
                }
                """)}
        );

        assertCodeContains(code, "x = -33;",
                "i32 variable should be resolved correctly");
        assertCodeContains(code, "y = 33;",
                "i32 variable should be resolved correctly");
        assertCodeContains(code, "z = 33;",
                "i32 variable should be resolved correctly");
    }
}
