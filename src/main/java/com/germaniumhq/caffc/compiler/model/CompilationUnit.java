package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Identifies a single *.caffc file, with all its constructs.
 */
public class CompilationUnit implements AstItem, Scope {
    public Module module;
    public List<CompileBlock> compileBlocks = new ArrayList<>();
    public Map<String, String> usedModules = new LinkedHashMap<>();

    public String astFilePath;
    public int astColumn;
    public int astLine;

    private boolean isResolved;

    public static CompilationUnit fromAntlr(ModuleProvider moduleProvider, caffcParser.CompilationUnitContext antlrCompilationUnit, String filePath) {
        CompilationUnit compilationUnit = new CompilationUnit();

        String moduleName = antlrCompilationUnit.module().fqdn().getText();
        compilationUnit.module = moduleProvider.getModule(moduleName);

        compilationUnit.astFilePath = filePath;
        compilationUnit.astLine = antlrCompilationUnit.getStart().getLine();
        compilationUnit.astColumn = antlrCompilationUnit.getStart().getCharPositionInLine();

        for (caffcParser.UseStatementContext useStatement : antlrCompilationUnit.useStatement()) {
            final String usedModule = useStatement.use().fqdn().getText();
            final String usedAlias;
            if (useStatement.use_alias() != null) {
                usedAlias = useStatement.use_alias().ID().getText();
            } else {
                usedAlias = usedModule;
            }

            compilationUnit.usedModules.put(usedAlias, usedModule);

            compilationUnit.module.usedModules.add(moduleProvider.getModule(usedModule));
        }

        for (caffcParser.CompileBlockContext antlrCompileBlock: antlrCompilationUnit.compileBlock()) {
            CompileBlock compileBlock = CompileBlock.fromAntlr(compilationUnit, compilationUnit, antlrCompileBlock);
            compilationUnit.compileBlocks.add(compileBlock);
        }

        // FIXME: reload compilation unit in the global program

        return compilationUnit;
    }

    public List<CompileBlock> getCompileBlocks() {
        return compileBlocks;
    }

    public void setCompileBlocks(List<CompileBlock> compileBlocks) {
        this.compileBlocks = compileBlocks;
    }

    @Override
    public AstItem getOwner() {
        return module;
    }

    @Override
    public Symbol resolve(String name) {
        String targetModule = usedModules.get(name);

        if (targetModule == null) {
            return null;
        }

        return this.module.program.getModule(targetModule);
    }

    public Function getFunction(String functionName) {
        for (CompileBlock compileBlock : compileBlocks) {
            if (compileBlock instanceof Function f && f.definition.name.equals(functionName)) {
                return f;
            }
        }

        return null;
    }

    @Override
    public String getFilePath() {
        return astFilePath;
    }

    @Override
    public int getLineNumber() {
        return astLine;
    }

    @Override
    public int getColumnNumber() {
        return astColumn;
    }

    @Override
    public void recurseResolveTypes() {
        if (this.isResolved) {
            return;
        }

        this.isResolved = true;

        for (CompileBlock compileBlock: this.compileBlocks) {
            compileBlock.recurseResolveTypes();
        }
    }

    @Override
    public String toString() {
        return "CompilationUnit{" +
                "file='" + astFilePath + '\'' +
                '}';
    }
}
