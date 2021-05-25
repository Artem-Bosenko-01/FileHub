package io.javaclasses.fileHub.users;

import io.javaclasses.fileHub.InvalidHandleCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserRegistrationProcessTest {

    @Test
    public void registerUserTest(){
        RegisterUserCommand command = new RegisterUserCommand("badk@h.com","bbb","ccc","56478");
        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();
        UserRegistrationProcess userRegistrationProcess = new UserRegistrationProcess(userStorageInMemory);
        try {
            userRegistrationProcess.handle(command);

            Assertions.assertEquals(userStorageInMemory.getRecordsSize(),1);
        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registerUsersWithEqualsIdTest(){
        RegisterUserCommand command = new RegisterUserCommand("badk@h.com","bbb","ccc","56478");
        RegisterUserCommand command1 = new RegisterUserCommand("badk@h.com","bbb","ccc","56478");
        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();
        UserRegistrationProcess userRegistrationProcess = new UserRegistrationProcess(userStorageInMemory);
        try {
            userRegistrationProcess.handle(command);
            Assertions.assertThrows(InvalidHandleCommandException.class, () -> userRegistrationProcess.handle(command1));
        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }

}