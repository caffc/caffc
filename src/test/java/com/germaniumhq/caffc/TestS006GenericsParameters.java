package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.model.ClassDefinition;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Field;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.Parameter;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionAssign;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclaration;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The purpose of this test is to see that classes that have generics have
 * correct types. This would guarantee that their C code should get generated
 * correctly.
 */
public class TestS006GenericsParameters {
    @Test
    public void testDeclaredGenericsField() {
        CompilationUnit ast = CodeAsserts.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
                        """
                        module caffc

                        class obj {
                          add(obj o1, obj o2) -> obj {
                            return o1
                          }
                        }
                        
                        class str {}

                        sum<T>(T p1, T p2) -> T {
                            return p1.add(p2)
                        }
                        
                        calls() {
                            str s1, s2, s3

                            s3 = sum<str>(s1, s2)
                        }
                        """)
                }
        );

        ExpressionAssign expressionAssign = AstUtil.findAstItem(ast, "calls.[1]");
        ClassDefinition returnCallClassDefinition = (ClassDefinition) expressionAssign.right.getSymbol();

        TypeName returnType = returnCallClassDefinition.typeName();

        Assertions.assertEquals(TypeName.STR, returnType);
    }

    @Test
    public void testDeclaredGenericsInstantiedParameter() {
        CompilationUnit ast = CodeAsserts.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc

                                class obj {}
                                class str {}
                                class ArrayList<T> {}

                                calls(ArrayList<str> a) {
                                }
                                """)
                }
        );

        Parameter parameter = AstUtil.findAstItem(ast, "calls.a");
        Assertions.assertEquals(
                TypeName.of("caffc", "ArrayList", null, DataType.OBJECT),
                parameter.typeName());
    }

}
