package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProfileReadManagementViewTest {


    @Test
    public void readInfoAboutUserByIdTest() throws InvalidHandleCommandException {

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserId id = UserTestData.registerJohnUser(userStorageInMemory);

        AuthToken token = UserTestData.authenticateJohnUser(userStorageInMemory, authorizationStorage);

        ReadUserProfileQuery command = new ReadUserProfileQuery(token, id);

        ReadingInfoAboutUser profileReadManagementProcess = new ReadingInfoAboutUser(userStorageInMemory,
                authorizationStorage);

        InfoAboutUserDto infoAboutUserDto = profileReadManagementProcess.handle(command);

        Assertions.assertEquals(infoAboutUserDto.id(), id);

    }


    @Test
    public void failedReadInfoByNotExistIdTest() throws InvalidHandleCommandException {

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserTestData.registerJohnUser(userStorageInMemory);

        AuthToken token = UserTestData.authenticateJohnUser(userStorageInMemory, authorizationStorage);

        ReadUserProfileQuery command = new ReadUserProfileQuery(token, new UserId("asas@h.com"));

        ReadingInfoAboutUser profileReadManagementProcess = new ReadingInfoAboutUser(userStorageInMemory,
                authorizationStorage);

        Assertions.assertThrows(InvalidHandleCommandException.class,
                () -> profileReadManagementProcess.handle(command));
    }


}