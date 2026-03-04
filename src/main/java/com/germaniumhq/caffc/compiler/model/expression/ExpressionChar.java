package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.generated.caffcParser;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;
import com.germaniumhq.caffc.generated.caffcParser;

import java.nio.charset.StandardCharsets;

public final class ExpressionChar implements Expression {
    public AstItem owner;
    public SourceLocation sourceLocation;
    public TypeSymbol symbol;
    public String value;
    public long charValue;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExCharContext charExpression) {
        ExpressionChar result = new ExpressionChar();

        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, charExpression);
        result.symbol = new TypeSymbol(TypeName.U32);

        String charText = charExpression.getText();
        result.value = charText;

        result.charValue = parseCharLiteral(result.sourceLocation, charText);

        return result;
    }

    private static long parseCharLiteral(SourceLocation owner, String charText) {
        if (charText.length() < 3) {
            CaffcCompiler.get().fatal(owner, "empty character literal");
            return 0;
        }

        String inner = charText.substring(1, charText.length() - 1);

        if (inner.isEmpty()) {
            CaffcCompiler.get().fatal(owner, "empty character literal");
            return 0;
        }

        // Validate that the character literal represents exactly one logical character.
        // A single unescaped character like 'a' is valid.
        // An escape sequence like '\n' or '\x32' is one logical character.
        // Multiple characters like 'aa' or 'hello' are invalid.
        if (inner.indexOf('\\') == -1) {
            // No escape sequences - check if it's a single character
            int charCount = inner.length();
            if (charCount > 1) {
                CaffcCompiler.get().fatal(owner, "character literal contains multiple characters: '" + charText + "'");
                return 0;
            }
        } else {
            // Has escape sequences - we need to count logical characters
            // An escape sequence like \x32 or \n is ONE logical character
            // A non-escape character like 'a' is ONE logical character
            // We need to ensure we have exactly one logical character total
            
            int logicalCharCount = 0;
            int inIndex = 0;
            
            while (inIndex < inner.length()) {
                if (inner.charAt(inIndex) == '\\') {
                    // This is an escape sequence - counts as one logical character
                    if (inIndex >= inner.length() - 1) {
                        CaffcCompiler.get().fatal(owner, "unterminated character escape");
                        return 0;
                    }
                    
                    char nextChar = inner.charAt(inIndex + 1);
                    // Validate escape sequence
                    switch (nextChar) {
                        case 'a': case 'b': case 'e': case 'f': case 'n': 
                        case 'r': case 't': case 'v': case '\\': case '\'': 
                        case '"': case '?':
                            // Simple escape - valid, one logical character
                            logicalCharCount++;
                            inIndex += 2;
                            break;
                        case 'x':
                            // Hex escape - need 2 hex digits after \x
                            if (inIndex + 4 > inner.length()) {
                                CaffcCompiler.get().fatal(owner, "hex escape sequence too short");
                                return 0;
                            }
                            logicalCharCount++;
                            inIndex += 4;
                            break;
                        case '0': case '1': case '2': case '3':
                            // Octal escape - need 3 octal digits after the initial \
                            if (inIndex + 4 > inner.length()) {
                                CaffcCompiler.get().fatal(owner, "octal escape sequence too short");
                                return 0;
                            }
                            logicalCharCount++;
                            inIndex += 4;
                            break;
                        default:
                            CaffcCompiler.get().fatal(owner, "unknown character escape: \\" + nextChar);
                            return 0;
                    }
                } else {
                    // Non-escape character - counts as one logical character
                    logicalCharCount++;
                    inIndex++;
                }
                
                // If we already have more than one logical character, fail
                if (logicalCharCount > 1) {
                    CaffcCompiler.get().fatal(owner, "character literal contains multiple characters: '" + charText + "'");
                    return 0;
                }
            }
            
            // We should have exactly one logical character
            if (logicalCharCount == 0) {
                CaffcCompiler.get().fatal(owner, "empty character literal");
                return 0;
            }
        }

        long result = 0;
        int inIndex = 0;

        while (inIndex < inner.length()) {
            char c = inner.charAt(inIndex);

            if (c != '\\') {
                byte[] utf8Bytes = String.valueOf(c).getBytes(StandardCharsets.UTF_8);

                for (byte b : utf8Bytes) {
                    result = (result << 8) | (b & 0xFF);
                }

                inIndex++;
            } else {
                if (inIndex >= inner.length() - 1) {
                    CaffcCompiler.get().fatal(owner, "unterminated character escape");
                    return 0;
                }

                char nextChar = inner.charAt(inIndex + 1);

                switch (nextChar) {
                    case 'a':
                        result = 0x07;
                        inIndex += 2;
                        break;
                    case 'b':
                        result = 0x08;
                        inIndex += 2;
                        break;
                    case 'e':
                        result = 0x1B;
                        inIndex += 2;
                        break;
                    case 'f':
                        result = 0x0C;
                        inIndex += 2;
                        break;
                    case 'n':
                        result = 0x0A;
                        inIndex += 2;
                        break;
                    case 'r':
                        result = 0x0D;
                        inIndex += 2;
                        break;
                    case 't':
                        result = 0x09;
                        inIndex += 2;
                        break;
                    case 'v':
                        result = 0x0B;
                        inIndex += 2;
                        break;
                    case '\\':
                        result = 0x5C;
                        inIndex += 2;
                        break;
                    case '\'':
                        result = 0x27;
                        inIndex += 2;
                        break;
                    case '"':
                        result = 0x22;
                        inIndex += 2;
                        break;
                    case '?':
                        result = 0x3F;
                        inIndex += 2;
                        break;
                    case 'x':
                        result = parseHexEscape(owner, inner, inIndex);
                        inIndex += 4;
                        break;
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                        result = parseOctalEscape(owner, inner, inIndex);
                        inIndex += 4;
                        break;
                    default:
                        CaffcCompiler.get().fatal(owner, "unknown character escape: \\" + nextChar);
                        return 0;
                }
            }
        }

        if (result > 0x7FFFffffL) {
            CaffcCompiler.get().fatal(owner, "character value too large for u32: " + result);
            return 0;
        }

        return result;
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
        return symbol;
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
