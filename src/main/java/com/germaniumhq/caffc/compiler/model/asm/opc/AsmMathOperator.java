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

    public static AsmMathOperator fromString(String operator) {
        if (operator == null) {
            throw new IllegalArgumentException("Null operator");
        }

        switch (operator) {
            case "+": return PLUS;
            case "-": return MINUS;
            case "*": return MULTIPLY;
            case "/": return DIVIDE;
            default: throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}
