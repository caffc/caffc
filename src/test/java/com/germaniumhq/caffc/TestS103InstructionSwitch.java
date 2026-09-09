package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS103InstructionSwitch {
    @Test
    public void testBooleanSwitchGeneration() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module main

                    test(i32 x) -> i32 {
                      i32 result = 0
                      switch {
                        case x == 1: {
                          result = 10
                        }
                        case x == 2: {
                          result = 20
                        }
                        default: {
                          result = 30
                        }
                      }
                      return result
                    }
                    """)
            }
        );

        CodeAssertsStr.assertCodeContains(code,
            """
            /* switchBegin1: */
            _caffc_temp_caffc_bool_1 = x == 1;
            if (! _caffc_temp_caffc_bool_1) { goto switchNext2; }
            result = 10;
            goto switchEnd1;
            switchNext2:
            _caffc_temp_caffc_bool_2 = x == 2;
            if (! _caffc_temp_caffc_bool_2) { goto switchNext3; }
            result = 20;
            goto switchEnd1;
            switchNext3:
            /* switchDefault1: */
            result = 30;
            switchEnd1:
            """,
            "boolean switch should lower to if/else-if/else style jumps");
    }

    @Test
    public void testValueSwitchPrimitiveGeneration() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module main

                    test(i32 x) -> i32 {
                      i32 result = 0
                      switch x {
                        case 1: {
                          result = 10
                        }
                        case 2: {
                          result = 20
                        }
                        default: {
                          result = 30
                        }
                      }
                      return result
                    }
                    """)
            }
        );

        CodeAssertsStr.assertCodeContains(code,
            """
            /* switchBegin1: */
            _caffc_switch_5_2 = x;
            _caffc_temp_caffc_bool_1 = _caffc_switch_5_2 == 1;
            if (! _caffc_temp_caffc_bool_1) { goto switchNext2; }
            result = 10;
            goto switchEnd1;
            switchNext2:
            _caffc_temp_caffc_bool_2 = _caffc_switch_5_2 == 2;
            if (! _caffc_temp_caffc_bool_2) { goto switchNext3; }
            result = 20;
            goto switchEnd1;
            switchNext3:
            /* switchDefault1: */
            result = 30;
            switchEnd1:
            """,
            "value switch on primitives should compare with ==");
    }

    @Test
    public void testValueSwitchStringAndNull() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module main

                    test(str x) -> i32 {
                      i32 result = 0
                      switch x {
                        case null: {
                          result = 1
                        }
                        case "a": {
                          result = 2
                        }
                        default: {
                          result = 3
                        }
                      }
                      return result
                    }
                    """)
            }
        );

        CodeAssertsStr.assertCodeContains(code,
            """
            _caffc_temp_caffc_bool_1 = _caffc_switch_
            """,
            "value switch should store the switched expression");

        CodeAssertsStr.assertCodeContains(code,
            """
            == caffc_null
            """,
            "case null should compare against null without calling equals");

        CodeAssertsStr.assertCodeContains(code,
            """
            caffc_str_equals(
            """,
            "non-null object cases should call equals");
    }

    @Test
    public void testSwitchBreak() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module main

                    test(i32 x) -> i32 {
                      i32 result = 0
                      switch x {
                        case 1: {
                          result = 10
                          break
                        }
                        default: {
                          result = 20
                        }
                      }
                      return result
                    }
                    """)
            }
        );

        CodeAssertsStr.assertCodeContains(code,
            """
            result = 10;
            goto switchEnd1;
            goto switchEnd1;
            """,
            "break inside switch should jump to switch end");
    }

    @Test
    public void testBooleanSwitchWithControlFlowOnly() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
            "caffc/template/c/compilation_unit_c.peb",
            "a/a.caffc",
            new TestUnit[] {
                new TestUnit("a/a.caffc",
                    """
                    module main

                    test(i32 x) -> i32 {
                      for i32 i = 0; i < 3; i += 1 {
                        switch {
                          case i == 1: break
                          default: {
                            x = x + 1
                          }
                        }
                      }
                      return x
                    }
                    """)
            }
        );

        CodeAssertsStr.assertCodeContains(code,
            """
            goto forEnd
            """,
            "break as sole case body should exit the enclosing loop");
    }
}
