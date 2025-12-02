package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;

/**
 * Does a mathematical operation.
 */
final public class AsmMath {
    public AsmVar lValue;
    public AsmMathOperator operator;
    public AsmValue value1;
    public AsmValue value2;
}
