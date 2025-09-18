package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.*;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

import java.math.BigInteger;

/**
 * An expression that identifies a constant number.
 */
public class ExpressionNumber implements Expression {
    public Symbol symbol;
    public String value;
    private AstItem owner;
    public String astFilePath;
    public int astColumn;
    public int astLine;
    public BigInteger bigIntValue;

    public static ExpressionNumber fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExNumberContext numberExpression) {
        ExpressionNumber result = new ExpressionNumber();

        result.owner = owner;
        result.astFilePath = unit.astFilePath;
        result.astLine = numberExpression.getStart().getLine();
        result.astColumn = numberExpression.getStart().getCharPositionInLine();

        String numberExpressionText = numberExpression.getText();

        if (numberExpressionText.endsWith("_i64")) {
            String textValue = numberExpressionText.substring(0, numberExpressionText.length() - "_i64".length());
            parseIntegerNumberFromString(result, textValue, TypeName.I64);
        } else if (numberExpressionText.endsWith("_u64")) {
            String textValue = numberExpressionText.substring(0, numberExpressionText.length() - "_u64".length());
            parseIntegerNumberFromString(result, textValue, TypeName.U64);
        } else if (numberExpressionText.endsWith("_i32")) {
            String textValue = numberExpressionText.substring(0, numberExpressionText.length() - "_i32".length());
            parseIntegerNumberFromString(result, textValue, TypeName.I32);
        } else if (numberExpressionText.endsWith("_u32")) {
            String textValue = numberExpressionText.substring(0, numberExpressionText.length() - "_u32".length());
            parseIntegerNumberFromString(result, textValue, TypeName.U32);
        } else if (numberExpressionText.endsWith("_i16")) {
            String textValue = numberExpressionText.substring(0, numberExpressionText.length() - "_i16".length());
            parseIntegerNumberFromString(result, textValue, TypeName.I16);
        } else if (numberExpressionText.endsWith("_u16")) {
            String textValue = numberExpressionText.substring(0, numberExpressionText.length() - "_u16".length());
            parseIntegerNumberFromString(result, textValue, TypeName.U16);
        } else if (numberExpressionText.endsWith("_i8")) {
            String textValue = numberExpressionText.substring(0, numberExpressionText.length() - "_i8".length());
            parseIntegerNumberFromString(result, textValue, TypeName.I8);
        } else if (numberExpressionText.endsWith("_u8")) {
            String textValue = numberExpressionText.substring(0, numberExpressionText.length() - "_u8".length());
            parseIntegerNumberFromString(result, textValue, TypeName.U8);
        } else {
            parseIntegerNumberFromString(result, numberExpressionText, TypeName.I32);
        }

        return result;
    }

    private static void parseIntegerNumberFromString(ExpressionNumber result, String textValue, TypeName primitiveTypeName) {
        result.symbol = new TypeSymbol(primitiveTypeName);

        BigInteger bigIntegerValue;

        if (textValue.startsWith("0x")) {
            bigIntegerValue = new BigInteger(textValue.substring(2), 16);
        } else if (textValue.startsWith("0b")) {
            bigIntegerValue = new BigInteger(textValue.substring(2), 2);
        } else if (textValue.startsWith("0") && textValue.length() > 1) {
            bigIntegerValue = new BigInteger(textValue.substring(1), 8);
        } else {
            bigIntegerValue = new BigInteger(textValue, 10);
        }

        BigInteger maximumValue = getMaximumValue(result, primitiveTypeName);
        if (bigIntegerValue.compareTo(maximumValue) > 0) {
            CaffcCompiler.get().error(result, String.format("%s number too large: %s is greater than %s",
                    primitiveTypeName.name,
                    bigIntegerValue,
                    maximumValue
            ));
            return;
        }

        BigInteger minimumValue = getMinimumValue(result, primitiveTypeName);
        if (bigIntegerValue.compareTo(minimumValue) < 0) {
            CaffcCompiler.get().error(result, String.format("%s number too small: %s is smaller than %s",
                    primitiveTypeName.name,
                    bigIntegerValue,
                    minimumValue
            ));
            return;
        }

        result.bigIntValue = bigIntegerValue;
        result.value = textValue;
    }

    private static int getHexLength(ExpressionNumber result, TypeName primitiveTypeName) {
        if (primitiveTypeName.name.equals(TypeName.I8.name)) {
            return 2;
        } else if (primitiveTypeName.name.equals(TypeName.U8.name)) {
            return 2;
        } else if (primitiveTypeName.name.equals(TypeName.I16.name)) {
            return 4;
        } else if (primitiveTypeName.name.equals(TypeName.U16.name)) {
            return 4;
        } else if (primitiveTypeName.name.equals(TypeName.I32.name)) {
            return 8;
        } else if (primitiveTypeName.name.equals(TypeName.U32.name)) {
            return 8;
        } else if (primitiveTypeName.name.equals(TypeName.I64.name)) {
            return 16;
        } else if (primitiveTypeName.name.equals(TypeName.U64.name)) {
            return 16;
        }

        CaffcCompiler.get().fatal(result, "expression is not an integer primitive type: " + primitiveTypeName);
        return 0; // not reached
    }

    private static BigInteger getMinimumValue(ExpressionNumber result, TypeName primitiveTypeName) {
        if (primitiveTypeName.name.equals(TypeName.I8.name)) {
            return new BigInteger("-128");
        } else if (primitiveTypeName.name.equals(TypeName.U8.name)) {
            return BigInteger.ZERO;
        } else if (primitiveTypeName.name.equals(TypeName.I16.name)) {
            return new BigInteger("-32768");
        } else if (primitiveTypeName.name.equals(TypeName.U16.name)) {
            return BigInteger.ZERO;
        } else if (primitiveTypeName.name.equals(TypeName.I32.name)) {
            return new BigInteger("-2147483648");
        } else if (primitiveTypeName.name.equals(TypeName.U32.name)) {
            return BigInteger.ZERO;
        } else if (primitiveTypeName.name.equals(TypeName.I64.name)) {
            return new BigInteger("-9223372036854775808");
        } else if (primitiveTypeName.name.equals(TypeName.U64.name)) {
            return BigInteger.ZERO;
        }

        CaffcCompiler.get().fatal(result, "expression is not an integer primitive type: " + primitiveTypeName);
        return BigInteger.ZERO; // not reached
    }

    private static BigInteger getMaximumValue(ExpressionNumber result, TypeName primitiveTypeName) {
        if (primitiveTypeName.name.equals(TypeName.I8.name)) {
            return new BigInteger("127");
        } else if (primitiveTypeName.name.equals(TypeName.U8.name)) {
            return new BigInteger("255");
        } else if (primitiveTypeName.name.equals(TypeName.I16.name)) {
            return new BigInteger("32767");
        } else if (primitiveTypeName.name.equals(TypeName.U16.name)) {
            return new BigInteger("65535");
        } else if (primitiveTypeName.name.equals(TypeName.I32.name)) {
            return new BigInteger("2147483647");
        } else if (primitiveTypeName.name.equals(TypeName.U32.name)) {
            return new BigInteger("4294967295");
        } else if (primitiveTypeName.name.equals(TypeName.I64.name)) {
            return new BigInteger("9223372036854775807");
        } else if (primitiveTypeName.name.equals(TypeName.U64.name)) {
            return new BigInteger("18446744073709551615");
        }

        CaffcCompiler.get().fatal(result, "expression is not an integer primitive type: " + primitiveTypeName);
        return BigInteger.ZERO; // not reached
    }

    public static String padAs0xString(String input, int totalLength) {
        boolean hasMinus = input.startsWith("-");
        StringBuilder sb = new StringBuilder();
        int paddingLength;

        if (hasMinus) {
            input = input.substring(1);
            sb.append("-0x");
        } else {
            sb.append("0x");
        }

        // Add leading zeros
        paddingLength = totalLength - input.length();

        for (int i = 0; i < paddingLength; i++) {
            sb.append('0');
        }

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
        return symbol;
    }
}
