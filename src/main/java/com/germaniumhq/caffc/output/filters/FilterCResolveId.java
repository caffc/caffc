package com.germaniumhq.caffc.output.filters;

import com.germaniumhq.caffc.compiler.model.ClassDefinition;
import com.germaniumhq.caffc.compiler.model.Field;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.Parameter;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionId;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclaration;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.List;
import java.util.Map;

/**
 * A filter called to resolve ids.
 */
public class FilterCResolveId implements Filter {
    @Override
    public Object apply(Object o, Map<String, Object> map, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) throws PebbleException {
        if (!(o instanceof ExpressionId expressionId)) {
            throw new IllegalArgumentException("Filter can only be used with ids");
        }

        String name = expressionId.name;

        Symbol symbol = SymbolResolver.mustResolveSymbol(expressionId, SymbolSearch.ofName(name));
        return resolveCSymbolName(symbol);
    }

    private String resolveCSymbolName(Symbol symbol) {
        if (symbol instanceof FunctionDefinition functionDefinition) {
            if (functionDefinition.clazz != null) {
                return functionDefinition.module + "_" + functionDefinition.clazz.name() + "_" + functionDefinition.name;
            }
            return functionDefinition.module + "_" + functionDefinition.name;
        }

        if (symbol instanceof ClassDefinition classDefinition) {
            return classDefinition.module.name + "_"
                    + classDefinition.name;
        }

        if (symbol instanceof Parameter parameter) {
            return parameter.name;
        }

        if (symbol instanceof VariableDeclaration variableDeclaration) {
            return variableDeclaration.name;
        }

        if (symbol instanceof Field field) {
            return "_this->" + field.name;
        }

        throw new IllegalArgumentException("unsupported symbol " + symbol);
    }

    @Override
    public List<String> getArgumentNames() {
        return List.of();
    }
}
