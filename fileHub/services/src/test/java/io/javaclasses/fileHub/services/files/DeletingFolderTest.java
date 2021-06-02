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

    private FolderId createFolder(FolderStorage folderStorage)
            throws InvalidHandleCommandException {

        CreateFolderCommand createFolderCommand = FolderTestData.createFolder();

        CreatingFolder creatingFolder = new CreatingFolder(folderStorage);

        return creatingFolder.handle(createFolderCommand);

    }


    @Test
    public void deleteFolderByIdTest() throws InvalidHandleCommandException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        FolderId id = createFolder(folderStorage);

        Assertions.assertEquals(folderStorage.getSizeRecordsList(), 1);

        DeleteFolderCommand deleteFileCommand = new DeleteFolderCommand(new AuthToken("1"), id);

        DeletingFolder deleteFileProcess = new DeletingFolder(folderStorage);

        deleteFileProcess.handle(deleteFileCommand);

        Assertions.assertEquals(folderStorage.getSizeRecordsList(), 0);

    }


    @Test
    public void deleteFolderWithNotExistedIdTest() throws InvalidHandleCommandException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        createFolder(folderStorage);

        DeleteFolderCommand deleteFolderCommand = new DeleteFolderCommand(new AuthToken("1"),
                new FolderId("name", new UserId("vadvdva")));

        DeletingFolder deleteFileProcess = new DeletingFolder(folderStorage);

        Assertions.assertThrows(InvalidHandleCommandException.class,
                () -> deleteFileProcess.handle(deleteFolderCommand));

    }
}
