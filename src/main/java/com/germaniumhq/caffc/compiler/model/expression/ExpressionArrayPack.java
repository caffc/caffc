package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.ClassDefinition;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.FunctionDefinition;
import com.germaniumhq.caffc.compiler.model.HasMethods;
import com.germaniumhq.caffc.compiler.model.TypeSymbol;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmCall;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmNewArray;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.instruction.ExceptionHandler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeAssignability;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds a temporary array and assigns the given element expressions into it.
 * Used to materialize varargs before a call.
 */
public final class ExpressionArrayPack implements Expression {
    public AstItem owner;
    public Symbol arrayType;
    public List<Expression> elements = new ArrayList<>();
    public SourceLocation sourceLocation;

    private boolean isResolved;

    public static ExpressionArrayPack of(AstItem owner, SourceLocation location, Symbol arrayType, List<Expression> elements) {
        ExpressionArrayPack result = new ExpressionArrayPack();
        result.owner = owner;
        result.sourceLocation = location;
        result.arrayType = arrayType;
        result.elements.addAll(elements);
        return result;
    }

    @Override
    public Symbol typeSymbol() {
        return arrayType;
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
        if (isResolved) {
            return;
        }
        isResolved = true;

        Symbol elementType = null;
        if (arrayType instanceof ClassDefinition arrayClass) {
            elementType = arrayClass.childDefinition;
        }

        for (Expression element : elements) {
            element.recurseResolveTypes();
            if (elementType != null && !TypeAssignability.isExpressionAssignable(elementType, element)) {
                CaffcCompiler.get().error(element.getSourceLocation(), String.format(
                        "varargs element has type '%s' which is not assignable to array element type '%s'",
                        TypeAssignability.describe(element.typeSymbol()),
                        TypeAssignability.describe(elementType)));
            }
        }
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmLinearFormResult result = new AsmLinearFormResult();
        AsmVar arrayVar = block.addTempVar(this, arrayType);
        result.value = arrayVar;

        AsmConstant sizeConstant = new AsmConstant(TypeSymbol.I32, Integer.toString(elements.size()));
        List<AsmValue> dimensions = new ArrayList<>();
        dimensions.add(sizeConstant);
        result.instructions.add(new AsmNewArray(sourceLocation, arrayVar, arrayType, dimensions));

        if (elements.isEmpty()) {
            return result;
        }

        HasMethods arrayClass = (HasMethods) arrayType;
        FunctionDefinition setter = arrayClass.getFunction("set");
        if (setter == null) {
            CaffcCompiler.get().fatal(this, "varargs array type has no set() method: " + arrayType);
        }

        AsmLabel exceptionLabel = ExceptionHandler.resolveExceptionLabel(this, block);

        for (int i = 0; i < elements.size(); i++) {
            AsmLinearFormResult elementLinear = elements.get(i).asLinearForm(block);
            result.instructions.addAll(elementLinear.instructions);

            AsmConstant indexConstant = new AsmConstant(TypeSymbol.I32, Integer.toString(i));
            result.instructions.add(new AsmCall(
                    sourceLocation,
                    exceptionLabel,
                    setter,
                    arrayVar,
                    indexConstant,
                    elementLinear.value
            ));
        }

        return result;
    }
}
