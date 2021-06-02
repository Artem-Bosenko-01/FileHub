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

class CreatingFileTest {

    @Test
    public void createFileTest() throws InvalidHandleCommandException {

        UserId userID = new UserId("artem@gmail.com");

        FolderId folderID = new FolderId("folder", userID);

        CreateFileCommand createFileCommand = new CreateFileCommand(new AuthToken("651"),
                "file.txt", MimeType.TEXT, userID, folderID);

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        CreatingFile createFileManagementProcess = new CreatingFile(fileStorageInMemory);

        FileId id = createFileManagementProcess.handle(createFileCommand);

        Assertions.assertNotNull(id);

    }

    @Test
    public void createFileWithExistIdTest() throws InvalidHandleCommandException {

        UserId userID = new UserId("artem@gmail.com");

        FolderId folderID = new FolderId("folder", userID);

        CreateFileCommand createFileCommand = new CreateFileCommand(new AuthToken("651"),
                "file.txt", MimeType.TEXT, userID, folderID);

        CreateFileCommand createFileCommandERROR = new CreateFileCommand(new AuthToken("23654"),
                "file.txt", MimeType.TEXT, userID, folderID);

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        CreatingFile createFileManagementProcess = new CreatingFile(fileStorageInMemory);

        createFileManagementProcess.handle(createFileCommand);

        Assertions.assertThrows(InvalidHandleCommandException.class,
                () -> createFileManagementProcess.handle(createFileCommandERROR));

    }
}