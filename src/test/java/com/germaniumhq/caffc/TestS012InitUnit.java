package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.error.CancelCompilationException;
import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAssertsStr.*;

public class TestS012InitUnit {
    @Test
    public void initUnitAfterGlobalsAndInitModule() {
        String moduleC = compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "test.caffc",
                new TestUnit[] {
                        new TestUnit("test.caffc",
"""
module yolo

i32 x = 42

init_module() {
  yolo_setup()
}

init_unit() {
  yolo_unit()
}

yolo_setup() {
}

yolo_unit() {
}

main() -> i32 {
  return 0
}
""")}
        );

        assertCodeContains(moduleC, """
        x = 42;
        yolo_yolo_setup(); if (_caffc_exception) { goto fnUncaughtException0; };
        yolo_yolo_unit(); if (_caffc_exception) { goto fnUncaughtException0; };
        """);
        assertCodeNotContains(moduleC, "yolo_init_unit", "init_unit itself must not be generated");
    }

    @Test
    public void initUnitGeneratesInitModuleWithoutGlobals() {
        String header = compileCaffcProgram(
                "caffc/template/c/module_h.peb",
                "test.caffc",
                new TestUnit[] {
                        new TestUnit("test.caffc",
"""
module yolo

init_unit() {
  yolo_unit()
}

yolo_unit() {
}

main() -> i32 {
  return 0
}
""")}
        );

        assertCodeContains(header, "void yolo_init_module();");
        assertCodeNotContains(header, "init_unit", "init_unit must not appear in the module header");
    }

    @Test
    public void initUnitBodyMovedToSyntheticInitModule() {
        String moduleC = compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "yolo_init_module.caffc",
                new TestUnit[] {
                        new TestUnit("test.caffc",
"""
module yolo

init_unit() {
  yolo_unit()
}

yolo_unit() {
}

main() -> i32 {
  return 0
}
""")}
        );

        assertCodeContains(moduleC, "yolo_yolo_unit(); if (_caffc_exception) { goto fnUncaughtException0; };");
    }

    @Test
    public void initUnitLocalsMovedToInitModule() {
        String moduleC = compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "test.caffc",
                new TestUnit[] {
                        new TestUnit("test.caffc",
"""
module yolo

init_module() {
}

init_unit() {
  i32 localX = 7
}

main() -> i32 {
  return 0
}
""")}
        );

        assertCodeContains(moduleC, "caffc_i32 localX = 0;");
        assertCodeContains(moduleC, "localX = 7;");
        assertCodeNotContains(moduleC, "yolo_init_unit", "init_unit itself must not be generated");
    }

    @Test
    public void initUnitFromMultipleCompilationUnits() {
        String moduleC = compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "yolo_init_module.caffc",
                new TestUnit[] {
                        new TestUnit("b.caffc",
"""
module yolo

init_unit() {
  second()
}

second() {
}
"""),
                        new TestUnit("a.caffc",
"""
module yolo

init_unit() {
  first()
}

first() {
}

main() -> i32 {
  return 0
}
""")}
        );

        assertCodeContains(moduleC, """
        yolo_first(); if (_caffc_exception) { goto fnUncaughtException0; };
        """);
        assertCodeContains(moduleC, """
        yolo_second(); if (_caffc_exception) { goto fnUncaughtException0; };
        """);
        assertCodeNotContains(moduleC, "init_unit", "init_unit itself must not be generated");
    }

    @Test
    public void initUnitRemovedFromOriginalCompilationUnit() {
        String unitC = compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "test.caffc",
                new TestUnit[] {
                        new TestUnit("test.caffc",
"""
module yolo

init_unit() {
  yolo_unit()
}

yolo_unit() {
}

main() -> i32 {
  return 0
}
""")}
        );

        assertCodeNotContains(unitC, "init_unit", "init_unit must be deleted from the original unit");
        assertCodeContains(unitC, "void yolo_yolo_unit()");
    }

    @Test
    public void initUnitCallIsCompilerError() {
        try {
            compileCaffcProgram(
                    "caffc/template/c/compilation_unit_c.peb",
                    "test.caffc",
                    new TestUnit[] {
                            new TestUnit("test.caffc",
"""
module yolo

init_unit() {
}

main() -> i32 {
  init_unit()
  return 0
}
""")}
            );
            throw new AssertionError("Expected fatal error for calling init_unit()");
        } catch (CancelCompilationException e) {
            if (!e.getMessage().contains("cannot call init_unit")) {
                throw new AssertionError("Expected cannot call init_unit error but got: " + e.getMessage(), e);
            }
        }
    }

    @Test
    public void initUnitWithParametersIsCompilerError() {
        try {
            compileCaffcProgram(
                    "caffc/template/c/compilation_unit_c.peb",
                    "test.caffc",
                    new TestUnit[] {
                            new TestUnit("test.caffc",
"""
module yolo

init_unit(i32 x) {
}

main() -> i32 {
  return 0
}
""")}
            );
            throw new AssertionError("Expected fatal error for parameterized init_unit()");
        } catch (CancelCompilationException e) {
            if (!e.getMessage().contains("cannot have parameters")) {
                throw new AssertionError("Expected parameters error but got: " + e.getMessage(), e);
            }
        }
    }

    @Test
    public void initUnitWithReturnTypeIsCompilerError() {
        try {
            compileCaffcProgram(
                    "caffc/template/c/compilation_unit_c.peb",
                    "test.caffc",
                    new TestUnit[] {
                            new TestUnit("test.caffc",
"""
module yolo

init_unit() -> i32 {
  return 0
}

main() -> i32 {
  return 0
}
""")}
            );
            throw new AssertionError("Expected fatal error for non-void init_unit()");
        } catch (CancelCompilationException e) {
            if (!e.getMessage().contains("cannot return a value")) {
                throw new AssertionError("Expected return type error but got: " + e.getMessage(), e);
            }
        }
    }
}
