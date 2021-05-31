package io.javaclasses.fileHub.files.content;

import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.files.*;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;


class GetFileContentViewTest {
    private CreateFileContentDTO createFile(FileContentStorageInMemory fileStorageInMemory, FileID id, byte[] content){
        CreateFileContentCommand contentCommand = new CreateFileContentCommand(new AuthToken(UUID.randomUUID().toString()),
               id, content);

        CreateFileContentProcess createFileContentProcess = new CreateFileContentProcess(fileStorageInMemory);

        try {
            return createFileContentProcess.handle(contentCommand);
        } catch (InvalidHandleCommandException e) {
            throw new RuntimeException(e.getCause());
        }
    }


    @Test
    public void readInfoAboutFileByUserIdTest(){
        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();
        UserID userID = new UserID("Artem");
        FolderID folderID = new FolderID("folder", userID);
        FileID fileID = new FileID("fil", userID,folderID);
        CreateFileContentDTO createFileContentDTO = createFile(contentStorageInMemory, fileID, new byte[]{5,6,7,55,2});

        GetFileContentQuery command = new GetFileContentQuery(new AuthToken("1"),fileID);
        GetFileContentView viewByUser = new GetFileContentView(contentStorageInMemory);

        try {
            GetFileContentDTO getFileContentDTO = viewByUser.handle(command);
            Assertions.assertNotNull(getFileContentDTO);
            Assertions.assertEquals(getFileContentDTO.content(),createFileContentDTO.content());

        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void failedReadInfoByNotExistIdTest(){
        FileContentStorageInMemory contentStorageInMemory  = new FileContentStorageInMemory();
        UserID userID = new UserID("Artem");
        FolderID folderID = new FolderID("folder", userID);
        FileID fileID = new FileID("fil", userID,folderID);
        createFile(contentStorageInMemory, fileID, new byte[]{5,6,55,2});

        fileID = new FileID("JHGF", userID, folderID);
        GetFileContentQuery command = new GetFileContentQuery(new AuthToken("1"),
                fileID);
        GetFileContentView viewByUser = new GetFileContentView(contentStorageInMemory);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> viewByUser.handle(command));
    }
}