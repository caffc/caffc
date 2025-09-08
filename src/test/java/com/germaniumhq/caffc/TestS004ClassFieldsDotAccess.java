package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Field;
import com.germaniumhq.caffc.compiler.model.Parameter;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestS004ClassFieldsDotAccess {
    @Test
    public void testDeclaredFieldDotAccess() {
        CompilationUnit ast = CodeAsserts.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
    
                                class A {
                                  i32 x
                                }

                                x() {
                                  A a = new A()
                                  a.x = 3
                                }
                                """)
                }
        );

        Field itemDefinition = AstUtil.findAstItem(ast, "x.a.x");
        Assertions.assertNotNull(itemDefinition);
        Assertions.assertEquals(
                TypeName.I32,
                itemDefinition.typeSymbol.typeName());
    }

    @Test
    public void testThisFieldDotAccess() {
        CompilationUnit ast = CodeAsserts.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc

                                class A {
                                  i32 x

                                  getX() -> i32 {
                                    return _this.x
                                  }
                                }
                                """)
                }
        );

        Parameter parameterDefinition = AstUtil.findAstItem(ast, "A.getX._this");
        Assertions.assertNotNull(parameterDefinition);
        Assertions.assertEquals(
                TypeName.of("caffc", "A", null, DataType.OBJECT),
                parameterDefinition.typeSymbol.typeName());
    }
}
