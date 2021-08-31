package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.files.FolderStorageInMemory;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.persistent.users.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class DeleteFolderTest {


    @Test
    public void deleteFolderByIdTest() throws InvalidCommandHandlingException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FolderId id = fileSystemTestData.createFolder(folderStorage, null);

        Assertions.assertEquals(folderStorage.getSizeRecordsList(), 1);

        DeleteFolderCommand deleteFileCommand = new DeleteFolderCommand(fileSystemTestData.token(), id);

        DeleteFolder deleteFileProcess = new DeleteFolder(folderStorage, authorizationStorage);

        deleteFileProcess.handle(deleteFileCommand);

        Assertions.assertEquals(folderStorage.getSizeRecordsList(), 0);

    }


    @Test
    public void deleteFolderWithNotExistedIdTest() throws InvalidCommandHandlingException {


        FolderStorage folderStorage = new FolderStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        fileSystemTestData.createFolder(folderStorage, null);

        DeleteFolderCommand deleteFolderCommand = new DeleteFolderCommand(fileSystemTestData.token(),
                new FolderId("name", new UserId("vadvdva")));

        DeleteFolder deleteFileProcess = new DeleteFolder(folderStorage, authorizationStorage);

        Assertions.assertThrows(InvalidCommandHandlingException.class,
                () -> deleteFileProcess.handle(deleteFolderCommand));

    }
}
