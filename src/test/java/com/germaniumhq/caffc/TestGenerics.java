package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAsserts.assertCodeContains;
import static com.germaniumhq.caffc.CodeAsserts.compileFullCaffcProgram;

public class TestGenerics {
    @Test
    public void testGenericsDefaultExtends() {
        String code = compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb", /* template         */
                "test.caffc",                              /* compilation unit */
                new TestUnit[] {
                new TestUnit("test.caffc",
                        """
        module main

        class List<T> {
          T data

          get(i32 x) -> T {
            return _this.data
          }

          set(T value) {
            _this.data = value
          }
        }

        main() {
        }
        """)}
);

        assertCodeContains(code, "void main_List_set(main_List* _this, caffc_obj* value) {",
                "functions using generics parameters should be generated correctly");
        assertCodeContains(code, "caffc_obj* main_List_get(main_List* _this, i32 x) {",
                "functions returning generic objects should be generated correctly");
    }

}
