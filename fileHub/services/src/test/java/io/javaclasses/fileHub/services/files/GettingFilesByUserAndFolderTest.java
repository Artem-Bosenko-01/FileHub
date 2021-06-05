package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.*;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.users.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class GettingFilesByUserAndFolderTest {

    @Test
    public void readInfoAboutFileByUserIdTest() throws InvalidHandleCommandException {

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FileId id = fileSystemTestData.createFile(fileStorageInMemory);

        FolderId parent = new FolderId("folder", fileSystemTestData.id());

        GetFilesByUserAndFolderQuery command = new GetFilesByUserAndFolderQuery(fileSystemTestData.token(),
                parent, fileSystemTestData.id());

        GettingFilesByUserAndFolder viewByUser = new GettingFilesByUserAndFolder(fileStorageInMemory, authorizationStorage);

        List<FileInformation> files = viewByUser.handle(command);

        Assertions.assertEquals(files.get(0).fileID(), id);

    }


    @Test
    public void failedReadInfoByNotExistIdTest() throws InvalidHandleCommandException {

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        fileSystemTestData.createFile(fileStorageInMemory);

        UserId userID = new UserId("avdvv");

        FolderId folderID = new FolderId("JHGF", userID);

        GetFilesByUserAndFolderQuery command = new GetFilesByUserAndFolderQuery(fileSystemTestData.token(),
                folderID, userID);

        GettingFilesByUserAndFolder viewByUser = new GettingFilesByUserAndFolder(fileStorageInMemory, authorizationStorage);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> viewByUser.handle(command));
    }
}