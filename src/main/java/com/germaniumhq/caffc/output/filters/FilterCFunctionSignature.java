package com.germaniumhq.caffc.output.filters;

import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.Parameter;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.List;
import java.util.Map;

import static com.germaniumhq.caffc.output.filters.FilterCName.getCType;

public class FilterCFunctionSignature implements Filter {
    @Override
    public Object apply(Object o, Map<String, Object> map, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) throws PebbleException {
        if (o instanceof Function function) {
            return getFunctionSignature(function.definition);
        }

        if (o instanceof FunctionDefinition functionDefinition) {
            return getFunctionSignature(functionDefinition);
        }

        throw new IllegalArgumentException("Unable to find the C global header for " + o);
    }

    private static String getFunctionSignature(FunctionDefinition functionDefinition) {
        // is value or is a pointer?
        boolean isValue = functionDefinition.returnType.typeName().dataType == DataType.PRIMITIVE ||
            functionDefinition.returnType.typeName().dataType == DataType.STRUCT;
        return String.format("%s %s(%s)",
                getCType(functionDefinition.returnType.typeName()) + (isValue ? "" : "*"),
                getCType(functionDefinition.typeName()),
                getParametersAsString(functionDefinition)
        );
    }

    public static String getParametersAsString(FunctionDefinition functionDefinition) {
        String result = "";
        String comma = "";

        for (Parameter parameter : functionDefinition.parameters) {
            result += comma + getSingleParameterAsString(parameter);
            comma = ", ";
        }

        return result;
    }

    public static String getSingleParameterAsString(Parameter parameter) {
        return getCType(parameter.typeSymbol.typeName()) +
                (parameter.typeSymbol.typeName().dataType == DataType.PRIMITIVE ? "" : "*") + " " + parameter.name();
    }

    @Override
    public List<String> getArgumentNames() {
        return List.of();
    }
}
