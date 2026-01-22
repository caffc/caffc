package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.AstItemCodeRenderer;
import com.germaniumhq.caffc.compiler.model.BlockVariable;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.output.filters.FilterCName;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a block of instructions. Blocks are never created by
 * the AST parser, but instead are created when breaking down complex
 * expressions into linear form, after both the AST parsing has
 * completed, and the recursive type resolving was finished.
 */
public final class AsmBlock implements Scope, AsmInstruction {
    public Map<String, BlockVariable> blockVariables = new LinkedHashMap<>();
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

    public BlockVariable addTempVar(AstItem owner, Symbol typeSymbol) {
        if (typeSymbol == null) {
            throw new IllegalStateException("null type defined for the temp variable");
        }

        String cTypeName = FilterCName.getCType(typeSymbol.typeName());
        Integer index = typeIndexes.compute(cTypeName, (it, old) -> old == null ? 1 : old + 1);

        String variableName = "_caffc_temp_" + cTypeName + "_" + index;

        BlockVariable result = new BlockVariable(
            owner,
            typeSymbol,
            variableName);

        this.blockVariables.put(variableName, result);

        return result;
    }
}
