package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.files.FolderStorageInMemory;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.files.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;


class GetFolderByNameViewTest {

    @Test
    public void readInfoAboutFolderByIdTest() throws InvalidHandleCommandException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FolderId id = fileSystemTestData.createFolder(folderStorage, null);

        GetFolderByNameQuery query = new GetFolderByNameQuery(fileSystemTestData.token(),
                "folder", fileSystemTestData.id());

        GettingFolderByName view = new GettingFolderByName(folderStorage, authorizationStorage);

        GetFolderByNameDto folderByNameDTO = view.handle(query);

        Assertions.assertEquals(folderByNameDTO.folderID(), id);

    }


    @Test
    public void failedReadFolderInfoByNotExistIdTest() throws InvalidHandleCommandException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        fileSystemTestData.createFolder(folderStorage, null);

        Assertions.assertEquals(folderStorage.getSizeRecordsList(), 1);

        GetFolderByNameQuery query = new GetFolderByNameQuery(fileSystemTestData.token(),
                "JHGF", fileSystemTestData.id());

        GettingFolderByName view = new GettingFolderByName(folderStorage, authorizationStorage);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> view.handle(query));
    }
}