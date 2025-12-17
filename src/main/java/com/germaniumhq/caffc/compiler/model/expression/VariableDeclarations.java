package com.germaniumhq.caffc.compiler.model.expression;

import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Function;
import com.germaniumhq.caffc.compiler.model.Statement;
import com.germaniumhq.caffc.compiler.model.type.SymbolSearch;
import com.germaniumhq.caffc.generated.caffcParser;

import java.util.ArrayList;
import java.util.List;

public class VariableDeclarations {
    public static <T extends Statement> List<T> fromAntlr(
            CompilationUnit unit,
            AstItem owner,
            caffcParser.VariableDeclarationsContext variableDeclarationsContext) {

        List<VariableDeclaration> result = new ArrayList<>();

        SymbolSearch symbolSearch = SymbolSearch.fromAntlr(unit, variableDeclarationsContext.varType());

        for (caffcParser.VariableDeclarationContext variableDeclarationContext: variableDeclarationsContext.variableDeclaration()) {
            result.add(
                VariableDeclaration.fromAntlr(unit, owner, symbolSearch, variableDeclarationContext));
        }

        Function function = AstItem.findParentOrSelf(owner, Function.class);

        for (VariableDeclaration variableDeclaration: result) {
            function.registerVariable(variableDeclaration);
        }

        return (List<T>) result;
    }

}
