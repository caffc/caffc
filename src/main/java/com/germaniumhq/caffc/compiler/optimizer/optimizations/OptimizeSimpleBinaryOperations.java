package com.germaniumhq.caffc.compiler.optimizer.optimizations;

import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmAssign;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmInstruction;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmMath;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;

import java.util.List;

public class OptimizeSimpleBinaryOperations extends BaseOptimization {
    @Override
    public boolean optimize(Function function) {

        List<AsmInstruction> instructions = function.instructions;
        for (int i = 0; i < instructions.size(); i++) {
            var op = instructions.get(i);
            if (!(op instanceof AsmMath asmMath)) {
                continue;
            }
            if (!(asmMath.value1 instanceof AsmConstant constant1)) {
                continue;
            }

            if (!(asmMath.value2 instanceof AsmConstant constant2)) {
                continue;
            }

            var apply = applyOpOnConstants(asmMath.operator.name(), constant1, constant2);
            if (apply == null) {
                continue;
            }
            instructions.set(i, new AsmAssign(asmMath.lValue, apply));

            System.out.println(asmMath);
        }

        return false;
    }

    AsmConstant applyOpOnConstants(String op, AsmConstant constant1, AsmConstant constant2) {
        String expressionType = constant1.type.name();
        switch (op) {
            case "MULTIPLY":
                return applyMultiply(constant1, constant2, expressionType);
            default:
                System.out.println("BUG: unsupported operation " + op);
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
