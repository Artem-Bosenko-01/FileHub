package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.persistent.files.FileStorage;
import io.javaclasses.fileHub.persistent.files.FileStorageInMemory;
import io.javaclasses.fileHub.persistent.files.content.FileContentStorageInMemory;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.services.files.FileSystemTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CreatingFileContentTest {

    @Test
    public void createFileContentTest() throws InvalidHandleCommandException {

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();

        FileStorage fileStorage = new FileStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FileId fileID = fileSystemTestData.createFile(fileStorage);

        CreateFileContentCommand fileContentCommand = new CreateFileContentCommand(fileSystemTestData.token(), fileID,
                new byte[]{5, 7, 6, 2, 0, 4, 1, 5});

        CreatingFileContent fileContentProcess = new CreatingFileContent(contentStorageInMemory, authorizationStorage);

        byte[] id = fileContentProcess.handle(fileContentCommand);

        Assertions.assertNotNull(id);

    }

    @Test
    public void createFileContentForExistedFileIDTest() throws InvalidHandleCommandException {

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();

        FileStorage fileStorage = new FileStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FileId fileID = fileSystemTestData.createFile(fileStorage);

        CreateFileContentCommand fileContentCommand = new CreateFileContentCommand(fileSystemTestData.token(), fileID,
                new byte[]{5, 7, 6, 2, 0, 4, 1, 5});

        CreateFileContentCommand fileContentCommandERROR = new CreateFileContentCommand(fileSystemTestData.token(),
                fileID, new byte[]{7, 9, 1});


        CreatingFileContent fileContentProcess = new CreatingFileContent(contentStorageInMemory, authorizationStorage);

        fileContentProcess.handle(fileContentCommand);

        Assertions.assertThrows(InvalidHandleCommandException.class,
                () -> fileContentProcess.handle(fileContentCommandERROR));

    }
}