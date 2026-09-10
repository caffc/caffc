package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Field;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;

/**
 * Shared checks for assignments to readonly fields (lambda captures).
 */
final class ReadonlyAssignCheck {
    private ReadonlyAssignCheck() {
    }

    static void check(AstItem location, Expression left) {
        if (!(left instanceof ExpressionDotAccess dotAccess)) {
            return;
        }

        Symbol leftType = dotAccess.leftOfDot.typeSymbol();
        if (leftType == null) {
            return;
        }

        while (!(leftType instanceof Scope) && leftType != null && leftType.typeSymbol() != leftType) {
            leftType = leftType.typeSymbol();
        }

        if (!(leftType instanceof Scope scope)) {
            return;
        }

        Symbol property = scope.resolve(dotAccess.rightOfDot);
        if (!(property instanceof Field field) || !field.isReadonly) {
            return;
        }

        Function function = location.findAstParent(Function.class);
        if (function != null && "constructor".equals(function.definition.name)) {
            return;
        }

        CaffcCompiler.get().error(location, String.format(
                "cannot assign to captured variable '%s' (captures are readonly)",
                field.name));
    }
}
