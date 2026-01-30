package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS010Array {
    @Test
    public void testArrayImplementation() {
        String code = CodeAssertsStr.compileCaffcProgram(
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

        CodeAssertsStr.assertCodeContains(code, """
                caffc__obj_a* caffc__obj_a_new(caffc_i32 size) {
                  caffc__obj_a* _this = (caffc__obj_a*) caffc_new(
                      &caffc__obj_a_type,
                      (caffc_i32) caffc__caffc__obj_a_size(size));
                  caffc__obj_a_constructor(_this, size);
                """,
                "the new call should go via the dynamic size function defined for _obj_a");

        System.out.println(code);
    }

    @Test
    public void testArrayInstantiation() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
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

        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_caffc_u8_arr_1 = caffc_u8_arr_new_array(2);
                primitive_unidimensional = _caffc_temp_caffc_u8_arr_1;
                """,
                "unidimensional primitive array new generation isn't working");
        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_caffc_u8_arr_arr_arr_1 = caffc_u8_arr_arr_arr_new_array(1, 2, 3);
                primitive_multidimensional = _caffc_temp_caffc_u8_arr_arr_arr_1;
                """,
                "multidimensional primitive array new generation isn't working");
        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_yolo_Swag_arr_1 = yolo_Swag_arr_new_array(2);
                class_unidimensional = _caffc_temp_yolo_Swag_arr_1;
                """,
                "unidimensional object array new generation isn't working");
        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_yolo_Swag_arr_arr_arr_1 = yolo_Swag_arr_arr_arr_new_array(1, 2, 3);
                class_multidimensional = _caffc_temp_yolo_Swag_arr_arr_arr_1;
                """,
                "multidimensional primitive array new generation isn't working");
    }

    @Test
    public void testArrayTypeCreationNonPrimitive() {
        String code = CodeAssertsStr.compileCaffcProgram(
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
        String code = CodeAssertsStr.compileCaffcProgram(
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
        String code = CodeAssertsStr.compileCaffcProgram(
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

    @Test
    public void testMultiArrayPrimitiveIndexAccess() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[]{
                new TestUnit("a/a.caffc", """
                    module yolo
                    
                    class Swag {}
                    
                    test() {
                        u8[][][] primitive_multidimensional = new u8[1][2][3]
                        primitive_multidimensional[0][1][2] = 3
                    }
                    """
                )
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_caffc_u8_arr_arr_arr_1 = caffc_u8_arr_arr_arr_new_array(1, 2, 3);
                primitive_multidimensional = _caffc_temp_caffc_u8_arr_arr_arr_1;
                _caffc_temp_caffc_u8_arr_arr_1 = caffc_obj_arr_get(primitive_multidimensional, 0);
                _caffc_temp_caffc_u8_arr_1 = caffc_obj_arr_get(_caffc_temp_caffc_u8_arr_arr_1, 1);
                caffc_obj_arr_set(_caffc_temp_caffc_u8_arr_1, 2, 3);
                """,
            "index assignment isn't working");

    }

    @Test
    public void testMultiArrayObjectsIndexAccess() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[]{
                new TestUnit("a/a.caffc", """
                    module yolo

                    class Swag {}
                    
                    test() {
                        yolo.Swag[][][] class_multidimensional = new yolo.Swag[1][2][3]
                        class_multidimensional[0][1][2] = new yolo.Swag()
                    }
                    """
                )
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
            _caffc_temp_yolo_Swag_arr_arr_arr_1 = yolo_Swag_arr_arr_arr_new_array(1, 2, 3);
            class_multidimensional = _caffc_temp_yolo_Swag_arr_arr_arr_1;
            _caffc_temp_yolo_Swag_1 = yolo_Swag_new();
            _caffc_temp_yolo_Swag_arr_arr_1 = caffc_obj_arr_get(class_multidimensional, 0);
            _caffc_temp_yolo_Swag_arr_1 = caffc_obj_arr_get(_caffc_temp_yolo_Swag_arr_arr_1, 1);
            caffc_obj_arr_set(_caffc_temp_yolo_Swag_arr_1, 2, _caffc_temp_yolo_Swag_1);
            """,
            "index assignment isn't working");

    }
}
