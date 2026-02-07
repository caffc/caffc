package com.germaniumhq.caffc.compiler.optimizer.optimizations;

import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmInstruction;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;

import java.util.HashMap;
import java.util.List;

public class BasicBlockOptimization extends BaseOptimization{
    @Override
    public boolean optimize(Function function) {

        List<AsmInstruction> instructions = function.instructions;

        var constants = new HashMap<String, AsmConstant>();
        for (int i = 0; i < instructions.size(); i++) {
            var op = instructions.get(i);
        }
        return false;
    }
}
