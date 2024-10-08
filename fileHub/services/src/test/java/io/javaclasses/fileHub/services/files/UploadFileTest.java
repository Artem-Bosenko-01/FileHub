package io.javaclasses.fileHub.services.files;

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


class UploadFileTest {

    @Test
    public void uploadFileTest() throws InvalidCommandHandlingException, InvalidValidationCommandDataException, NotAuthorizedUserException {

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        UploadFileCommand uploadFileCommand = fileSystemTestData.uploadFileCommand();

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();

        FolderStorage folderStorage = new FolderStorageInMemory();

        UploadFile uploadFile = new UploadFile(contentStorageInMemory, fileStorageInMemory, folderStorage,
                authorizationStorage);

        String id = uploadFile.handle(uploadFileCommand);

        Assertions.assertNotNull(id);

    }

    @Test
    public void uploadFileWithExistIdTest() throws InvalidCommandHandlingException, InvalidValidationCommandDataException, NotAuthorizedUserException {

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        UploadFileCommand createFileCommand = fileSystemTestData.uploadFileCommand();

        UploadFileCommand createFileCommandERROR = fileSystemTestData.uploadFileCommand();

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        FolderStorage folderStorage = new FolderStorageInMemory();

        FIleContentStorage contentStorageInMemory = new FileContentStorageInMemory();

        UploadFile createFileManagementProcess = new UploadFile(contentStorageInMemory, fileStorageInMemory,
                folderStorage, authorizationStorage);

        createFileManagementProcess.handle(createFileCommand);

        Assertions.assertThrows(InvalidCommandHandlingException.class,
                () -> createFileManagementProcess.handle(createFileCommandERROR));

    }
}