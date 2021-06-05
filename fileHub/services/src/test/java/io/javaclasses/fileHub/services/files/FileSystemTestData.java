package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.*;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.users.UserTestData;

import javax.annotation.Nullable;

public final class FileSystemTestData {

    private final AuthorizationStorage authorizationStorage;
    private final UserId id;
    private final AuthToken token;

    public FileSystemTestData(UserStorage userStorage, AuthorizationStorage authorizationStorage)
            throws InvalidHandleCommandException {

        this.authorizationStorage = authorizationStorage;

        id = UserTestData.registerJohnUser(userStorage);
        token = UserTestData.authenticateJohnUser(userStorage, authorizationStorage);
    }

    public UserId id() {
        return id;
    }

    public AuthToken token() {
        return token;
    }

    public FileId createFile(FileStorage fileStorage) throws InvalidHandleCommandException {

        CreateFileCommand createFileCommand = new CreateFileCommand(token, "folder", MimeType.TEXT, id,
                new FolderId("folder", id));

        CreatingFile creatingFile = new CreatingFile(fileStorage, authorizationStorage);

        return creatingFile.doHandle(createFileCommand);
    }

    public FileId createFile(FileStorage fileStorage, FolderId parent) throws InvalidHandleCommandException {

        CreateFileCommand createFileCommand = new CreateFileCommand(token, "folder", MimeType.TEXT, id, parent);

        CreatingFile creatingFile = new CreatingFile(fileStorage, authorizationStorage);

        return creatingFile.doHandle(createFileCommand);
    }

    public DeleteFileCommand deleteFile(String name) {

        UserId userID = new UserId("artem@gmail.com");

        return new DeleteFileCommand(
                new AuthToken("6956"),
                new FileId(name, userID, new FolderId("folder", userID))

        );
    }

    public UploadFileCommand uploadFile() {

        return new UploadFileCommand(
                token,
                "file.txt",
                MimeType.TEXT,
                id,
                new FolderId("folder", id),
                new byte[]{1, 5, 8, 7, 5}
        );
    }

    public FolderId createFolder(FolderStorage folderStorage, @Nullable FolderId parent)
            throws InvalidHandleCommandException {

        CreateFolderCommand createFolderCommand = new CreateFolderCommand(token, "folder", id, null);

        CreatingFolder creatingFile = new CreatingFolder(folderStorage, authorizationStorage);

        return creatingFile.doHandle(createFolderCommand);
    }

    public FolderId createFolder(FolderStorage folderStorage, String name, @Nullable FolderId parent)
            throws InvalidHandleCommandException {

        CreateFolderCommand createFolderCommand = new CreateFolderCommand(token, name, id, null);

        CreatingFolder creatingFile = new CreatingFolder(folderStorage, authorizationStorage);

        return creatingFile.doHandle(createFolderCommand);
    }
}
