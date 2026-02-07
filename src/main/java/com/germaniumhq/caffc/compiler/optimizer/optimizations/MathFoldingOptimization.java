package com.germaniumhq.caffc.compiler.optimizer.optimizations;

import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmAssign;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmInstruction;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmMath;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;

import java.util.List;

public class MathFoldingOptimization extends BaseOptimization {
    @Override
    public boolean optimize(Function function) {
        boolean found = false;
        List<AsmInstruction> instructions = function.instructions;
        for (int i = 0; i < instructions.size(); i++) {
            var op = instructions.get(i);
            if (!isConstantMathOp(op)) {
                continue;
            }
            AsmMath asmMath = (AsmMath) op;
            AsmConstant constant1 = (AsmConstant) asmMath.value1;
            AsmConstant constant2 = (AsmConstant) asmMath.value2;

            String operatorName = asmMath.operator.name();
            var apply = applyOpOnConstants(operatorName, constant1, constant2);
            if (apply == null) {
                continue;
            }
            instructions.set(i, new AsmAssign(asmMath.lValue, apply));

            found = true;
        }

        return found;
    }
    boolean isConstantMathOp(AsmInstruction op) {
        return op instanceof AsmMath asmMath && asmMath.value1 instanceof AsmConstant && asmMath.value2 instanceof AsmConstant;
    }

    AsmConstant applyOpOnConstants(String op, AsmConstant constant1, AsmConstant constant2) {
        String expressionType = constant1.type.name();
        switch (op) {
            case "MULTIPLY":
                return applyMultiply(constant1, constant2, expressionType);
            case "PLUS":
                return applyPlus(constant1, constant2, expressionType);
            default:
                System.out.println("BUG: unsupported operation " + op);
        }
        return null;
    }

    private AsmConstant applyPlus(AsmConstant constant1, AsmConstant constant2, String expressionType) {
        switch (expressionType) {
            case "i32":
                return new AsmConstant(constant1.type, Integer.toString(Integer.parseInt(constant1.value) + Integer.parseInt(constant2.value)));
            default:
                System.out.println("BUG: unsupported type " + expressionType);
        }
        return null;
    }

    private AsmConstant applyMultiply(AsmConstant constant1, AsmConstant constant2, String expressionType) {
        switch (expressionType) {
            case "i32":
                return new AsmConstant(constant1.type, Integer.toString(Integer.parseInt(constant1.value) * Integer.parseInt(constant2.value)));
            default:
                System.out.println("BUG: unsupported type " + expressionType);
        }
        return null;
    }
}
