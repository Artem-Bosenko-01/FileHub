package io.javaclasses.fileHub.files;

import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;


class FileSystemBrowsingViewByUserTest {

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

        FileSystemBrowsingQuery<UserID> command = new FileSystemBrowsingQuery<>(new AuthToken("1"), userID);
        FileSystemBrowsingViewByUser viewByUser = new FileSystemBrowsingViewByUser(fileStorageInMemory);

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

        userID = new UserID("JHGF");
        FileSystemBrowsingQuery<UserID> command = new FileSystemBrowsingQuery<>(new AuthToken("1"), userID);
        FileSystemBrowsingViewByUser viewByUser = new FileSystemBrowsingViewByUser(fileStorageInMemory);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> {
            viewByUser.handle(command);
        });
    }
}