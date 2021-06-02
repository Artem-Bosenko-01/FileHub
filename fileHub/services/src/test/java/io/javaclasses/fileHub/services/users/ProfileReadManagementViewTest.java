package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProfileReadManagementViewTest {

    private UserId registerUser(UserStorageInMemory userStorageInMemory, String login)
            throws InvalidHandleCommandException {

        RegistrationUserCommand registrationUserCommand = new RegistrationUserCommand(
                login,
                "bbb",
                "ccc",
                "56478"
        );

        RegistrationUser registrationUser = new RegistrationUser(userStorageInMemory);

        return registrationUser.handle(registrationUserCommand);

    }

    @Test
    public void readInfoAboutUserByIdTest() throws InvalidHandleCommandException {

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        UserId id = registerUser(userStorageInMemory, "badk@h.com");

        ReadUserProfileQuery command = new ReadUserProfileQuery(new AuthToken("1"), new UserId("badk@h.com"));

        ReadingInfoAboutUser profileReadManagementProcess = new ReadingInfoAboutUser(userStorageInMemory);

        InfoAboutUserDto infoAboutUserDto = profileReadManagementProcess.handle(command);

        Assertions.assertEquals(infoAboutUserDto.id(), id);

    }


    @Test
    public void failedReadInfoByNotExistIdTest() throws InvalidHandleCommandException {

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        registerUser(userStorageInMemory, "badk@h.com");

        ReadUserProfileQuery command = new ReadUserProfileQuery(new AuthToken("1"), new UserId("asas@h.com"));

        ReadingInfoAboutUser profileReadManagementProcess = new ReadingInfoAboutUser(userStorageInMemory);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> {
            profileReadManagementProcess.handle(command);
        });
    }


}