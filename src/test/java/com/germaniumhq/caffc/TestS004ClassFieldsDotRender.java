package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Field;
import com.germaniumhq.caffc.compiler.model.Parameter;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestS004ClassFieldsDotRender {
    @Test
    public void testDeclaredFieldDotAccess() {
        String code = CodeAssertsStr.compileCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
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

        CodeAssertsStr.assertCodeContains(code, """
                a->x = 3;
                """,
            "dot access should translate into field access");
    }

    @Test
    public void testThisFieldDotAccess() {
        CompilationUnit ast = CodeAssertsAst.compileCaffcUnitsAst(
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
