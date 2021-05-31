package io.javaclasses.fileHub.files;

import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.files.content.FileContentStorageInMemory;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class UploadFileProcessTest {
    @Test
    public void uploadFileTest(){
        UserID userID = new UserID("artem@gmail.com");
        FolderID folderID = new FolderID("folder", userID);
        UploadFileCommand uploadFileCommand = new UploadFileCommand(new AuthToken("651"),
                "file.txt", MimeType.TEXT, userID, folderID, new byte[]{1,5,8,7,5});

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();
        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();

        UploadFileProcess uploadFileProcess = new UploadFileProcess(contentStorageInMemory, fileStorageInMemory);

        try {
            UploadFileDTO fileDTO = uploadFileProcess.handle(uploadFileCommand);
            Assertions.assertNotNull(fileDTO);
        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void uploadFileWithExistIdTest(){
        UserID userID = new UserID("artem@gmail.com");
        FolderID folderID = new FolderID("folder", userID);
        UploadFileCommand createFileCommand = new UploadFileCommand(new AuthToken("651"),
                "file.txt", MimeType.TEXT, userID, folderID, new byte[]{1,5,8,7,5});

        UploadFileCommand createFileCommandERROR = new UploadFileCommand(new AuthToken("23654"),
                "file.txt", MimeType.TEXT, userID, folderID, new byte[]{1,5});

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();
        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();


        UploadFileProcess createFileManagementProcess = new UploadFileProcess(contentStorageInMemory,fileStorageInMemory);

        try {
            createFileManagementProcess.handle(createFileCommand);
            Assertions.assertThrows(InvalidHandleCommandException.class,
                    () -> createFileManagementProcess.handle(createFileCommandERROR));
        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }
}