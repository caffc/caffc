package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.error.CancelCompilationException;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.settings.BuildSettings;
import com.germaniumhq.caffc.compiler.settings.debug.CLineMacro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestS104SharpSwitch {
    @Test
    public void methodLevelPicksMatchingCase() {
        BuildSettings settings = new BuildSettings();
        // defaults: gc.impl=default

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
                      #switch {
                      #case gc.impl == "default": {
                        result = 1
                      }
                      #default: {
                        result = 2
                      }
                      }
                      return result
                    }
                    """)
            },
            settings
        );

        CodeAssertsStr.assertCodeContains(code, "result = 1;", "matching #case should be kept");
        CodeAssertsStr.assertCodeNotContains(code, "result = 2;", "non-matching #default should be dropped");
    }

    @Test
    public void methodLevelUsesDefaultWhenNoCaseMatches() {
        BuildSettings settings = new BuildSettings();
        settings.debug.cLineMacro = CLineMacro.none;

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
                      #switch {
                      #case debug.c_line_macro == "macro": {
                        result = 1
                      }
                      #default: {
                        result = 99
                      }
                      }
                      return result
                    }
                    """)
            },
            settings
        );

        CodeAssertsStr.assertCodeContains(code, "result = 99;", "#default should win when no #case matches");
        CodeAssertsStr.assertCodeNotContains(code, "result = 1;", "non-matching #case should be dropped");
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

                    #switch {
                    #case gc.impl == "default": {
                      kept() -> i32 {
                        return 1
                      }
                    }
                    #default: {
                      dropped() -> i32 {
                        return 2
                      }
                    }
                    }
                    """)
            },
            settings
        );

        Assertions.assertNotNull(unit.getFunction("kept"), "matching unit #case function should exist");
        Assertions.assertNull(unit.getFunction("dropped"), "non-matching unit #default function should not exist");
    }

    @Test
    public void stringAndNumericLiteralConditions() {
        BuildSettings settings = new BuildSettings();
        settings.setOneFile("main.c");

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
                      #switch {
                      #case one_file == "main.c" and 1 + 2 > 2: {
                        result = 42
                      }
                      #default: {
                        result = 0
                      }
                      }
                      return result
                    }
                    """)
            },
            settings
        );

        CodeAssertsStr.assertCodeContains(code, "result = 42;",
                "string == and numeric compare should work in #case");
    }

    @Test
    public void unknownSettingPathIsFatal() {
        BuildSettings settings = new BuildSettings();

        Assertions.assertThrows(CancelCompilationException.class, () ->
            CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
                        """
                        module main

                        interface obj {}

                        test() -> i32 {
                          #switch {
                          #case missing.path == "x": {
                            return 1
                          }
                          #default: {
                            return 0
                          }
                          }
                        }
                        """)
                },
                settings
            )
        );
    }

    @Test
    public void nestedSharpSwitchInMethodCaseIsRejectedByGrammar() {
        // Method #case bodies are statement* only, so nested #switch must fail to parse.
        Assertions.assertThrows(Exception.class, () ->
            CodeAssertsAst.compileCaffcUnitsAst(
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
                        """
                        module main

                        interface obj {}

                        test() -> i32 {
                          #switch {
                          #case gc.impl == "default": {
                            #switch {
                            #case 1 == 1: {
                              return 1
                            }
                            #default: {
                              return 2
                            }
                            }
                          }
                          #default: {
                            return 0
                          }
                          }
                        }
                        """)
                },
                new BuildSettings()
            )
        );
    }

    @Test
    public void emptyWhenNoMatchAndNoDefault() {
        BuildSettings settings = new BuildSettings();
        settings.debug.cLineMacro = CLineMacro.comment;

        CompilationUnit unit = CodeAssertsAst.compileCaffcUnitsAst(
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module main

                    interface obj {}

                    #switch {
                    #case debug.c_line_macro == "none": {
                      only_if_none() -> i32 {
                        return 1
                      }
                    }
                    }

                    always() -> i32 {
                      return 0
                    }
                    """)
            },
            settings
        );

        Assertions.assertNull(unit.getFunction("only_if_none"));
        Assertions.assertNotNull(unit.getFunction("always"));
    }
}
