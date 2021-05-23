package io.javaclasses.fileHub.users;

import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ProfileUpdateManagementProcessTest {

    @Test
    public void updateInfoUserTest(){
        RegisterUserCommand registerUserCommand = new RegisterUserCommand(new UserID(1), "badk@h.com","bbb","ccc","56478");
        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();
        UserRegistrationProcess userRegistrationProcess = new UserRegistrationProcess(userStorageInMemory);
        try {
            userRegistrationProcess.handle(registerUserCommand);
        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
        ProfileUpdateManagementCommand command = new ProfileUpdateManagementCommand(new AuthToken("1"),
                new UserID(1), "aaa@h.com","cas","abc","56478");
        ProfileUpdateManagementProcess process = new ProfileUpdateManagementProcess(userStorageInMemory);
        try {
            UserRegisterDTO userRegisterDTO = process.handle(command);
            Assertions.assertEquals(userRegisterDTO.firstName(), "cas");
            Assertions.assertEquals(userRegisterDTO.lastName(), "abc");
        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }
}