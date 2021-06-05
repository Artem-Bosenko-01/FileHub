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
import io.javaclasses.fileHub.services.files.CreateFolderCommand;
import io.javaclasses.fileHub.services.files.CreatingFolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CreatingFolderTest {

    @Test
    public void createFolderTest() throws InvalidHandleCommandException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        CreateFolderCommand createFolderCommand = new CreateFolderCommand(fileSystemTestData.token(),
                "folder", fileSystemTestData.id(), null);

        CreatingFolder createFileManagementProcess = new CreatingFolder(folderStorage, authorizationStorage);

        FolderId id = createFileManagementProcess.handle(createFolderCommand);

        Assertions.assertNotNull(id);

    }

    @Test
    public void createFileWithExistIdTest() throws InvalidHandleCommandException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        CreateFolderCommand createFolderCommand = new CreateFolderCommand(fileSystemTestData.token(),
                "folder", fileSystemTestData.id(), null);

        CreateFolderCommand createFolderCommandERROR = new CreateFolderCommand(fileSystemTestData.token(),
                "folder", fileSystemTestData.id(), null);

        CreatingFolder createFileManagementProcess = new CreatingFolder(folderStorage, authorizationStorage);

        createFileManagementProcess.handle(createFolderCommand);
        Assertions.assertThrows(InvalidHandleCommandException.class,
                () -> createFileManagementProcess.handle(createFolderCommandERROR));

    }
}