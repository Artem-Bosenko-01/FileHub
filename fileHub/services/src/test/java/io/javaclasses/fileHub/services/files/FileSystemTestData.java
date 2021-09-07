package io.javaclasses.fileHub.services.files;

import com.google.common.net.MediaType;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorage;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.ValidationCommandDataException;
import io.javaclasses.fileHub.services.users.UserTestData;

import javax.annotation.Nullable;

public final class FileSystemTestData {

    private final AuthorizationStorage authorizationStorage;
    private final UserId id;
    private final AuthToken token;
    private final byte[] content = new byte[]{5, 6, 8, 4, 5};

    public FileSystemTestData(UserStorage userStorage, AuthorizationStorage authorizationStorage)
            throws InvalidCommandHandlingException, ValidationCommandDataException {

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

    public byte[] content() {
        return content;
    }

    public FileId uploadFile(FileStorage fileStorage, FIleContentStorage fIleContentStorage)
            throws InvalidCommandHandlingException {

        UploadFileCommand command = new UploadFileCommand(token, "folder", MediaType.PLAIN_TEXT_UTF_8, id,
                new FolderId("folder", id), content);

        UploadFile createFile = new UploadFile(fIleContentStorage, fileStorage, authorizationStorage);

        return createFile.doHandle(command);
    }

    public FileId uploadFile(FileStorage fileStorage, FIleContentStorage fIleContentStorage, FolderId parent)
            throws InvalidCommandHandlingException {

        UploadFileCommand command = new UploadFileCommand(token, "folder", MediaType.PLAIN_TEXT_UTF_8, id, parent,
                content);

        UploadFile createFile = new UploadFile(fIleContentStorage, fileStorage, authorizationStorage);

        return createFile.doHandle(command);
    }

    public DeleteFileCommand deleteFileCommand(String name) {

        UserId userID = new UserId("artem@gmail.com");

        return new DeleteFileCommand(
                new AuthToken("6956"),
                new FileId(name, userID, new FolderId("folder", userID))

        );
    }

    public UploadFileCommand uploadFileCommand() {

        return new UploadFileCommand(
                token,
                "file.txt",
                MediaType.PLAIN_TEXT_UTF_8,
                id,
                new FolderId("folder", id),
                content
        );
    }

    public FolderId createFolder(FolderStorage folderStorage, @Nullable FolderId parent)
            throws InvalidCommandHandlingException {

        CreateFolderCommand createFolderCommand = new CreateFolderCommand(token, "folder", id, 8, null);

        CreateFolder creatingFile = new CreateFolder(folderStorage, authorizationStorage);

        return creatingFile.doHandle(createFolderCommand);
    }

    public FolderId createFolder(FolderStorage folderStorage, String name, @Nullable FolderId parent)
            throws InvalidCommandHandlingException {

        CreateFolderCommand createFolderCommand = new CreateFolderCommand(token, name, id, 8, null);

        CreateFolder creatingFile = new CreateFolder(folderStorage, authorizationStorage);

        return creatingFile.doHandle(createFolderCommand);
    }
}
