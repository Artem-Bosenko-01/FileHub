package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.persistent.files.*;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.files.content.FileContentStorageInMemory;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.ValidationCommandDataException;
import io.javaclasses.fileHub.services.files.FileSystemTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class GetFolderContentTest {

    @Test
    public void readFolderContentByIdTest() throws InvalidCommandHandlingException, ValidationCommandDataException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        FIleContentStorage fIleContentStorage = new FileContentStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        FileStorage fileStorage = new FileStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FolderId parent = fileSystemTestData.createFolder(folderStorage, null);

        fileSystemTestData.uploadFile(fileStorage, fIleContentStorage, parent);

        fileSystemTestData.createFolder(folderStorage, "folder1", parent);

        fileSystemTestData.createFolder(folderStorage, "folder2", parent);


        GetFolderContentQuery query = new GetFolderContentQuery(fileSystemTestData.token(), parent,
                fileSystemTestData.id());

        GetFolderContent view = new GetFolderContent(folderStorage, fileStorage, authorizationStorage);

        GetFolderContentDTO folderContentDTO = view.handle(query);

        Assertions.assertNotNull(folderContentDTO);

    }

}