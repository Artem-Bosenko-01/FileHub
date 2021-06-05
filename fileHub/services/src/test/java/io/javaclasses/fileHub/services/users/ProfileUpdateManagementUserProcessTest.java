package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ProfileUpdateManagementUserProcessTest {

    @Test
    public void updateInfoUserTest() throws InvalidHandleCommandException {

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserId id = UserTestData.registerJohnUser(userStorageInMemory);

        AuthToken authToken = UserTestData.authenticateJohnUser(userStorageInMemory, authorizationStorage);

        UpdatingProfileCommand command = new UpdatingProfileCommand(authToken, id, "aaa@h.com",
                "newName", "newLastName", "casascac");

        UpdatingProfile process = new UpdatingProfile(userStorageInMemory, authorizationStorage);

        String userRegisterDTO = process.handle(command);

        Assertions.assertEquals(userRegisterDTO, "aaa@h.com");

    }

}