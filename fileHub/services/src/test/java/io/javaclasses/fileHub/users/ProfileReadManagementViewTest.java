package io.javaclasses.fileHub.users;

import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProfileReadManagementViewTest {

    private UserDTO registerUser(UserStorageInMemory userStorageInMemory, String login){
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
        UserDTO registerDTO = registerUser(userStorageInMemory, "badk@h.com");

        ProfileReadQuery command = new ProfileReadQuery(new AuthToken("1"), new UserID("badk@h.com"));
        ProfileReadView profileReadManagementProcess = new ProfileReadView(userStorageInMemory);

        try {
            UserDTO user = profileReadManagementProcess.handle(command);
            Assertions.assertEquals(user, registerDTO);

        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void failedReadInfoByNotExistIdTest(){
        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();
        UserDTO registerDTO = registerUser(userStorageInMemory, "badk@h.com");

        ProfileReadQuery command = new ProfileReadQuery(new AuthToken("1"), new UserID("asas@h.com"));
        ProfileReadView profileReadManagementProcess = new ProfileReadView(userStorageInMemory);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> {
            UserDTO user = profileReadManagementProcess.handle(command);
        });
    }


}