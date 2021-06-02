package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RegistrationUserTest {

    @Test
    public void registerUserTest() throws InvalidHandleCommandException {

        RegistrationUserCommand command = new RegistrationUserCommand(
                "badk@h.com",
                "bbb",
                "ccc",
                "56478"
        );

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        RegistrationUser registrationUser = new RegistrationUser(userStorageInMemory);

        registrationUser.handle(command);

        Assertions.assertEquals(userStorageInMemory.getRecordsSize(), 1);

    }

    @Test
    public void registerUsersWithEqualsIdTest() throws InvalidHandleCommandException {

        RegistrationUserCommand command = new RegistrationUserCommand(
                "badk@h.com",
                "bbb",
                "ccc",
                "56478"
        );

        RegistrationUserCommand command1 = new RegistrationUserCommand(
                "badk@h.com",
                "dvsvs",
                "qdwqq",
                "52"
        );

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        RegistrationUser registrationUser = new RegistrationUser(userStorageInMemory);

        registrationUser.handle(command);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> registrationUser.handle(command1));

    }

}