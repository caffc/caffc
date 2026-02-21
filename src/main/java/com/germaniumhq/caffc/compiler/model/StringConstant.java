package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

public class StringConstant {
    public String name;
    public String value;
    public byte[] bytes;
    public int bytesSize;

    private static MessageDigest sha256Digest;

    {
        try {
            StringConstant.sha256Digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            CaffcCompiler.get().fatal(null, "sha256 issue: " + e.getMessage());
        }
    }


    public static StringConstant newStringConstantFromAntlr(SourceLocation owner, String antlrString) {
        // removes the surrounding quotes
        String stringWithoutQuotes = antlrString.substring(1, antlrString.length() - 1);

        return newStringConstant(owner, stringWithoutQuotes);
    }

    public static StringConstant newStringConstant(SourceLocation owner, String stringWithoutQuotes) {
        StringConstant stringConstant = new StringConstant();
        String inputString = stringWithoutQuotes;

        // the actual data can only be shorter than this, after escapes
        int inIndex, outIndex;
        byte[] outputData = new byte[inputString.getBytes(StandardCharsets.UTF_8).length];

        for (outIndex = 0, inIndex = 0; inIndex < inputString.length(); inIndex++, outIndex++) {
            char c = inputString.charAt(inIndex);

            // regular character, can be unicode
            if (c != '\\') {
                byte[] byteBufferArray = String.valueOf(c).getBytes(StandardCharsets.UTF_8);

                for (int j = 0; j < byteBufferArray.length; j++) {
                    outputData[outIndex + j] = byteBufferArray[j];
                }

                outIndex += byteBufferArray.length - 1;

                continue;
            }

            // we have an escape character

            // ar we at the end of the string?
            if (inIndex == inputString.length() - 1) {
                CaffcCompiler.get().fatal(owner, "unterminated string escape");
                return null;
            }

            char nextChar = inputString.charAt(inIndex + 1);

            switch (nextChar) {
                case 'a':
                    outputData[outIndex] = 0x07;
                    inIndex += 1;
                    break;
                case 'b':
                    outputData[outIndex] = 0x08;
                    inIndex += 1;
                    break;
                case 'e':
                    outputData[outIndex] = 0x1B;
                    inIndex += 1;
                    break;
                case 'f':
                    outputData[outIndex] = 0x0C;
                    inIndex += 1;
                    break;
                case 'n':
                    outputData[outIndex] = 0x0A;
                    inIndex += 1;
                    break;
                case 'r':
                    outputData[outIndex] = 0x0D;
                    inIndex += 1;
                    break;
                case 't':
                    outputData[outIndex] = 0x09;
                    inIndex += 1;
                    break;
                case 'v':
                    outputData[outIndex] = 0x0B;
                    inIndex += 1;
                    break;
                case '\\':
                    outputData[outIndex] = 0x5C;
                    inIndex += 1;
                    break;
                case '\'':
                    outputData[outIndex] = 0x27;
                    inIndex += 1;
                    break;
                case '"':
                    outputData[outIndex] = 0x22;
                    inIndex += 1;
                    break;
                case '?':
                    outputData[outIndex] = 0x3F;
                    inIndex += 1;
                    break;
                case 'x':
                    outputData[outIndex] = parseCharFromHexNumbers(
                        owner,
                        /* firstHexChar */ inputString.charAt(inIndex + 2),
                        /* secondHexChar */ inputString.charAt(inIndex + 3));
                    inIndex += 3;
                    break;
                case '0':
                case '1':
                case '2':
                case '3': // octal, max is 377
                    // octal parsing
                    outputData[outIndex] = parseCharFromOctalNumbers(
                        owner,
                        /* firstOctalChar */ nextChar,
                        /* secondOctalChar */ inputString.charAt(inIndex + 2),
                        /* thirdOctalChar */ inputString.charAt(inIndex + 3));
                    inIndex += 3;
                    break;
                default:
                    CaffcCompiler.get().fatal(owner, "unknown string escape: \\" + nextChar);
            }
        }

        outputData = Arrays.copyOfRange(outputData, 0, outIndex);

        stringConstant.name = "caffc_cstr_" + bytesToHex(sha256Digest.digest(outputData));
        stringConstant.bytes = outputData;
        stringConstant.bytesSize = outputData.length + 1; // we add the null terminator
        stringConstant.value = stringWithoutQuotes;

        Program.get().addStringConstant(stringConstant);

        return stringConstant;
    }

    private static byte parseCharFromOctalNumbers(SourceLocation owner,
                                                  char firstOctalChar,
                                                  char secondOctalChar,
                                                  char thirdOctalChar) {
        byte result;

        result = byteFromOctal(owner, firstOctalChar);
        result <<= 3;
        result |= byteFromOctal(owner, secondOctalChar);
        result <<= 3;
        result |= byteFromOctal(owner, thirdOctalChar);

        return result;
    }

    private static byte byteFromOctal(SourceLocation owner, char octalChar) {
        byte result = 0;

        if (octalChar >= '0' && octalChar <= '7') {
            result = (byte) (octalChar - '0');
        } else {
            CaffcCompiler.get().fatal(owner, "octal number is invalid in string: " + octalChar);
        }

        return result;
    }

    private static byte parseCharFromHexNumbers(SourceLocation owner, char firstHexChar, char secondHexChar) {
        byte result;

        result = byteFromHex(owner, firstHexChar);
        result <<= 4;
        result |= byteFromHex(owner, secondHexChar);

        return result;
    }

    private static byte byteFromHex(SourceLocation owner, char firstHexChar) {
        byte result = 0;

        if (firstHexChar >= '0' && firstHexChar <= '9') {
            result = (byte) (firstHexChar - '0');
        } else if (firstHexChar >= 'a' && firstHexChar <= 'f') {
            result = (byte) (firstHexChar - 'a' + 10);
        } else if (firstHexChar >= 'A' && firstHexChar <= 'F') {
            result = (byte) (firstHexChar - 'A' + 10);
        } else {
            CaffcCompiler.get().fatal(owner, "unknown hex digit in string: \\" + firstHexChar);
        }

        return result;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StringConstant that = (StringConstant) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @UsedInTemplate("caffc/template/c/constants_c.peb")
    public String valueAsBytes() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            int unsignedByte = bytes[i] & 0xFF;
            String hexString = Integer.toHexString(unsignedByte);
            result.append("0x").append(hexString.length() == 1 ? "0" + hexString : hexString);
            result.append(", ");
        }

        result.append("0x00");

        return result.toString();
    }
}
