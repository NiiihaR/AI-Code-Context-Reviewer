package dev.reviewer;

public class GitExecutionException extends Exception {
    public GitExecutionException(String message) {
        super(message);
    }

    public GitExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
