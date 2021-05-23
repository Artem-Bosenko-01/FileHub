package io.javaclasses.fileHub.users;

import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProfileReadManagementProcessTest {

    @Test
    public void readInfoAboutUserByIdTest(){
        RegisterUserCommand registerUserCommand = new RegisterUserCommand(new UserID(1), "badk@h.com","bbb","ccc","56478");
        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();
        UserRegistrationProcess userRegistrationProcess = new UserRegistrationProcess(userStorageInMemory);
        UserRegisterDTO registerDTO = null;
        try {
            registerDTO = userRegistrationProcess.handle(registerUserCommand);
        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }

        ProfileReadManagementCommand command = new ProfileReadManagementCommand(new AuthToken("1"), new UserID(1));
        ProfileReadManagementProcess profileReadManagementProcess = new ProfileReadManagementProcess(userStorageInMemory);

        try {
            UserRegisterDTO user = profileReadManagementProcess.handle(command);
            Assertions.assertEquals(user, registerDTO);

        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }
}