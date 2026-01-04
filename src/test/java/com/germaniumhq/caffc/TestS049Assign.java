
package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS049Assign {
    @Test
    public void testExpressionParens() {
        String code = CodeAssertsStr.compileCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                            module main
                            
                            multi() -> i32 x, i32 y {
                              return 0, 0
                            }

                            main() -> i32 {
                              i32 x, y

                              x, y = multi()

                              return 0
                            }
                            """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
                z = (x + y) * (x - y);
                """,
            "parens should translate into the generated code");
    }
}
