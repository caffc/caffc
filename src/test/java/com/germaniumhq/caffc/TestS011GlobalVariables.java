package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.error.CancelCompilationException;
import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAssertsStr.*;

public class TestS011GlobalVariables {
    @Test
    public void globalVariableDeclaration() {
        String code = compileCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "test.caffc",
            new TestUnit[] {
                new TestUnit("test.caffc",
                    """
                    module yolo
                    
                    i32 x = 42
                    
                    main() -> i32 {
                      return x
                    }
                    """
                )
            }
        );

        // Global variable init is moved to module_init(), main just references it
        assertCodeContains(code, "return x;");
    }

    @Test
    public void globalVariableInModuleHeader() {
        String header = compileCaffcProgram(
            "caffc/template/c/module_h.peb",
            "test.caffc",
            new TestUnit[] {
                new TestUnit("test.caffc",
                    """
                    module yolo
                    
                    i32 x = 42
                    
                    main() -> i32 {
                      return 0
                    }
                    """
                )
            }
        );

        assertCodeContains(header, "extern caffc_i32 x;");
        assertCodeContains(header, "void yolo_module_init();");
    }

    @Test
    public void globalVariableInitFunction() {
        String moduleC = compileCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "yolo_module_init.caffc",
            new TestUnit[] {
                new TestUnit("test.caffc",
                    """
                    module yolo
                    
                    i32 x = 42
                    i32 y = 100
                    obj a = get_data()
                    
                    class obj {}
                    
                    get_data() -> obj {
                      return null
                    }
                    
                    main() -> i32 {
                      return 0
                    }
                    """
                )
            }
        );

        assertCodeContains(
            moduleC,
            """
            x = 42;
            y = 100;
            _caffc_temp_yolo_obj_1 = yolo_get_data(); if (_caffc_exception) { goto fnUncaughtException0; };
            a = _caffc_temp_yolo_obj_1;
            """
        );
    }

    @Test
    public void globalVariableShadowingError() {
        try {
            compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "test.caffc",
                new TestUnit[] {
                    new TestUnit("test.caffc",
                        """
                        module yolo
                        
                        i32 x = 42
                        
                        main(i32 x) -> i32 {
                          return x
                        }
                        """
                    )
                }
            );
            throw new AssertionError("Expected fatal error for parameter shadowing global variable");
        } catch (CancelCompilationException e) {
            if (!e.getMessage().contains("shadows")) {
                throw new AssertionError("Expected shadows error but got: " + e.getMessage(), e);
            }
        }
    }

    @Test
    public void globalVariableDuplicateError() {
        try {
            compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "test.caffc",
                new TestUnit[] {
                    new TestUnit("test.caffc",
                        """
                        module yolo
                        
                        i32 x = 42
                        i32 x = 100
                        
                        main() -> i32 {
                          return 0
                        }
                        """
                    )
                }
            );
            throw new AssertionError("Expected fatal error for duplicate global variable");
        } catch (CancelCompilationException e) {
            if (!e.getMessage().contains("already declared")) {
                throw new AssertionError("Expected already declared error but got: " + e.getMessage(), e);
            }
        }
    }

    @Test
    public void noModuleInitWithoutGlobalVars() {
        String header = compileCaffcProgram(
                "caffc/template/c/module_h.peb",
                "test.caffc",
                new TestUnit[] {
                    new TestUnit("test.caffc",
"""
module yolo

main() -> i32 {
  return 0
}
""")}
        );

        assertCodeNotContains(header, "yolo_module_init", "module_init should not be generated without global variables");
    }

    @Test
    public void moduleInitAutoGeneratedWithGlobals() {
        String header = compileCaffcProgram(
                "caffc/template/c/module_h.peb",
                "test.caffc",
                new TestUnit[] {
                        new TestUnit("test.caffc",
"""
module yolo

i32 x = 42

main() -> i32 {
  return 0
}
""")}
        );

        assertCodeContains(header, "void yolo_module_init();");
    }

    /**
     * This tests if the global variables get augmented at the beginning of the
     * existing `module_init()`.
     */
    @Test
    public void moduleInitAugmentedWithGlobals() {
        String moduleC = compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "test.caffc",
                new TestUnit[] {
                        new TestUnit("test.caffc",
"""
module yolo

i32 x = 42

module_init() {
  yolo_setup()
}

yolo_setup() {
}

main() -> i32 {
  return 0
}
""")}
        );

        assertCodeContains(moduleC, """
        x = 42;
        yolo_yolo_setup(); if (_caffc_exception) { goto fnUncaughtException0; };
        fnUncaughtException0:
        _caffc_stack_frame_unregister(caffc_null);
        """);
    }
}
