package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.generated.caffcParser;

public class GlobalVariableDeclarations implements CompileBlock, AstItem {
    public GlobalVariable variable;
    public Module owner;
    public com.germaniumhq.caffc.compiler.model.source.SourceLocation sourceLocation;

    public static GlobalVariableDeclarations fromAntlr(CompilationUnit unit, Module module, caffcParser.VariableDeclarationsContext ctx) {
        if (ctx.variableDeclaration().size() != 1) {
            com.germaniumhq.caffc.compiler.error.CaffcCompiler.get().fatal(
                com.germaniumhq.caffc.compiler.model.source.SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, ctx),
                "only single variable declarations are supported at module level");
            return null;
        }

        SymbolSearch symbolSearch = SymbolSearch.fromAntlr(unit, ctx.varType());
        caffcParser.VariableDeclarationContext declarationCtx = ctx.variableDeclaration(0);

        GlobalVariable globalVar = GlobalVariable.fromAntlr(unit, module, symbolSearch, declarationCtx);

        Symbol collision = module.resolveWithAnyName(globalVar.name);
        if (collision != null) {
            if (collision instanceof GlobalVariable) {
                com.germaniumhq.caffc.compiler.error.CaffcCompiler.get().fatal(globalVar,
                    "global variable " + globalVar.name + " is already declared");
            } else {
                com.germaniumhq.caffc.compiler.error.CaffcCompiler.get().fatal(globalVar,
                    "global variable " + globalVar.name + " shadows " +
                        com.germaniumhq.caffc.compiler.model.type.Symbol.typeOfSymbol(collision) + " " + collision.name() +
                        " defined at " + com.germaniumhq.caffc.compiler.error.CaffcCompiler.fileLocation(collision));
            }
        }

        module.registerGlobalVariable(globalVar);

        GlobalVariableDeclarations result = new GlobalVariableDeclarations();
        result.variable = globalVar;
        result.owner = module;
        result.sourceLocation = globalVar.sourceLocation;

        return result;
    }

    @Override
    public AstItem getOwner() {
        return owner;
    }

    @Override
    public com.germaniumhq.caffc.compiler.model.source.SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    @Override
    public void recurseResolveTypes() {
        variable.recurseResolveTypes();
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
            codeRenderer.field("variable", this.variable);
        });
    }
}
