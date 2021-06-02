package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.persistent.files.content.FileContentStorageInMemory;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CreatingFileContentTest {

    @Test
    public void createFileContentTest() throws InvalidHandleCommandException {

        UserId userID = new UserId("Adam");

        FileId fileID = new FileId("file.txt", userID, new FolderId("papka", userID));

        CreateFileContentCommand fileContentCommand = new CreateFileContentCommand(new AuthToken("dca"), fileID,
                new byte[]{5, 7, 6, 2, 0, 4, 1, 5});

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();

        CreatingFileContent fileContentProcess = new CreatingFileContent(contentStorageInMemory);

        byte[] id = fileContentProcess.handle(fileContentCommand);

        Assertions.assertNotNull(id);

    }

    @Test
    public void createFileContentForExistedFileIDTest() throws InvalidHandleCommandException {

        UserId userID = new UserId("Artem");

        FileId fileID = new FileId("file.txt", userID, new FolderId("papka", userID));

        CreateFileContentCommand fileContentCommand = new CreateFileContentCommand(new AuthToken("dca"), fileID,
                new byte[]{5, 7, 6, 2, 0, 4, 1, 5});

        CreateFileContentCommand fileContentCommandERROR = new CreateFileContentCommand(new AuthToken("fvs"),
                fileID, new byte[]{7, 9, 1});

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();

        CreatingFileContent fileContentProcess = new CreatingFileContent(contentStorageInMemory);

        fileContentProcess.handle(fileContentCommand);

        Assertions.assertThrows(InvalidHandleCommandException.class,
                () -> fileContentProcess.handle(fileContentCommandERROR));

    }
}