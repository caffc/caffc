package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.error.CaffcAntlrErrorListener;
import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.error.CancelCompilationException;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Program;
import com.germaniumhq.caffc.generated.caffcLexer;
import com.germaniumhq.caffc.generated.caffcParser;
import com.germaniumhq.caffc.output.PebbleTemplater;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CodeAsserts {
    public static void assertCodeContains(String code, String containedCode) {
        assertCodeContains(code, containedCode, "code isn't containing expected:  `" + containedCode + "`");
    }

    public static void assertCodeContains(String code, String containedCode, String errorMessage) {
        // multiline check?
        if (containedCode.contains("\n")) {
            String[] containedCodeLines = containedCode.split("\n");
            assertCodeContainsMultiLine(code, containedCodeLines, errorMessage);
            return;
        }

        if (!code.contains(containedCode)) {
            System.out.println(code);
            throw new AssertionError(errorMessage + "\nmissing:  `" + containedCode + "`");
        }
    }

    private static void assertCodeContainsMultiLine(String code, String[] containedCodeLines, String errorMessage) {
        String[] codeLines = code.split("\n");

        nextLine:
        for (int i = 0; i < codeLines.length - containedCodeLines.length + 1; i++) {
            for (int j = i; j - i < containedCodeLines.length; j++) {
                if (!codeLines[j].contains(containedCodeLines[j - i])) {
                    continue nextLine;
                }
            }

            return; // we found a match
        }

        System.out.println(code);
        throw new AssertionError(errorMessage + "\nmissing:  `" + String.join("\n", containedCodeLines) + "`");
    }

    public static void assertCodeNotContains(String code, String containedCode, String errorMessage) {
        if (code.contains(containedCode)) {
            System.out.println(code);
            throw new AssertionError(errorMessage + "\nfound:  " + containedCode);
        }
    }

    public static String compileCaffcCode(String template, String compilationUnitPath, String caffcCode) {
        caffcLexer lexer = new caffcLexer(new ANTLRInputStream(caffcCode));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        caffcParser parser = new caffcParser(tokens);

        Program program = Program.reset();

        caffcParser.CompilationUnitContext antlrCompilationUnit = parser.compilationUnit();
        CompilationUnit compilationUnit = CompilationUnit.fromAntlr(program, antlrCompilationUnit, compilationUnitPath);

        compilationUnit.recurseResolveTypes();
        program.recreateConstants();

        Map<String, Object> renderContext = PebbleTemplater.createRenderContext(
                template.contains("module") ?
                        compilationUnit.module :
                        compilationUnit);
        String code = PebbleTemplater.INSTANCE.renderToString(template, renderContext);

        return code;
    }

    /**
     * Compiles a caffc compilation unit, but includes also the caffc core libraries.
     * So arrays, strings, etc. are available and resolvable
     *
     * @param template
     * @param unit
     * @param testUnits
     * @return
     */
    public static String compileFullCaffcProgram(
            String template,
            String unit,
            TestUnit[] testUnits) {
        List<TestUnit> allUnits = new ArrayList<>();

        allUnits.addAll(Arrays.asList(testUnits));
        allUnits.addAll(caffcFeature("common", "default"));
        allUnits.addAll(caffcFeature("gc", "test"));

        return compileCaffcProgram(template, unit, allUnits.toArray(new TestUnit[0]));
    }

    /**
     * Finds all the compilation units for a given feature, under a specific profile.
     * @param feature
     * @param profile
     * @return
     */
    private static List<TestUnit> caffcFeature(String feature, String profile) {
        List<TestUnit> featureUnits = new ArrayList<>();

        String fullPath = "src/main/templates/" + feature + "/" + profile + "/caffc";

        for (String filePath: findCaffcFiles(fullPath)) {
            TestUnit testUnit = new TestUnit(filePath, readFileContents(filePath));
            featureUnits.add(testUnit);
        }

        return featureUnits;
    }

    private static String readFileContents(String filePath) {
        try {
            return Files.readString(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> findCaffcFiles(String fullPath) {
        if (!Files.isDirectory(Path.of(fullPath))) {
            return Collections.emptyList();
        }

        List<String> files = new ArrayList<>();

        File[] items = new File(fullPath).listFiles((File dir, String it) -> it.endsWith(".caffc"));
        for (File file : items) {
            files.add(file.getAbsolutePath());
        }

        return files;
    }

    /**
     * Compile multiple units, however output a single file. Files can even be in different modules.
     */
    public static String compileCaffcProgram(
            String template,
            String unit,
            TestUnit[] testUnits) {
        Program program = Program.reset();

        try {
            return compileCaffcUnits(program, template, unit, testUnits);
        } catch (CancelCompilationException e) {
            CodeAsserts.printUnitWithLineNumbers(unit, testUnits);
            throw new RuntimeException(e);
        }
    }

    /**
     * Compiles multiple units, does the typing resolving, constant creation, etc,
     * but returns only the CompilationUnit.
     */
    public static CompilationUnit compileCaffcUnitsAst(String unit, TestUnit[] testUnits) {
        Program program = Program.reset();

        try {
            return compileCaffcUnitsAst(program, unit, testUnits);
        } catch (CancelCompilationException e) {
            printUnitWithLineNumbers(unit, testUnits);
            throw new RuntimeException(e);
        }
    }

    private static void printUnitWithLineNumbers(String unitName, TestUnit[] testUnits) {
        for (TestUnit testUnit: testUnits) {
            if (testUnit.unitPath.equals(unitName)) {
                String[] lines = testUnit.code.split("\n");

                for (int i = 0; i < lines.length; i++) {
                    System.err.printf("%04d:  %s\n", i + 1, lines[i]);
                }
            }
        }
    }

    /**
     * Compiles all the given Caffc compilation units, and outputs the rendered
     * code with the <code>template</code> against the specified compilation
     * unit (<code>unit</code>)
     *
     * @param program
     * @param template
     * @param unit
     * @param testUnits
     * @return
     */
    private static String compileCaffcUnits(
            Program program,
            String template,
            String unit,
            TestUnit[] testUnits) {
        CompilationUnit compilationUnit = compileCaffcUnitsAst(program, unit, testUnits);

        if (CaffcCompiler.get().hasErrors) {
            CaffcCompiler.get().fatal(compilationUnit, "Errors found in compilation");
        }

        try {
            Map<String, Object> renderContext = PebbleTemplater.createRenderContext(
                    template.contains("module") ?
                            compilationUnit.module :
                            compilationUnit);

            String code = PebbleTemplater.INSTANCE.renderToString(template, renderContext);

            return code;
        } catch (Exception e) {
            throw new RuntimeException("Unable to render unit: " + unit, e);
        }
    }

    private static CompilationUnit compileCaffcUnitsAst(Program program, String unit, TestUnit[] testUnits) {
        Map<String, CompilationUnit> compilationUnits = new HashMap<>();

        for (int i = 0; i < testUnits.length; i++) {
            String compilationUnitPath = testUnits[i].unitPath;
            String caffcCode = testUnits[i].code;

            caffcLexer lexer = new caffcLexer(new ANTLRInputStream(caffcCode));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            caffcParser parser = new caffcParser(tokens);

            lexer.removeErrorListeners();
            lexer.addErrorListener(new CaffcAntlrErrorListener(compilationUnitPath));
            parser.removeErrorListeners();
            parser.addErrorListener(new CaffcAntlrErrorListener(compilationUnitPath));

            caffcParser.CompilationUnitContext antlrCompilationUnit = parser.compilationUnit();
            CompilationUnit compilationUnit = CompilationUnit.fromAntlr(program, antlrCompilationUnit, compilationUnitPath);

            compilationUnits.put(compilationUnitPath, compilationUnit);
        }

        for (CompilationUnit compilationUnit: compilationUnits.values()) {
            compilationUnit.recurseResolveTypes();
        }
        program.recreateConstants();

        CompilationUnit compilationUnit = compilationUnits.get(unit);

        if (compilationUnit == null) {
            CaffcCompiler.get().fatal(compilationUnit, "unit not found: " + unit + " known units: " + compilationUnits.keySet());
        }

        return compilationUnit;
    }
}
