package io.javaclasses.fileHub.users;

import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class ProfileReadManagementViewTest {

    private UserRegisterDTO registerUser(UserStorageInMemory userStorageInMemory, String login){
        RegisterUserCommand registerUserCommand = new RegisterUserCommand(login,"bbb","ccc","56478");

        UserRegistrationProcess userRegistrationProcess = new UserRegistrationProcess(userStorageInMemory);

        try {
            return userRegistrationProcess.handle(registerUserCommand);
        } catch (InvalidHandleCommandException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    @Test
    public void readInfoAboutUserByIdTest(){
        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();
        UserRegisterDTO registerDTO = registerUser(userStorageInMemory, "badk@h.com");

        ProfileReadQuery command = new ProfileReadQuery(new AuthToken("1"), new UserID("badk@h.com"));
        ProfileReadManagementView profileReadManagementProcess = new ProfileReadManagementView(userStorageInMemory);

        try {
            UserRegisterDTO user = profileReadManagementProcess.handle(command);
            Assertions.assertEquals(user, registerDTO);

        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void failedReadInfoByNotExistIdTest(){
        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();
        UserRegisterDTO registerDTO = registerUser(userStorageInMemory, "badk@h.com");

        ProfileReadQuery command = new ProfileReadQuery(new AuthToken("1"), new UserID("asas@h.com"));
        ProfileReadManagementView profileReadManagementProcess = new ProfileReadManagementView(userStorageInMemory);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> {
            UserRegisterDTO user = profileReadManagementProcess.handle(command);
        });
    }


}