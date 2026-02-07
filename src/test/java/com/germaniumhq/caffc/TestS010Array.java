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
    public void testArrayTypeCreationNonPrimitive() {
        // FIXME: generation of the multi arrays has a potential ubg since the
        //        values are not managed by the GC:
        // yolo_Swag_arr_arr_arr* yolo_Swag_arr_arr_arr_multi_new(caffc_i32 s1, caffc_i32 s2, caffc_i32 s3) {
        //    caffc_i32 i;
        //    yolo_Swag_arr_arr_arr* result = yolo_Swag_arr_arr_arr_new(s1); <-- not managed by the GC
        //
        //    for (i = 0; i < s1; i++) {
        //        caffc_obj_arr_set(result, i, (caffc_ptr) yolo_Swag_arr_arr_multi(s2, s3));
        //    }
        //
        //    return result;
        //}
        String code = CodeAssertsStr.compileFullCaffcProgram(
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
        String code = CodeAssertsStr.compileFullCaffcProgram(
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
        String code = CodeAssertsStr.compileFullCaffcProgram(
                "caffc/template/c/module_c.peb",
                "a/a.caffc",
                new TestUnit[]{
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
            _caffc_temp_yolo_Swag_arr_arr_1 = caffc_obj_arr_get(class_multidimensional, 0);
            _caffc_temp_yolo_Swag_arr_1 = caffc_obj_arr_get(_caffc_temp_yolo_Swag_arr_arr_1, 1);
            caffc_obj_arr_set(_caffc_temp_yolo_Swag_arr_1, 2, _caffc_temp_yolo_Swag_1);
            """,
            "index assignment isn't working");

    }

    /**
     * Checks if calls for things such as `new u8[x]` would generate correct C code
     */
    @Test
    public void testArrayPrimitiveNew() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[]{
                new TestUnit("a/a.caffc", """
                        module yolo

                        test() {
                          u8[] primitive_unidimensional = new u8[2]
                        }
                        """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_caffc_u8_arr_1 = (caffc_u8_arr*) caffc_u8_arr_newa(2);
                primitive_unidimensional = _caffc_temp_caffc_u8_arr_1;
                """,
            "unidimensional primitive array new generation isn't working");
    }

    @Test
    public void testArrayObjectNew() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[]{
                new TestUnit("a/a.caffc", """
                        module yolo

                        class Swag {
                        }

                        test() {
                          yolo.Swag[] object_unidimensional = new yolo.Swag[2]
                        }
                        """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
                _caffc_temp_yolo_Swag_arr_1 = (yolo_Swag_arr*) yolo_Swag_arr_newa(2);
                object_unidimensional = _caffc_temp_yolo_Swag_arr_1;
                """,
            "multidimensional primitive array new generation isn't working");
    }

    @Test
    public void testArrayPrimitiveIndexAssign() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[]{
                new TestUnit("a/a.caffc", """
                        module yolo

                        test() {
                          u8[] primitive_unidimensional = new u8[2]
                          primitive_unidimensional[1] = 3
                        }

                        """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
                primitive_unidimensional = _caffc_temp_caffc_u8_arr_1;
                caffc_u8_arr_set(primitive_unidimensional, 1, 3);
                """,
            "multidimensional primitive array index assign isn't working");
    }

    @Test
    public void testArrayObjectIndexAssign() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[]{
                new TestUnit("a/a.caffc", """
                    module yolo
                    
                    class Swag {
                    }
                    
                    test() {
                      yolo.Swag[] class_unidimensional = new yolo.Swag[2]
                      class_unidimensional[1] = 0
                    }
                    
                    """)
            }
        );

        CodeAssertsStr.assertCodeContains(code, """
                class_unidimensional = _caffc_temp_yolo_Swag_arr_1;
                caffc_obj_arr_set(class_unidimensional, 1, 0);
                """,
            "unidimensional object array index assign isn't working");
    }


}
