package io.javaclasses.fileHub.services;

/**
 * This is error when some of {@link UserProcess processes} while handling command catch some invalid value.
 * */
public class InvalidHandleCommandException extends Exception{
    private static final long serialVersionUID = -8365495320270473370L;

    public InvalidHandleCommandException(String message) {
        super(message);
    }
}
