package com.germaniumhq.caffc.compiler.model.asm.opc;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmValue;
import com.germaniumhq.caffc.compiler.model.asm.vars.AsmVar;
import com.germaniumhq.caffc.compiler.model.type.TypeDefinitionSymbol;

public final class AsmInstanceOf implements AsmInstruction {
    public final SourceLocation sourceLocation;
    public final AsmVar result;
    public final AsmValue objectPointer;
    public final TypeDefinitionSymbol instanceOfType;

    public AsmInstanceOf(SourceLocation sourceLocation, AsmVar result, AsmValue objectPointer, TypeDefinitionSymbol typeId) {
        this.sourceLocation = sourceLocation;
        this.result = result;
        this.objectPointer = objectPointer;
        this.instanceOfType = typeId;
    }

    @Override
    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }
}
