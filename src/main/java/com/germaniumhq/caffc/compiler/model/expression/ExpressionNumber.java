package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.AstItemCodeRenderer;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

import java.math.BigInteger;

/**
 * An expression that identifies a constant number.
 */
public final class ExpressionNumber implements Expression {
    public Symbol symbol;
    public String value;
    private AstItem owner;
    public SourceLocation sourceLocation;
    public BigInteger bigIntValue;

    public static ExpressionNumber fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExNumberContext numberExpression) {
        ExpressionNumber result = new ExpressionNumber();

        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlr(unit.sourceLocation.filePath, numberExpression);

        String numberExpressionText = numberExpression.getText();

        if (numberExpressionText.contains(".") || numberExpressionText.contains("e") && !numberExpressionText.startsWith("0x")) {
            parseFloatNumber(numberExpressionText, result);
        } else {
            parseIntegerNumber(numberExpressionText, result);
        }

        return result;
    }

    private static void parseFloatNumber(String numberExpressionText, ExpressionNumber result) {
        if (numberExpressionText.endsWith("_f64")) {
            String textValue = numberExpressionText.substring(0, numberExpressionText.length() - "_f64".length());
            parseFloatNumberFromString(result, textValue, TypeName.F64);
        } else if (numberExpressionText.endsWith("_f32")) {
            String textValue = numberExpressionText.substring(0, numberExpressionText.length() - "_f32".length());
            parseFloatNumberFromString(result, textValue, TypeName.F32);
        } else {
            parseFloatNumberFromString(result, numberExpressionText, TypeName.F32);
        }
    }

    private static void parseIntegerNumber(String numberExpressionText, ExpressionNumber result) {
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
    }

    private static void parseFloatNumberFromString(ExpressionNumber result, String textValue, TypeName primitiveTypeName) {
        result.symbol = new TypeSymbol(primitiveTypeName);
        result.value = textValue;
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
    }

    @Override
    public Symbol typeSymbol() {
        return symbol;
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
            codeRenderer.field("typeSymbol", this.symbol);
            codeRenderer.field("value", this.value);
        });
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        result.value = new AsmConstant(this.typeSymbol(), value);

        return result;
    }
}
