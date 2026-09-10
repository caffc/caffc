package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;

/**
 * Interface marking the block where the code execution handling should jump
 * in case an exception is encountered.
 */
public interface ExceptionHandler extends AstItem {
    AsmLabel getExceptionHandlingTargetLabel();

    /**
     * Prefer the AST owner chain (so try/catch wins over the enclosing function),
     * then fall back to the linear {@link AsmBlock} parent (needed when expressions
     * are not reparented under the function, e.g. decorator call trees).
     */
    static AsmLabel resolveExceptionLabel(AstItem expression, AsmBlock block) {
        ExceptionHandler handler = expression.findAstParent(ExceptionHandler.class);
        if (handler == null) {
            handler = block.findAstParent(ExceptionHandler.class);
        }
        if (handler == null) {
            CaffcCompiler.get().fatal(expression, "BUG: exception handler not found?");
        }
        return handler.getExceptionHandlingTargetLabel();
    }
}
