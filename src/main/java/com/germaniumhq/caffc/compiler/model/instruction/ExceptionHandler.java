package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;

/**
 * Interface marking the block where the code execution handling should jump
 * in case an exception is encountered.
 */
public interface ExceptionHandler extends AstItem {
    AsmLabel getExceptionHandlingTargetLabel();
}
