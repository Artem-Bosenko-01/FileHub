package io.javaclasses.fileHub.files.content;

import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.files.FileID;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CreateFileContentProcessTest {

    @Test
    public void createFileContentTest(){

        UserID userID = new UserID("Adam");
        FileID fileID = new FileID("file.txt", userID, new FolderID("papka", userID));

        CreateFileContentCommand fileContentCommand = new CreateFileContentCommand(new AuthToken("dca"),fileID,
                new byte[]{5,7,6,2,0,4,1,5});

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();
        CreateFileContentProcess fileContentProcess = new CreateFileContentProcess(contentStorageInMemory);

        try {
            CreateFileContentDTO fileContentDTO = fileContentProcess.handle(fileContentCommand);

            Assertions.assertNotNull(fileContentDTO);
            Assertions.assertEquals(fileContentDTO.fileID(), fileID);

        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createFileContentForExistedFileIDTest(){

        UserID userID = new UserID("Artem");
        FileID fileID = new FileID("file.txt", userID, new FolderID("papka", userID));

        CreateFileContentCommand fileContentCommand = new CreateFileContentCommand(new AuthToken("dca"),fileID,
                new byte[]{5,7,6,2,0,4,1,5});

        CreateFileContentCommand fileContentCommandERROR = new CreateFileContentCommand(new AuthToken("fvs"),
                fileID, new byte[]{7,9,1});

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();
        CreateFileContentProcess fileContentProcess = new CreateFileContentProcess(contentStorageInMemory);

        try {
            fileContentProcess.handle(fileContentCommand);
            Assertions.assertThrows(InvalidHandleCommandException.class, () -> fileContentProcess.handle(fileContentCommandERROR));
        } catch (InvalidHandleCommandException e) {
            e.printStackTrace();
        }
    }
}