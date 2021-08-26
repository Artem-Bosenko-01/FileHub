package io.javaclasses.fileHub.services;

import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.users.AuthenticationUser;
import io.javaclasses.fileHub.services.users.RegistrationUser;

public class ServiceAdapter {
    private final UserStorage userStorage = new UserStorageInMemory();
    private final AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

    public AuthenticationUser authenticateUser() {
        return new AuthenticationUser(userStorage, authorizationStorage);
    }

    public RegistrationUser registerUser() {
        return new RegistrationUser(userStorage);
    }
}
