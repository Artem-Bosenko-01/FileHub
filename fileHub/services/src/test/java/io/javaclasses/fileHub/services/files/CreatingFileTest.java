package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.*;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreatingFileTest {

    @Test
    public void createFileTest() throws InvalidHandleCommandException {

        UserStorage userStorage = new UserStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        FileStorage fileStorage = new FileStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        CreateFileCommand createFileCommand = new CreateFileCommand(fileSystemTestData.token(), "folder",
                MimeType.TEXT, fileSystemTestData.id(), null);

        CreatingFile createFileManagementProcess = new CreatingFile(fileStorage, authorizationStorage);

        FileId id = createFileManagementProcess.handle(createFileCommand);

        Assertions.assertNotNull(id);

    }

    @Test
    public void createFileWithExistIdTest() throws InvalidHandleCommandException {

        UserStorage userStorage = new UserStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        FileStorage fileStorage = new FileStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        CreateFileCommand createFileCommand = new CreateFileCommand(fileSystemTestData.token(), "folder",
                MimeType.TEXT, fileSystemTestData.id(), null);

        CreateFileCommand createFileCommandERROR = new CreateFileCommand(fileSystemTestData.token(), "folder",
                MimeType.TEXT, fileSystemTestData.id(), null);

        CreatingFile createFileManagementProcess = new CreatingFile(fileStorage, authorizationStorage);

        createFileManagementProcess.handle(createFileCommand);

        Assertions.assertThrows(InvalidHandleCommandException.class,
                () -> createFileManagementProcess.handle(createFileCommandERROR));

    }
}