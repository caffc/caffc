package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;

import java.nio.charset.StandardCharsets;

/**
 * A character constant. This is actually an u32 value.
 */
public final class ExpressionChar implements Expression {
    public AstItem owner;
    public SourceLocation sourceLocation;
    public String value;
    public long charValue;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExCharContext charExpression) {
        ExpressionChar result = new ExpressionChar();

        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, charExpression);

        result.value = charExpression.getText();
        result.charValue = parseCharLiteral(result.sourceLocation, result.value);

        return result;
    }

    private static long parseCharLiteral(SourceLocation owner, String charTextIncludingQuotes) {
        if (charTextIncludingQuotes.length() < 3) {
            CaffcCompiler.get().fatal(owner, "empty character literal");
            return 0;
        }

        String inner = charTextIncludingQuotes.substring(1, charTextIncludingQuotes.length() - 1);

        if (inner.isEmpty()) {
            CaffcCompiler.get().fatal(owner, "empty character literal");
            return 0;
        }

        long result = 0;
        int inIndex = 0;

        while (inIndex < inner.length()) {
            char c = inner.charAt(inIndex);

            if (c != '\\') {
                // if we have a normal (i.e. non-escape sequence) character, we read it and we're done.
                // the tokenizer already allowed _a single_ character to exist.
                byte[] utf8Bytes = String.valueOf(c).getBytes(StandardCharsets.UTF_8);

                for (byte b : utf8Bytes) {
                    result = (result << 8) | (b & 0xFF);
                }

                return result;
            }

            if (inIndex >= inner.length() - 1) {
                CaffcCompiler.get().fatal(owner, "unterminated character escape");
                return 0;
            }

            char nextChar = inner.charAt(inIndex + 1);

            switch (nextChar) {
                case 'a':
                    checkEscapeCharLength(owner, inIndex, inner.length());
                    return 0x07;
                case 'b':
                    checkEscapeCharLength(owner, inIndex, inner.length());
                    return 0x08;
                case 'e':
                    checkEscapeCharLength(owner, inIndex, inner.length());
                    return 0x1B;
                case 'f':
                    checkEscapeCharLength(owner, inIndex, inner.length());
                    return 0x0C;
                case 'n':
                    checkEscapeCharLength(owner, inIndex, inner.length());
                    return 0x0A;
                case 'r':
                    checkEscapeCharLength(owner, inIndex, inner.length());
                    return 0x0D;
                case 't':
                    checkEscapeCharLength(owner, inIndex, inner.length());
                    return 0x09;
                case 'v':
                    checkEscapeCharLength(owner, inIndex, inner.length());
                    return 0x0B;
                case '\\':
                    checkEscapeCharLength(owner, inIndex, inner.length());
                    return 0x5C;
                case '\'':
                    checkEscapeCharLength(owner, inIndex, inner.length());
                    return 0x27;
                case '"':
                    checkEscapeCharLength(owner, inIndex, inner.length());
                    return 0x22;
                case '?':
                    checkEscapeCharLength(owner, inIndex, inner.length());
                    return 0x3F;
                case 'x':
                    checkHexOctEscapeLength(owner, inner.length());
                    long hexValue = parseHexEscape(owner, inner, inIndex);
                    result = (result << 8) | hexValue;
                    inIndex += 4;
                    break;
                case '0': case '1': case '2': case '3':
                    checkHexOctEscapeLength(owner, inner.length());
                    long octalValue = parseOctalEscape(owner, inner, inIndex);
                    result = (result << 8) | octalValue;
                    inIndex += 4;
                    break;
                default:
                    CaffcCompiler.get().fatal(owner, "unknown character escape: \\" + nextChar);
                    return 0;
            }
        }

        if (result > 0xffffffffL) {
            CaffcCompiler.get().fatal(owner, "character value too large for u32: " + result);
            return 0;
        }

        return result;
    }

    private static void checkHexOctEscapeLength(SourceLocation owner, int length) {
        if (length > 16) { // 4 bytes -> max UTF8 char sequence, every escaped byte is of size() 4 in str, i.e. '\x32'
            CaffcCompiler.get().fatal(owner, "too many characters in escape sequence");
        }
    }

    private static void checkEscapeCharLength(SourceLocation owner, int inIndex, int length) {
        if (inIndex != 0 || length != 2) { // every escaped char + the prefix slash, i.e. `\b`
            CaffcCompiler.get().fatal(owner, "too many characters in escape sequence");
        }
    }

    private static long parseHexEscape(SourceLocation owner, String text, int startIndex) {
        if (startIndex + 3 >= text.length()) {
            CaffcCompiler.get().fatal(owner, "hex escape sequence too short");
            return 0;
        }

        char hex1 = text.charAt(startIndex + 2);
        char hex2 = text.charAt(startIndex + 3);

        byte b1 = byteFromHex(owner, hex1);
        byte b2 = byteFromHex(owner, hex2);

        return ((b1 & 0xFF) << 4) | (b2 & 0xFF);
    }

    private static long parseOctalEscape(SourceLocation owner, String text, int startIndex) {
        if (startIndex + 3 >= text.length()) {
            CaffcCompiler.get().fatal(owner, "octal escape sequence too short");
            return 0;
        }

        char oct1 = text.charAt(startIndex + 1);
        char oct2 = text.charAt(startIndex + 2);
        char oct3 = text.charAt(startIndex + 3);

        byte b1 = byteFromOctal(owner, oct1);
        byte b2 = byteFromOctal(owner, oct2);
        byte b3 = byteFromOctal(owner, oct3);

        return ((b1 & 0xFF) << 6) | ((b2 & 0xFF) << 3) | (b3 & 0xFF);
    }

    private static byte byteFromHex(SourceLocation owner, char hexChar) {
        if (hexChar >= '0' && hexChar <= '9') {
            return (byte) (hexChar - '0');
        } else if (hexChar >= 'a' && hexChar <= 'f') {
            return (byte) (hexChar - 'a' + 10);
        } else if (hexChar >= 'A' && hexChar <= 'F') {
            return (byte) (hexChar - 'A' + 10);
        } else {
            CaffcCompiler.get().fatal(owner, "unknown hex digit: \\" + hexChar);
            return 0;
        }
    }

    private static byte byteFromOctal(SourceLocation owner, char octalChar) {
        if (octalChar >= '0' && octalChar <= '7') {
            return (byte) (octalChar - '0');
        } else {
            CaffcCompiler.get().fatal(owner, "invalid octal digit: " + octalChar);
            return 0;
        }
    }

    @Override
    public Symbol typeSymbol() {
        return TypeSymbol.U32;
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
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        result.value = new AsmConstant(this.typeSymbol(), String.valueOf(this.charValue));

        return result;
    }
}
