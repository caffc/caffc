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
                  i32 i32default = 33
                  u8 u8var = 33_u8
                  i8 i8var = 33_i8
                  i8 i8var_negative = -33_i8
                  u16 u16var = 33_u16
                  i16 i16var = 33_i16
                  i16 i16var_negative = -33_i16
                  u32 u32var = 33_u32
                  i32 i32var = 33_i32
                  i32 i32var_negative = -33_i32
                  u64 u64var = 33_u64
                  i64 i64var = 33_u64
                  i64 i64var_negative = -33_i64
                }
                """)}
        );

        assertCodeContains(code, "i32default = 33;",
                "variable should be generated correctly");
        assertCodeContains(code, "u8var = 33;",
                "variable should be generated correctly");
        assertCodeContains(code, "i8var = 33;",
                "variable should be generated correctly");
        assertCodeContains(code, "i8var_negative = -33;",
                "variable should be generated correctly");
        assertCodeContains(code, "u16var = 33;",
                "variable should be generated correctly");
        assertCodeContains(code, "i16var = 33;",
                "variable should be generated correctly");
        assertCodeContains(code, "i16var_negative = -33;",
                "variable should be generated correctly");
        assertCodeContains(code, "u32var = 33;",
                "variable should be generated correctly");
        assertCodeContains(code, "i32var = 33;",
                "variable should be generated correctly");
        assertCodeContains(code, "i32var_negative = -33;",
                "variable should be generated correctly");
        assertCodeContains(code, "u64var = 33;",
                "variable should be generated correctly");
        assertCodeContains(code, "i64var = 33;",
                "variable should be generated correctly");
        assertCodeContains(code, "i64var_negative = -33;",
                "variable should be generated correctly");
    }
}
