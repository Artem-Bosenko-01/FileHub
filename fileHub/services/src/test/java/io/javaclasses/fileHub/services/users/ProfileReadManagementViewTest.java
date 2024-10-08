package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class ProfileReadManagementViewTest {


    @Test
    public void readInfoAboutUserByIdTest() throws InvalidCommandHandlingException, InvalidValidationCommandDataException, NotAuthorizedUserException {

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserId id = UserTestData.registerJohnUser(userStorageInMemory);

        AuthToken token = UserTestData.authenticateJohnUser(userStorageInMemory, authorizationStorage);

        GetUserQuery command = new GetUserQuery(token);

        GetUserInfo profileReadManagementProcess = new GetUserInfo(userStorageInMemory,
                authorizationStorage);

        InfoAboutUserDto infoAboutUserDto = profileReadManagementProcess.handle(command);

        Assertions.assertEquals(infoAboutUserDto.id(), id);

    }


    @Test
    public void failedReadInfoByNotExistIdTest() throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

        UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserTestData.registerJohnUser(userStorageInMemory);

        GetUserQuery command = new GetUserQuery(new AuthToken(UUID.randomUUID().toString()));

        GetUserInfo profileReadManagementProcess = new GetUserInfo(userStorageInMemory,
                authorizationStorage);

        Assertions.assertThrows(NotAuthorizedUserException.class,
                () -> profileReadManagementProcess.handle(command));
    }


}