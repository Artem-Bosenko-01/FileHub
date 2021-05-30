package io.javaclasses.fileHub.files;

import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreateFileManagementProcessTest {

    @Test
    public void createFileTest(){
        UserID userID = new UserID("artem@gmail.com");
        FolderID folderID = new FolderID("folder", userID);
        CreateFileCommand createFileCommand = new CreateFileCommand(new AuthToken("651"),
                "file.txt", MimeType.TEXT, userID, folderID);

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();
        CreateFileProcess createFileManagementProcess = new CreateFileProcess(fileStorageInMemory);

        try {
            CreateFileDTO fileDTO = createFileManagementProcess.handle(createFileCommand);
            Assertions.assertNotNull(fileDTO);
        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createFileWithExistIdTest(){
        UserID userID = new UserID("artem@gmail.com");
        FolderID folderID = new FolderID("folder", userID);
        CreateFileCommand createFileCommand = new CreateFileCommand(new AuthToken("651"),
                "file.txt", MimeType.TEXT, userID, folderID);

        CreateFileCommand createFileCommandERROR = new CreateFileCommand(new AuthToken("23654"),
                "file.txt", MimeType.TEXT, userID, folderID);

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();
        CreateFileProcess createFileManagementProcess = new CreateFileProcess(fileStorageInMemory);

        try {
            createFileManagementProcess.handle(createFileCommand);
            Assertions.assertThrows(InvalidHandleCommandException.class,
                    () -> createFileManagementProcess.handle(createFileCommandERROR));
        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }
}