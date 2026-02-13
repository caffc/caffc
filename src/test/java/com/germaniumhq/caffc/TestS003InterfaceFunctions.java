package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.model.Clazz;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Interface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAssertsStr.assertCodeContains;
import static com.germaniumhq.caffc.CodeAssertsStr.compileCaffcProgram;

public class TestS003InterfaceFunctions {
    @Test
    public void testInterfaceDeclaration() {
        CompilationUnit ast = CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
    
                                class str {}
    
                                interface obj {
                                  to_string() -> str
                                }
                                
                                class A implements obj {
                                }
                                """)
                }
        );

        Interface interfaceDefinition = AstUtil.findAstItem(ast, "obj");
        Assertions.assertNotNull(interfaceDefinition);

        Clazz clazzDefiniton = AstUtil.findAstItem(ast, "A");
        boolean implementing = clazzDefiniton.definition.isImplementing(interfaceDefinition.definition);
        Assertions.assertTrue(implementing, "class `A` doesn't implement interface `obj` as it should");
    }

    @Test
    public void testInterfaceMethodImplementation() {
        CompilationUnit ast = CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
    
                                class str {}
    
                                interface obj {
                                  to_string() -> str
                                }
                                
                                class A implements obj {
                                  to_string() -> str {
                                    return "abc"
                                  }
                                }
                                
                                main() {
                                  obj x = new A()
                                  str s = x.to_string()
                                }
                                """)
                }
        );

        Interface interfaceDefinition = AstUtil.findAstItem(ast, "obj");
        Assertions.assertNotNull(interfaceDefinition);

        Clazz clazzDefiniton = AstUtil.findAstItem(ast, "A");
        boolean implementing = clazzDefiniton.definition.isImplementing(interfaceDefinition.definition);
        Assertions.assertTrue(implementing, "class `A` doesn't implement interface `obj` as it should");
    }

    @org.junit.Test
    @Test
    public void testCompileInterfaceMetodImplementationGeneration() {
        String code = compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb", /* template         */
                "test.caffc",                              /* compilation unit */
                new TestUnit[] {
                        new TestUnit("test.caffc",
                                """
                module main

                interface obj {}
                class str{}

                interface I {
                  x() -> str
                }

                class A implements I {
                  x() -> str {
                    return 0  //should be null
                  }
                }
                
                class B implements I {
                  x() -> str {
                    return 0
                  }
                }

                main() {
                  I a = new A()
                  a.x()
                }
                """)}
        );

        assertCodeContains(code, """
            main_str* main_I_x(main_I* _this) {
              if (_this->_caffc_class_header == &main_A_type) {
                return main_A_x(_this);
              }
              if (_this->_caffc_class_header == &main_B_type) {
                return main_B_x(_this);
              }
              return 0; /* FIXME: throw an error, _this isn't a valid pointer */
            }
            """,
    "interfaces should be generated correctly");
    }
}
