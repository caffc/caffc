package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.settings.BuildSettings;
import com.germaniumhq.caffc.compiler.settings.FilesSetting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestS105SharpIfdef {
    @Test
    public void methodLevelKeepsThenBranch() {
        BuildSettings settings = new BuildSettings();

        String code = CodeAssertsStr.compileCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module main

                    interface obj {}

                    test() -> i32 {
                      i32 result = 0
                      #ifdef gc.impl == "default" {
                        result = 1
                      } #else {
                        result = 2
                      }
                      return result
                    }
                    """)
            },
            settings
        );

        CodeAssertsStr.assertCodeContains(code, "result = 1;", "matching #ifdef then-branch should be kept");
        CodeAssertsStr.assertCodeNotContains(code, "result = 2;", "non-matching #else should be dropped");
    }

    @Test
    public void methodLevelUsesElseWhenFalse() {
        BuildSettings settings = new BuildSettings();
        settings.setOneFile("other.c");

        String code = CodeAssertsStr.compileCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module main

                    interface obj {}

                    test() -> i32 {
                      i32 result = 0
                      #ifdef one_file == "main.c" {
                        result = 1
                      } #else {
                        result = 7
                      }
                      return result
                    }
                    """)
            },
            settings
        );

        CodeAssertsStr.assertCodeContains(code, "result = 7;", "#else should win when #ifdef is false");
        CodeAssertsStr.assertCodeNotContains(code, "result = 1;", "false #ifdef then-branch should be dropped");
    }

    @Test
    public void unitLevelIncludesMatchingFunction() {
        BuildSettings settings = new BuildSettings();

        CompilationUnit unit = CodeAssertsAst.compileCaffcUnitsAst(
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module main

                    interface obj {}

                    #ifdef gc.impl == "default" {
                      kept() -> i32 {
                        return 1
                      }
                    } #else {
                      dropped() -> i32 {
                        return 2
                      }
                    }
                    """)
            },
            settings
        );

        Assertions.assertNotNull(unit.getFunction("kept"), "matching #ifdef function should exist");
        Assertions.assertNull(unit.getFunction("dropped"), "non-matching #else function should not exist");
    }

    @Test
    public void filesContainsSelectsCodePage() {
        BuildSettings settings = new BuildSettings();
        settings.i18n.files = new FilesSetting();
        settings.i18n.files.readFrom(
            java.util.Map.of(
                "includes", java.util.List.of("glob(\"generated/iso/CodePage8859_2.caffc\")"),
                "excludes", java.util.List.of()
            ),
            com.germaniumhq.caffc.compiler.model.source.SourceLocation.UNKNOWN
        );

        CompilationUnit unit = CodeAssertsAst.compileCaffcUnitsAst(
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module main

                    interface obj {}

                    #ifdef i18n.files.contains("generated/iso/CodePage8859_2.caffc") {
                      registered() -> i32 {
                        return 1
                      }
                    }

                    #ifdef i18n.files.contains("generated/iso/CodePageMissing.caffc") {
                      missing() -> i32 {
                        return 2
                      }
                    }
                    """)
            },
            settings
        );

        Assertions.assertNotNull(unit.getFunction("registered"));
        Assertions.assertNull(unit.getFunction("missing"));
    }

    @Test
    public void emptyWhenIfdefFalseAndNoElse() {
        BuildSettings settings = new BuildSettings();
        settings.setOneFile("x.c");

        CompilationUnit unit = CodeAssertsAst.compileCaffcUnitsAst(
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module main

                    interface obj {}

                    #ifdef one_file == "main.c" {
                      only_if_main() -> i32 {
                        return 1
                      }
                    }

                    always() -> i32 {
                      return 0
                    }
                    """)
            },
            settings
        );

        Assertions.assertNull(unit.getFunction("only_if_main"));
        Assertions.assertNotNull(unit.getFunction("always"));
    }
}
