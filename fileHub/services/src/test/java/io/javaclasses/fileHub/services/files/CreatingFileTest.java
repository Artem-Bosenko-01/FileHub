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

        CreateFileCommand createFileCommand = FileTestData.createFile("file.txt");

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        CreatingFile createFileManagementProcess = new CreatingFile(fileStorageInMemory);

        FileId id = createFileManagementProcess.handle(createFileCommand);

        Assertions.assertNotNull(id);

    }

    @Test
    public void createFileWithExistIdTest() throws InvalidHandleCommandException {


        CreateFileCommand createFileCommand = FileTestData.createFile("file.txt");

        CreateFileCommand createFileCommandERROR = FileTestData.createFile("file.txt");

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        CreatingFile createFileManagementProcess = new CreatingFile(fileStorageInMemory);

        createFileManagementProcess.handle(createFileCommand);

        Assertions.assertThrows(InvalidHandleCommandException.class,
                () -> createFileManagementProcess.handle(createFileCommandERROR));

    }
}