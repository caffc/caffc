package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.germaniumhq.caffc.CodeAsserts.assertCodeContains;
import static com.germaniumhq.caffc.CodeAsserts.compileCaffcCode;

public class TestNativeBlocks {
    @Test
    public void testCompileUnit() throws IOException {
        String code = compileCaffcCode(
            "caffc/template/c/compilation_unit_c.peb", /* template         */
            "test.caffc",                              /* compilation unit */
            """
            module main
            
            native {
              #include <stdio.h>
            }
            
            main() -> i32 {
              native {
                printf("hello world!\\n");
              }
            
              return 0
            }
            """);
        assertCodeContains(code, "#include <stdio.h>",
            "native code outside functions should work.");
        assertCodeContains(code, "printf(\"hello world!\\n\");",
                "native code inside functions should work.");
        System.out.println(code);
    }

    @Test
    public void testCompileModule() throws IOException {
        String code = compileCaffcCode(
            "caffc/template/c/module_h.peb", /* template         */
            "test.caffc",                              /* compilation unit */
            """
            module main
            
            native {
              #include <stdio.h>
            }
            
            hello_planet(i32 x) {
            }
            
            main() -> i32 {
              native {
                printf("hello world!\\n");
              }
            
              return 0
            }
            """);
        assertCodeContains(code, "void main_hello_planet(caffc_i32 x);",
                "functions declarations with parameters should work");
        assertCodeContains(code, "caffc_i32 main_main();",
                "function declarations without parameters should work");
    }
}
