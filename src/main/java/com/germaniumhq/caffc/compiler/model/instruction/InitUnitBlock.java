package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AsmLinearFormResult;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.AstItemCodeRenderer;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.expression.LocalVariable;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Wrapper for a {@link com.germaniumhq.caffc.compiler.model.Module#UNIT_INIT}
 * body that was transplanted into
 * {@link com.germaniumhq.caffc.compiler.model.Module#MODULE_INIT}. Linear form
 * conversion emits a dedicated {@link AsmBlock} so the unit's locals live on
 * that block.
 */
public final class InitUnitBlock implements Statement {
    public AstItem owner;
    public SourceLocation sourceLocation;
    public List<Statement> statements = new ArrayList<>();
    public Map<String, LocalVariable> variables = new LinkedHashMap<>();

    public static InitUnitBlock fromInitUnit(Function initModuleFunction, Function initUnit) {
        InitUnitBlock result = new InitUnitBlock();
        result.owner = initModuleFunction;
        result.sourceLocation = initUnit.getSourceLocation();
        result.statements.addAll(initUnit.statements);
        result.variables.putAll(initUnit._variables);

        for (Statement statement : result.statements) {
            reparentStatement(statement, result);
        }

        for (LocalVariable localVariable : result.variables.values()) {
            localVariable.owner = initModuleFunction;
            initModuleFunction.registerVariable(localVariable);
        }

        return result;
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
        // Statements and variables were already resolved in the original unit_init.
    }

    @Override
    public AsmLinearFormResult asLinearForm(AsmBlock block) {
        AsmBlock unitBlock = new AsmBlock(block);
        unitBlock.sourceLocation = this.sourceLocation;
        unitBlock.blockVariables.putAll(this.variables);

        for (Statement statement : statements) {
            unitBlock.instructions.addAll(statement.asLinearForm(unitBlock).instructions);
        }

        AsmLinearFormResult result = new AsmLinearFormResult();
        result.instructions.add(unitBlock);
        return result;
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
            codeRenderer.field("variables", variables);
            codeRenderer.field("statements", statements);
        });
    }

    /**
     * Top-level statements still point at the original {@code unit_init}
     * function. Reparent them so {@code findAstParent(Function.class)} and
     * {@link ExceptionHandler} resolve to {@code module_init}.
     */
    private static void reparentStatement(Statement statement, AstItem newOwner) {
        Class<?> type = statement.getClass();
        while (type != null) {
            try {
                Field field = type.getDeclaredField("owner");
                field.setAccessible(true);
                field.set(statement, newOwner);
                return;
            } catch (NoSuchFieldException e) {
                type = type.getSuperclass();
            } catch (IllegalAccessException e) {
                break;
            }
        }

        CaffcCompiler.get().fatal(statement,
                "BUG: cannot reparent statement of type " + statement.getClass().getName());
    }
}
