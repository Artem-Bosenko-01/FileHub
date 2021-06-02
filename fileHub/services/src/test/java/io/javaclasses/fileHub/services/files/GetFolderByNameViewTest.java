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


class GetFolderByNameViewTest {

    private FolderId createFolder(FolderStorage folderStorage, String name, UserId userID, FolderId folderID)
            throws InvalidHandleCommandException {

        CreateFolderCommand createFolderCommand = new CreateFolderCommand(new AuthToken(UUID.randomUUID().toString()),
                name, userID, folderID);

        CreatingFolder creatingFolder = new CreatingFolder(folderStorage);

        return creatingFolder.handle(createFolderCommand);

    }

    @Test
    public void readInfoAboutFolderByIdTest() throws InvalidHandleCommandException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        UserId userID = new UserId("Artem");

        FolderId folderID = new FolderId("parent", userID);

        FolderId id = createFolder(folderStorage, "folder", userID, folderID);

        GetFolderByNameQuery query = new GetFolderByNameQuery(new AuthToken("1"),
                "folder", userID);

        GettingFolderByName view = new GettingFolderByName(folderStorage);

        GetFolderByNameDto folderByNameDTO = view.handle(query);
        Assertions.assertEquals(folderByNameDTO.folderID(), id);

    }


    @Test
    public void failedReadFolderInfoByNotExistIdTest() throws InvalidHandleCommandException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        UserId userID = new UserId("Artem");

        FolderId folderID = new FolderId("parent", userID);

        createFolder(folderStorage, "folder", userID, folderID);

        Assertions.assertEquals(folderStorage.getSizeRecordsList(), 1);

        GetFolderByNameQuery query = new GetFolderByNameQuery(new AuthToken("1"),
                "JHGF", userID);

        GettingFolderByName view = new GettingFolderByName(folderStorage);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> view.handle(query));
    }
}