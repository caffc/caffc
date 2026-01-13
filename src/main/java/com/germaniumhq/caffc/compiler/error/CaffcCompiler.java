package com.germaniumhq.caffc.compiler.error;

import com.germaniumhq.caffc.compiler.model.source.SourceItem;

public class CaffcCompiler {
    public boolean hasErrors;
    private static CaffcCompiler INSTANCE = new CaffcCompiler();

    public static CaffcCompiler get() {
        return INSTANCE;
    }

    /**
     * Reports the error to the user, and terminates execution.
     * @param sourceItem
     * @param message
     */
    public void fatal(SourceItem sourceItem, String message) {
        String errorMessage = String.format("%s: %s: %s",
                fileLocation(sourceItem), "FATAL", message);
        System.err.println(errorMessage);
        this.hasErrors = true;

        throw new CancelCompilationException(errorMessage);
    }

    /**
     * Reports the error to the user, execution continues.
     * Code won't be generated anymore.
     *
     * @param sourceItem
     * @param message
     */
    public void error(SourceItem sourceItem, String message) {
        this.hasErrors = true;
        System.err.format("%s: %s: %s\n",
                fileLocation(sourceItem), "ERROR", message);
    }

    /**
     * Report the warning to the user, execution continues.
     * Code will be generated.
     *
     * @param sourceItem
     * @param message
     */
    public void warning(SourceItem sourceItem, String message) {
        System.err.format("%s: %s: %s\n",
                fileLocation(sourceItem), "WARN ", message);
    }


    public static String fileLocation(SourceItem sourceItem) {
        return String.format("%s:%d:%d",
            sourceItem.getFilePath(),
            sourceItem.getLineNumber(),
            sourceItem.getColumnNumber()
        );
    }
}
