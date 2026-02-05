package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Field;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestS008GenericsClassUsingArray {
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
    public void testDeclaredGenericsFieldArray() {
        CompilationUnit ast = CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
                                
                                interface obj {}
                                class obj_arr {}
                                
                                class A<T> {
                                  T[] item
                                }
                                """)
                }
        );

        Field itemDefinition = AstUtil.findAstItem(ast, "A.item");
        Assertions.assertNotNull(itemDefinition);
        Assertions.assertEquals(
                TypeName.of("caffc", "obj[]", null, DataType.ARRAY),
                itemDefinition.typeName());
    }

    @Test
    public void testDeclaredRestrictedGenericsFieldArray() {
        CompilationUnit ast = CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
                                
                                class str {}
                                interface obj {}
                                class obj_arr {}
                                
                                class A<T is str> {
                                  T[] item
                                }
                                """)
                }
        );

        Field itemDefinition = AstUtil.findAstItem(ast, "A.item");
        Assertions.assertNotNull(itemDefinition);
        Assertions.assertEquals(TypeName.of("caffc", "str[]", null, DataType.ARRAY),
                itemDefinition.typeName());
    }
}
