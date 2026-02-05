package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestS007InstantiatedGenericsClasses {
    @Test
    public void testDeclaredGenericsField() {
        CompilationUnit ast = CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
    
                                interface obj {}
                                class str {}
    
                                class A<T> {
                                  get() -> T {
                                    return 0 // this should be null
                                  }
                                }
                                
                                instantiate() {
                                  A<obj> objA = new A<obj>()
                                  A<str> strA = new A<str>()
                                }
                                """)
                }
        );

        FunctionDefinition objAGet = AstUtil.findAstItem(ast, "instantiate.objA.get");
        Assertions.assertNotNull(objAGet);
        Assertions.assertEquals(TypeName.OBJ, objAGet.returnType.typeName());

        FunctionDefinition strAGet = AstUtil.findAstItem(ast, "instantiate.strA.get");
        Assertions.assertNotNull(strAGet);
        Assertions.assertEquals(TypeName.STR, strAGet.returnType.typeName());
    }
}
