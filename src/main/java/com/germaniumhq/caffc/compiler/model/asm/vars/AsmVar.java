package com.germaniumhq.caffc.compiler.model.asm.vars;

import com.germaniumhq.caffc.compiler.model.source.HasSourceLocation;

/**
 * Represents a reference to a variable. The variable is either
 * function local, class local, or global.
 */
public interface AsmVar extends AsmValue, HasSourceLocation {
}
