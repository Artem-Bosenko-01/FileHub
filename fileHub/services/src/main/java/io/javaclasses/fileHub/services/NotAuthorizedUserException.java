package io.javaclasses.fileHub.services;

public class NotAuthorizedUserException extends Exception {

    private static final long serialVersionUID = 4926551043606974133L;

    public NotAuthorizedUserException(String message) {

        super(message);
    }
}
