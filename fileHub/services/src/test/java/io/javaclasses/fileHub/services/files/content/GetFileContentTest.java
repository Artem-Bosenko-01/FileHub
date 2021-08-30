package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.persistent.files.content.FileContentStorageInMemory;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.files.*;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.files.FileSystemTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class GetFileContentTest {

    private byte[] createFileContent(AuthorizationStorage authorizationStorage,
                                     FileContentStorageInMemory fileStorageInMemory,AuthToken token,
                                     FileId id, byte[] content) throws InvalidHandleCommandException {

        CreateFileContentCommand contentCommand = new CreateFileContentCommand(token,id, content);

        CreateFileContent createFileContent = new CreateFileContent(fileStorageInMemory, authorizationStorage);

        return createFileContent.handle(contentCommand);

    }

    @Test
    public void readInfoAboutFileByUserIdTest() throws InvalidHandleCommandException {

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();

        FileStorage fileStorage = new FileStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FileId fileID = fileSystemTestData.createFile(fileStorage);

        byte[] createFileContent = createFileContent(authorizationStorage, contentStorageInMemory, fileSystemTestData.token(),
                fileID, new byte[]{5, 6, 7, 55, 2});

        GetFileContentQuery command = new GetFileContentQuery(fileSystemTestData.token(), fileID);

        GetFileContent viewByUser = new GetFileContent(contentStorageInMemory, authorizationStorage);

        GetFileContentDTO getFileContentDTO = viewByUser.handle(command);

        Assertions.assertNotNull(getFileContentDTO);

        Assertions.assertArrayEquals(getFileContentDTO.content(), createFileContent);

    }


    @Test
    public void failedReadInfoByNotExistIdTest() throws InvalidHandleCommandException {

        FileContentStorageInMemory contentStorageInMemory = new FileContentStorageInMemory();

        FileStorage fileStorage = new FileStorageInMemory();

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();

        UserStorage userStorage = new UserStorageInMemory();

        FileSystemTestData fileSystemTestData = new FileSystemTestData(userStorage, authorizationStorage);

        FileId fileID = fileSystemTestData.createFile(fileStorage);

        createFileContent(authorizationStorage, contentStorageInMemory, fileSystemTestData.token(),
                fileID, new byte[]{5, 6, 7, 55, 2});

        fileID = new FileId("JHGF", fileSystemTestData.id(), new FolderId("newFolder", fileSystemTestData.id()));

        GetFileContentQuery command = new GetFileContentQuery(fileSystemTestData.token(),
                fileID);

        GetFileContent viewByUser = new GetFileContent(contentStorageInMemory, authorizationStorage);

        Assertions.assertThrows(InvalidHandleCommandException.class, () -> viewByUser.handle(command));

    }
}