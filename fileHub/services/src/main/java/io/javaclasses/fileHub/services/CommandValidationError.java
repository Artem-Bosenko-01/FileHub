package io.javaclasses.fileHub.services;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Data that describes an error during the validation of the input parameters of command.
 */
public final class CommandValidationError {

    private final String field;
    private final String message;

    public CommandValidationError(String field, String message) {

        this.field = checkNotNull(field);
        this.message = checkNotNull(message);
    }

    public String field() {
        return field;
    }

    public String message() {
        return message;
    }
}
