package com.germaniumhq.caffc.compiler.model;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AstItemCodeRenderer {
    private StringBuilder currentCode = new StringBuilder();
    private int currentIndent = 0;

    private final static boolean showDebug = false;

    /**
     * Write an object with its fields. For this to work, the renderer
     * (this class) will call the given runnable, so the object can
     * continue writing its field in an indented block.
     */
    public AstItemCodeRenderer object(AstItem astItem, Runnable runnable) {
        if (showDebug) {
            this.currentCode.append("<object>");
        }

        this.currentCode.append(astItem.getClass().getSimpleName())
            .append(":\n");

        this.currentIndent++;
        runnable.run();
        this.currentIndent--;

        if (showDebug) {
            this.currentCode.append("</object>");
        }

        return this;
    }

    /**
     * Write a field for the current object. Indent isn't changed. Depending
     * on the variable type, a different representation might be used.
     */
    public void field(String name, Object value) {
        if (showDebug) {
            this.currentCode.append("<field>");
        }

        this.writeIndent();
        this.currentCode.append(name).append(":");

        this.writeValue(this, value);
        this.currentCode.append("\n");

        if (showDebug) {
            this.currentCode.append("</field>");
        }
    }

    /**
     * Write the given value into the output.
     * @param parent
     * @param value
     */
    private void writeValue(Object parent, Object value) {
        if (value == null) {
            writeNull();
            return;
        }

        if (value instanceof List listValue) {
            writeList(parent, listValue);
            return;
        }

        if (value instanceof AstItem childAstItem) {
            writeAstItem(parent, childAstItem);
            return;
        }

        if (value instanceof String stringValue) {
            writeString(stringValue);
            return;
        }

        if (value instanceof Boolean boolValue) {
            writeBoolean(boolValue);
            return;
        }

        if (value instanceof Map mapValue) {
            writeMap(parent, mapValue);

            return;
        }

        throw new IllegalArgumentException("Unsupported value type: " + value.getClass());
    }

    private void writeMap(Object parent, Map map) {
        if (showDebug) {
            this.currentCode.append("<map>");
        }

        try {
            Iterator mapIterator = map.entrySet().iterator();

            if (!mapIterator.hasNext()) {
                this.currentCode.append("{}");
                return;
            }

            // we have items in the map
            // NOTE: if we are inside of a list, the first item doesn't need indent, just display
            //       since the list already indented it.
            if (!(parent instanceof List)) {
                this.currentCode.append("\n");
            }

            int i = 0;
            while (mapIterator.hasNext()) {
                Map.Entry entry = (Map.Entry) mapIterator.next();

                this.currentIndent++;

                if (i != 0 || !(parent instanceof List)) {
                    this.writeIndent();
                }
                i++;

                this.writeValue(map, entry.getKey());
                this.currentCode.append(": ");
                this.writeValue(map, entry.getValue());

                if (mapIterator.hasNext()) {
                    this.currentCode.append("\n"); // except last
                }

                this.currentIndent--;
            }
        } finally {
            if (showDebug) {
                this.currentCode.append("</map>");
            }
        }
    }

    private void writeBoolean(Boolean boolValue) {
        this.currentCode.append(boolValue);
    }

    private void writeString(String stringValue) {
        this.currentCode.append("\"").append(stringValue).append("\"");
    }

    private void writeAstItem(Object parent, AstItem childAstItem) {
        if (showDebug) {
            this.currentCode.append("<ast>");
        }

        this.currentIndent++;

        if (!(parent instanceof List) && !(parent instanceof Map)) {
            this.currentCode.append("\n");
            this.writeIndent();
        }

        childAstItem.renderAst(this);
        this.currentIndent--;

        if (showDebug) {
            this.currentCode.append("</ast>");
        }
    }

    private void writeList(Object parent, List list) {
        if (showDebug) {
            this.currentCode.append("<list>");
        }

        try {

            if (list.isEmpty()) {
                this.currentCode.append("[]");
                return;
            }

            // we have items in the list
            this.currentCode.append("\n");

            for (int i = 0; i != list.size(); ++i) {
                Object childValue = list.get(i);

                this.writeIndent();
                this.currentCode.append("- ");

                this.currentIndent++;

                this.writeValue(list, childValue);

                if (i != list.size() - 1) {
                    this.currentCode.append("\n"); // except last
                }

                this.currentIndent--;
            }

        } finally {
            if (showDebug) {
                this.currentCode.append("</list>");
            }
        }
    }

    private void writeNull() {
        this.currentCode.append("null");
    }

    private void writeIndent() {
        for (int i = 0; i < currentIndent; i++) {
            currentCode.append(' ');
        }
    }

    public String toString() {
        return currentCode.toString();
    }

    public void text(String text) {
        currentCode.append(text);
    }
}
