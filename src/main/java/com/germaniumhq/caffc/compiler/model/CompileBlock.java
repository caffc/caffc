package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.generated.caffcParser;

public interface CompileBlock extends AstItem {
    static CompileBlock fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.CompileBlockContext antlrCompileBlock) {
        if (antlrCompileBlock.nativeBlock() != null) {
            return NativeBlock.fromAntlr(unit, antlrCompileBlock.nativeBlock(), owner);
        }

        if (antlrCompileBlock.function() != null) {
            return Function.fromAntlr(unit, owner, antlrCompileBlock.function());
        }

        if (antlrCompileBlock.classDefinition() != null) {
            return Clazz.fromAntlr(unit, owner, antlrCompileBlock.classDefinition());
        }

        if (antlrCompileBlock.interfaceDefinition() != null) {
            return Interface.fromAntlr(unit, owner, antlrCompileBlock.interfaceDefinition());
        }

        CaffcCompiler.get().fatal(AstItem.fromAntlr(unit.astFilePath, antlrCompileBlock),
                "unsupported compile block: " + antlrCompileBlock.getText());

        return null; // not reached
    }
}
