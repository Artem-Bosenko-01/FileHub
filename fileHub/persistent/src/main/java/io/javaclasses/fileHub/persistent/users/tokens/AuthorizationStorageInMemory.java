package io.javaclasses.fileHub.persistent.users.tokens;

import io.javaclasses.fileHub.persistent.AbstractInMemoryStorage;

/**
 * This is in memory storage for saving users authentication history in FIlehub application.
 *
 * */
public class AuthorizationStorageInMemory extends AbstractInMemoryStorage<UserAuthToken, AuthorizationUsers>
        implements AuthorizationStorage{

    @Override
    public boolean isTokenExist(String token) {
        return records().containsKey(new UserAuthToken(token));
    }
}
