package io.javaclasses.fileHub.services.files;

import com.google.common.net.MediaType;
import io.javaclasses.fileHub.persistent.files.*;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.files.content.FileContentStorageInMemory;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.users.UserTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UpdateFileTest {

    @Test
    public void updateInfoAboutFileByTest() throws InvalidCommandHandlingException, InvalidValidationCommandDataException, NotAuthorizedUserException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        FIleContentStorage fIleContentStorage = new FileContentStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FolderId folderID = new FolderId("folder" + fileSystemTestData.id().value());

        FileId creteFileId = new FileId(fileSystemTestData.uploadFile(fileStorageInMemory, fIleContentStorage, folderStorage));

        UpdateFileCommand command = new UpdateFileCommand(fileSystemTestData.token(), creteFileId.value(),
                "lkijij", MediaType.GIF, 65L, folderID.value());

        UpdateFile process = new UpdateFile(fileStorageInMemory, authorizationStorage);

        FileId updateFileId = process.handle(command);

        Assertions.assertEquals(updateFileId, creteFileId);

    }


    @Test
    public void failedUpdateInfoAboutFileByNotExistIdTest() throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        FileStorageInMemory fileStorageInMemory = new FileStorageInMemory();

        FIleContentStorage fIleContentStorage = new FileContentStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        UserId userID = new UserId("artem@gmail.com");

        AuthToken token = UserTestData.authenticateJohnUser(userStorage, authorizationStorage);

        fileSystemTestData.uploadFile(fileStorageInMemory, fIleContentStorage, folderStorage);

        FolderId folderID = new FolderId("JHGF" + userID.value());

        UpdateFileCommand command = new UpdateFileCommand(token, new FileId("csac").value(),
                "lkijij", MediaType.GIF, 65L, folderID.value());

        UpdateFile process = new UpdateFile(fileStorageInMemory, authorizationStorage);

        Assertions.assertThrows(FileNotFoundException.class, () -> process.handle(command));

    }
}