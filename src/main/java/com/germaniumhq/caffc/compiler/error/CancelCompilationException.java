package com.germaniumhq.caffc.compiler.error;

/**
 * Exception to use when aborting compilation. This will cancel
 * all the executions and won't be logged out.
 */
public class CancelCompilationException extends RuntimeException {
    public CancelCompilationException(String errorMessage) {
        super(errorMessage);
    }
}
