package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.files.FolderStorageInMemory;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.files.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;


class UpdatingFolderTest {

    private FolderId createFolder(FolderStorage folderStorage, String name, UserId userID, FolderId folderID)
            throws InvalidHandleCommandException {

        CreateFolderCommand createFolderCommand = new CreateFolderCommand(new AuthToken(UUID.randomUUID().toString()),
                name, userID, folderID);

        CreatingFolder creatingFolder = new CreatingFolder(folderStorage);

        return creatingFolder.handle(createFolderCommand);

    }


    @Test
    public void updateInfoAboutFolderByTest() throws InvalidHandleCommandException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        UserId userID = new UserId("Artem");

        FolderId folderID = new FolderId("parent", userID);

        FolderId createFolderId = createFolder(folderStorage, "folder", userID, folderID);

        UpdateFolderCommand command = new UpdateFolderCommand(new AuthToken("1"), createFolderId,
                "lkijij", userID, folderID);

        UpdatingFolder process = new UpdatingFolder(folderStorage);

        FolderId updateFolderId = process.handle(command);

        Assertions.assertEquals(updateFolderId, createFolderId);

    }


    @Test
    public void failedUpdateInfoAboutFolderByNotExistIdTest() {

        FolderStorage folderStorage = new FolderStorageInMemory();

        UserId userID = new UserId("Artem");

        FolderId folderID = new FolderId("parent", userID);

        UpdateFolderCommand command = new UpdateFolderCommand(new AuthToken("1"), new FolderId("newFolder",
                userID),
                "lkijij", userID, folderID);

        UpdatingFolder process = new UpdatingFolder(folderStorage);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> process.handle(command));
    }

}