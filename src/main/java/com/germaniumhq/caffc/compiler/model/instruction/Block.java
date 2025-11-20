package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.AstItemCodeRenderer;
import com.germaniumhq.caffc.compiler.model.BlockVariable;
import com.germaniumhq.caffc.compiler.model.Expression;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.output.filters.FilterCTypeName;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a block of statements. Blocks are never created by
 * the AST parser, but instead are created when breaking down complex
 * expressions into linear form, after both the AST parsing has
 * completed, and the recursive type resolving was finished.
 */
public class Block implements Statement, Scope {
    public Map<String, BlockVariable> blockVariables = new LinkedHashMap<>();
    public List<Statement> statements = new ArrayList<>();

    private Map<String, Integer> typeIndexes = new TreeMap<>();

    AstItem owner;

    public static Block createFrom(Statement statement) {
        Block block = new Block();
        block.owner = statement;

        return block;
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
            "since the types should have been already resolved");
    }

    public void addStatement(Statement statement) {
        this.statements.add(statement);
    }

    public BlockVariable addTempVariableFor(Expression expression) {
        String cTypeName = FilterCTypeName.getCType(expression.typeSymbol().typeName());
        Integer index = typeIndexes.compute(cTypeName, (it, old) -> old == null ? 1 : old + 1);

        String variableName = "_caffc_temp_" + cTypeName + "_" + index;

        BlockVariable result = new BlockVariable(
            expression,
            expression.typeSymbol(),
            variableName);

        this.blockVariables.put(variableName, result);

        return result;
    }

    @Override
    public void renderAst(AstItemCodeRenderer codeRenderer) {
        codeRenderer.object(this, () -> {
            codeRenderer.field("variables", this.blockVariables);
            codeRenderer.field("statements", this.statements);
        });
    }
}
