package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.*;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * An expression that identifies a constant number.
 */
public class ExpressionNumber implements Expression {
    public Symbol type;
    public String value;
    private AstItem owner;
    public String astFilePath;
    public int astColumn;
    public int astLine;

    public static ExpressionNumber fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExNumberContext numberExpression) {
        ExpressionNumber result = new ExpressionNumber();

        result.owner = owner;
        result.astFilePath = unit.astFilePath;
        result.astLine = numberExpression.getStart().getLine();
        result.astColumn = numberExpression.getStart().getCharPositionInLine();

        result.type = new TypeSymbol(TypeName.I32);
        result.value = numberExpression.getText();

        if (result.value.endsWith("_i64")) {
            String textValue = result.value.substring(0, result.value.length() - "_i64".length());
            BigInteger bigIntegerValue;

            if (textValue.startsWith("0x")) {
                bigIntegerValue = new BigInteger(textValue.substring(2), 16);
            } else if (textValue.startsWith("0b")) {
                bigIntegerValue = new BigInteger(textValue.substring(2), 2);
            } else if (textValue.startsWith("0")) {
                bigIntegerValue = new BigInteger(textValue.substring(2), 8);
            } else {
                bigIntegerValue = new BigInteger(textValue, 10);
            }

            String strNumber = bigIntegerValue.toString(16);
            if (strNumber.length() > 16) {
                CaffcCompiler.get().error(result, "i64 number too large: " + textValue);
            } else if (strNumber.length() < 16) {
                strNumber = padString(strNumber, 16);
            }

            result.value = "0x" + strNumber;
            result.type = new TypeSymbol(TypeName.U64);
        }

        return result;
    }

    public static String padString(String input, int totalLength) {
        if (input == null || totalLength <= input.length()) {
            return input; // Return input as is if totalLength is not greater than input length
        }

        StringBuilder sb = new StringBuilder();

        // Calculate the number of zeros needed
        int paddingLength = totalLength - input.length();

        // Add leading zeros
        for (int i = 0; i < paddingLength; i++) {
            sb.append('0');
        }

        // Append the original string
        sb.append(input);

        return sb.toString();
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
    }

    @Override
    public Symbol getSymbol() {
        return type;
    }
}
