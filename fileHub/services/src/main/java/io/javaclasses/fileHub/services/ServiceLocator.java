package io.javaclasses.fileHub.services;

import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.users.AuthenticateUser;
import io.javaclasses.fileHub.services.users.RegisterUser;

/**
 * Set of processes that allows manages file's and user's systems of FileHub application.
 */
public class ServiceLocator {

    private final AuthenticateUser authenticateUser;
    private final RegisterUser registerUser;

    public ServiceLocator() {

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();
        UserStorage userStorage = new UserStorageInMemory();

        registerUser = new RegisterUser(userStorage);
        authenticateUser = new AuthenticateUser(userStorage, authorizationStorage);
    }

    public AuthenticateUser authenticateUser() {
        return authenticateUser;
    }

    public RegisterUser registerUser() {
        return registerUser;
    }
}
