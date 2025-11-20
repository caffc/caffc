package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.AstItemCodeRenderer;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ReturnInstruction implements Statement {
    public AstItem owner;
    public String astFilePath;
    public List<NamedReturn> returns = new ArrayList<>();

    public int astColumn;
    public int astLine;

    public static ReturnInstruction fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ReturnContext ctx) {
        ReturnInstruction result = new ReturnInstruction();

        result.owner = owner;
        result.astFilePath = unit.astFilePath;
        result.astLine = ctx.getStart().getLine();
        result.astColumn = ctx.getStart().getCharPositionInLine();

        if (ctx.expression() != null) {
            Function function = result.getFunction();

            for (int i = 0; i < ctx.expression().size(); i++) {
                NamedReturn namedReturn = new NamedReturn();

                namedReturn.name = function.definition.getReturnName(i);
                namedReturn.value = Expression.fromAntlr(unit, result, ctx.expression(i));

                result.returns.add(namedReturn);
            }
        }

        return result;
    }

    @Override
    public AstItem getOwner() {
        return owner;
    }

    @Override
    public String getFilePath() {
        return astFilePath;
    }

    @Override
    public int getLineNumber() {
        return astLine;
    }

    @Override
    public int getColumnNumber() {
        return astColumn;
    }

    @Override
    public void recurseResolveTypes() {
        Function function = this.getFunction();

        if (this.returns.size() != function.definition.returnTypes.size()) {
            CaffcCompiler.get().error(this,
                String.format(
                    "failure processing return. function expects %d parameters, return has %d",
                    function.definition.returnTypes.size(),
                    this.returns.size()
                )
            );
        }

        for (NamedReturn namedReturn: this.returns) {
            if (namedReturn.value != null) {
                namedReturn.value.recurseResolveTypes();
            }
        }
    }

    public Function getFunction() {
        return this.findAstParent(Function.class);
    }

    public static class NamedReturn {
        public Expression value;
        public String name;
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
            List values = returns.stream().map(it -> {
                Map<String, Object> result = new HashMap<>();
                result.put("name", it.name);
                result.put("value", it.value);

                return result;
            }).toList();

            codeRenderer.field("returns", values);
        });
    }
}
