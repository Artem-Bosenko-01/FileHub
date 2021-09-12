package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

/**
 * Throws when a user's login doesn't exist in {@link io.javaclasses.fileHub.persistent.Storage storage}.
 */
public class UserNotFoundException extends InvalidCommandHandlingException {

    private static final long serialVersionUID = 8175924901843712702L;

    public UserNotFoundException(String loginName) {

        super("Cannot find user by login: " + loginName);
    }
}
