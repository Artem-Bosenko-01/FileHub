package io.javaclasses.fileHub.webservices.user;

import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.users.AuthenticateUser;
import io.javaclasses.fileHub.services.users.AuthenticationUserCommand;
import io.javaclasses.fileHub.services.users.DuplicatedUserException;
import io.javaclasses.fileHub.services.users.UserNotFoundException;

public class AuthenticateUserBaseStub extends AuthenticateUser {

    public AuthenticateUserBaseStub(UserStorage userStorage, AuthorizationStorage authorizationStorage) {
        super(userStorage, authorizationStorage);
    }

    @Override
    public AuthToken handle(AuthenticationUserCommand inputCommand)
            throws DuplicatedUserException, UserNotFoundException {

        return new AuthToken("token");
    }
}

