package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.AstItemCodeRenderer;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmAssign;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmInstruction;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmReturn;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmFieldVar;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ReturnInstruction implements Statement {
    public AstItem owner;
    public String astFilePath;
    public List<NamedReturn> returns = new ArrayList<>();

    public int astColumn;
    public int astLine;
    public Function function;

    public static ReturnInstruction fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ReturnContext ctx) {
        ReturnInstruction result = new ReturnInstruction();

        result.owner = owner;
        result.astFilePath = unit.astFilePath;
        result.astLine = ctx.getStart().getLine();
        result.astColumn = ctx.getStart().getCharPositionInLine();

        result.function = AstItem.findParentOrSelf(owner, Function.class);

        if (ctx.expression() != null) {
            for (int i = 0; i < ctx.expression().size(); i++) {
                NamedReturn namedReturn = new NamedReturn();

                namedReturn.name = result.function.definition.getReturnName(i);
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

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        if (this.getFunction().definition.isReturnEmpty()) {
            return new AsmLinearFormResult(List.of(new AsmReturn(this.function, null)));
        }

        if (this.getFunction().definition.isMultiReturn()) {
            List<AsmInstruction> instructions = new ArrayList<>();
            AsmVar structVar = block.addTempVar(this.getFunction(), this.getFunction().definition.returnType);

            for (NamedReturn namedReturn: this.returns) {
                AsmLinearFormResult namedReturnLinearForm = namedReturn.value.asLinearForm(block);
                instructions.addAll(namedReturnLinearForm.instructions);
                AsmFieldVar structFieldVar = new AsmFieldVar(structVar, namedReturn.value.typeSymbol(), namedReturn.name);
                instructions.add(new AsmAssign(structFieldVar, namedReturnLinearForm.value));
            }

            instructions.add(new AsmReturn(this.function, structVar));

            return new AsmLinearFormResult(instructions);
        }

        List<AsmInstruction> instructions = new ArrayList<>();
        NamedReturn ret = this.returns.get(0);

        AsmLinearFormResult linearForm = ret.value.asLinearForm(block);
        instructions.addAll(linearForm.instructions);
        instructions.add(new AsmReturn(this.function, linearForm.value));

        return new AsmLinearFormResult(instructions);
    }

    public static class NamedReturn {
        public String name;
        public Expression value;
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
