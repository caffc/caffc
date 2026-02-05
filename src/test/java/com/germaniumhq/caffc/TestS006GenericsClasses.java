package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Field;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.Parameter;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclaration;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The purpose of this test is to see that classes that have generics have
 * correct types. This would guarantee that their C code should get generated
 * correctly.
 */
public class TestS006GenericsClasses {
    @Test
    public void testDeclaredGenericsField() {
        CompilationUnit ast = CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
                                
                                interface obj {}
    
                                class A<T> {
                                  T item
                                }
                                """)
                }
        );

        Field itemDefinition = AstUtil.findAstItem(ast, "A.item");
        Assertions.assertNotNull(itemDefinition);
        Assertions.assertEquals(TypeName.OBJ, itemDefinition.typeName());
    }

    @Test
    public void testDeclaredGenericsRestrictedField() {
        CompilationUnit ast = CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc

                                interface obj {}
                                class str {}
    
                                class A<T is str> {
                                  T item
                                }
                                """)
                }
        );

        Field itemDefinition = AstUtil.findAstItem(ast, "A.item");
        Assertions.assertNotNull(itemDefinition);
        Assertions.assertEquals(TypeName.STR, itemDefinition.typeName());
    }

    @Test
    public void testDeclaredGenericsClassFunction() {
        CompilationUnit ast = CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc

                                interface obj {}

                                class A<T> {
                                  classFunction() -> T {
                                    return 0 // FIXME: this should be null
                                  }
                                }
                                """)
                }
        );

        Function function = AstUtil.findAstItem(ast, "A.classFunction");
        Assertions.assertNotNull(function);
        Assertions.assertEquals(TypeName.OBJ, function.definition.returnType.typeName());
    }

    @Test
    public void testDeclaredGenericsRestrictedClassFunction() {
        CompilationUnit ast = CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
                                
                                interface obj {}
                                class str {}
                                
                                class A<T is str> {
                                  classFunction() -> T {
                                    return 0 // FIXME: this should be null
                                  }
                                }
                                """)
                }
        );

        Function function = AstUtil.findAstItem(ast, "A.classFunction");
        Assertions.assertNotNull(function);
        Assertions.assertEquals(TypeName.STR, function.definition.returnType.typeName());
    }

    @Test
    public void testDeclaredGenericsClassFunctionParameter() {
        CompilationUnit ast = CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
                                
                                interface obj {}
                                
                                class A<T> {
                                  classFunction(T t) -> T {
                                    return t // FIXME: this should be null
                                  }
                                }
                                """)
                }
        );

        Parameter parameter = AstUtil.findAstItem(ast, "A.classFunction.t");
        Assertions.assertNotNull(parameter);
        Assertions.assertEquals(TypeName.OBJ, parameter.typeName());
    }

    @Test
    public void testDeclaredGenericsRestrictedClassFunctionParameterRestricted() {
        CompilationUnit ast = CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
                                
                                interface obj {}
                                class str {}
                                
                                class A<T is str> {
                                  classFunction(T t) -> T {
                                    return t // FIXME: this should be null
                                  }
                                }
                                """)
                }
        );

        Parameter parameter = AstUtil.findAstItem(ast, "A.classFunction.t");
        Assertions.assertNotNull(parameter);
        Assertions.assertEquals(TypeName.STR, parameter.typeName());
    }

    @Test
    public void testDeclaredGenericsClassFunctionVariable() {
        CompilationUnit ast = CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
                                
                                interface obj {}
                                
                                class A<T> {
                                  classFunction() -> T {
                                    T v = 0  // this should be null
                                    return v
                                  }
                                }
                                """)
                }
        );

        VariableDeclaration parameter = AstUtil.findAstItem(ast, "A.classFunction.v");
        Assertions.assertNotNull(parameter);
        Assertions.assertEquals(TypeName.OBJ, parameter.typeName());
    }

    @Test
    public void testDeclaredGenericsRestrictedClassFunctionVariableRestricted() {
        CompilationUnit ast = CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
                                
                                interface obj {}
                                class str {}
                                
                                class A<T is str> {
                                  classFunction() -> T {
                                    T v = 0  // FIXME: this should be null
                                    return v
                                  }
                                }
                                """)
                }
        );

        VariableDeclaration parameter = AstUtil.findAstItem(ast, "A.classFunction.v");
        Assertions.assertNotNull(parameter);
        Assertions.assertEquals(TypeName.STR, parameter.typeName());
    }
}
