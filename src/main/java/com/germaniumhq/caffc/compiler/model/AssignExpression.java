package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.expression.ExpressionAssign;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionOpAssign;
import com.germaniumhq.caffc.generated.caffcParser;

public class AssignExpression {
    public static Expression fromAntlr(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.AssignExpressionContext expression) {
        if (expression instanceof caffcParser.ExOpAssignContext exOpAssignContext) {
            return ExpressionOpAssign.fromAntlr(unit, owner, exOpAssignContext);
        }

        if (expression instanceof caffcParser.ExAssignContext assignContext) {
            return ExpressionAssign.fromAntlr(unit, owner, assignContext);
        }

        throw new IllegalStateException("unknown expression " + expression);
    }
}
