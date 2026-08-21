package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.error.CaffcAntlrErrorListener;
import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Module;
import com.germaniumhq.caffc.compiler.model.Program;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.optimizer.LinearFormConverter;
import com.germaniumhq.caffc.generated.caffcLexer;
import com.germaniumhq.caffc.generated.caffcParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Utility functions to test AST parsing. AST parsing may have also optimizations enabled,
 * so we can test linear form generation.
 */
public class CodeAssertsAst {
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
     * Compiles multiple units, does the typing resolving, constant creation, etc,
     * but returns only the CompilationUnit.
     */
    public static CompilationUnit compileCaffcUnitsAst(
            String unit,
            TestUnit[] testUnits) {
        Program program = Program.reset();
        CaffcCompiler.reset();

        Set<CompilationUnit> compilationUnits = new LinkedHashSet<>();

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

            compilationUnits.add(compilationUnit);
        }

        for (CompilationUnit compilationUnit: compilationUnits) {
            compilationUnit.recurseResolveTypes();
        }

        for (com.germaniumhq.caffc.compiler.model.Module module: program.modules.values()) {
            Module.createInitModule(module, compilationUnits);
        }

        program.recreateConstants();

        for (CompilationUnit compilationUnit: compilationUnits) {
            LinearFormConverter.convertAstToLinearForm(compilationUnit);
        }

        CompilationUnit compilationUnit = null;

        for (CompilationUnit unit1: compilationUnits) {
            if (unit1.sourceLocation.filePath.equals(unit)) {
                compilationUnit = unit1;
                break;
            }
        }

        if (compilationUnit == null) {
            CaffcCompiler.get().fatal(SourceLocation.UNKNOWN,
                "unit not found: " + unit + " known units: " + compilationUnits);
        }

        return compilationUnit;
    }
}
