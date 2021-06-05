package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorageInMemory;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.files.content.FileContentStorageInMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class UploadingFileTest {

    @Test
    public void uploadFileTest() throws InvalidHandleCommandException {

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        UploadFileCommand uploadFileCommand = fileSystemTestData.uploadFile();

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();

        UploadingFile uploadingFile = new UploadingFile(contentStorageInMemory, fileStorageInMemory, authorizationStorage);

        FileId id = uploadingFile.handle(uploadFileCommand);

        Assertions.assertNotNull(id);

    }

    @Test
    public void uploadFileWithExistIdTest() throws InvalidHandleCommandException {

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        UploadFileCommand createFileCommand = fileSystemTestData.uploadFile();

        UploadFileCommand createFileCommandERROR = fileSystemTestData.uploadFile();

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        FIleContentStorage contentStorageInMemory = new FileContentStorageInMemory();

        UploadingFile createFileManagementProcess = new UploadingFile(contentStorageInMemory, fileStorageInMemory,
                authorizationStorage);

        createFileManagementProcess.handle(createFileCommand);

        Assertions.assertThrows(InvalidHandleCommandException.class,
                () -> createFileManagementProcess.handle(createFileCommandERROR));

    }
}