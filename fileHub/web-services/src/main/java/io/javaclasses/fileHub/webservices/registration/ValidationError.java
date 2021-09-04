package io.javaclasses.fileHub.webservices.registration;

/**
 * Type of error in the registration user process.
 */
public final class ValidationError {

    private final String field;

    private final String message;

    public ValidationError(String fieldName, String errorMessage) {

        this.field = fieldName;

        this.message = errorMessage;
    }
}
