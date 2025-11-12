package com.germaniumhq.caffc.compiler.model.instruction;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.BlockVariable;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a block of statements. Blocks are never created by
 * the AST parser, but instead are created when breaking down complex
 * expressions into linear form, after both the AST parsing has
 * completed, and the recursive type resolving was finished.
 */
public class Block implements Statement, Scope {
    public Map<String, BlockVariable> blockVariables = new LinkedHashMap<>();
    public List<Statement> statements = new ArrayList<>();

    AstItem owner;

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
}
