package com.germaniumhq.caffc.output;

import java.util.ArrayList;
import java.util.List;

/**
 * Post-processes generated C so no physical line exceeds {@code maxWidth} columns.
 * <p>
 * Breaks prefer statement boundaries ({@code ;}), then commas, then spaces / {@code =}.
 * Preprocessor lines use {@code \} continuations when needed.
 * Leading indentation is preserved; continuation lines get two extra spaces.
 */
public final class CCodeLineWrapper {
    private CCodeLineWrapper() {
    }

    public static String wrap(String code, int maxWidth) {
        if (code == null || code.isEmpty() || maxWidth <= 0) {
            return code;
        }

        boolean endsWithNewline = code.endsWith("\n");
        String[] lines = code.split("\n", -1);
        StringBuilder out = new StringBuilder(code.length() + 64);

        int limit = endsWithNewline ? lines.length - 1 : lines.length;
        for (int i = 0; i < limit; i++) {
            if (i > 0) {
                out.append('\n');
            }
            wrapLineInto(lines[i], maxWidth, out);
        }
        if (endsWithNewline) {
            out.append('\n');
        }

        return out.toString();
    }

    private static void wrapLineInto(String line, int maxWidth, StringBuilder out) {
        if (line.length() <= maxWidth) {
            out.append(line);
            return;
        }

        String trimmedStart = line.stripLeading();
        if (trimmedStart.startsWith("#")) {
            wrapPreprocessorLine(line, maxWidth, out);
            return;
        }

        int indentLen = line.length() - trimmedStart.length();
        String indent = line.substring(0, indentLen);
        String contIndent = indent + "  ";

        List<String> statements = splitStatements(trimmedStart);
        for (int s = 0; s < statements.size(); s++) {
            if (s > 0) {
                out.append('\n');
            }
            appendWrappedStatement(statements.get(s), indent, contIndent, maxWidth, out);
        }
    }

    /**
     * Split on {@code ;} that end a statement, keeping the semicolon with the left part.
     * Does not split inside strings, chars, or comments.
     */
    static List<String> splitStatements(String line) {
        List<String> parts = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        int i = 0;
        while (i < line.length()) {
            char c = line.charAt(i);
            if (c == '"' || c == '\'') {
                int end = scanQuoted(line, i);
                current.append(line, i, end);
                i = end;
                continue;
            }
            if (c == '/' && i + 1 < line.length()) {
                char n = line.charAt(i + 1);
                if (n == '/') {
                    current.append(line.substring(i));
                    break;
                }
                if (n == '*') {
                    int end = line.indexOf("*/", i + 2);
                    if (end < 0) {
                        current.append(line.substring(i));
                        break;
                    }
                    current.append(line, i, end + 2);
                    i = end + 2;
                    continue;
                }
            }
            current.append(c);
            if (c == ';') {
                int j = i + 1;
                while (j < line.length() && Character.isWhitespace(line.charAt(j))) {
                    j++;
                }
                if (j < line.length()) {
                    parts.add(current.toString().stripTrailing());
                    current.setLength(0);
                    i = j;
                    continue;
                }
            }
            i++;
        }
        if (current.length() > 0) {
            parts.add(current.toString());
        }
        if (parts.isEmpty()) {
            parts.add(line);
        }
        return parts;
    }

    private static void appendWrappedStatement(
            String statement,
            String indent,
            String contIndent,
            int maxWidth,
            StringBuilder out) {
        if (indent.length() + statement.length() <= maxWidth) {
            out.append(indent).append(statement);
            return;
        }

        List<String> chunks = breakAtCommasAndSpaces(statement, maxWidth, indent, contIndent);
        for (int i = 0; i < chunks.size(); i++) {
            if (i > 0) {
                out.append('\n');
            }
            out.append(chunks.get(i));
        }
    }

    private static List<String> breakAtCommasAndSpaces(
            String statement,
            int maxWidth,
            String indent,
            String contIndent) {
        List<String> result = new ArrayList<>();
        String remaining = statement;
        String currentIndent = indent;
        boolean splicedContinuation = false;

        while (!remaining.isEmpty()) {
            int available = maxWidth - currentIndent.length();
            if (available < 8) {
                available = Math.max(1, maxWidth - 2);
                currentIndent = "";
            }

            if (remaining.length() <= available) {
                result.add(currentIndent + remaining);
                break;
            }

            int breakAt = findBreakPosition(remaining, available);
            if (breakAt <= 0) {
                // No soft break: splice with \ (C phase-2). Continuation must NOT
                // introduce whitespace or the spliced token is corrupted.
                int hard = Math.min(Math.max(1, available - 1), remaining.length());
                if (hard > 1 && remaining.charAt(hard - 1) == '\\') {
                    hard--;
                }
                if (hard <= 0) {
                    hard = 1;
                }
                if (hard >= remaining.length()) {
                    result.add(currentIndent + remaining);
                    break;
                }
                result.add(currentIndent + remaining.substring(0, hard) + "\\");
                remaining = remaining.substring(hard);
                splicedContinuation = true;
            } else {
                String piece = remaining.substring(0, breakAt).stripTrailing();
                result.add(currentIndent + piece);
                remaining = remaining.substring(breakAt).stripLeading();
                splicedContinuation = false;
            }
            currentIndent = splicedContinuation ? "" : contIndent;
        }

        return result;
    }

    /**
     * Largest index ≤ available that is a good break.
     * Prefers after {@code ,}, then before {@code =}, then at whitespace.
     * Returns 0 if no break found.
     */
    static int findBreakPosition(String text, int available) {
        int commaBest = 0;
        int assignBest = 0;
        int spaceBest = 0;
        boolean inString = false;
        boolean inChar = false;
        boolean inLineComment = false;
        boolean inBlockComment = false;
        boolean escape = false;

        int limit = Math.min(available, text.length());
        for (int i = 0; i < limit; i++) {
            char c = text.charAt(i);
            char next = i + 1 < text.length() ? text.charAt(i + 1) : 0;

            if (inLineComment) {
                continue;
            }
            if (inBlockComment) {
                if (c == '*' && next == '/') {
                    inBlockComment = false;
                    i++; // skip '/'
                }
                continue;
            }
            if (inString) {
                if (escape) {
                    escape = false;
                } else if (c == '\\') {
                    escape = true;
                } else if (c == '"') {
                    inString = false;
                }
                continue;
            }
            if (inChar) {
                if (escape) {
                    escape = false;
                } else if (c == '\\') {
                    escape = true;
                } else if (c == '\'') {
                    inChar = false;
                }
                continue;
            }
            if (c == '/' && next == '/') {
                inLineComment = true;
                i++;
                continue;
            }
            if (c == '/' && next == '*') {
                inBlockComment = true;
                i++;
                continue;
            }
            if (c == '"') {
                inString = true;
                continue;
            }
            if (c == '\'') {
                inChar = true;
                continue;
            }

            if (c == ',' && i + 1 <= available) {
                commaBest = i + 1;
            } else if (c == '=' && i > 0 && i <= available) {
                char prev = text.charAt(i - 1);
                if (prev != '=' && prev != '!' && prev != '<' && prev != '>' && next != '=') {
                    assignBest = i;
                }
            } else if (Character.isWhitespace(c) && i > 0 && i <= available) {
                spaceBest = i;
            }
        }

        if (commaBest > 0) {
            return commaBest;
        }
        if (assignBest > 0) {
            return assignBest;
        }
        return spaceBest;
    }

    private static int scanQuoted(String line, int start) {
        char quote = line.charAt(start);
        boolean escape = false;
        for (int i = start + 1; i < line.length(); i++) {
            char c = line.charAt(i);
            if (escape) {
                escape = false;
                continue;
            }
            if (c == '\\') {
                escape = true;
                continue;
            }
            if (c == quote) {
                return i + 1;
            }
        }
        return line.length();
    }

    private static void wrapPreprocessorLine(String line, int maxWidth, StringBuilder out) {
        if (line.length() <= maxWidth) {
            out.append(line);
            return;
        }

        int i = 0;
        while (i < line.length()) {
            int remaining = line.length() - i;
            if (remaining <= maxWidth) {
                out.append(line, i, line.length());
                return;
            }

            int take = maxWidth - 1; // leave room for '\'
            // Don't end on an odd number of trailing backslashes (would escape the continuation).
            int end = i + take;
            int bs = 0;
            for (int k = end - 1; k >= i && line.charAt(k) == '\\'; k--) {
                bs++;
            }
            if (bs % 2 == 1) {
                take--;
            }
            if (take <= 0) {
                take = 1;
            }
            out.append(line, i, i + take).append("\\\n");
            i += take;
        }
    }
}
