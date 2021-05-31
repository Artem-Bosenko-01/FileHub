package io.javaclasses.fileHub.folders.content;

import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.files.FileID;
import io.javaclasses.fileHub.files.FileStorage;
import io.javaclasses.fileHub.files.FileStorageInMemory;
import io.javaclasses.fileHub.files.content.*;
import io.javaclasses.fileHub.folders.*;
import io.javaclasses.fileHub.users.UserID;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GetFolderContentViewTest {

    private CreateFolderDTO createFolder(FolderStorage folderStorage, String name, UserID userID, FolderID folderID){
        CreateFolderCommand createFolderCommand = new CreateFolderCommand(new AuthToken(UUID.randomUUID().toString()),
                name, userID, folderID);

        CreateFolderProcess createFolderProcess = new CreateFolderProcess(folderStorage);

        try {
            return createFolderProcess.handle(createFolderCommand);
        } catch (InvalidHandleCommandException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    @Test
    public void readFolderContentByIdTest(){
        FolderStorage folderStorage = new FolderStorageInMemory();
        FileStorage fileStorage = new FileStorageInMemory();

        UserID userID = new UserID("Artem");
        FolderID folderID = new FolderID("parent", userID);

        CreateFolderDTO createFolder1DTO = createFolder(folderStorage, "folder1", userID, folderID);

        CreateFolderDTO createFolder2DTO = createFolder(folderStorage, "folder2", userID, folderID);


        GetFolderContentQuery query = new GetFolderContentQuery(new AuthToken("1"), folderID,
                userID);
        GetFolderContentView view = new GetFolderContentView(folderStorage, fileStorage);

        try {
            GetFolderContentDTO folderContentDTO = view.handle(query);

            Assertions.assertNotNull(folderContentDTO);

        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }


    /*@Test
    public void failedReadFolderInfoByNotExistIdTest(){
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
    }*/
}