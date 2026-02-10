package com.germaniumhq.caffc.compiler.optimizer.optimizations;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmAssign;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmInstruction;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmMath;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmReturn;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclaration;

import java.util.HashMap;
import java.util.List;

public final class BasicBlockOptimization implements BaseOptimization {
    private final List<Class<? extends AsmInstruction>> supportedOperations = List.of(
        AsmAssign.class, AsmMath.class, AsmReturn.class);

    @Override
    public boolean optimize(Function function) {

        List<AsmInstruction> instructions = function.instructions;

        var found = false;
        var constants = new HashMap<String, AsmConstant>();

        for (int i = 0; i < instructions.size(); i++) {
            var operation = instructions.get(i);
            Class<? extends AsmInstruction> operationClass = operation.getClass();

            if (!supportedOperations.contains(operationClass)) {
                constants.clear();
                continue;
            }

            if (AsmAssign.class.equals(operationClass)) {
                var assign = (AsmAssign) operation;
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

            found |= applyConstantsToInstruction(operation, constants);

        }
        return found;
    }

    private boolean applyConstantsToInstruction(
                AsmInstruction op,
                HashMap<String, AsmConstant> constants) {
        if (constants.isEmpty()) {
            return false;
        }

        return switch (op) {
            case AsmAssign a -> applyConstantsAssign(a, constants);
            case AsmMath a -> applyConstantsToMath(a, constants);
            case AsmReturn r -> applyConstantsToReturn(r, constants);
            default -> {
                // FIXME: asm instructions should copy the source data from the AST
                CaffcCompiler.get().fatal(null, "BUG: unsupported operation " + op);
                yield false; // not reached
            }
        };
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
