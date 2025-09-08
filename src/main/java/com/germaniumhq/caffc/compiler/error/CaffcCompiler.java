package com.germaniumhq.caffc.compiler.error;

import com.germaniumhq.caffc.compiler.model.AstItem;

public class CaffcCompiler {
    public boolean hasErrors;
    private static CaffcCompiler INSTANCE = new CaffcCompiler();

    public static CaffcCompiler get() {
        return INSTANCE;
    }

    /**
     * Reports the error to the user, and terminates execution.
     * @param astItem
     * @param message
     */
    public void fatal(AstItem astItem, String message) {
        String errorMessage = String.format("%s: %s: %s",
                fileLocation(astItem), "FATAL", message);
        System.err.println(errorMessage);
        this.hasErrors = true;

        throw new CancelCompilationException(errorMessage);
    }

    /**
     * Reports the error to the user, execution continues.
     * Code won't be generated anymore.
     *
     * @param astItem
     * @param message
     */
    public void error(AstItem astItem, String message) {
        this.hasErrors = true;
        System.err.format("%s: %s: %s\n",
                fileLocation(astItem), "ERROR", message);
    }

    /**
     * Report the warning to the user, execution continues.
     * Code will be generated.
     *
     * @param astItem
     * @param message
     */
    public void warning(AstItem astItem, String message) {
        System.err.format("%s: %s: %s\n",
                fileLocation(astItem), "WARN ", message);
    }


    public static String fileLocation(AstItem astItem) {
        return String.format("%s:%d:%d",
            astItem.getFilePath(),
            astItem.getLineNumber(),
            astItem.getColumnNumber()
        );
    }
}
