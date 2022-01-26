package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.persistent.files.*;
import io.javaclasses.fileHub.persistent.files.content.FileContentStorageInMemory;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import io.javaclasses.fileHub.services.NotAuthorizedUserException;
import io.javaclasses.fileHub.services.files.FileSystemTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class GetFileContentTest {

    @Test
    public void readInfoAboutFileByUserIdTest() throws InvalidCommandHandlingException, InvalidValidationCommandDataException, NotAuthorizedUserException {

        FolderStorage folderStorage = new FolderStorageInMemory();

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();

        FileStorage fileStorage = new FileStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FileId fileID = new FileId(fileSystemTestData.uploadFile(fileStorage, contentStorageInMemory, folderStorage));

        byte[] createFileContent = fileSystemTestData.content();

        GetFileContentQuery command = new GetFileContentQuery(fileSystemTestData.token(), fileID.value());

        GetFileContent viewByUser = new GetFileContent(contentStorageInMemory, authorizationStorage);

        GetFileContentDTO getFileContentDTO = viewByUser.handle(command);

        Assertions.assertNotNull(getFileContentDTO);

        Assertions.assertArrayEquals(getFileContentDTO.content(), createFileContent);

    }


    @Test
    public void failedReadInfoByNotExistIdTest() throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FileId fileID = new FileId("JHGF");

        GetFileContentQuery command = new GetFileContentQuery(fileSystemTestData.token(), fileID.value());

        GetFileContent viewByUser = new GetFileContent(contentStorageInMemory, authorizationStorage);

        Assertions.assertThrows(InvalidCommandHandlingException.class, () -> viewByUser.handle(command));

    }
}