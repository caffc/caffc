package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.settings.BuildSettings;
import com.germaniumhq.caffc.output.CCodeLineWrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestS066MaxLineWidth {
    @Test
    public void wrapSplitsStatementsAndCommasAt72() {
        String input = ""
                + "caffc_print((caffc_str*)&caffc_cstr_0123456789abcdef); "
                + "if (_caffc_exception) { goto fnUncaughtException0; };\n"
                + "void foo(caffc_i32 a, caffc_i32 b, caffc_i32 c, caffc_i32 d, caffc_i32 e);\n";

        String wrapped = CCodeLineWrapper.wrap(input, BuildSettings.DEFAULT_MAX_LINE_WIDTH);

        for (String line : wrapped.split("\n", -1)) {
            assertTrue(line.length() <= 72,
                    "line exceeds 72 (" + line.length() + "): " + line);
        }
        assertTrue(wrapped.contains("if (_caffc_exception)"));
        assertTrue(wrapped.contains("caffc_print("));
    }

    @Test
    public void wrapPreprocessorUsesBackslashContinuation() {
        String input = "#line 3 \"/home/raptor/projects/caffc/caffc-tests/hello-world-caffc/src/main.caffc\"\n";
        String wrapped = CCodeLineWrapper.wrap(input, 72);

        for (String line : wrapped.split("\n", -1)) {
            if (line.isEmpty()) {
                continue;
            }
            assertTrue(line.length() <= 72,
                    "line exceeds 72 (" + line.length() + "): " + line);
        }
        assertTrue(wrapped.contains("\\\n") || wrapped.contains("\\"));
        // Spliced form still contains the path pieces
        assertTrue(wrapped.replace("\\\n", "").contains("hello-world-caffc"));
    }

    @Test
    public void shortLinesUnchanged() {
        String input = "int x = 1;\n";
        assertEquals(input, CCodeLineWrapper.wrap(input, 72));
    }

    @Test
    public void hardWrapDoesNotInsertWhitespaceIntoTokens() {
        // 80-char identifier-like token with no soft break points
        String longIdent = "a" + "b".repeat(80);
        String input = longIdent + ";\n";
        String wrapped = CCodeLineWrapper.wrap(input, 72);
        String spliced = wrapped.replace("\\\n", "");
        assertEquals(input, spliced);
        for (String line : wrapped.split("\n", -1)) {
            if (!line.isEmpty()) {
                assertTrue(line.length() <= 72, line);
            }
        }
    }

    @Test
    public void generatedProgramRespectsMaxLineWidth() {
        String code = CodeAssertsStr.compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "test.caffc",
                new TestUnit[]{
                        new TestUnit("test.caffc",
                                """
                                module main

                                main() -> i32 {
                                  str msg = "hello world from a somewhat longer greeting"
                                  print(msg)
                                  return 0
                                }
                                """)
                });

        int over = 0;
        for (String line : code.split("\n", -1)) {
            if (line.length() > BuildSettings.DEFAULT_MAX_LINE_WIDTH) {
                over++;
            }
        }
        assertEquals(0, over, "generated C has lines longer than max_line_width");
        assertFalse(code.contains("caffc_cstr_" + "0".repeat(20)));
    }
}
