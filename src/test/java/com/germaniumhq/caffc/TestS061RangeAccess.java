package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAssertsStr.compileFullCaffcProgram;

public class TestS061RangeAccess {
    @Test
    public void testStringRangeSugarLowersToRangeCall() {
        String code = compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "test.caffc",
                new TestUnit[]{
                        new TestUnit("test.caffc",
                                """
                                        module main

                                        main() -> i32 {
                                          str h = "hello.exe"
                                          str a = h[1:5]
                                          str b = h[-3:]
                                          str c = h[:5]
                                          str d = h[:]
                                          return 0
                                        }
                                        """)
                });

        CodeAssertsStr.assertCodeContains(code, "caffc_str_range(",
                "range sugar should lower to str.range()");
        CodeAssertsStr.assertCodeContains(code, "caffc_str_size(",
                "omitted range end should call size()");
    }
}
