package io.javaclasses.fileHub.webservices.registration;

import io.javaclasses.fileHub.services.CommandValidationError;
import io.javaclasses.fileHub.webservices.JsonResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * List of validation errors for response with 422 code status.
 */
public class ValidationErrorResponse extends JsonResponse {

    List<ValidationError> errors = new ArrayList<>();

    public void addError(String fieldName, String errorMessage) {

        errors.add(new ValidationError(fieldName, errorMessage));
    }

    public void addErrors(List<CommandValidationError> errors) {

        errors.forEach(exception -> this.errors.add(new ValidationError(exception.field(), exception.message())));
    }
}
