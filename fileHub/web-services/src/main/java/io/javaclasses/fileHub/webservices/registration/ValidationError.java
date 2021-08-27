package io.javaclasses.fileHub.webservices.registration;

/**
 * Type of error in the registration user process.
 */
public class ValidationError {
    private final String fieldName;
    private final String errorMessage;
    public ValidationError(String fieldName, String errorMessage){
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }
}
