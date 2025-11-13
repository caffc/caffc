package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.germaniumhq.caffc.CodeAssertsStr.assertCodeContains;
import static com.germaniumhq.caffc.CodeAssertsStr.compileCaffcCode;

public class TestClasses {
    @Test
    public void testModuleClassGeneration() throws IOException {
        String code = compileCaffcCode(
            "caffc/template/c/module_h.peb", /* template         */
            "test.caffc",                              /* compilation unit */
            """
            module main
            
            class A {
              i32 some_field
              B b_ref

              some_function() {
              }
            }
            
            class B {
              A a_ref

              b_function() {
              }
            }
            """);

        CodeAssertsStr.assertCodeContains(code, "main_B* b_ref;");
        CodeAssertsStr.assertCodeContains(code, "main_A* a_ref;");
        CodeAssertsStr.assertCodeContains(code, "void main_A_some_function(main_A* _this);");
        CodeAssertsStr.assertCodeContains(code, "void main_B_b_function(main_B* _this);");
    }

    @Test
    public void testClassInstantiation() throws IOException {
        String code = compileCaffcCode(
            "caffc/template/c/compilation_unit_c.peb", /* template         */
            "test.caffc",                              /* compilation unit */
            """
            module main
            
            class A {
              i32 some_field

              constructor(i32 x) {
                _this.some_field = x
              }

              print_field() {
                native {
                  printf("some field is %d\\n", _this->some_field);
                }
              }
            }
            
            class B {
            }

            main() {
              new A(3).print_field()
            }
            """);

        CodeAssertsStr.assertCodeContains(code, " _this->some_field = x;",
                "local function resolving, or local property resolving, doesn't seem to be working");

        CodeAssertsStr.assertCodeContains(code, "void main_A_constructor(main_A* _this, caffc_i32 x) {",
                "new operator generation doesn't seem to be working for constructors with parameters");
        CodeAssertsStr.assertCodeContains(code, " main_A* _this = (main_A*) caffc_new(&main_A_type, sizeof(main_A));",
                "new operator generation doesn't seem to be working for constructors with parameters");
        CodeAssertsStr.assertCodeContains(code, "main_A_constructor(_this, x);",
                "new operator generation doesn't seem to be working for constructors with parameters");

        CodeAssertsStr.assertCodeContains(code, "main_B* main_B_new() {",
                "new operator generation doesn't seem to be working for default constructors");
        CodeAssertsStr.assertCodeContains(code, " main_B* _this = (main_B*) caffc_new(&main_B_type, sizeof(main_B));",
                "new operator generation doesn't seem to be working for default constructors");
        CodeAssertsStr.assertCodeNotContains(code, "main_B_constructor(_this)",
                "new operator generation doesn't seem to be working for default constructors");

        CodeAssertsStr.assertCodeContains(code, "main_A_print_field(main_A_new(3));",
                "calling functions isn't working for class instances");
    }

    @Test
    public void testClassDynamicSizeInstantiation() {
        String code = compileCaffcCode(
                "caffc/template/c/compilation_unit_c.peb", /* template         */
                "test.caffc",                              /* compilation unit */
                """
                module caffc
                
                _caffc_ByteArray_size(i32 size) -> i32 {
                  return size
                }
                
                class ByteArray {
                  u32 size
                  
                  constructor(u32 size) {
                    _this.size = size
                  }
                }
                
                main() {
                  new ByteArray(3)
                }
                """);

        CodeAssertsStr.assertCodeContains(code, "  caffc_ByteArray* _this = (caffc_ByteArray*) caffc_new(\n" +
                        "      &caffc_ByteArray_type,\n" +
                        "      (caffc_i32) caffc__caffc_ByteArray_size(size));\n" +
                        "  caffc_ByteArray_constructor(_this, size);",
                "instantiation should work with dynamic sizes for the objects");

    }
}
