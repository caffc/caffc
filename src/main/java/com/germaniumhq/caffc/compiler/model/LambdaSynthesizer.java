package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionAssign;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionDotAccess;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionId;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionNull;
import com.germaniumhq.caffc.compiler.model.expression.LocalVariable;
import com.germaniumhq.caffc.compiler.model.expression.PrimitiveBoxing;
import com.germaniumhq.caffc.compiler.model.instruction.ReturnInstruction;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.DataType;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.compiler.model.type.TypeName;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Builds a synthetic class that implements {@code fn<R>} for lambdas and decorated functions.
 *
 * <p>User parameters are unpacked at the start of {@code call(... obj[] args, Dict kw)} into
 * locals with the original names. Free variables from enclosing functions are captured as
 * readonly fields, initialized by a synthetic constructor.
 */
public final class LambdaSynthesizer {
    private static final AtomicInteger NEXT_ID = new AtomicInteger();

    private LambdaSynthesizer() {
    }

    public static final class Result {
        public final Clazz clazz;
        public final Function callFunction;
        public final SymbolSearch returnTypeSearch;
        public final boolean wasVoid;
        public final List<LambdaCapture> captures;

        Result(
                Clazz clazz,
                Function callFunction,
                SymbolSearch returnTypeSearch,
                boolean wasVoid,
                List<LambdaCapture> captures) {
            this.clazz = clazz;
            this.callFunction = callFunction;
            this.returnTypeSearch = returnTypeSearch;
            this.wasVoid = wasVoid;
            this.captures = captures;
        }
    }

    public static Result synthesize(
            CompilationUnit unit,
            SourceLocation location,
            String nameHint,
            Function bindingFunction,
            List<Parameter> userParameters,
            SymbolSearch returnTypeSearch,
            List<Statement> bodyStatements) {

        boolean wasVoid = returnTypeSearch == null || "void".equals(returnTypeSearch.name);
        SymbolSearch effectiveReturn = wasVoid ? SymbolSearch.ofName("obj") : returnTypeSearch;

        String className = uniqueClassName(nameHint);

        Clazz clazz = new Clazz();
        clazz.owner = unit;
        clazz.definition.module = unit.module;
        clazz.definition.name = className;
        clazz.definition.typeName = TypeName.of(unit.module.name, className, null, DataType.OBJECT);
        clazz.definition.sourceLocation = location;

        SymbolSearch fnSearch = SymbolSearch.ofName("fn");
        fnSearch.generics = new SymbolSearch[]{effectiveReturn};
        clazz.definition.implementedInterfacesSearch.add(fnSearch);

        List<LambdaCapture> captures = new ArrayList<>();

        Function call = new Function();
        call.owner = clazz;
        call.definition.owner = clazz.definition;
        call.definition.clazz = clazz.definition;
        call.definition.module = unit.module.name;
        call.definition.name = "call";
        call.definition.sourceLocation = location;
        call.sourceLocationCurlyOpen = location;
        call.sourceLocationCurlyClose = location;

        Parameter thisParam = new Parameter(call.definition, "_this");
        thisParam.sourceLocation = location;
        call.definition.parameters.add(thisParam);

        Parameter argsParam = new Parameter(call.definition, "args");
        argsParam.sourceLocation = location;
        argsParam.setSymbolSearch(SymbolSearch.ofName("obj[]"));
        argsParam.isVarargsSection = true;
        argsParam.isVarargsMarked = true;
        call.definition.parameters.add(argsParam);

        Parameter kwParam = new Parameter(call.definition, "kw");
        kwParam.sourceLocation = location;
        kwParam.setSymbolSearch(dictStrObjSearch());
        call.definition.parameters.add(kwParam);

        call.definition.returnTypeSearches.put("", effectiveReturn);

        // Capture rewrite needs `call` so `_this` resolves on the synthetic method.
        captures.addAll(LambdaCapture.collectAndRewrite(bindingFunction, call, bodyStatements));
        addCaptureFields(clazz, location, captures);

        if (!captures.isEmpty()) {
            Function constructor = buildConstructor(clazz, location, captures);
            clazz.functions.add(constructor);
            clazz.definition.functions.add(constructor.definition);
            constructor.stringConstantName = StringConstant.newStringConstant(location, constructor.definition.name);
            unit.module.registerConstant(constructor.stringConstantName);
        }

        List<Statement> statements = new ArrayList<>();
        statements.addAll(buildUnpackLocals(call, location, userParameters));

        for (Statement bodyStatement : bodyStatements) {
            reparent(bodyStatement, call);
            if (wasVoid) {
                rewriteVoidReturn(bodyStatement, call, location);
            }
            statements.add(bodyStatement);
        }

        if (wasVoid) {
            statements.add(buildReturnNull(call, location));
        }

        call.statements = statements;

        clazz.functions.add(call);
        clazz.definition.functions.add(call.definition);

        unit.module.clazzes.put(clazz.definition.name, clazz.definition);
        unit.module.program.register(clazz.definition.typeName, clazz.definition);
        unit.compileBlocks.add(clazz);

        call.stringConstantName = StringConstant.newStringConstant(location, call.definition.name);
        unit.module.registerConstant(call.stringConstantName);

        return new Result(clazz, call, effectiveReturn, wasVoid, captures);
    }

    /**
     * Convert a function into a lambda class (used by decorators).
     * Does not register the original function on the module.
     */
    public static Result synthesizeFromFunction(CompilationUnit unit, Function function) {
        List<Parameter> userParams = new ArrayList<>();
        for (Parameter parameter : function.definition.parameters) {
            if ("_this".equals(parameter.name)) {
                continue;
            }
            userParams.add(copyParameter(parameter));
        }

        SymbolSearch returnSearch;
        if (function.definition.returnTypeSearches == null || function.definition.returnTypeSearches.isEmpty()) {
            returnSearch = SymbolSearch.ofName("void");
        } else if (function.definition.returnTypeSearches.size() == 1) {
            returnSearch = function.definition.returnTypeSearches.values().iterator().next();
        } else {
            throw new IllegalStateException("multi-return decorated functions are not supported");
        }

        List<Statement> body = new ArrayList<>(function.statements);

        Result result = synthesize(
                unit,
                function.getSourceLocation(),
                function.definition.name,
                function,
                userParams,
                returnSearch,
                body);

        // Re-register non-unpack locals that were on the original function
        for (LocalVariable localVariable : function._variables.values()) {
            if (result.callFunction._variables.containsKey(localVariable.name)) {
                continue;
            }
            localVariable.owner = result.callFunction;
            result.callFunction.registerVariable(localVariable);
        }

        return result;
    }

    private static void addCaptureFields(Clazz clazz, SourceLocation location, List<LambdaCapture> captures) {
        for (LambdaCapture capture : captures) {
            Field field = new Field(clazz, capture.name);
            field.sourceLocation = capture.location.sourceLocation() != null
                    ? capture.location.sourceLocation()
                    : location;
            field.setTypeSearch(capture.typeSearch);
            field.isReadonly = true;
            clazz.definition.fields.add(field);
        }
    }

    private static Function buildConstructor(Clazz clazz, SourceLocation location, List<LambdaCapture> captures) {
        Function constructor = new Function();
        constructor.owner = clazz;
        constructor.definition.owner = clazz.definition;
        constructor.definition.clazz = clazz.definition;
        constructor.definition.module = clazz.definition.module.name;
        constructor.definition.name = "constructor";
        constructor.definition.sourceLocation = location;
        constructor.sourceLocationCurlyOpen = location;
        constructor.sourceLocationCurlyClose = location;
        constructor.definition.returnTypeSearches.put("", SymbolSearch.ofName("void"));

        Parameter thisParam = new Parameter(constructor.definition, "_this");
        thisParam.sourceLocation = location;
        thisParam.setSymbolSearch(SymbolSearch.ofName(clazz.definition.name));
        constructor.definition.parameters.add(thisParam);

        List<Statement> statements = new ArrayList<>();
        for (LambdaCapture capture : captures) {
            Parameter param = new Parameter(constructor.definition, capture.name);
            param.sourceLocation = capture.location.sourceLocation() != null
                    ? capture.location.sourceLocation()
                    : location;
            param.setSymbolSearch(capture.typeSearch);
            constructor.definition.parameters.add(param);

            ExpressionDotAccess fieldAccess = ExpressionDotAccess.fromParts(
                    constructor,
                    ExpressionId.fromName(null, constructor, "_this"),
                    capture.name);
            fieldAccess.sourceLocation = param.sourceLocation;

            ExpressionAssign assign = ExpressionAssign.fromCode(
                    constructor,
                    fieldAccess,
                    ExpressionId.fromName(null, constructor, capture.name));
            assign.sourceLocation = param.sourceLocation;
            statements.add(assign);
        }

        constructor.statements = statements;
        return constructor;
    }

    private static Parameter copyParameter(Parameter original) {
        Parameter copy = new Parameter(original.owner, original.name);
        copy.sourceLocation = original.sourceLocation;
        copy.setSymbolSearch(original.getSymbolSearch());
        copy.defaultExpression = original.defaultExpression;
        copy.isVarargsSection = original.isVarargsSection;
        copy.isVarargsMarked = original.isVarargsMarked;
        return copy;
    }

    private static List<Statement> buildUnpackLocals(
            Function call,
            SourceLocation location,
            List<Parameter> userParameters) {
        List<Statement> result = new ArrayList<>();
        if (userParameters == null || userParameters.isEmpty() || isRawFnSignature(userParameters)) {
            return result;
        }

        for (int i = 0; i < userParameters.size(); i++) {
            Parameter userParam = userParameters.get(i);
            LocalVariable local = new LocalVariable();
            local.name = userParam.name;
            local.owner = call;
            local.sourceLocation = location != null ? location : userParam.sourceLocation;
            local.typeSymbolSearch = userParam.getSymbolSearch() != null
                    ? userParam.getSymbolSearch()
                    : SymbolSearch.ofName("obj");

            Expression argsId = ExpressionId.fromName(null, local, "args");
            Expression unpack = PrimitiveBoxing.unpackArg(local, argsId, i, local.typeSymbolSearch);
            local.assignExpression = ExpressionAssign.fromCode(
                    local,
                    ExpressionId.fromName(null, local, local.name),
                    unpack);

            call.registerVariable(local);
            result.add(local);
        }

        return result;
    }

    private static void reparent(Statement statement, Function call) {
        // Most statements keep their owner chain via findAstParent(Function).
        // VariableDeclarations already registered on the original function; ensure
        // Expression trees can still find the synthetic Function as parent.
        if (statement instanceof LocalVariable localVariable) {
            localVariable.owner = call;
        }
        if (statement instanceof ReturnInstruction returnInstruction) {
            returnInstruction.owner = call;
            returnInstruction.function = call;
        }
    }

    private static void rewriteVoidReturn(Statement statement, Function call, SourceLocation location) {
        if (!(statement instanceof ReturnInstruction returnInstruction)) {
            return;
        }
        if (!returnInstruction.returns.isEmpty()) {
            return;
        }
        ReturnInstruction.NamedReturn named = new ReturnInstruction.NamedReturn();
        named.name = "";
        named.value = ExpressionNull.of(call, location != null ? location : returnInstruction.sourceLocation);
        returnInstruction.returns.add(named);
    }

    private static ReturnInstruction buildReturnNull(Function call, SourceLocation location) {
        ReturnInstruction ret = new ReturnInstruction();
        ret.owner = call;
        ret.function = call;
        ret.sourceLocation = location;

        ReturnInstruction.NamedReturn named = new ReturnInstruction.NamedReturn();
        named.name = "";
        named.value = ExpressionNull.of(call, location);
        ret.returns.add(named);
        return ret;
    }

    private static boolean isRawFnSignature(List<Parameter> userParameters) {
        if (userParameters.size() != 2) {
            return false;
        }
        Parameter first = userParameters.get(0);
        Parameter second = userParameters.get(1);
        return first.isExplicitVarargs()
                && first.getSymbolSearch() != null
                && "obj[]".equals(first.getSymbolSearch().name)
                && second.getSymbolSearch() != null
                && ("Dict".equals(second.getSymbolSearch().name) || "dict".equals(second.getSymbolSearch().name));
    }

    private static SymbolSearch dictStrObjSearch() {
        SymbolSearch dict = SymbolSearch.ofName("Dict");
        dict.generics = new SymbolSearch[]{
                SymbolSearch.ofName("str"),
                SymbolSearch.ofName("obj")
        };
        return dict;
    }

    private static String uniqueClassName(String hint) {
        String base = hint == null || hint.isEmpty() ? "lambda" : hint;
        base = base.replaceAll("[^A-Za-z0-9_]", "_");
        return "_Lambda_" + base + "_" + NEXT_ID.incrementAndGet();
    }
}
