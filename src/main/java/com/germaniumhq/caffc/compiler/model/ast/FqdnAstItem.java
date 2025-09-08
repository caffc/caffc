package com.germaniumhq.caffc.compiler.model.ast;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.AstItem;
import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.generated.caffcParser;


// FIXME: I'm not super sure on this. on one hand we need multiple
//        modules, on the other hand I'm not sure
public class FqdnAstItem implements AstItem {
    private String astFilePath;
    private int astLine;
    private int astColumn;
    private AstItem owner;

    private FqdnAstItem leftOfDot;
    private String rightOfDot;
    private boolean isResolved;

    public static FqdnAstItem fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.FqdnContext fqdnContext) {
        FqdnAstItem result = new FqdnAstItem();

        result.astFilePath = unit.astFilePath;
        result.astLine = fqdnContext.getStart().getLine();
        result.astColumn = fqdnContext.getStart().getCharPositionInLine();
        result.owner = owner;

        if (fqdnContext instanceof caffcParser.FqdnMultipleContext fqdnMultipleContext) {
            result.leftOfDot = FqdnAstItem.fromAntlr(unit, result, fqdnMultipleContext.fqdn());
            result.rightOfDot = fqdnMultipleContext.ID().getText();
        } else if (fqdnContext instanceof caffcParser.FqdnIdContext fqdnIdContext) {
            result.rightOfDot = fqdnIdContext.ID().getText();
        } else {
            CaffcCompiler.get().fatal(
                    AstItem.fromAntlr(unit.astFilePath, fqdnContext),
                    "unsupported fqdn type: " + fqdnContext.getText() + " " + fqdnContext.getClass().getCanonicalName());
        }

        return result;
    }

    @Override
    public AstItem getOwner() {
        return this.owner;
    }

    @Override
    public String getFilePath() {
        return this.astFilePath;
    }

    @Override
    public int getLineNumber() {
        return this.astLine;
    }

    @Override
    public int getColumnNumber() {
        return this.astColumn;
    }

    @Override
    public void recurseResolveTypes() {
        if (this.isResolved) {
            return;
        }

        this.isResolved = true;

        if (leftOfDot != null) {
            leftOfDot.recurseResolveTypes();
        }

    }

    public Symbol getType() {
        throw new IllegalStateException("not implemented");
    }
}
