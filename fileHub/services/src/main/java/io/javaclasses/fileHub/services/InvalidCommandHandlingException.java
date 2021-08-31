package io.javaclasses.fileHub.services;

/**
 * This is an exception that throws when some of the handling of the command fail in the {@link UserProcess processes}.
 * */
public class InvalidCommandHandlingException extends Exception{
    private static final long serialVersionUID = -8365495320270473370L;

    public InvalidCommandHandlingException(String message) {
        super(message);
    }
}
