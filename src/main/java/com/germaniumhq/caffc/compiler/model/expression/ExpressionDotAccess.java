package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Field;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmFieldVar;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

public final class ExpressionDotAccess implements Expression {
    public AstItem owner;
    public Expression leftOfDot;
    public String rightOfDot;

    public String astFilePath;
    public int astColumn;
    public int astLine;

    public Symbol symbol;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExDotAccessContext exDotAccessContext) {
        ExpressionDotAccess expression = new ExpressionDotAccess();

        expression.astFilePath = unit.astFilePath;
        expression.astLine = exDotAccessContext.getStart().getLine();
        expression.astColumn = exDotAccessContext.getStart().getCharPositionInLine();

        expression.owner = owner;
        expression.leftOfDot = Expression.fromAntlr(unit, expression, exDotAccessContext.expression());
        expression.rightOfDot = exDotAccessContext.ID().getText();

        return expression;
    }

    @Override
    public Symbol typeSymbol() {
        return symbol;
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
        this.leftOfDot.recurseResolveTypes();

        Symbol leftOfDotType = this.leftOfDot.typeSymbol();

        if (!(leftOfDotType instanceof Scope)) {
            List<Symbol> resolvedPath = new ArrayList<>();

            while (!(leftOfDotType instanceof Scope)) {
                resolvedPath.add(leftOfDotType);
                leftOfDotType = leftOfDotType.typeSymbol();

                if (leftOfDotType instanceof Scope) {
                    break;
                }

                if (leftOfDotType.typeSymbol() == leftOfDotType) {
                    CaffcCompiler.get().fatal(this, "left of dot expression is not a scope: " + resolvedPath);
                }
            }
        }

        this.symbol = ((Scope) leftOfDotType).resolve(this.rightOfDot);

        if (this.symbol == null) {
            CaffcCompiler.get().error(this.leftOfDot,
                    "No such property: `" + this.rightOfDot + "` on " + leftOfDotType.typeName().fqdn() + ".");
        }
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        AsmLinearFormResult leftOfDotLinear = this.leftOfDot.asLinearForm(block);
        result.instructions.addAll(leftOfDotLinear.instructions);

        Scope scope = (Scope) leftOfDotLinear.value.typeSymbol();
        Symbol fieldType = scope.resolve(this.rightOfDot).typeSymbol();

        result.value = new AsmFieldVar(
            (AsmVar) leftOfDotLinear.value,
            fieldType,
            rightOfDot);

        return result;
    }
}
