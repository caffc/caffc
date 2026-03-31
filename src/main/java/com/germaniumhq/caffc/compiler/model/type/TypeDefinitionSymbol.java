package com.germaniumhq.caffc.compiler.model.type;

/**
 * A TypeDefinition is a class/interface registered type where we need to know its type id.
 * There's only two things right now that implement this: classes and interfaces.
 */
public interface TypeDefinitionSymbol extends Symbol {
    /**
     * Returns the type id of the definition. This typeId is assigned
     * by the Program itself.
     */
    int typeId();

    /**
     * Sets the typeId of the TypeDefinition.
     * @param typeId
     */
    void setTypeId(int typeId);

    /**
     * How many fields from this are supposed to be handled by the GC.
     */
    int getGcFieldsCount();
}
