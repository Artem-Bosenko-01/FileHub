package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.persistent.files.content.FileContentStorageInMemory;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.files.*;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;


class GettingFileContentTest {

    private byte[] createFile(FileContentStorageInMemory fileStorageInMemory, FileId id, byte[] content)
            throws InvalidHandleCommandException {

        CreateFileContentCommand contentCommand = new CreateFileContentCommand(
                new AuthToken(UUID.randomUUID().toString()),
                id, content);

        CreatingFileContent creatingFileContent = new CreatingFileContent(fileStorageInMemory);

        return creatingFileContent.handle(contentCommand);

    }


    @Test
    public void readInfoAboutFileByUserIdTest() throws InvalidHandleCommandException {

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();

        UserId userID = new UserId("Artem");

        FolderId folderID = new FolderId("folder", userID);

        FileId fileID = new FileId("fil", userID, folderID);

        byte[] createFileContent = createFile(contentStorageInMemory, fileID, new byte[]{5, 6, 7, 55, 2});

        GetFileContentQuery command = new GetFileContentQuery(new AuthToken("1"), fileID);

        GettingFileContent viewByUser = new GettingFileContent(contentStorageInMemory);

        GetFileContentDTO getFileContentDTO = viewByUser.handle(command);

        Assertions.assertNotNull(getFileContentDTO);

        Assertions.assertEquals(getFileContentDTO.content(), createFileContent);

    }


    @Test
    public void failedReadInfoByNotExistIdTest() throws InvalidHandleCommandException {

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();

        UserId userID = new UserId("Artem");

        FolderId folderID = new FolderId("folder", userID);

        FileId fileID = new FileId("fil", userID, folderID);

        createFile(contentStorageInMemory, fileID, new byte[]{5, 6, 55, 2});

        fileID = new FileId("JHGF", userID, folderID);

        GetFileContentQuery command = new GetFileContentQuery(new AuthToken("1"),
                fileID);

        GettingFileContent viewByUser = new GettingFileContent(contentStorageInMemory);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> viewByUser.handle(command));

    }
}