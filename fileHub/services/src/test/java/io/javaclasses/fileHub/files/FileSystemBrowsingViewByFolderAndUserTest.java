package io.javaclasses.fileHub.files;

import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

class FileSystemBrowsingViewByFolderAndUserTest {
    private CreateFileDTO createFile(FileStorageInMemory fileStorageInMemory, String name, UserID userID, FolderID folderID){
        CreateFileCommand createFileCommand = new CreateFileCommand(new AuthToken(UUID.randomUUID().toString()),
                name,MimeType.TEXT,userID,folderID);

        CreateFileManagementProcess createFileManagementProcess = new CreateFileManagementProcess(fileStorageInMemory);

        try {
            return createFileManagementProcess.handle(createFileCommand);
        } catch (InvalidHandleCommandException e) {
            throw new RuntimeException(e.getCause());
        }
    }


    @Test
    public void readInfoAboutFileByUserIdTest(){
        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();
        UserID userID = new UserID("Artem");
        FolderID folderID = new FolderID("folder", userID);
        CreateFileDTO createFileDTO = createFile(fileStorageInMemory, "file.txt", userID, folderID);

        FileSystemUserAndFolderBrowsingQuery command = new FileSystemUserAndFolderBrowsingQuery(new AuthToken("1"),
                folderID, userID);
        FileSystemBrowsingViewByFolderAndUser viewByUser = new FileSystemBrowsingViewByFolderAndUser(fileStorageInMemory);

        try {
            List<File> user = viewByUser.handle(command);
            Assertions.assertEquals(user.get(0).id(), createFileDTO.fileID());

        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void failedReadInfoByNotExistIdTest(){
        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();
        UserID userID = new UserID("Artem");
        FolderID folderID = new FolderID("folder", userID);
        CreateFileDTO createFileDTO = createFile(fileStorageInMemory, "file.txt", userID, folderID);

        folderID = new FolderID("JHGF", userID);
        FileSystemUserAndFolderBrowsingQuery command = new FileSystemUserAndFolderBrowsingQuery(new AuthToken("1"),
                folderID, userID);
        FileSystemBrowsingViewByFolderAndUser viewByUser = new FileSystemBrowsingViewByFolderAndUser(fileStorageInMemory);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> {
            viewByUser.handle(command);
        });
    }
}