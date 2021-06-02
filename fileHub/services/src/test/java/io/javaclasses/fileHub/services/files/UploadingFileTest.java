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

        UserId userID = new UserId("artem@gmail.com");

        FolderId folderID = new FolderId("folder", userID);

        UploadFileCommand uploadFileCommand = new UploadFileCommand(new AuthToken("651"),
                "file.txt", MimeType.TEXT, userID, folderID, new byte[]{1, 5, 8, 7, 5});

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();

        UploadingFile uploadingFile = new UploadingFile(contentStorageInMemory, fileStorageInMemory);

        FileId id = uploadingFile.handle(uploadFileCommand);
        Assertions.assertNotNull(id);

    }

    @Test
    public void uploadFileWithExistIdTest() throws InvalidHandleCommandException {

        UserId userID = new UserId("artem@gmail.com");

        FolderId folderID = new FolderId("folder", userID);

        UploadFileCommand createFileCommand = new UploadFileCommand(new AuthToken("651"),
                "file.txt", MimeType.TEXT, userID, folderID, new byte[]{1, 5, 8, 7, 5});

        UploadFileCommand createFileCommandERROR = new UploadFileCommand(new AuthToken("23654"),
                "file.txt", MimeType.TEXT, userID, folderID, new byte[]{1, 5});

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();

        UploadingFile createFileManagementProcess = new UploadingFile(contentStorageInMemory, fileStorageInMemory);

        createFileManagementProcess.handle(createFileCommand);
        Assertions.assertThrows(InvalidHandleCommandException.class,
                () -> createFileManagementProcess.handle(createFileCommandERROR));

    }
}