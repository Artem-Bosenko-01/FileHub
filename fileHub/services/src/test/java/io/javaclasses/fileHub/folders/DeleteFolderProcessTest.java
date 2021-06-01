package io.javaclasses.fileHub.folders;

import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.users.UserID;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;


class DeleteFolderProcessTest {

    private CreateFolderDTO createFolder(FolderStorage folderStorage, String name, UserID userID, FolderID folderID) {
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
    public void deleteFileByIdTest() {
        FolderStorage folderStorage = new FolderStorageInMemory();
        UserID userID = new UserID("Artem");
        FolderID folderID = new FolderID("parent", userID);
        CreateFolderDTO createFolderDTO = createFolder(folderStorage, "folder", userID, folderID);

        Assertions.assertEquals(folderStorage.getSizeRecordsList(), 1);

        DeleteFolderCommand deleteFileCommand = new DeleteFolderCommand(new AuthToken("1"), createFolderDTO.folderID());

        DeleteFolderProcess deleteFileProcess = new DeleteFolderProcess(folderStorage);

        try {
            deleteFileProcess.handle(deleteFileCommand);
            Assertions.assertEquals(folderStorage.getSizeRecordsList(), 0);

        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void deleteFileWithNotExistedIdTest() {
        FolderStorage folderStorage = new FolderStorageInMemory();
        UserID userID = new UserID("Artem");
        FolderID folderID = new FolderID("folder", userID);
        CreateFolderDTO createFolderDTO = createFolder(folderStorage, "file.txt", userID, folderID);

        DeleteFolderCommand deleteFileCommand = new DeleteFolderCommand(new AuthToken("1"),
                new FolderID("name", userID));

        DeleteFolderProcess deleteFileProcess = new DeleteFolderProcess(folderStorage);


        Assertions.assertThrows(InvalidHandleCommandException.class, () -> deleteFileProcess.handle(deleteFileCommand));

    }
}
