package io.javaclasses.fileHub.services;

public class NotAuthorizedUserException extends Exception {

    public NotAuthorizedUserException(String message) {

        super(message);
    }
}
