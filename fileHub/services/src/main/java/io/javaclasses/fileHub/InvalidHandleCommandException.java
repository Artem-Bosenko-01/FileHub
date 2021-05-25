package io.javaclasses.fileHub;

/**
 * This is error when some of {@link Process processes} while handling command catch some invalid value.
 * */
public class InvalidHandleCommandException extends Exception{
    public InvalidHandleCommandException(String message) {
        super(message);
    }
}
