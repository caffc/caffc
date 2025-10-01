package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

/**
 * This checks the generation of an entrypoint is correctly generated.
 */
public class TestS900EntryPoint {
    @Test
    public void testGenerationOfAMainFunctionWorks() {
        String code = CodeAsserts.compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
            """
module main

main(str[] args) -> i32 {
}
""")
                }
        );

        CodeAsserts.assertCodeContains(code, """
int main(int argc, char* argv[]) {
  caffc_str_arr* arr;
  ptr _caffc_locals[] = { &arr };
  int i;
  int result;

  atexit(caffc_done);
  caffc_init();

  _caffc_stack_frame_register("#entrypoint", _caffc_locals, 1);

  arr = caffc_str_arr_new(argc);

  for (i = 0; i < argc; i++) {
    caffc_obj_arr_set((caffc_obj_arr*) arr, (i32) i, caffc_str_fromPtr(argv[i]));
  }

  result = main_main(arr);

  _caffc_stack_frame_unregister(&_caffc_locals);

  return result;
}
        """);
    }

    @Test
    public void testGenerationOfAMainVoidFunctionWorks() {
        String code = CodeAsserts.compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                    module main

                    main(str[] args) {
                    }
                    """)
                }
        );

        CodeAsserts.assertCodeContains(code, """
int main(int argc, char* argv[]) {
  caffc_str_arr* arr;
  ptr _caffc_locals[] = { &arr };
  int i;

  atexit(caffc_done);
  caffc_init();

  _caffc_stack_frame_register("#entrypoint", _caffc_locals, 1);

  arr = caffc_str_arr_new(argc);

  for (i = 0; i < argc; i++) {
    caffc_obj_arr_set((caffc_obj_arr*) arr, (i32) i, caffc_str_fromPtr(argv[i]));
  }

  main_main(arr);

  _caffc_stack_frame_unregister(&_caffc_locals);

  return 0;
}
        """);
    }

    @Test
    public void testGenerationOfAMainWithoutParamsFunctionWorks() {
        String code = CodeAsserts.compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                    module main

                    main() -> i32 {
                      return 0
                    }
                    """)
                }
        );

        CodeAsserts.assertCodeContains(code, """
int main(int argc, char* argv[]) {
  int result;

  atexit(caffc_done);
  caffc_init();

  _caffc_stack_frame_register("#entrypoint", null, 0);

  result = main_main();

  _caffc_stack_frame_unregister(null);

  return result;
}
        """);
    }
}
