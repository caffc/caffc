package com.germaniumhq.caffc.compiler.optimizer.optimizations;

import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmAssign;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmInstruction;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmMath;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmReturn;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclaration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BasicBlockOptimization extends BaseOptimization {
    List<String> supportedOperations = List.of("AsmAssign", "AsmMath", "AsmReturn");

    @Override
    public boolean optimize(Function function) {

        List<AsmInstruction> instructions = function.instructions;

        var found = false;
        var constants = new HashMap<String, AsmConstant>();
        for (int i = 0; i < instructions.size(); i++) {
            var op = instructions.get(i);
            var opName = op.getClass().getSimpleName();
            if (!supportedOperations.contains(opName)) {
                constants.clear();
                continue;
            }

            if (opName.equals("AsmAssign")) {
                var assign = (AsmAssign) op;
                if (!(assign.left instanceof VariableDeclaration variableDeclaration)) {
                    constants.clear();
                    //TODO: BUG?
                    continue;
                }

                var right = assign.right;
                if (right instanceof AsmConstant asmConstant) {
                    constants.put(variableDeclaration.name, asmConstant);
                    continue;
                } else {
                    constants.remove(variableDeclaration.name);
                }

            }

            found |= applyConstantsToInstruction(op, opName, constants);

        }
        return found;
    }

    private boolean applyConstantsToInstruction(AsmInstruction op, String opName, HashMap<String, AsmConstant> constants) {
        if (constants.isEmpty()) {
            return false;
        }
        switch (opName) {
            case "AsmAssign":
                return applyConstantsAssign((AsmAssign) op, constants);
            case "AsmMath":
                return applyConstantsToMath((AsmMath) op, constants);
            case "AsmReturn":
                return applyConstantsToReturn((AsmReturn) op, constants);
            default:
                System.out.println("BUG: unsupported operation " + opName);
                return false;
        }
    }

    private boolean applyConstantsAssign(AsmAssign op, HashMap<String, AsmConstant> constants) {
        String whatName = varName(op.right);
        if (whatName != null && constants.containsKey(whatName)) {
            op.right = constants.get(whatName);
            return true;
        }
        return false;
    }

    private boolean applyConstantsToReturn(AsmReturn op, HashMap<String, AsmConstant> constants) {
        String whatName = varName(op.what);
        if (whatName != null && constants.containsKey(whatName)) {
            op.what = constants.get(whatName);
            return true;
        }
        return false;
    }


    private boolean applyConstantsToMath(AsmMath op, HashMap<String, AsmConstant> constants) {
        boolean changed = false;
        String value1Name = varName(op.value1);
        if (value1Name != null && constants.containsKey(value1Name)) {
            op.value1 = constants.get(value1Name);
            changed = true;
        }
        String value2Name = varName(op.value2);
        if (value2Name != null && constants.containsKey(value2Name)) {
            op.value2 = constants.get(value2Name);
            changed = true;
        }
        return changed;
    }

    String varName(AsmValue value) {
        if (value instanceof VariableDeclaration variableDeclaration) {
            return variableDeclaration.name;
        }
        return null;
    }
}
