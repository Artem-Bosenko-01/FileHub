package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.files.FolderStorageInMemory;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.files.CreateFolderCommand;
import io.javaclasses.fileHub.services.files.CreatingFolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CreatingFolderTest {

    @Test
    public void createFolderTest() throws InvalidHandleCommandException {

        UserId userID = new UserId("artem@gmail.com");

        FolderId folderID = new FolderId("parent", userID);

        CreateFolderCommand createFileCommand = new CreateFolderCommand(new AuthToken("651"),
                "folder", userID, folderID);

        FolderStorage folderStorage = new FolderStorageInMemory();

        CreatingFolder createFileManagementProcess = new CreatingFolder(folderStorage);

        FolderId id = createFileManagementProcess.handle(createFileCommand);

        Assertions.assertNotNull(id);

    }

    @Test
    public void createFileWithExistIdTest() throws InvalidHandleCommandException {

        UserId userID = new UserId("artem@gmail.com");

        FolderId folderID = new FolderId("parent", userID);

        CreateFolderCommand createFolderCommand = new CreateFolderCommand(new AuthToken("651"),
                "folder", userID, folderID);

        CreateFolderCommand createFolderCommandERROR = new CreateFolderCommand(new AuthToken("4198529"),
                "folder", userID, folderID);

        FolderStorage folderStorage = new FolderStorageInMemory();

        CreatingFolder createFileManagementProcess = new CreatingFolder(folderStorage);

        createFileManagementProcess.handle(createFolderCommand);
        Assertions.assertThrows(InvalidHandleCommandException.class,
                () -> createFileManagementProcess.handle(createFolderCommandERROR));

    }
}