package com.germaniumhq.caffc.output.functions;

import com.germaniumhq.caffc.compiler.model.NativeBlock;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionIndexAccess;
import com.germaniumhq.caffc.compiler.model.instruction.ForInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.IfInstruction;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.List;
import java.util.Map;

public class FunctionIsIndex implements com.mitchellbosecke.pebble.extension.Function {
    @Override
    public Object execute(Map<String, Object> parameters, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) {
        Object expression = parameters.values().iterator().next();

        return expression instanceof ExpressionIndexAccess;
    }

    @Override
    public List<String> getArgumentNames() {
        return List.of("statement");
    }
}
