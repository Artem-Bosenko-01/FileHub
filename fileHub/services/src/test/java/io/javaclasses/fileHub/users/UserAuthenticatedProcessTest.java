package io.javaclasses.fileHub.users;

import io.javaclasses.fileHub.InvalidHandleCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserAuthenticatedProcessTest {

    @Test
    public void authenticateTest(){
        RegisterUserCommand registerUserCommand = new RegisterUserCommand(new UserID(1), "badk@h.com","bbb","ccc","56478");
        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();
        UserRegistrationProcess userRegistrationProcess = new UserRegistrationProcess(userStorageInMemory);
        try {
            userRegistrationProcess.handle(registerUserCommand);
        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }

        UserAuthenticatedCommand userAuthenticatedCommand = new UserAuthenticatedCommand("badk@h.com","56478");
        UserAuthenticatedProcess process = new UserAuthenticatedProcess(userStorageInMemory);
        try {
            Assertions.assertNotNull(process.handle(userAuthenticatedCommand));
        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }

}