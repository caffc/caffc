package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.HasMethods;
import com.germaniumhq.caffc.compiler.model.StringConstant;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmCall;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.instruction.ExceptionHandler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.SymbolResolver;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeAssignability;
import com.germaniumhq.caffc.generated.caffcLexer;
import com.germaniumhq.caffc.generated.caffcParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Python-style interpolated string {@code f"user: {user}"}.
 * <p>
 * Lowers to chained {@code str.add} calls. Interpolation expressions are
 * restricted to variable names, dot access, indexes, and ranges.
 */
public final class ExpressionFString implements Expression {
    public sealed interface Part {
        record Literal(StringConstant constant) implements Part {}
        record Interpolation(Expression expression) implements Part {}
    }

    public AstItem owner;
    public List<Part> parts = new ArrayList<>();
    public SourceLocation sourceLocation;

    private FunctionDefinition addFunction;
    private Symbol strType;

    public static Expression fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.ExFStringContext fStringExpression) {
        ExpressionFString result = new ExpressionFString();

        result.owner = owner;
        result.sourceLocation = SourceLocation.fromAntlrContext(unit.sourceLocation.filePath, fStringExpression);

        String raw = fStringExpression.F_STRING().getText();
        String content = stripFStringQuotes(result.sourceLocation, raw);
        parseParts(unit, result, content);

        return result;
    }

    /**
     * Strips the leading {@code f} and surrounding quotes from an f-string token.
     */
    static String stripFStringQuotes(SourceLocation location, String raw) {
        if (raw == null || raw.length() < 3 || raw.charAt(0) != 'f') {
            CaffcCompiler.get().fatal(location, "invalid f-string token");
            return "";
        }

        String withoutPrefix = raw.substring(1);
        if (withoutPrefix.startsWith("\"\"\"") && withoutPrefix.endsWith("\"\"\"") && withoutPrefix.length() >= 6) {
            return withoutPrefix.substring(3, withoutPrefix.length() - 3);
        }
        if (withoutPrefix.startsWith("\"") && withoutPrefix.endsWith("\"") && withoutPrefix.length() >= 2) {
            return withoutPrefix.substring(1, withoutPrefix.length() - 1);
        }

        CaffcCompiler.get().fatal(location, "invalid f-string quotes");
        return "";
    }

    private static void parseParts(CompilationUnit unit, ExpressionFString result, String content) {
        StringBuilder literal = new StringBuilder();

        for (int i = 0; i < content.length(); ) {
            char c = content.charAt(i);

            if (c == '\\') {
                int escapeEnd = skipEscape(result.sourceLocation, content, i);
                literal.append(content, i, escapeEnd);
                i = escapeEnd;
                continue;
            }

            if (c == '{') {
                if (i + 1 < content.length() && content.charAt(i + 1) == '{') {
                    literal.append('{');
                    i += 2;
                    continue;
                }

                flushLiteral(unit, result, literal);

                int close = content.indexOf('}', i + 1);
                if (close < 0) {
                    CaffcCompiler.get().fatal(result, "unclosed '{' in f-string");
                    return;
                }

                String inner = content.substring(i + 1, close);
                if (inner.isBlank()) {
                    CaffcCompiler.get().fatal(result, "empty '{}' in f-string");
                    return;
                }

                Expression interpolation = parseInterpolation(unit, result, inner);
                result.parts.add(new Part.Interpolation(interpolation));
                i = close + 1;
                continue;
            }

            if (c == '}') {
                if (i + 1 < content.length() && content.charAt(i + 1) == '}') {
                    literal.append('}');
                    i += 2;
                    continue;
                }

                CaffcCompiler.get().fatal(result, "single '}' in f-string; use '}}' for a literal brace");
                return;
            }

            literal.append(c);
            i++;
        }

        flushLiteral(unit, result, literal);

        if (result.parts.isEmpty()) {
            // f"" → empty string constant
            StringConstant empty = StringConstant.newStringConstant(result.sourceLocation, "");
            unit.module.registerConstant(empty);
            result.parts.add(new Part.Literal(empty));
        }
    }

    private static void flushLiteral(CompilationUnit unit, ExpressionFString result, StringBuilder literal) {
        if (literal.isEmpty()) {
            return;
        }

        StringConstant constant = StringConstant.newStringConstant(result.sourceLocation, literal.toString());
        unit.module.registerConstant(constant);
        result.parts.add(new Part.Literal(constant));
        literal.setLength(0);
    }

    /**
     * Advances past a CaffC string escape starting at {@code index} (which points at {@code '\\'}).
     */
    private static int skipEscape(SourceLocation location, String content, int index) {
        if (index + 1 >= content.length()) {
            CaffcCompiler.get().fatal(location, "unterminated string escape in f-string");
            return content.length();
        }

        char next = content.charAt(index + 1);
        return switch (next) {
            case 'a', 'b', 'e', 'f', 'n', 'r', 't', 'v', '\\', '\'', '"', '?' -> index + 2;
            case 'x' -> {
                if (index + 3 >= content.length()) {
                    CaffcCompiler.get().fatal(location, "unterminated hex escape in f-string");
                    yield content.length();
                }
                yield index + 4;
            }
            case '0', '1', '2', '3' -> {
                if (index + 3 >= content.length()) {
                    CaffcCompiler.get().fatal(location, "unterminated octal escape in f-string");
                    yield content.length();
                }
                yield index + 4;
            }
            default -> {
                CaffcCompiler.get().fatal(location, "unknown string escape in f-string: \\" + next);
                yield index + 2;
            }
        };
    }

    private static Expression parseInterpolation(CompilationUnit unit, ExpressionFString owner, String inner) {
        caffcLexer lexer = new caffcLexer(CharStreams.fromString(inner));
        caffcParser parser = new caffcParser(new CommonTokenStream(lexer));

        parser.removeErrorListeners();
        lexer.removeErrorListeners();

        final boolean[] hadError = {false};
        BaseErrorListener errorListener = new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                                    int charPositionInLine, String msg, RecognitionException e) {
                hadError[0] = true;
                CaffcCompiler.get().error(owner,
                        "invalid f-string expression `{" + inner + "}`: " + msg
                                + " (only variable names, `.`, `[]`, and `[:]` are allowed)");
            }
        };
        parser.addErrorListener(errorListener);
        lexer.addErrorListener(errorListener);

        caffcParser.FStringInnerContext ctx = parser.fStringInner();
        if (hadError[0] || ctx.fStringPrimary() == null) {
            CaffcCompiler.get().fatal(owner,
                    "invalid f-string expression `{" + inner + "}` "
                            + "(only variable names, `.`, `[]`, and `[:]` are allowed)");
            return ExpressionId.fromName(unit, owner, "_");
        }

        return fromFStringPrimary(unit, owner, ctx.fStringPrimary());
    }

    private static Expression fromFStringPrimary(CompilationUnit unit, AstItem owner,
                                                 caffcParser.FStringPrimaryContext ctx) {
        if (ctx instanceof caffcParser.FStrIdContext idCtx) {
            return ExpressionId.fromName(unit, owner, idCtx.ID().getText());
        }

        if (ctx instanceof caffcParser.FStrDotContext dotCtx) {
            Expression left = fromFStringPrimary(unit, owner, dotCtx.fStringPrimary());
            return ExpressionDotAccess.fromParts(owner, left, dotCtx.ID().getText());
        }

        if (ctx instanceof caffcParser.FStrIndexContext indexCtx) {
            Expression left = fromFStringPrimary(unit, owner, indexCtx.fStringPrimary());
            Expression index = fromFStringIndexExpr(unit, owner, indexCtx.fStringIndexExpr());
            return ExpressionIndexAccess.fromParts(owner, left, index);
        }

        if (ctx instanceof caffcParser.FStrRangeContext rangeCtx) {
            Expression left = fromFStringPrimary(unit, owner, rangeCtx.fStringPrimary());
            Expression start = rangeCtx.fStringStart != null
                    ? fromFStringIndexExpr(unit, owner, rangeCtx.fStringStart)
                    : null;
            Expression end = rangeCtx.fStringEnd != null
                    ? fromFStringIndexExpr(unit, owner, rangeCtx.fStringEnd)
                    : null;
            return ExpressionRangeAccess.fromParts(owner, left, start, end);
        }

        CaffcCompiler.get().fatal(owner, "BUG: unsupported f-string primary: " + ctx.getClass().getSimpleName());
        return ExpressionId.fromName(unit, owner, "_");
    }

    private static Expression fromFStringIndexExpr(CompilationUnit unit, AstItem owner,
                                                   caffcParser.FStringIndexExprContext ctx) {
        if (ctx instanceof caffcParser.FStrIndexNumberContext numberCtx) {
            return ExpressionNumber.fromLiteral(owner, numberCtx.NUMBER().getText());
        }

        if (ctx instanceof caffcParser.FStrIndexPrimaryContext primaryCtx) {
            return fromFStringPrimary(unit, owner, primaryCtx.fStringPrimary());
        }

        CaffcCompiler.get().fatal(owner, "BUG: unsupported f-string index: " + ctx.getClass().getSimpleName());
        return ExpressionNumber.fromLiteral(owner, "0");
    }

    @Override
    public Symbol typeSymbol() {
        return TypeSymbol.STR;
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
        this.strType = SymbolResolver.mustResolveSymbol(this, SymbolSearch.ofName("str"));

        Symbol type = this.strType;
        while (!(type instanceof HasMethods) && type != null && type.typeSymbol() != type) {
            type = type.typeSymbol();
        }

        if (!(type instanceof HasMethods hasMethods)) {
            CaffcCompiler.get().fatal(this, "str type does not expose methods for f-string concatenation");
            return;
        }

        this.addFunction = hasMethods.getFunction("add");
        if (this.addFunction == null) {
            CaffcCompiler.get().fatal(this, "no `add` function defined for str");
            return;
        }
        this.addFunction.recurseResolveTypes();

        for (Part part : parts) {
            if (part instanceof Part.Interpolation interpolation) {
                interpolation.expression().recurseResolveTypes();
                if (!TypeAssignability.isAssignable(this.strType, interpolation.expression().typeSymbol())) {
                    CaffcCompiler.get().error(interpolation.expression(),
                            "f-string interpolation must be `str`, got "
                                    + TypeAssignability.describe(interpolation.expression().typeSymbol()));
                }
            }
        }
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();

        // Pure literal (including f"" and f"hello" / f"{{x}}") — same as a normal string constant.
        if (parts.size() == 1 && parts.get(0) instanceof Part.Literal literal) {
            result.value = new AsmConstant(TypeSymbol.STR, literal.constant().name);
            return result;
        }

        AsmValue accumulator = null;
        AsmLabel exceptionLabel = this.findAstParent(ExceptionHandler.class).getExceptionHandlingTargetLabel();

        for (Part part : parts) {
            AsmValue partValue;

            if (part instanceof Part.Literal literal) {
                partValue = new AsmConstant(TypeSymbol.STR, literal.constant().name);
            } else if (part instanceof Part.Interpolation interpolation) {
                AsmLinearFormResult partLinear = interpolation.expression().asLinearForm(block);
                result.instructions.addAll(partLinear.instructions);
                partValue = partLinear.value;
            } else {
                CaffcCompiler.get().fatal(this, "BUG: unknown f-string part");
                return result;
            }

            if (accumulator == null) {
                accumulator = partValue;
                continue;
            }

            AsmVar addResult = block.addTempVar(this, TypeSymbol.STR);
            AsmCall addCall = new AsmCall(
                    this.sourceLocation,
                    exceptionLabel,
                    this.addFunction,
                    accumulator,
                    partValue);
            addCall.result = addResult;
            result.instructions.add(addCall);
            accumulator = addResult;
        }

        result.value = accumulator;
        return result;
    }
}
