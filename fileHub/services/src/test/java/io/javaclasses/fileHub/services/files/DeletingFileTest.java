package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorageInMemory;
import io.javaclasses.fileHub.persistent.files.MimeType;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;


class DeletingFileTest {

    private FileId createFile(FileStorageInMemory fileStorageInMemory, String name, UserId userID, FolderId folderID)
            throws InvalidHandleCommandException {

        CreateFileCommand createFileCommand = new CreateFileCommand(new AuthToken(UUID.randomUUID().toString()),
                name, MimeType.TEXT, userID, folderID);

        CreatingFile createFileManagementProcess = new CreatingFile(fileStorageInMemory);

        return createFileManagementProcess.handle(createFileCommand);

    }


    @Test
    public void deleteFileByIdTest() throws InvalidHandleCommandException {

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        UserId userID = new UserId("Artem");

        FolderId folderID = new FolderId("folder", userID);

        FileId id = createFile(fileStorageInMemory, "file.txt", userID, folderID);

        DeleteFileCommand deleteFileCommand = new DeleteFileCommand(new AuthToken("1"), id);

        DeletingFile deletingFile = new DeletingFile(fileStorageInMemory);

        deletingFile.handle(deleteFileCommand);

        Assertions.assertEquals(fileStorageInMemory.getRecordsSize(), 0);

    }


    @Test
    public void deleteFileWithNotExistedIdTest() throws InvalidHandleCommandException {

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        UserId userID = new UserId("Artem");

        FolderId folderID = new FolderId("folder", userID);

        createFile(fileStorageInMemory, "file.txt", userID, folderID);

        DeleteFileCommand deleteFileCommand = new DeleteFileCommand(new AuthToken("1"),
                new FileId("name", userID, folderID));

        DeletingFile deletingFile = new DeletingFile(fileStorageInMemory);


        Assertions.assertThrows(InvalidHandleCommandException.class, () -> deletingFile.handle(deleteFileCommand));

    }
}