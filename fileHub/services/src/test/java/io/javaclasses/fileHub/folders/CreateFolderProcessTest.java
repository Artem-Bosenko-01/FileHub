package io.javaclasses.fileHub.folders;

import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.users.UserID;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CreateFolderProcessTest {
    @Test
    public void createFileTest(){
        UserID userID = new UserID("artem@gmail.com");
        FolderID folderID = new FolderID("parent", userID);
        CreateFolderCommand createFileCommand = new CreateFolderCommand(new AuthToken("651"),
                "folder", userID, folderID);

        FolderStorage folderStorage = new FolderStorageInMemory();
        CreateFolderProcess createFileManagementProcess = new CreateFolderProcess(folderStorage);

        try {
            CreateFolderDTO fileDTO = createFileManagementProcess.handle(createFileCommand);
            Assertions.assertNotNull(fileDTO);
        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createFileWithExistIdTest(){
        UserID userID = new UserID("artem@gmail.com");
        FolderID folderID = new FolderID("parent", userID);
        CreateFolderCommand createFolderCommand = new CreateFolderCommand(new AuthToken("651"),
                "folder", userID, folderID);

        CreateFolderCommand createFolderCommandERROR = new CreateFolderCommand(new AuthToken("4198529"),
                "folder", userID, folderID);

        FolderStorage folderStorage = new FolderStorageInMemory();
        CreateFolderProcess createFileManagementProcess = new CreateFolderProcess(folderStorage);

        try {
            createFileManagementProcess.handle(createFolderCommand);
            Assertions.assertThrows(InvalidHandleCommandException.class,
                    () -> createFileManagementProcess.handle(createFolderCommandERROR));
        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }
}