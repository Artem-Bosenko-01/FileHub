package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.files.FolderStorageInMemory;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RegisterUserTest {

    @Test
    public void registerUserTest() throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

        String loginName = "badk@h.com";

        RegistrationUserCommand command = new RegistrationUserCommand(loginName, "scascsa");

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        FolderStorage folderStorage = new FolderStorageInMemory();

        RegisterUser registerUser = new RegisterUser(userStorageInMemory, folderStorage);

        Assertions.assertNotNull(registerUser.handle(command));

        Assertions.assertTrue(userStorageInMemory.findByID(new UserId(loginName)).isPresent());

    }

    @Test
    public void registerUsersWithEqualsIdTest() throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

        RegistrationUserCommand command = new RegistrationUserCommand("badk@h.com", "scascsa");

        RegistrationUserCommand command1 = new RegistrationUserCommand("badk@h.com", "scascsa");

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        FolderStorage folderStorage = new FolderStorageInMemory();

        RegisterUser registerUser = new RegisterUser(userStorageInMemory, folderStorage);

        registerUser.handle(command);

        Assertions.assertThrows(InvalidCommandHandlingException.class, () -> registerUser.handle(command1));

    }

}