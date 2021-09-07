package io.javaclasses.fileHub.webservices.user;

/**
 * Type of error in the registration user process.
 */
final class ValidationError {

    private final String field;

    private final String message;

    public ValidationError(String fieldName, String errorMessage) {

        this.field = fieldName;

        this.message = errorMessage;
    }
}
