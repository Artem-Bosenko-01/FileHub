package io.javaclasses.fileHub.services;

/**
 * Throws when user with {@link AuthToken token} doesn't have permission for handling
 * {@link SecuredUserProcess secured processes}.
 */
public class NotAuthorizedUserException extends Exception {

    private static final long serialVersionUID = 4926551043606974133L;

    public NotAuthorizedUserException(String message) {

        super(message);
    }
}
