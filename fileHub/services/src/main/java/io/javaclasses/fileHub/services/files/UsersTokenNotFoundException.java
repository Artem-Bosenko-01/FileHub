package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;

/**
 * The exception that throws when {@link io.javaclasses.fileHub.persistent.users.tokens.UserAuthToken user's token}
 * doesn't exist at {@link io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage storage}.
 */
public final class UsersTokenNotFoundException extends InvalidHandleCommandException {

    public UsersTokenNotFoundException(AuthToken token) {

        super("Cannot find user by token: " + token);
    }
}
