package com.germaniumhq.caffc.compiler.error;

import com.germaniumhq.caffc.compiler.model.source.HasSourceLocation;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;

public class CaffcCompiler {
    public boolean hasErrors;
    private static CaffcCompiler INSTANCE = new CaffcCompiler();

    public static CaffcCompiler get() {
        return INSTANCE;
    }

    /**
     * Reports the error to the user, and terminates execution.
     * @param sourceLocation
     * @param message
     */
    public void fatal(SourceLocation sourceLocation, String message) {
        String errorMessage = String.format("%s: %s: %s",
                fileLocation(sourceLocation), "FATAL", message);
        System.err.println(errorMessage);
        this.hasErrors = true;

        throw new CancelCompilationException(errorMessage);
    }

    public void fatal(HasSourceLocation hasSourceLocation, String message) {
        fatal(hasSourceLocation.getSourceLocation(), message);
    }

    /**
     * Reports the error to the user, execution continues.
     * Code won't be generated anymore.
     *
     * @param sourceLocation
     * @param message
     */
    public void error(SourceLocation sourceLocation, String message) {
        this.hasErrors = true;
        System.err.format("%s: %s: %s\n",
                fileLocation(sourceLocation), "ERROR", message);
    }

    public void error(HasSourceLocation hasSourceLocation, String message) {
        error(hasSourceLocation.getSourceLocation(), message);
    }

    /**
     * Report the warning to the user, execution continues.
     * Code will be generated.
     *
     * @param sourceLocation
     * @param message
     */
    public void warning(SourceLocation sourceLocation, String message) {
        System.err.format("%s: %s: %s\n",
                fileLocation(sourceLocation), "WARN ", message);
    }

    public void warning(HasSourceLocation hasSourceLocation, String message) {
        warning(hasSourceLocation.getSourceLocation(), message);
    }

    public static String fileLocation(SourceLocation sourceLocation) {
        return String.format("%s:%d:%d",
            sourceLocation.getFilePath(),
            sourceLocation.getLineNumber(),
            sourceLocation.getColumnNumber()
        );
    }

    public static String fileLocation(HasSourceLocation hasSourceLocation) {
        return fileLocation(hasSourceLocation.getSourceLocation());
    }
}
