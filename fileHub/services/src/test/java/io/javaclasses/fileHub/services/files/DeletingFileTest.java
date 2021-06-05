package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorageInMemory;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.files.FolderId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class DeletingFileTest {


    @Test
    public void deleteFileByIdTest() throws InvalidHandleCommandException {

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FileId id = fileSystemTestData.createFile(fileStorageInMemory);

        DeleteFileCommand deleteFileCommand = new DeleteFileCommand(fileSystemTestData.token(), id);

        DeletingFile deletingFile = new DeletingFile(fileStorageInMemory, authorizationStorage);

        deletingFile.handle(deleteFileCommand);

        Assertions.assertEquals(fileStorageInMemory.getRecordsSize(), 0);

    }


    @Test
    public void deleteFileWithNotExistedIdTest() throws InvalidHandleCommandException {

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        fileSystemTestData.createFile(fileStorageInMemory);

        DeleteFileCommand deleteFileCommand = new DeleteFileCommand(fileSystemTestData.token(), new FileId("name",
                fileSystemTestData.id(), new FolderId("name", fileSystemTestData.id())));

        DeletingFile deletingFile = new DeletingFile(fileStorageInMemory, authorizationStorage);


        Assertions.assertThrows(InvalidHandleCommandException.class, () -> deletingFile.handle(deleteFileCommand));

    }
}