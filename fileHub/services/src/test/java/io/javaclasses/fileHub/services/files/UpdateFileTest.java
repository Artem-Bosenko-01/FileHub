package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorageInMemory;
import io.javaclasses.fileHub.persistent.files.MimeType;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UpdateFileTest {

    @Test
    public void updateInfoAboutFileByTest() throws InvalidHandleCommandException {

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FolderId folderID = new FolderId("folder", fileSystemTestData.id());

        FileId creteFileId = fileSystemTestData.createFile(fileStorageInMemory);

        UpdateFileCommand command = new UpdateFileCommand(fileSystemTestData.token(), creteFileId, "lkijij",
                MimeType.GIF, 65, fileSystemTestData.id(), folderID);

        UpdateFile process = new UpdateFile(fileStorageInMemory, authorizationStorage);

        FileId updateFileId = process.handle(command);

        Assertions.assertEquals(updateFileId, creteFileId);

    }


    @Test
    public void failedUpdateInfoAboutFileByNotExistIdTest() throws InvalidHandleCommandException {

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        UserId userID = new UserId("artem@gmail.com");

        fileSystemTestData.createFile(fileStorageInMemory);

        FolderId folderID = new FolderId("JHGF", userID);

        UpdateFileCommand command = new UpdateFileCommand(new AuthToken("1"), new FileId("csac",
                userID, folderID), "lkijij",
                MimeType.GIF, 65, new UserId("abc"), folderID);

        UpdateFile process = new UpdateFile(fileStorageInMemory, authorizationStorage);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> process.handle(command));

    }
}