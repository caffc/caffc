package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionMath;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionNumber;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestS310LinearFormBlockCollapse {
    @Test
    public void testBlockCollapse() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                            module main
        
                            test() {
                              u32 x, y

                              x = y + 3
                              if (x) {
                                y = x + 3
                              }
                            }
                            """)
            }
        );

        CodeAssertsStr.assertCodeContains(code,
            """
i = 0;
while (i < 3) {
if (i == 1) {
continue;
}

if (i == 2) {
continue;
}

i += 1;
}
            """,
            "blocks should be removed after optimizations");

    }
}
