package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

/**
 * This checks the generation of an entrypoint is correctly generated.
 */
public class TestS900EntryPoint {
    @Test
    public void testGenerationOfAMainFunctionWorks() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
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

        CodeAssertsStr.assertCodeContains(code, """
int main(int argc, char* argv[]) {
  int result;
  caffc_str_arr* arr;
  caffc_ptr _caffc_locals[1];
  int i;

  _caffc_locals[0] = &arr;

  atexit(caffc_done);
  caffc_init();

  if (_caffc_exception) {
    goto mainUnhandledException;
  }

  /* FIXME: add <entrypoint> as a caffc.str */
  _caffc_stack_frame_register(caffc_null, _caffc_locals, 1);

  arr = caffc_str_arr_newa(argc);

  for (i = 0; i < argc; i++) {
    caffc_obj_arr_set(
      (caffc_obj_arr*) arr,
      (caffc_i32) i,
      (caffc_obj*) caffc_str_fromPtr(argv[i])
    );
  }

  result = main_main(arr);
  if (_caffc_exception) {
    mainUnhandledException:
    caffc_str* _caffc_exception_msg = caffc_exception_message((caffc_exception*)_caffc_exception);
    if (_caffc_exception_msg) {
      printf("Uncaught exception: %s\\n", _caffc_exception_msg->_caffc_data);
    }

    /* we want the GC to sweep it */
    _caffc_exception = caffc_null;

    exit(1);
  }

  _caffc_stack_frame_unregister(&_caffc_locals);

return result;
}
            """);
    }

    @Test
    public void testGenerationOfAMainVoidFunctionWorks() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
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

        CodeAssertsStr.assertCodeContains(code, """
int main(int argc, char* argv[]) {
  caffc_str_arr* arr;
  caffc_ptr _caffc_locals[1];
  int i;

  _caffc_locals[0] = &arr;

  atexit(caffc_done);
  caffc_init();

  if (_caffc_exception) {
    goto mainUnhandledException;
  }

  /* FIXME: add <entrypoint> as a caffc.str */
  _caffc_stack_frame_register(caffc_null, _caffc_locals, 1);

  arr = caffc_str_arr_newa(argc);

  for (i = 0; i < argc; i++) {
    caffc_obj_arr_set(
      (caffc_obj_arr*) arr,
      (caffc_i32) i,
      (caffc_obj*) caffc_str_fromPtr(argv[i])
    );
  }

  main_main(arr);
  if (_caffc_exception) {
    mainUnhandledException:
    caffc_str* _caffc_exception_msg = caffc_exception_message((caffc_exception*)_caffc_exception);
    if (_caffc_exception_msg) {
      printf("Uncaught exception: %s\\n", _caffc_exception_msg->_caffc_data);
    }

    /* we want the GC to sweep it */
    _caffc_exception = caffc_null;

    exit(1);
  }

  _caffc_stack_frame_unregister(&_caffc_locals);

return 0;
}
            """);
    }

    @Test
    public void testGenerationOfAMainWithoutParamsFunctionWorks() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
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

        CodeAssertsStr.assertCodeContains(code, """
int main(int argc, char* argv[]) {
  int result;

  atexit(caffc_done);
  caffc_init();

  if (_caffc_exception) {
    goto mainUnhandledException;
  }

  /* FIXME: add <entrypoint> as a caffc.str */
  _caffc_stack_frame_register(caffc_null, caffc_null, 0);

  result = main_main();
  if (_caffc_exception) {
    mainUnhandledException:
    caffc_str* _caffc_exception_msg = caffc_exception_message((caffc_exception*)_caffc_exception);
    if (_caffc_exception_msg) {
      printf("Uncaught exception: %s\\n", _caffc_exception_msg->_caffc_data);
    }

    /* we want the GC to sweep it */
    _caffc_exception = caffc_null;

    exit(1);
  }

  _caffc_stack_frame_unregister(caffc_null);

return result;
}
            """);
    }
}
