package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

/**
 * The exception that throws when {@link io.javaclasses.fileHub.persistent.users.tokens.UserAuthToken user's token}
 * doesn't exist at {@link io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage storage}.
 */
public final class UsersTokenNotFoundHandlingException extends InvalidCommandHandlingException {

    public UsersTokenNotFoundHandlingException(AuthToken token) {

        super("Cannot find user by token: " + token);
    }
}
