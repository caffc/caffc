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
        String code = CodeAssertsStr.compileCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
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

        CodeAssertsStr.assertCodeContains(code, """
                return _this->x;
                """,
            "dot access should translate into field access");
    }

    @Test
    public void testFunctionMethodAccess() {
        String code = CodeAssertsStr.compileCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module main
                    
                    class A {
                      getX() -> i32 {
                        return 0
                      }
                    
                      getA() -> A {
                        return _this
                      }
                    }
                    
                    main() {
                      A a = new A()
                      a.getA().getX()
                    }
                    """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_main_A_2 = main_A_getA(a);
                _caffc_temp_caffc_i32_1 = main_A_getX(_caffc_temp_main_A_2);
                """,
            "dot access should translate into function access");
    }
}
