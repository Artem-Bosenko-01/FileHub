package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.ValidationCommandDataException;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProfileReadManagementViewTest {


    @Test
    public void readInfoAboutUserByIdTest() throws InvalidCommandHandlingException, ValidationCommandDataException {

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserId id = UserTestData.registerJohnUser(userStorageInMemory);

        AuthToken token = UserTestData.authenticateJohnUser(userStorageInMemory, authorizationStorage);

        GetUserQuery command = new GetUserQuery(token, id);

        GetUserInfo profileReadManagementProcess = new GetUserInfo(userStorageInMemory,
                authorizationStorage);

        InfoAboutUserDto infoAboutUserDto = profileReadManagementProcess.handle(command);

        Assertions.assertEquals(infoAboutUserDto.id(), id);

    }


    @Test
    public void failedReadInfoByNotExistIdTest() throws InvalidCommandHandlingException, ValidationCommandDataException {

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserTestData.registerJohnUser(userStorageInMemory);

        AuthToken token = UserTestData.authenticateJohnUser(userStorageInMemory, authorizationStorage);

        GetUserQuery command = new GetUserQuery(token, new UserId("asas@h.com"));

        GetUserInfo profileReadManagementProcess = new GetUserInfo(userStorageInMemory,
                authorizationStorage);

        Assertions.assertThrows(InvalidCommandHandlingException.class,
                () -> profileReadManagementProcess.handle(command));
    }


}