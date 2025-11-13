package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.GenericDefinition;
import com.germaniumhq.caffc.compiler.model.Parameter;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclaration;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestS005GenericsDataFunctions {
    @Test
    public void testDeclaredGenericsFunctionParameter() {
        CompilationUnit ast = CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
    
                                class obj {}
    
                                get<T>(T t) -> T {
                                  T x = t
                                  return t
                                }
                                """)
                }
        );

        GenericDefinition genericDefinition = AstUtil.findAstItem(ast, "get.T");
        Assertions.assertNotNull(genericDefinition);
        Assertions.assertEquals(TypeName.OBJ, genericDefinition.typeName());

        Parameter parameter = AstUtil.findAstItem(ast, "get.t");
        Assertions.assertNotNull(parameter);
        Assertions.assertEquals(TypeName.OBJ, parameter.typeName());

        VariableDeclaration variable = AstUtil.findAstItem(ast, "get.x");
        Assertions.assertNotNull(variable);
        Assertions.assertEquals(TypeName.OBJ, variable.typeName());

        Function function = AstUtil.findAstItem(ast, "get");
        Assertions.assertNotNull(function);
        Assertions.assertEquals(TypeName.OBJ, function.definition.returnType.typeName());
    }

    @Test
    public void testDeclaredGenericsRestrictedFunctionParameter() {
        CompilationUnit ast = CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc", /* compilation unit */
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                module caffc
                                
                                class str{}
    
                                get<T is str>(T t) -> T {
                                  T x = t
                                  return x
                                }
                                """)
                }
        );

        GenericDefinition genericDefinition = AstUtil.findAstItem(ast, "get.T");
        Assertions.assertNotNull(genericDefinition);
        Assertions.assertEquals(TypeName.STR, genericDefinition.typeName());

        Parameter parameter = AstUtil.findAstItem(ast, "get.t");
        Assertions.assertNotNull(parameter);
        Assertions.assertEquals(TypeName.STR, parameter.typeName());

        VariableDeclaration variable = AstUtil.findAstItem(ast, "get.x");
        Assertions.assertNotNull(variable);
        Assertions.assertEquals(TypeName.STR, variable.typeName());

        Function function = AstUtil.findAstItem(ast, "get");
        Assertions.assertNotNull(function);
        Assertions.assertEquals(TypeName.STR, function.definition.returnType.typeName());
    }
}
