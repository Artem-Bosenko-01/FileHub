package io.javaclasses.fileHub.folders;

import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.users.UserID;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;


class UpdateFolderProcessTest {

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
    public void updateInfoAboutFolderByTest(){
        FolderStorage folderStorage = new FolderStorageInMemory();
        UserID userID = new UserID("Artem");
        FolderID folderID = new FolderID("parent", userID);
        CreateFolderDTO createFolderDTO = createFolder(folderStorage, "folder", userID, folderID);

        UpdateFolderCommand command = new UpdateFolderCommand(new AuthToken("1"), createFolderDTO.folderID(),
                "lkijij", userID, folderID);

        UpdateFolderProcess process = new UpdateFolderProcess(folderStorage);

        try {
            UpdateFolderDTO updateFolderDTO = process.handle(command);
            Assertions.assertEquals(updateFolderDTO.id(), createFolderDTO.folderID());

        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void failedUpdateInfoAboutFolderByNotExistIdTest(){
        FolderStorage folderStorage = new FolderStorageInMemory();
        UserID userID = new UserID("Artem");
        FolderID folderID = new FolderID("parent", userID);

        UpdateFolderCommand command = new UpdateFolderCommand(new AuthToken("1"), new FolderID("newFolder",
                userID),
                "lkijij", userID, folderID);

        UpdateFolderProcess process = new UpdateFolderProcess(folderStorage);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> process.handle(command));
    }

}