package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.files.FolderStorageInMemory;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import io.javaclasses.fileHub.services.users.UserTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class UpdateFolderTest {

    @Test
    public void updateInfoAboutFolderByTest() throws InvalidCommandHandlingException, InvalidValidationCommandDataException, NotAuthorizedUserException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FolderId id = fileSystemTestData.createFolder(folderStorage, null);

        UpdateFolderCommand command = new UpdateFolderCommand(fileSystemTestData.token(), id.toString(),
                "lkijij", 0L, null);

        UpdateFolder process = new UpdateFolder(folderStorage, authorizationStorage);

        FolderId updateFolderId = process.handle(command);

        Assertions.assertEquals(updateFolderId, id);

    }


    @Test
    public void failedUpdateInfoAboutFolderByNotExistIdTest() throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        fileSystemTestData.createFolder(folderStorage, null);

        FolderId folderID = new FolderId("parent" + fileSystemTestData.id());

        AuthToken token = UserTestData.authenticateJohnUser(userStorage, authorizationStorage);

        UpdateFolderCommand command = new UpdateFolderCommand(token,
                "newFolder", "lkijij", 0L, folderID.toString());

        UpdateFolder process = new UpdateFolder(folderStorage, authorizationStorage);

        Assertions.assertThrows(InvalidCommandHandlingException.class, () -> process.handle(command));
    }

}