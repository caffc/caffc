package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAssertsStr.assertCodeContains;
import static com.germaniumhq.caffc.CodeAssertsStr.compileCaffcProgram;

/**
 * This simply checks if primitives are correctly resolved.
 */
public class TestS001ResolvePrimitives {
    @Test
    public void testResolvePrimitivesInVariableAssignments() {
        String code = compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb", /* template         */
                "test.caffc",                              /* compilation unit */
                new TestUnit[] {
                        new TestUnit("test.caffc",
                                """
                module main
        
                main(i32 i) {
                  u8 v1 = 1, w1 = 2, x1 = 3, y1, z1 = 5
                  i8 v2
                  u16 v3
                  i16 v4
                  u32 v5
                  i32 v6
                  u64 v7
                  i64 v8
                  f32 v9
                  f64 v10
                  ptr v11
                }
                """)}
        );

        System.out.println(code);

        assertCodeContains(code, "u8 v1",
                "variable should be resolved correctly");
        assertCodeContains(code, "i8 v2",
                "variable should be resolved correctly");
        assertCodeContains(code, "u16 v3",
                "variable should be resolved correctly");
        assertCodeContains(code, "i16 v4",
                "variable should be resolved correctly");
        assertCodeContains(code, "u32 v5",
                "variable should be resolved correctly");
        assertCodeContains(code, "i32 v6",
                "variable should be resolved correctly");
        assertCodeContains(code, "u64 v7",
                "variable should be resolved correctly");
        assertCodeContains(code, "i64 v8",
                "variable should be resolved correctly");
        assertCodeContains(code, "f32 v9",
                "variable should be resolved correctly");
        assertCodeContains(code, "f64 v10",
                "variable should be resolved correctly");
    }
}
