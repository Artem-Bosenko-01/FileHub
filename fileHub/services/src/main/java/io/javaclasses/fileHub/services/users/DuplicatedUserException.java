package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

/**
 * Throws when the value for a field already used in {@link io.javaclasses.fileHub.persistent.Storage storage}.
 */
public class DuplicatedUserException extends InvalidCommandHandlingException {

    private static final long serialVersionUID = 6950440625513627262L;
    private final String field;
    private final String message;

    public DuplicatedUserException(String field, String message) {

        super(message);

        this.field = field;
        this.message = message;
    }

    public String field() {
        return field;
    }

    public String message() {
        return message;
    }
}
