package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.AstItemCodeRenderer;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmInstanceOf;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeDefinitionSymbol;
import com.germaniumhq.caffc.generated.caffcParser;

public final class ExpressionInstanceOf implements Expression {
    public SourceLocation sourceLocation;
    public AstItem owner;

    public Expression checkedObject;

    private SymbolSearch instanceOfTypeSearch;
    private TypeDefinitionSymbol instanceOfType;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExInstanceOfContext instanceOfContext) {
        ExpressionInstanceOf result = new ExpressionInstanceOf();

        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, instanceOfContext);

        result.owner = owner;
        result.instanceOfTypeSearch = SymbolSearch.fromAntlr(unit, instanceOfContext.newType());
        result.checkedObject = Expression.fromAntlr(unit, result, instanceOfContext.leftExpression);

        return result;
    }

    @Override
    public Symbol typeSymbol() {
        return TypeSymbol.BOOL;
    }

    @Override
    public AstItem getOwner() {
        return owner;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    @Override
    public void recurseResolveTypes() {
        Symbol searchedType = SymbolResolver.mustResolveSymbol(this, this.instanceOfTypeSearch);

        if (!(searchedType instanceof TypeDefinitionSymbol)) {
            CaffcCompiler.get().fatal(this, "instanceof type is not a class/interface");
        }

        this.instanceOfType = (TypeDefinitionSymbol) searchedType;

        checkedObject.recurseResolveTypes();
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
//            codeRenderer.field("objectExpression", this.objectExpression);
//            codeRenderer.field("typeExpression", this.typeExpression);
        });
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        AsmVar resultValue = block.addTempVar(this, TypeSymbol.BOOL); // add a BOOL variable
        result.value = resultValue;

        AsmLinearFormResult checkedObjectLinear = this.checkedObject.asLinearForm(block);
        result.instructions.addAll(checkedObjectLinear.instructions);

        result.instructions.add(new AsmInstanceOf(
            this.sourceLocation,
            resultValue,
            checkedObjectLinear.value,
            this.instanceOfType
        ));

        return result;
    }
}
