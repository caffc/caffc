package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Field;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestS009InstantiatedGenericsClassUsingArray {
    @Test
    public void testDeclaredGenericsField() {
        CompilationUnit ast = CodeAsserts.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
                                
                                class obj {}
    
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
        CompilationUnit ast = CodeAsserts.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
                                
                                class obj {}
                                class str {}
                                class obj_arr {}
                                
                                class A<T> {
                                  T[] item
                                }
                                
                                x() {
                                  A<obj> a_obj_item = new A<obj>()
                                  A<str> a_str_item = new A<str>()
                                }
                                """)
                }
        );

        Field itemDefinition = AstUtil.findAstItem(ast, "A.item");
        Assertions.assertNotNull(itemDefinition);
        Assertions.assertEquals(TypeName.of("caffc", "obj[]", null, DataType.ARRAY),
                itemDefinition.typeSymbol.typeName());

        Symbol varDefinition = AstUtil.findAstItem(ast, "x.a_str_item.item");
        Assertions.assertNotNull(varDefinition);
    }

    @Test
    public void testDeclaredRestrictedGenericsFieldArray() {
        CompilationUnit ast = CodeAsserts.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
                                
                                class str {}
                                class obj {}
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
