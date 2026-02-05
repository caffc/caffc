package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.AstItemCodeRenderer;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.expression.VariableDeclaration;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.output.filters.FilterCName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a block of instructions. Blocks are never created by
 * the AST parser, but instead are created when breaking down complex
 * expressions into linear form, after both the AST parsing has
 * completed, and the recursive type resolving was finished.
 *
 * They exist only to make it easier to find variables that could be
 * reused.
 */
public final class AsmBlock implements Scope, AsmInstruction {
    public Map<String, VariableDeclaration> blockVariables = new HashMap<>();
    public List<AsmInstruction> instructions = new ArrayList<>();

    private Map<String, Integer> typeIndexes = new TreeMap<>();

    AstItem owner;

    public AsmBlock(AstItem owner) {
        this.owner = owner;
    }

    @Override
    public Symbol resolve(String name) {
        return blockVariables.get(name);
    }

    @Override
    public AstItem getOwner() {
        return owner;
    }

    @Override
    public String getFilePath() {
        return owner.getFilePath();
    }

    @Override
    public int getLineNumber() {
        return owner.getLineNumber();
    }

    @Override
    public int getColumnNumber() {
        return owner.getColumnNumber();
    }

    @Override
    public void recurseResolveTypes() {
        throw new IllegalStateException("recurse resolve types shouldn't be called, " +
            "since the types should have been already resolved. This is an AsmInstruction.");
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
            codeRenderer.field("variables", this.blockVariables);
        });
    }

    public VariableDeclaration addTempVar(AstItem owner, Symbol typeSymbol) {
        if (typeSymbol == null) {
            throw new IllegalStateException("null type defined for the temp variable");
        }

        // FIXME: since variables can be managed by the GC does it make sense
        //        to set the variable to 0 when exiting the block?

        String cTypeName = FilterCName.getCType(typeSymbol.typeName());
        Integer index = this.getNextTypeIndex(cTypeName);
        this.typeIndexes.put(cTypeName, index);

        String variableName = "_caffc_temp_" + cTypeName + "_" + index;

        VariableDeclaration variable = this.findAstParent(Function.class)
            .ensureVariableExists(owner, variableName, typeSymbol);

        this.blockVariables.put(variableName, variable);

        return variable;
    }

    public int getNextTypeIndex(String typeName) {
        if (this.typeIndexes.containsKey(typeName)) {
            return this.typeIndexes.get(typeName) + 1;
        }

        AsmBlock parentBlock = this.findAstParent(AsmBlock.class);
        if (parentBlock != null) {
            return parentBlock.getNextTypeIndex(typeName);
        }

        return 1;
    }
}
