package com.germaniumhq.caffc.compiler.model.asm.vars;

import com.germaniumhq.caffc.compiler.model.type.Symbol;

/**
 * Represents anything that is a value. Can be either a constant, or a variable.
 * The value itself is not an instruction.
 */
public interface AsmValue {
    /**
     * Returns the type symbol of this value. The symbol is a definition in case of
     * classes/functions.
     */
    Symbol typeSymbol();
}
