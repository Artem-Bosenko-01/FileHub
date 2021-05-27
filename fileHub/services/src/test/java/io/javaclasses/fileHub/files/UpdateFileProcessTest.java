package io.javaclasses.fileHub.files;

import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class UpdateFileProcessTest {
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

        UpdateFileCommand command = new UpdateFileCommand(new AuthToken("1"), createFileDTO.fileID(), "lkijij",
                MimeType.GIF, 65,new UserID("abc"), folderID);
        UpdateFileProcess process = new UpdateFileProcess(fileStorageInMemory);

        try {
            CreateFileDTO updateFile = process.handle(command);
            Assertions.assertEquals(updateFile.fileID(), createFileDTO.fileID());

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
        UpdateFileCommand command = new UpdateFileCommand(new AuthToken("1"), new FileID("csac",
                userID,folderID), "lkijij",
                MimeType.GIF, 65,new UserID("abc"), folderID);
        UpdateFileProcess process = new UpdateFileProcess(fileStorageInMemory);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> {
            process.handle(command);
        });
    }
}