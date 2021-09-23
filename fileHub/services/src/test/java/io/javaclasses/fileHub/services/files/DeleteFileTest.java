package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorageInMemory;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.files.FolderStorageInMemory;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.files.content.FileContentStorageInMemory;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class DeleteFileTest {


    @Test
    public void deleteFileByIdTest() throws InvalidCommandHandlingException, InvalidValidationCommandDataException, NotAuthorizedUserException {

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        FolderStorage folderStorage = new FolderStorageInMemory();

        FIleContentStorage fIleContentStorage = new FileContentStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FileId id = new FileId(fileSystemTestData.uploadFile(fileStorageInMemory, fIleContentStorage, folderStorage));

        DeleteFileCommand deleteFileCommand = new DeleteFileCommand(fileSystemTestData.token(), id.value());

        DeleteFile deleteFile = new DeleteFile(fileStorageInMemory, folderStorage, authorizationStorage);

        deleteFile.handle(deleteFileCommand);

        Assertions.assertEquals(fileStorageInMemory.getRecordsSize(), 0);

    }


    @Test
    public void deleteFileWithNotExistedIdTest() throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        FolderStorage folderStorage = new FolderStorageInMemory();

        FIleContentStorage fIleContentStorage = new FileContentStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        fileSystemTestData.uploadFile(fileStorageInMemory, fIleContentStorage, folderStorage);

        DeleteFileCommand deleteFileCommand = new DeleteFileCommand(fileSystemTestData.token(), "fileIdacacsa");

        DeleteFile deleteFile = new DeleteFile(fileStorageInMemory, folderStorage, authorizationStorage);


        Assertions.assertThrows(InvalidCommandHandlingException.class, () -> deleteFile.handle(deleteFileCommand));

    }
}