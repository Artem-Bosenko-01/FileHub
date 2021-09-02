package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorageInMemory;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.files.content.FileContentStorageInMemory;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.ValidationCommandDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class DeleteFileTest {


    @Test
    public void deleteFileByIdTest() throws InvalidCommandHandlingException, ValidationCommandDataException {

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        FIleContentStorage fIleContentStorage = new FileContentStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FileId id = fileSystemTestData.uploadFile(fileStorageInMemory, fIleContentStorage);

        DeleteFileCommand deleteFileCommand = new DeleteFileCommand(fileSystemTestData.token(), id);

        DeleteFile deleteFile = new DeleteFile(fileStorageInMemory, authorizationStorage);

        deleteFile.handle(deleteFileCommand);

        Assertions.assertEquals(fileStorageInMemory.getRecordsSize(), 0);

    }


    @Test
    public void deleteFileWithNotExistedIdTest() throws InvalidCommandHandlingException, ValidationCommandDataException {

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        FIleContentStorage fIleContentStorage = new FileContentStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        fileSystemTestData.uploadFile(fileStorageInMemory, fIleContentStorage);

        DeleteFileCommand deleteFileCommand = new DeleteFileCommand(fileSystemTestData.token(), new FileId("name",
                fileSystemTestData.id(), new FolderId("name", fileSystemTestData.id())));

        DeleteFile deleteFile = new DeleteFile(fileStorageInMemory, authorizationStorage);


        Assertions.assertThrows(InvalidCommandHandlingException.class, () -> deleteFile.handle(deleteFileCommand));

    }
}