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


class DeletingFolderTest {

    private FolderId createFolder(FolderStorage folderStorage, String name, UserId userID, FolderId folderID)
            throws InvalidHandleCommandException {

        CreateFolderCommand createFolderCommand = new CreateFolderCommand(new AuthToken(UUID.randomUUID().toString()),
                name, userID, folderID);

        CreatingFolder creatingFolder = new CreatingFolder(folderStorage);

        return creatingFolder.handle(createFolderCommand);

    }


    @Test
    public void deleteFileByIdTest() throws InvalidHandleCommandException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        UserId userID = new UserId("Artem");

        FolderId folderID = new FolderId("parent", userID);

        FolderId id = createFolder(folderStorage, "folder", userID, folderID);

        Assertions.assertEquals(folderStorage.getSizeRecordsList(), 1);

        DeleteFolderCommand deleteFileCommand = new DeleteFolderCommand(new AuthToken("1"), id);

        DeletingFolder deleteFileProcess = new DeletingFolder(folderStorage);

        deleteFileProcess.handle(deleteFileCommand);

        Assertions.assertEquals(folderStorage.getSizeRecordsList(), 0);

    }


    @Test
    public void deleteFileWithNotExistedIdTest() throws InvalidHandleCommandException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        UserId userID = new UserId("Artem");

        FolderId folderID = new FolderId("folder", userID);

        createFolder(folderStorage, "file.txt", userID, folderID);

        DeleteFolderCommand deleteFileCommand = new DeleteFolderCommand(new AuthToken("1"),
                new FolderId("name", userID));

        DeletingFolder deleteFileProcess = new DeletingFolder(folderStorage);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> deleteFileProcess.handle(deleteFileCommand));

    }
}
