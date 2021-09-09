package io.javaclasses.fileHub.services;

import java.util.ArrayList;
import java.util.List;

/**
 * Throws if the validation of {@link Command command} input parameters fails.
 * Exception contains list of {@link CommandValidationError errors},
 * that that defines the field where the error occurred and a special error message.
 * Fields validate by the specific {@link ValidationRules rules}.
 */
public class InvalidValidationCommandDataException extends Exception {

    private final List<CommandValidationError> errors;

    public InvalidValidationCommandDataException() {

        this.errors = new ArrayList<>();
    }

    public void addError(String field, String errorMessage) {

        errors.add(new CommandValidationError(field, errorMessage));
    }

    public List<CommandValidationError> errors() {
        return errors;
    }
}
