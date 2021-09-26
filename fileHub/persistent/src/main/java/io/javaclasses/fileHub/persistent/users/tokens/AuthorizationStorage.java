package io.javaclasses.fileHub.persistent.users.tokens;

import io.javaclasses.fileHub.persistent.Storage;

/**
 * The {@link Storage storage} that contains instruments for managing user's sessions using
 * {@link UserAuthToken authentication token} in the FileHub application.
 */
public interface AuthorizationStorage extends Storage<UserAuthToken, AuthorizationUsers> {

    boolean isTokenExist(String token);
}
