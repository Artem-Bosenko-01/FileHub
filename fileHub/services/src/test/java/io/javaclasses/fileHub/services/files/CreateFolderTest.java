package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.files.FolderStorageInMemory;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.ValidationCommandDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CreateFolderTest {

    @Test
    public void createFolderTest() throws InvalidCommandHandlingException, ValidationCommandDataException, NotAuthorizedUserException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        CreateFolderCommand createFolderCommand = new CreateFolderCommand(fileSystemTestData.token(),
                "folder", 9, null);

        CreateFolder createFileManagementProcess = new CreateFolder(folderStorage, authorizationStorage);

        FolderId id = createFileManagementProcess.handle(createFolderCommand);

        Assertions.assertNotNull(id);

    }

    @Test
    public void createFileWithExistIdTest() throws InvalidCommandHandlingException, ValidationCommandDataException, NotAuthorizedUserException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        CreateFolderCommand createFolderCommand = new CreateFolderCommand(fileSystemTestData.token(),
                "folder", 7, null);

        CreateFolderCommand createFolderCommandERROR = new CreateFolderCommand(fileSystemTestData.token(),
                "folder", 8, null);

        CreateFolder createFileManagementProcess = new CreateFolder(folderStorage, authorizationStorage);

        createFileManagementProcess.handle(createFolderCommand);
        Assertions.assertThrows(InvalidCommandHandlingException.class,
                () -> createFileManagementProcess.handle(createFolderCommandERROR));

    }
}