package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RegisterUserTest {

    @Test
    public void registerUserTest() throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

        RegistrationUserCommand command = new RegistrationUserCommand("badk@h.com", "scascsa");

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        RegisterUser registerUser = new RegisterUser(userStorageInMemory);

        Assertions.assertNotNull(registerUser.handle(command));

        Assertions.assertEquals(userStorageInMemory.getRecordsSize(), 1);

    }

    @Test
    public void registerUsersWithEqualsIdTest() throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

        RegistrationUserCommand command = new RegistrationUserCommand("badk@h.com", "scascsa");

        RegistrationUserCommand command1 = new RegistrationUserCommand("badk@h.com", "scascsa");

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        RegisterUser registerUser = new RegisterUser(userStorageInMemory);

        registerUser.handle(command);

        Assertions.assertThrows(InvalidCommandHandlingException.class, () -> registerUser.handle(command1));

    }

}