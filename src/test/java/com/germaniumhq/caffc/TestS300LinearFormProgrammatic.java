package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionMath;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionNumber;
import com.germaniumhq.caffc.compiler.model.asm.opc.Block;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestS300LinearFormProgrammatic {
    @Test
    public void testBreakingDownExpression() {
        ExpressionMath expressionMath = new ExpressionMath();
        expressionMath.symbol = new TypeSymbol(TypeName.I32);
        expressionMath.operator = "+";

        expressionMath.left = createNumber(1);
        expressionMath.right = createNumber(2);

        AsmLinearFormResult linearForm = expressionMath.asLinearForm(new Block());

        assertNotNull(linearForm.value);
    }

    private static ExpressionNumber createNumber(int number) {
        ExpressionNumber result = new ExpressionNumber();
        result.symbol = new TypeSymbol(TypeName.I32);
        result.value = Integer.toString(number);
        result.bigIntValue = BigInteger.valueOf(number);

        return result;
    }
}
