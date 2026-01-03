package com.germaniumhq.caffc.compiler.model.asm.vars;

import com.germaniumhq.caffc.compiler.model.type.Symbol;

/**
 * Holds a local variable defined in a block.
 *
 * In a fully optimized program all these should be removed (along with
 * the blocks they belong to)
 */
final public class AsmBlockVar {
    public Symbol type;
    public String name;
}
