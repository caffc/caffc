package com.germaniumhq.caffc.compiler.error;

/**
 * Report errors to the outside world.
 */
public interface ErrorReporter {
    void reportError(ErrorType errorType, String location, String message);
}
