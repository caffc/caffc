package com.germaniumhq.caffc.output.functions;

import com.germaniumhq.caffc.compiler.model.NativeBlock;
import com.germaniumhq.caffc.compiler.model.instruction.ForInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.IfInstruction;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.List;
import java.util.Map;

public class FunctionIsBlockStatement implements com.mitchellbosecke.pebble.extension.Function {
    @Override
    public Object execute(Map<String, Object> parameters, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) {
        Object statement = parameters.get("statement");

        return statement instanceof NativeBlock ||
                statement instanceof IfInstruction ||
                statement instanceof ForInstruction;
    }

    @Override
    public List<String> getArgumentNames() {
        return List.of("statement");
    }
}
