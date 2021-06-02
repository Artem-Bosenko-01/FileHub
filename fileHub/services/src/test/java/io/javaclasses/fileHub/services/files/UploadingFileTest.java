package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorageInMemory;
import io.javaclasses.fileHub.persistent.files.MimeType;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.files.content.FileContentStorageInMemory;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class UploadingFileTest {

    @Test
    public void uploadFileTest() throws InvalidHandleCommandException {

        UploadFileCommand uploadFileCommand = FileTestData.uploadFile();

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();

        UploadingFile uploadingFile = new UploadingFile(contentStorageInMemory, fileStorageInMemory);

        FileId id = uploadingFile.handle(uploadFileCommand);
        Assertions.assertNotNull(id);

    }

    @Test
    public void uploadFileWithExistIdTest() throws InvalidHandleCommandException {

        UploadFileCommand createFileCommand = FileTestData.uploadFile();

        UploadFileCommand createFileCommandERROR = FileTestData.uploadFile();

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();

        UploadingFile createFileManagementProcess = new UploadingFile(contentStorageInMemory, fileStorageInMemory);

        createFileManagementProcess.handle(createFileCommand);

        Assertions.assertThrows(InvalidHandleCommandException.class,
                () -> createFileManagementProcess.handle(createFileCommandERROR));

    }
}