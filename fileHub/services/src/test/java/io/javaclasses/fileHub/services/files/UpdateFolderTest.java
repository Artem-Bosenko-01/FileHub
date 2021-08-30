package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.files.FolderStorageInMemory;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.files.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;


class UpdateFolderTest {

    @Test
    public void updateInfoAboutFolderByTest() throws InvalidHandleCommandException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FolderId id = fileSystemTestData.createFolder(folderStorage, null);

        UpdateFolderCommand command = new UpdateFolderCommand(fileSystemTestData.token(), id,
                "lkijij", fileSystemTestData.id(), null);

        UpdateFolder process = new UpdateFolder(folderStorage, authorizationStorage);

        FolderId updateFolderId = process.handle(command);

        Assertions.assertEquals(updateFolderId, id);

    }


    @Test
    public void failedUpdateInfoAboutFolderByNotExistIdTest() throws InvalidHandleCommandException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        fileSystemTestData.createFolder(folderStorage, null);

        FolderId folderID = new FolderId("parent", fileSystemTestData.id());


        UpdateFolderCommand command = new UpdateFolderCommand(new AuthToken("1"),
                new FolderId("newFolder",
                        fileSystemTestData.id()),
                "lkijij", fileSystemTestData.id(), folderID);

        UpdateFolder process = new UpdateFolder(folderStorage, authorizationStorage);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> process.handle(command));
    }

}