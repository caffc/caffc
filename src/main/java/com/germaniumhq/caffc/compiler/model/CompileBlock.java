package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.generated.caffcParser;

public interface CompileBlock extends AstItem {
    static CompileBlock fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.CompileBlockContext antlrCompileBlock) {
        if (antlrCompileBlock.sharpSwitchUnit() != null) {
            CaffcCompiler.get().fatal(SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, antlrCompileBlock),
                    "BUG: #switch must be expanded via SharpSwitch.expandUnit");
            return null;
        }

        if (antlrCompileBlock.sharpIfdefUnit() != null) {
            CaffcCompiler.get().fatal(SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, antlrCompileBlock),
                    "BUG: #ifdef must be expanded via SharpIfdef.expandUnit");
            return null;
        }

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

        if (antlrCompileBlock.variableDeclarations() != null) {
            return GlobalVariableDeclarations.fromAntlr(unit, unit.module, antlrCompileBlock.variableDeclarations());
        }

        CaffcCompiler.get().fatal(SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, antlrCompileBlock),
                "unsupported compile block: " + antlrCompileBlock.getText());

        return null; // not reached
    }

    static CompileBlock fromPlain(CompilationUnit unit, AstItem owner, caffcParser.CompileBlockPlainContext antlrCompileBlock) {
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

        if (antlrCompileBlock.variableDeclarations() != null) {
            return GlobalVariableDeclarations.fromAntlr(unit, unit.module, antlrCompileBlock.variableDeclarations());
        }

        CaffcCompiler.get().fatal(SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, antlrCompileBlock),
                "unsupported compile block: " + antlrCompileBlock.getText());

        return null; // not reached
    }
}
