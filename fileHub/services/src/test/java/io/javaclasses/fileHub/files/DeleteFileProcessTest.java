package io.javaclasses.fileHub.files;

import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;


class DeleteFileProcessTest {
    private CreateFileDTO createFile(FileStorageInMemory fileStorageInMemory, String name, UserID userID, FolderID folderID){
        CreateFileCommand createFileCommand = new CreateFileCommand(new AuthToken(UUID.randomUUID().toString()),
                name,MimeType.TEXT,userID,folderID);

        CreateFileProcess createFileManagementProcess = new CreateFileProcess(fileStorageInMemory);

        try {
            return createFileManagementProcess.handle(createFileCommand);
        } catch (InvalidHandleCommandException e) {
            throw new RuntimeException(e.getCause());
        }
    }


    @Test
    public void deleteFileByIdTest(){
        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();
        UserID userID = new UserID("Artem");
        FolderID folderID = new FolderID("folder", userID);
        CreateFileDTO createFileDTO = createFile(fileStorageInMemory, "file.txt", userID, folderID);

        DeleteFileCommand deleteFileCommand = new DeleteFileCommand(new AuthToken("1"), createFileDTO.fileID());

        DeleteFileProcess deleteFileProcess = new DeleteFileProcess(fileStorageInMemory);

        try {
            deleteFileProcess.handle(deleteFileCommand);
            Assertions.assertEquals(fileStorageInMemory.getRecordsSize(), 0);

        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void deleteFileWithNotExistedIdTest(){
        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();
        UserID userID = new UserID("Artem");
        FolderID folderID = new FolderID("folder", userID);
        CreateFileDTO createFileDTO = createFile(fileStorageInMemory, "file.txt", userID, folderID);

        DeleteFileCommand deleteFileCommand = new DeleteFileCommand(new AuthToken("1"),
                new FileID("name",userID,folderID));

        DeleteFileProcess deleteFileProcess = new DeleteFileProcess(fileStorageInMemory);


            Assertions.assertThrows(InvalidHandleCommandException.class, () -> deleteFileProcess.handle(deleteFileCommand));

    }
}