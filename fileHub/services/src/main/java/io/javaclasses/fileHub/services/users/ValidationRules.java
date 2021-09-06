package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.services.ValidationCommandDataException;

/**
 * Set of value limits for a necessary {@link io.javaclasses.fileHub.services.Command command}.
 */
public class ValidationRules {

    public static void validateUsersCredentials(String loginName, String password)
            throws ValidationCommandDataException {

        ValidationCommandDataException exception = new ValidationCommandDataException();

        if (loginName.trim().isEmpty()) {
            exception.addError("email", "Email is empty!");
        }

        if (loginName.length() < 5) {
            exception.addError("email", "Email length should be more than 5 symbols");
        }

        if (password.trim().isEmpty()) {
            exception.addError("password", "Password is empty!");
        }

        if (password.length() < 6) {
            exception.addError("password", "Password length should be more than 6 symbols");
        }

        if (exception.errors().size() > 0) {
            throw exception;
        }
    }
}
