package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorageInMemory;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.persistent.files.content.FileContentStorageInMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class UploadFileTest {

    @Test
    public void uploadFileTest() throws InvalidCommandHandlingException {

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        UploadFileCommand uploadFileCommand = fileSystemTestData.uploadFile();

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();

        UploadFile uploadFile = new UploadFile(contentStorageInMemory, fileStorageInMemory, authorizationStorage);

        FileId id = uploadFile.handle(uploadFileCommand);

        Assertions.assertNotNull(id);

    }

    @Test
    public void uploadFileWithExistIdTest() throws InvalidCommandHandlingException {

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        UploadFileCommand createFileCommand = fileSystemTestData.uploadFile();

        UploadFileCommand createFileCommandERROR = fileSystemTestData.uploadFile();

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        FIleContentStorage contentStorageInMemory = new FileContentStorageInMemory();

        UploadFile createFileManagementProcess = new UploadFile(contentStorageInMemory, fileStorageInMemory,
                authorizationStorage);

        createFileManagementProcess.handle(createFileCommand);

        Assertions.assertThrows(InvalidCommandHandlingException.class,
                () -> createFileManagementProcess.handle(createFileCommandERROR));

    }
}