package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ProfileUpdateManagementProcessTest {

    @Test
    public void updateInfoUserTest() throws InvalidHandleCommandException {

        RegistrationUserCommand registrationUserCommand = new RegistrationUserCommand("badk@h.com", "bbb", "ccc", "56478");

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        RegistrationUser registrationUser = new RegistrationUser(userStorageInMemory);

        registrationUser.handle(registrationUserCommand);

        UpdatingProfileCommand command = new UpdatingProfileCommand(new AuthToken("1"),
                new UserId("badk@h.com"), "aaa@h.com", "cas", "abc", "56478");

        UpdatingProfile process = new UpdatingProfile(userStorageInMemory);

        String userRegisterDTO = process.handle(command);

        Assertions.assertEquals(userRegisterDTO, "aaa@h.com");

    }

}