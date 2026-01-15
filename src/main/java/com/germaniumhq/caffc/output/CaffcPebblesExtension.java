package com.germaniumhq.caffc.output;

import com.germaniumhq.caffc.compiler.model.BlockVariable;
import com.germaniumhq.caffc.compiler.model.ClassDefinition;
import com.germaniumhq.caffc.compiler.model.Clazz;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.Interface;
import com.germaniumhq.caffc.compiler.model.InterfaceDefinition;
import com.germaniumhq.caffc.compiler.model.NativeBlock;
import com.germaniumhq.caffc.compiler.model.Struct;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmAssign;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBitNot;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBoolNot;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmCall;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmCast;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmIfZJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmMath;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmNew;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmReturn;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmShift;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmZeroClear;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmConstant;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmFieldVar;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionAssign;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionBitNot;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionBitOperation;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionBoolCompare;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionBoolNot;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionBoolOperation;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionCast;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionDotAccess;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionFnCall;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionId;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionIndexAccess;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionMath;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionNewArray;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionNewObject;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionNumber;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionOpAssign;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionParens;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionShift;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionString;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionTernary;
import com.germaniumhq.caffc.compiler.model.expression.ExpressionUnaryMinus;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclaration;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclarations;
import com.germaniumhq.caffc.compiler.model.instruction.ControlFlowInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.ForInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.IfInstruction;
import com.germaniumhq.caffc.compiler.model.instruction.ReturnInstruction;
import com.germaniumhq.caffc.output.filters.AsHeader;
import com.germaniumhq.caffc.output.filters.FilterCConstructorParameters;
import com.germaniumhq.caffc.output.filters.FilterCConstructorParametersCall;
import com.germaniumhq.caffc.output.filters.FilterCFunctionSignature;
import com.germaniumhq.caffc.output.filters.FilterCGlobalHeader;
import com.germaniumhq.caffc.output.filters.FilterCHeaderGuard;
import com.germaniumhq.caffc.output.filters.FilterCName;
import com.germaniumhq.caffc.output.filters.FilterCResolveId;
import com.germaniumhq.caffc.output.filters.FilterCType;
import com.germaniumhq.caffc.output.filters.FilterCTypeName;
import com.germaniumhq.caffc.output.filters.FilterSemicolon;
import com.germaniumhq.caffc.output.filters.Render;
import com.germaniumhq.caffc.output.functions.FunctionGet;
import com.germaniumhq.caffc.output.functions.FunctionIsBlockStatement;
import com.germaniumhq.caffc.output.functions.FunctionIsIndex;
import com.mitchellbosecke.pebble.attributes.AttributeResolver;
import com.mitchellbosecke.pebble.extension.Extension;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.extension.NodeVisitorFactory;
import com.mitchellbosecke.pebble.extension.Test;
import com.mitchellbosecke.pebble.operator.BinaryOperator;
import com.mitchellbosecke.pebble.operator.UnaryOperator;
import com.mitchellbosecke.pebble.tokenParser.TokenParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaffcPebblesExtension implements Extension {
    @Override
    public Map<String, Filter> getFilters() {
        Map<String, Filter> filters = new HashMap<>();

        filters.put("c_header", new AsHeader());
        filters.put("c_global_header", new FilterCGlobalHeader());
        filters.put("c_header_guard", new FilterCHeaderGuard());
        filters.put("c_function_signature", new FilterCFunctionSignature());
        filters.put("c_resolve_id", new FilterCResolveId());
        filters.put("c_name", new FilterCName());
        filters.put("c_type_name", new FilterCTypeName());
        filters.put("c_type", new FilterCType());

        filters.put("c_constructor_parameters", new FilterCConstructorParameters());
        filters.put("c_constructor_parameters_call", new FilterCConstructorParametersCall());
        filters.put("semicolon", new FilterSemicolon());

        Render renderPebbleFilter = new Render()
                // bigger containers functions/classes
                .withMapping(Function.class, "c/container/function.peb")
                .withMapping(Clazz.class, "c/container/class.peb")
                .withMapping(ClassDefinition.class, "c/container/class_definition.peb")
                .withMapping(Interface.class, "c/container/interface.peb")
                .withMapping(InterfaceDefinition.class, "c/container/interface_definition.peb")
                .withMapping(Struct.class, "c/container/struct_definition.peb")

                // asm
                .withMapping(AsmAssign.class, "c/asm/assign.peb")
                .withMapping(AsmBitNot.class, "c/asm/bit_not.peb")
                .withMapping(AsmBoolNot.class, "c/asm/bool_not.peb")
                .withMapping(AsmCast.class, "c/asm/cast.peb")
                .withMapping(AsmConstant.class, "c/asm/constant.peb")
                .withMapping(AsmFieldVar.class, "c/asm/field_var.peb")
                .withMapping(AsmIfZJmp.class, "c/asm/if_zjmp.peb")
                .withMapping(AsmNew.class, "c/asm/new.peb")
                .withMapping(AsmShift.class, "c/asm/shift.peb")
                .withMapping(AsmZeroClear.class, "c/asm/zero_clear.peb")
                .withMapping(AsmLabel.class, "c/asm/label.peb")
                .withMapping(AsmMath.class, "c/asm/math.peb")
                .withMapping(AsmCall.class, "c/asm/call.peb")
                .withMapping(AsmBlock.class, "c/asm/block.peb")
                .withMapping(AsmReturn.class, "c/asm/return.peb")

                .withMapping(VariableDeclaration.class, "c/asm/variable_declaration.peb")
                .withMapping(BlockVariable.class, "c/asm/block_variable.peb")

                // statements
                .withMapping(IfInstruction.class, "c/instruction/if.peb")
                .withMapping(ForInstruction.class, "c/instruction/for.peb")
                .withMapping(NativeBlock.class, "c/instruction/native.peb")
                .withMapping(ReturnInstruction.class, "c/instruction/return.peb")
                .withMapping(ControlFlowInstruction.class, "c/expression/control.peb")
                .withMapping(VariableDeclarations.class, "c/expression/variable_declaration.peb")

                // expressions
                // keep in sync with Expression and caffc.g4
                .withMapping(ExpressionNumber.class, "c/expression/number.peb")
                .withMapping(ExpressionString.class, "c/expression/string.peb")
                .withMapping(ExpressionId.class, "c/expression/id.peb")
                .withMapping(ExpressionParens.class, "c/expression/parens.peb")
                .withMapping(ExpressionDotAccess.class, "c/expression/dot_access.peb")
                .withMapping(ExpressionNewObject.class, "c/expression/new_object.peb")
                .withMapping(ExpressionNewArray.class, "c/expression/new_array.peb")
                .withMapping(ExpressionFnCall.class, "c/expression/fn_call.peb")
                .withMapping(ExpressionIndexAccess.class, "c/expression/index_access.peb")
                .withMapping(ExpressionCast.class, "c/expression/cast.peb")
                .withMapping(ExpressionBoolNot.class, "c/expression/bool_not.peb")
                .withMapping(ExpressionBitNot.class, "c/expression/bit_not.peb")
                .withMapping(ExpressionUnaryMinus.class, "c/expression/unary_minus.peb")
                .withMapping(ExpressionMath.class, "c/expression/math.peb") // MulMod Div AddSub
                .withMapping(ExpressionShift.class, "c/expression/shift.peb")
                .withMapping(ExpressionBoolCompare.class, "c/expression/bool_compare.peb") // LtLteGtGte EqNeq
                .withMapping(ExpressionBitOperation.class, "c/expression/bit_operation.peb") // BitAnd BitXor BitOr
                .withMapping(ExpressionBoolOperation.class, "c/expression/bool_operation.peb") // BoolAnd BoolOr
                .withMapping(ExpressionTernary.class, "c/expression/ternary.peb") // BoolAnd BoolOr
                .withMapping(ExpressionAssign.class, "c/expression/assign.peb")
                .withMapping(ExpressionOpAssign.class, "c/expression/operation_assign.peb") // BoolAnd BoolOr
                ;

        // nested render
        filters.put("render", renderPebbleFilter);

        return filters;
    }

    @Override
    public Map<String, Test> getTests() {
        return null;
    }

    @Override
    public Map<String, com.mitchellbosecke.pebble.extension.Function> getFunctions() {
        Map<String, com.mitchellbosecke.pebble.extension.Function> functions = new HashMap<>();

        functions.put("isBlockStatement", new FunctionIsBlockStatement());
        functions.put("isIndex", new FunctionIsIndex());
        functions.put("get", new FunctionGet());

        return functions;
    }

    @Override
    public List<TokenParser> getTokenParsers() {
        return null;
    }

    @Override
    public List<BinaryOperator> getBinaryOperators() {
        return null;
    }

    @Override
    public List<UnaryOperator> getUnaryOperators() {
        return null;
    }

    @Override
    public Map<String, Object> getGlobalVariables() {
        return null;
    }

    @Override
    public List<NodeVisitorFactory> getNodeVisitors() {
        return null;
    }

    @Override
    public List<AttributeResolver> getAttributeResolver() {
        return null;
    }
}
