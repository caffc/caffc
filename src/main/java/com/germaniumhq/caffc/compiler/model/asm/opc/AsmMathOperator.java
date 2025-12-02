package com.germaniumhq.caffc.compiler.model.asm.opc;

public enum AsmMathOperator {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    ;

    public final String op;

    AsmMathOperator(String op) {
        this.op = op;
    }
}
