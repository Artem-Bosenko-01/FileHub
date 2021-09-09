package io.javaclasses.fileHub.services.files;

import com.google.common.net.MediaType;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorageInMemory;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.files.content.FileContentStorageInMemory;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UpdateFileTest {

    @Test
    public void updateInfoAboutFileByTest() throws InvalidCommandHandlingException, InvalidValidationCommandDataException, NotAuthorizedUserException {

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        FIleContentStorage fIleContentStorage = new FileContentStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FolderId folderID = new FolderId("folder" + fileSystemTestData.id());

        FileId creteFileId = new FileId(fileSystemTestData.uploadFile(fileStorageInMemory, fIleContentStorage));

        UpdateFileCommand command = new UpdateFileCommand(fileSystemTestData.token(), creteFileId.toString(),
                "lkijij", MediaType.GIF.toString(), 65L, folderID.toString());

        UpdateFile process = new UpdateFile(fileStorageInMemory, authorizationStorage);

        FileId updateFileId = process.handle(command);

        Assertions.assertEquals(updateFileId, creteFileId);

    }


    @Test
    public void failedUpdateInfoAboutFileByNotExistIdTest() throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        FIleContentStorage fIleContentStorage = new FileContentStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        UserId userID = new UserId("artem@gmail.com");

        fileSystemTestData.uploadFile(fileStorageInMemory, fIleContentStorage);

        FolderId folderID = new FolderId("JHGF" + userID);

        UpdateFileCommand command = new UpdateFileCommand(new AuthToken("1"), new FileId("csac").toString(),
                "lkijij", MediaType.GIF.toString(), 65L, folderID.toString());

        UpdateFile process = new UpdateFile(fileStorageInMemory, authorizationStorage);

        Assertions.assertThrows(InvalidCommandHandlingException.class, () -> process.handle(command));

    }
}