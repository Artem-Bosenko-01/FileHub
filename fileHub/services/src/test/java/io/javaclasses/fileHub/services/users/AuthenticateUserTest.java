package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AuthenticateUserTest {

    @Test
    public void authenticateTest() throws InvalidHandleCommandException {

       /*
        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        RegistrationUser registrationUser = new RegistrationUser(userStorageInMemory);

        registrationUser.handle(registrationUserCommand);

        AuthenticationUserCommand authenticationUserCommand = UserTestData.authenticateUser("badk@h.com");

        AuthenticationUser process = new AuthenticationUser(userStorageInMemory, authorizationStorage);

        AuthToken token = process.handle(authenticationUserCommand);

        Assertions.assertNotNull(token);

        Assertions.assertTrue(authorizationStorage.isTokenExist(token.value()));
*/    }

}