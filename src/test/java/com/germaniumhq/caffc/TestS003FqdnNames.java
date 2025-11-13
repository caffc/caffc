package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Field;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestS003FqdnNames {
    @Test
    public void testDeclaredFieldWithFqdnType() {
        CompilationUnit ast = CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
    
                                class B {
                                  i32 x
                                }
    
                                class A {
                                  caffc.B item
                                }
                                """)
                }
        );

        Field itemDefinition = AstUtil.findAstItem(ast, "A.item");
        Assertions.assertNotNull(itemDefinition);
        Assertions.assertEquals(
                TypeName.of("caffc", "B", null, DataType.OBJECT),
                itemDefinition.typeName());
    }
}
