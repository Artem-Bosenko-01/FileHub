package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RegistrationUserTest {

    @Test
    public void registerUserTest() throws InvalidHandleCommandException {

        RegistrationUserCommand command = UserTestData.registerUser("badk@h.com");

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        RegistrationUser registrationUser = new RegistrationUser(userStorageInMemory);

        registrationUser.handle(command);

        Assertions.assertEquals(userStorageInMemory.getRecordsSize(), 1);

    }

    @Test
    public void registerUsersWithEqualsIdTest() throws InvalidHandleCommandException {

        RegistrationUserCommand command = UserTestData.registerUser("badk@h.com");

        RegistrationUserCommand command1 = UserTestData.registerUser("badk@h.com");

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        RegistrationUser registrationUser = new RegistrationUser(userStorageInMemory);

        registrationUser.handle(command);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> registrationUser.handle(command1));

    }

}