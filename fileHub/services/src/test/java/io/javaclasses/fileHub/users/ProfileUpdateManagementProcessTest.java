package io.javaclasses.fileHub.users;

import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ProfileUpdateManagementProcessTest {

    @Test
    public void updateInfoUserTest(){
        RegisterUserCommand registerUserCommand = new RegisterUserCommand("badk@h.com","bbb","ccc","56478");
        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();
        UserRegistrationProcess userRegistrationProcess = new UserRegistrationProcess(userStorageInMemory);
        try {
            userRegistrationProcess.handle(registerUserCommand);
        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
        ProfileUpdateCommand command = new ProfileUpdateCommand(new AuthToken("1"),
                new UserID("badk@h.com"), "aaa@h.com", "cas","abc","56478");
        ProfileUpdateProcess process = new ProfileUpdateProcess(userStorageInMemory);
        try {
            UserDTO userRegisterDTO = process.handle(command);
            Assertions.assertEquals(userRegisterDTO.loginName(), "aaa@h.com");
            Assertions.assertEquals(userRegisterDTO.firstName(), "cas");
            Assertions.assertEquals(userRegisterDTO.lastName(), "abc");
        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }
}