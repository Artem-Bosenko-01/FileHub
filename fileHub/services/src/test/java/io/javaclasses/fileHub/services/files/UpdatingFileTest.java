package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorageInMemory;
import io.javaclasses.fileHub.persistent.files.MimeType;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class UpdatingFileTest {

    private FileId createFile(FileStorageInMemory fileStorageInMemory, String name,
                              UserId userID, FolderId folderID) throws InvalidHandleCommandException {

        CreateFileCommand createFileCommand = new CreateFileCommand(new AuthToken(UUID.randomUUID().toString()),
                name, MimeType.TEXT, userID, folderID);

        CreatingFile createFileManagementProcess = new CreatingFile(fileStorageInMemory);

        return createFileManagementProcess.handle(createFileCommand);

    }


    @Test
    public void updateInfoAboutFileByTest() throws InvalidHandleCommandException {

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        UserId userID = new UserId("Artem");

        FolderId folderID = new FolderId("folder", userID);

        FileId creteFileId = createFile(fileStorageInMemory, "file.txt", userID, folderID);

        UpdateFileCommand command = new UpdateFileCommand(new AuthToken("1"), creteFileId, "lkijij",
                MimeType.GIF, 65, new UserId("abc"), folderID);

        UpdatingFile process = new UpdatingFile(fileStorageInMemory);

        FileId updateFileId = process.handle(command);

        Assertions.assertEquals(updateFileId, creteFileId);

    }


    @Test
    public void failedUpdateInfoAboutFileByNotExistIdTest() throws InvalidHandleCommandException {

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        UserId userID = new UserId("Artem");

        FolderId folderID = new FolderId("folder", userID);

        createFile(fileStorageInMemory, "file.txt", userID, folderID);

        folderID = new FolderId("JHGF", userID);

        UpdateFileCommand command = new UpdateFileCommand(new AuthToken("1"), new FileId("csac",
                userID, folderID), "lkijij",
                MimeType.GIF, 65, new UserId("abc"), folderID);

        UpdatingFile process = new UpdatingFile(fileStorageInMemory);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> process.handle(command));

    }
}