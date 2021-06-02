package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AuthenticationUserTest {

    @Test
    public void authenticateTest() throws InvalidHandleCommandException {

        RegistrationUserCommand registrationUserCommand = UserTestData.registerUser("badk@h.com");

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        RegistrationUser registrationUser = new RegistrationUser(userStorageInMemory);

        registrationUser.handle(registrationUserCommand);

        AuthenticationUserCommand authenticationUserCommand = UserTestData.authenticateUser("badk@h.com");

        AuthenticationUser process = new AuthenticationUser(userStorageInMemory);

        Assertions.assertNotNull(process.handle(authenticationUserCommand));
    }

}