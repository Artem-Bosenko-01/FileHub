package io.javaclasses.fileHub.services;

import java.util.regex.Pattern;

/**
 * A set of validation rules for a particular {@link io.javaclasses.fileHub.services.Command command}.
 */
public final class ValidationRules {

    private ValidationRules() {
    }

    public static void validateUsersCredentials(String loginName, String password)
            throws InvalidValidationCommandDataException {

        InvalidValidationCommandDataException exception = new InvalidValidationCommandDataException();

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

    public static void validateFileSystemItemName(String name)
            throws InvalidValidationCommandDataException {

        InvalidValidationCommandDataException exception = new InvalidValidationCommandDataException();

        if (name.trim().isEmpty()) {
            exception.addError("itemName", "Name is empty!");
        }

        if (!validateString(name)) {
            exception.addError("itemName", "Item name contains invalidSymbols");
        }

        if (exception.errors().size() > 0) {
            throw exception;
        }
    }

    private static boolean validateString(String str) {

        for (int i = 0; i < str.length(); i++) {

            if (!Pattern.matches("[0-9a-zA-Z-_@.*,&]", String.valueOf(str.charAt(i)))) {

                return false;
            }
        }

        return true;
    }
}
