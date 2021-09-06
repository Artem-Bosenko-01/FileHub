package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

/**
 * Throws when a user's login doesn't exist in {@link io.javaclasses.fileHub.persistent.Storage storage}.
 */
public class UserNotFoundException extends InvalidCommandHandlingException {

    public UserNotFoundException(String loginName) {

        super("Cannot find user by login: " + loginName);
    }
}
