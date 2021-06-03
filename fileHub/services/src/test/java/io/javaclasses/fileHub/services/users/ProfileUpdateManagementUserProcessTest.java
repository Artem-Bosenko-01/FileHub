package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ProfileUpdateManagementUserProcessTest {

    @Test
    public void updateInfoUserTest() throws InvalidHandleCommandException {

        RegistrationUserCommand registrationUserCommand = UserTestData.registerUser("badk@h.com");

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        RegistrationUser registrationUser = new RegistrationUser(userStorageInMemory);

        registrationUser.handle(registrationUserCommand);

        UpdatingProfileCommand command = UserTestData.updateUser(new UserId("badk@h.com"));

        UpdatingProfile process = new UpdatingProfile(userStorageInMemory);

        String userRegisterDTO = process.handle(command);

        Assertions.assertEquals(userRegisterDTO, "aaa@h.com");

    }

}