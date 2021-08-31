package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.persistent.files.*;
import io.javaclasses.fileHub.services.files.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class GetFolderContentTest {

    @Test
    public void readFolderContentByIdTest() throws InvalidCommandHandlingException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        FileStorage fileStorage = new FileStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FolderId parent = fileSystemTestData.createFolder(folderStorage, null);

        fileSystemTestData.createFile(fileStorage, parent);

        fileSystemTestData.createFolder(folderStorage, "folder1", parent);

        fileSystemTestData.createFolder(folderStorage, "folder2", parent);


        GetFolderContentQuery query = new GetFolderContentQuery(fileSystemTestData.token(), parent,
                fileSystemTestData.id());

        GetFolderContent view = new GetFolderContent(folderStorage, fileStorage, authorizationStorage);

        GetFolderContentDTO folderContentDTO = view.handle(query);

        Assertions.assertNotNull(folderContentDTO);

    }


    /*@Test
    public void failedReadFolderInfoByNotExistIdTest(){
        FileContentStorageInMemory contentStorageInMemory  = new FileContentStorageInMemory();
        UserID userID = new UserID("Artem");
        FolderID folderID = new FolderID("folder", userID);
        FileID fileID = new FileID("fil", userID,folderID);
        createFile(contentStorageInMemory, fileID, new byte[]{5,6,55,2});

        fileID = new FileID("JHGF", userID, folderID);
        GetFileContentQuery command = new GetFileContentQuery(new AuthToken("1"),
                fileID);
        GetFileContentView viewByUser = new GetFileContentView(contentStorageInMemory);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> viewByUser.handle(command));
    }*/
}