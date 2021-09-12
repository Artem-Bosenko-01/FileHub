package io.javaclasses.fileHub.services.files;

import com.google.common.net.MediaType;
import io.javaclasses.fileHub.persistent.files.FileStorage;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import io.javaclasses.fileHub.services.users.UserTestData;

import javax.annotation.Nullable;

public final class FileSystemTestData {

    private final AuthorizationStorage authorizationStorage;
    private final UserId id;
    private final AuthToken token;
    private final byte[] content = new byte[]{5, 6, 8, 4, 5};

    public FileSystemTestData(UserStorage userStorage, AuthorizationStorage authorizationStorage)
            throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

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

    public String uploadFile(FileStorage fileStorage, FIleContentStorage fIleContentStorage, FolderStorage folderStorage)
            throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

        UploadFileCommand command = new UploadFileCommand(token, "folder", MediaType.PLAIN_TEXT_UTF_8,
                "folder" + id, content);

        UploadFile createFile = new UploadFile(fIleContentStorage, fileStorage, folderStorage, authorizationStorage);

        return createFile.doHandle(command);
    }

    public String uploadFile(FileStorage fileStorage, FIleContentStorage fIleContentStorage, FolderStorage folderStorage,
                             FolderId parent)
            throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

        UploadFileCommand command = new UploadFileCommand(token, "folder", MediaType.PLAIN_TEXT_UTF_8, parent.toString(),
                content);

        UploadFile createFile = new UploadFile(fIleContentStorage, fileStorage, folderStorage, authorizationStorage);

        return createFile.doHandle(command);
    }

    public DeleteFileCommand deleteFileCommand(String name) {

        UserId userID = new UserId("artem@gmail.com");

        return new DeleteFileCommand(
                new AuthToken("6956"),
                name + userID);
    }

    public UploadFileCommand uploadFileCommand() throws InvalidValidationCommandDataException {

        return new UploadFileCommand(
                token,
                "file.txt",
                MediaType.PLAIN_TEXT_UTF_8,
                "folder" + id,
                content
        );
    }

    public FolderId createFolder(FolderStorage folderStorage, @Nullable FolderId parent)
            throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

        CreateFolderCommand createFolderCommand = new CreateFolderCommand(token, "folder", 8, null);

        CreateFolder creatingFile = new CreateFolder(folderStorage, authorizationStorage);

        return creatingFile.doHandle(createFolderCommand);
    }

    public FolderId createFolder(FolderStorage folderStorage, String name, @Nullable FolderId parent)
            throws InvalidCommandHandlingException, InvalidValidationCommandDataException {

        CreateFolderCommand createFolderCommand = new CreateFolderCommand(token, name, 8, null);

        CreateFolder creatingFile = new CreateFolder(folderStorage, authorizationStorage);

        return creatingFile.doHandle(createFolderCommand);
    }
}
