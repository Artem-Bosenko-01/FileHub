package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.files.*;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.files.CreateFileCommand;
import io.javaclasses.fileHub.services.files.CreatingFile;
import io.javaclasses.fileHub.services.files.CreateFolderCommand;
import io.javaclasses.fileHub.services.files.CreatingFolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;


class GettingFolderContentTest {

    private FolderId createFolder(FolderStorage folderStorage, String name, UserId userID, FolderId folderID)
            throws InvalidHandleCommandException {

        CreateFolderCommand createFolderCommand = new CreateFolderCommand(new AuthToken(UUID.randomUUID().toString()),
                name, userID, folderID);

        CreatingFolder creatingFolder = new CreatingFolder(folderStorage);

        return creatingFolder.handle(createFolderCommand);

    }

    private FolderId createFolder(FolderStorage folderStorage, String name, UserId userID)
            throws InvalidHandleCommandException {

        CreateFolderCommand createFolderCommand = new CreateFolderCommand(new AuthToken(UUID.randomUUID().toString()),
                name, userID, null);

        CreatingFolder creatingFolder = new CreatingFolder(folderStorage);

        return creatingFolder.handle(createFolderCommand);

    }

    private FileId createFile(FileStorage fileStorageInMemory, String name, UserId userID, FolderId folderID)
            throws InvalidHandleCommandException {

        CreateFileCommand createFileCommand = new CreateFileCommand(new AuthToken(UUID.randomUUID().toString()),
                name, MimeType.TEXT, userID, folderID);

        CreatingFile createFileManagementProcess = new CreatingFile(fileStorageInMemory);

        return createFileManagementProcess.handle(createFileCommand);

    }


    @Test
    public void readFolderContentByIdTest() throws InvalidHandleCommandException {
        FolderStorage folderStorage = new FolderStorageInMemory();
        FileStorage fileStorage = new FileStorageInMemory();

        UserId userID = new UserId("Artem");

        FolderId parent = createFolder(folderStorage, "parent", userID);

        createFile(fileStorage, "file", userID, parent);

        createFolder(folderStorage, "folder1", userID, parent);

        createFolder(folderStorage, "folder2", userID, parent);


        GetFolderContentQuery query = new GetFolderContentQuery(new AuthToken("1"), parent,
                userID);

        GettingFolderContent view = new GettingFolderContent(folderStorage, fileStorage);

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