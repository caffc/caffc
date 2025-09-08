package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS010Array {
    @Test
    public void testArrayImplementation() {
        String code = CodeAsserts.compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
                        """
                        module caffc
                        
                        /**
                         * Compute the size of the array.
                         */
                        _caffc__obj_a_size(i32 size) -> i32 {
                          i32 result
                        
                          native {
                            result = sizeof(ptr) * size + sizeof(caffc_array_header);
                          }
                        
                          return result
                        }
                        
                        /**
                         * An array of objects. Pointers are managed by the GC
                         */
                        class _obj_a {
                          constructor(i32 size) {
                            native {
                              /* gc info */
                              _this._caffc_header.field_count = size;
                            }
                          }
                        
                          set(i32 index, ptr value) {
                            native {
                              _this._caffc_header.data[index] = value;
                            }
                          }
                        
                          get(i32 index) -> ptr {
                            ptr result
                        
                            native {
                              result = _this._caffc_header.data[index];
                            }
                        
                            return result
                          }
                        }
                        """)
                }
        );

        CodeAsserts.assertCodeContains(code, """
                caffc__obj_a* caffc__obj_a_new(i32 size) {
                  caffc__obj_a* _this = (caffc__obj_a*) caffc_new(
                      &caffc__obj_a_type,
                      (u32) caffc__caffc__obj_a_size(size));
                  caffc__obj_a_constructor(_this, size);
                """,
                "the new call should go via the dynamic size function defined for _obj_a");

        System.out.println(code);
    }

    @Test
    public void testArrayInstantiation() {
        String code = CodeAsserts.compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[]{
                        new TestUnit("caffc/arr.caffc", """
                        module caffc
                        
                        class obj_arr {}
                        """),
                        new TestUnit("a/a.caffc", """
                        module yolo

                        class Swag {
                        }

                        test() {
                          u8[] primitive_unidimensional = new u8[2]
                          u8[][][] primitive_multidimensional = new u8[1][2][3]

                          yolo.Swag[] class_unidimensional = new yolo.Swag[2]
                          yolo.Swag[][][] class_multidimensional = new yolo.Swag[1][2][3]

                          class_multidimensional[0][1][2] = new yolo.Swag()
                          primitive_multidimensional[0][1][2] = 3
                        }

                        """)
                }
        );

        CodeAsserts.assertCodeContains(code, "primitive_unidimensional = caffc_u8_arr_new(2);",
                "unidimensional primitive array new generation isn't working");
        CodeAsserts.assertCodeContains(code, "primitive_multidimensional = caffc_u8_arr_arr_arr_multi_new(1, 2, 3);",
                "multidimensional primitive array new generation isn't working");
        CodeAsserts.assertCodeContains(code, "class_unidimensional = yolo_Swag_arr_new(2);",
                "unidimensional object array new generation isn't working");
        CodeAsserts.assertCodeContains(code, "class_multidimensional = yolo_Swag_arr_arr_arr_multi_new(1, 2, 3);",
                "multidimensional primitive array new generation isn't working");
        CodeAsserts.assertCodeContains(code, "caffc_obj_arr_set((caffc_obj_arr*) ((yolo_Swag_arr*) caffc_obj_arr_get((yolo_Swag_arr_arr*) ((yolo_Swag_arr_arr*) caffc_obj_arr_get((yolo_Swag_arr_arr_arr*) class_multidimensional, 0)), 1)), 2, (ptr) yolo_Swag_new());",
                "index assignment isn't working");
    }

    @Test
    public void testArrayTypeCreationNonPrimitive() {
        String code = CodeAsserts.compileCaffcProgram(
                "caffc/template/c/module_c.peb",
                "a/a.caffc",
                new TestUnit[]{
                        new TestUnit("caffc/arr.caffc", """
                        module caffc

                        class obj_arr {}
                        """),
                        new TestUnit("a/a.caffc", """
                        module yolo

                        class Swag {
                        }

                        test() {
                          u8[] primitive_unidimensional = new u8[2]
                          u8[][][] primitive_multidimensional = new u8[1][2][3]

                          yolo.Swag[] class_unidimensional = new yolo.Swag[2]
                          yolo.Swag[][][] class_multidimensional = new yolo.Swag[1][2][3]

                          primitive_multidimensional[0][0][0]  = 3
                        }
                        """)
                });

        System.out.println(code);
    }

    @Test
    public void testArrayTypesModuleC() {
        String code = CodeAsserts.compileCaffcProgram(
                "caffc/template/c/module_c.peb",
                "a/a.caffc",
                new TestUnit[]{
                        new TestUnit("caffc/arr.caffc", """
                        module caffc

                        class obj_arr {}
                        """),
                        new TestUnit("a/a.caffc", """
                        module yolo

                        class Swag {
                        }

                        test() {
                          u8[] primitive_unidimensional = new u8[2]
                          u8[][][] primitive_multidimensional = new u8[1][2][3]

                          yolo.Swag[] class_unidimensional = new yolo.Swag[2]
                          yolo.Swag[][][] class_multidimensional = new yolo.Swag[1][2][3]

                          class_multidimensional[0][0][0] = new yolo.Swag[3]
                        }

                        """)
                });

        System.out.println(code);
    }

    @Test
    public void testArrayAccessInFunctionCall() {
        String code = CodeAsserts.compileCaffcProgram(
                "caffc/template/c/module_c.peb",
                "a/a.caffc",
                new TestUnit[]{
                        new TestUnit("caffc/arr.caffc", """
                        module caffc

                        class obj_arr {}
                        class str {}
                        """),
                        new TestUnit("a/a.caffc", """
                        module yolo

                        class Swag {
                          str data
                        }
                        
                        print(str what) {}

                        test() {
                          yolo.Swag[][][] class_multidimensional = new yolo.Swag[1][2][3]

                          print(class_multidimensional[0][0][0].data)
                        }

                        """)
                });

        System.out.println(code);
    }
}
