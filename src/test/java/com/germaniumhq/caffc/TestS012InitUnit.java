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

module_init() {
  yolo_setup()
}

unit_init() {
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
        yolo_x = 42;
        yolo_yolo_setup(); if (_caffc_exception) { goto fnUncaughtException0; };
        yolo_yolo_unit(); if (_caffc_exception) { goto fnUncaughtException0; };
        """);
        assertCodeNotContains(moduleC, "yolo_unit_init", "unit_init itself must not be generated");
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

unit_init() {
  yolo_unit()
}

yolo_unit() {
}

main() -> i32 {
  return 0
}
""")}
        );

        assertCodeContains(header, "void yolo_module_init();");
        assertCodeNotContains(header, "unit_init", "unit_init must not appear in the module header");
    }

    @Test
    public void initUnitBodyMovedToInitModule() {
        String moduleC = compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "test.caffc",
                new TestUnit[] {
                        new TestUnit("test.caffc",
"""
module yolo

module_init() {
}

unit_init() {
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

module_init() {
}

unit_init() {
  i32 localX = 7
}

main() -> i32 {
  return 0
}
""")}
        );

        assertCodeContains(moduleC, "caffc_i32 localX = 0;");
        assertCodeContains(moduleC, "localX = 7;");
        assertCodeNotContains(moduleC, "yolo_unit_init", "unit_init itself must not be generated");
    }

    @Test
    public void initUnitFromMultipleCompilationUnits() {
        String moduleC = compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a.caffc",
                new TestUnit[] {
                        new TestUnit("b.caffc",
"""
module yolo

unit_init() {
  second()
}

second() {
}
"""),
                        new TestUnit("a.caffc",
"""
module yolo

module_init() {
}

unit_init() {
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
        assertCodeNotContains(moduleC, "unit_init", "unit_init itself must not be generated");
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

unit_init() {
  yolo_unit()
}

yolo_unit() {
}

main() -> i32 {
  return 0
}
""")}
        );

        assertCodeNotContains(unitC, "unit_init", "unit_init must be deleted from the original unit");
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

unit_init() {
}

main() -> i32 {
  unit_init()
  return 0
}
""")}
            );
            throw new AssertionError("Expected fatal error for calling unit_init()");
        } catch (CancelCompilationException e) {
            // unit_init is never registered as a callable module function, so
            // a call fails at symbol resolution rather than a dedicated check.
            if (!e.getMessage().contains("cannot resolve unit_init")) {
                throw new AssertionError("Expected cannot resolve unit_init error but got: " + e.getMessage(), e);
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

unit_init(i32 x) {
}

main() -> i32 {
  return 0
}
""")}
            );
            throw new AssertionError("Expected fatal error for parameterized unit_init()");
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

unit_init() -> i32 {
  return 0
}

main() -> i32 {
  return 0
}
""")}
            );
            throw new AssertionError("Expected fatal error for non-void unit_init()");
        } catch (CancelCompilationException e) {
            if (!e.getMessage().contains("cannot return a value")) {
                throw new AssertionError("Expected return type error but got: " + e.getMessage(), e);
            }
        }
    }
}
