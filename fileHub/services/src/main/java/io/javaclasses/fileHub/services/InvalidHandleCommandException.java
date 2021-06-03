package io.javaclasses.fileHub.services;

/**
 * This is error when some of {@link UserProcess processes} while handling command catch some invalid value.
 * */
public class InvalidHandleCommandException extends Exception{
    public InvalidHandleCommandException(String message) {
        super(message);
    }
}
