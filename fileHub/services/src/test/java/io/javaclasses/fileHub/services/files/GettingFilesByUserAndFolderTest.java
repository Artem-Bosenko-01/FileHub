package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.*;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.users.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

class GettingFilesByUserAndFolderTest {

    private FileId createFile(FileStorageInMemory fileStorageInMemory, String name)
            throws InvalidHandleCommandException {

        CreateFileCommand createFileCommand = FileTestData.createFile(name);

        CreatingFile createFileManagementProcess = new CreatingFile(fileStorageInMemory);

        return createFileManagementProcess.handle(createFileCommand);

    }


    @Test
    public void readInfoAboutFileByUserIdTest() throws InvalidHandleCommandException {

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        UserId userID = new UserId("artem@gmail.com");

        FolderId folderID = new FolderId("folder", userID);

        FileId id = createFile(fileStorageInMemory, "file.txt");

        GetFilesByUserAndFolderQuery command = new GetFilesByUserAndFolderQuery(new AuthToken("1"),
                folderID, userID);

        GettingFilesByUserAndFolde viewByUser = new GettingFilesByUserAndFolde(fileStorageInMemory);

        List<FileInformation> files = viewByUser.handle(command);

        Assertions.assertEquals(files.get(0).fileID(), id);

    }


    @Test
    public void failedReadInfoByNotExistIdTest() throws InvalidHandleCommandException {

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        UserId userID = new UserId("avasav@gmail.com");

        createFile(fileStorageInMemory, "file.txt");

        FolderId folderID = new FolderId("JHGF", userID);

        GetFilesByUserAndFolderQuery command = new GetFilesByUserAndFolderQuery(new AuthToken("1"),
                folderID, userID);

        GettingFilesByUserAndFolde viewByUser = new GettingFilesByUserAndFolde(fileStorageInMemory);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> viewByUser.handle(command));
    }
}