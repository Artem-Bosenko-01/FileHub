package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.ValidationCommandDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AuthenticateUserTest {

    @Test
    public void authenticateTest() throws InvalidCommandHandlingException, ValidationCommandDataException {

        UserStorage userStorage = new UserStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserTestData.registerJohnUser(userStorage);

        AuthenticationUserCommand authenticationUserCommand = new AuthenticationUserCommand(
                "john@gmail.com", "564988");

        AuthenticateUser process = new AuthenticateUser(userStorage, authorizationStorage);

        AuthToken token = process.handle(authenticationUserCommand);

        Assertions.assertNotNull(token);

        Assertions.assertTrue(authorizationStorage.isTokenExist(token.value()));
    }

}