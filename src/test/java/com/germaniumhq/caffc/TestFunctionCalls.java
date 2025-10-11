package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.germaniumhq.caffc.CodeAsserts.assertCodeContains;
import static com.germaniumhq.caffc.CodeAsserts.compileCaffcCode;

public class TestFunctionCalls {
    @Test
    public void testCall() throws IOException {
        String code = compileCaffcCode(
            "caffc/template/c/compilation_unit_c.peb", /* template         */
            "test.caffc",                              /* compilation unit */
            """
            module main
            
            hello_world() {
            }
            
            main() {
              hello_world()
            }
            """);

        assertCodeContains(code, "void main_hello_world()",
                "the function hello_world(i16 x) should have been generated as main_hello_world");
        assertCodeContains(code, "main_hello_world();",
                "the call to hello_world(3) should have been generated");
    }

    @Test
    public void testCallParameters1() throws IOException {
        String code = compileCaffcCode(
            "caffc/template/c/compilation_unit_c.peb", /* template         */
            "test.caffc",                              /* compilation unit */
            """
            module main
            
            hello_world(i32 x) {
            }
            
            main() {
              hello_world(3)
            }
            """);

        assertCodeContains(code, "void main_hello_world(caffc_i32 x)",
                "the function hello_world(i32 x) should have been generated as main_hello_world");
        assertCodeContains(code, "main_hello_world(3);",
                "the call to hello_world(3) should have been generated");
    }

    @Test
    public void testCallParameters2() throws IOException {
        String code = compileCaffcCode(
            "caffc/template/c/compilation_unit_c.peb", /* template         */
            "test.caffc",                              /* compilation unit */
            """
            module main
            
            hello_world(i16 x, i32 y) {
            }
            
            main() {
              hello_world(3, 4)
            }
            """);

        assertCodeContains(code, "void main_hello_world(caffc_i16 x, caffc_i32 y)",
                "the function hello_world(i16 x, i32 y) should have been generated as main_hello_world");
        assertCodeContains(code, "main_hello_world(3, 4);",
                "the call to hello_world(3, 4) should have been generated");
    }
}
