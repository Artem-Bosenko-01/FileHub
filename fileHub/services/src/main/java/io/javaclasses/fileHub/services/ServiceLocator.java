package io.javaclasses.fileHub.services;

import com.google.common.net.MediaType;
import io.javaclasses.fileHub.persistent.DuplicatedIdException;
import io.javaclasses.fileHub.persistent.Storage;
import io.javaclasses.fileHub.persistent.files.*;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.files.content.FileContentStorageInMemory;
import io.javaclasses.fileHub.persistent.users.User;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationUsers;
import io.javaclasses.fileHub.persistent.users.tokens.UserAuthToken;
import io.javaclasses.fileHub.services.files.*;
import io.javaclasses.fileHub.services.files.content.GetFileContent;
import io.javaclasses.fileHub.services.files.content.GetFolderContent;
import io.javaclasses.fileHub.services.users.AuthenticateUser;
import io.javaclasses.fileHub.services.users.GetUserInfo;
import io.javaclasses.fileHub.services.users.LogOut;
import io.javaclasses.fileHub.services.users.RegisterUser;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Set of processes that allows manages file's and user's systems of FileHub application.
 */
public class ServiceLocator {

    private final AuthenticateUser authenticateUser;
    private final RegisterUser registerUser;
    private final GetRootFolder getRootFolder;
    private final GetFolderById getFolderById;
    private final GetUserInfo getUserInfo;
    private final GetFolderContent getFolderContent;
    private final DeleteFolder deleteFolder;
    private final DeleteFile deleteFile;
    private final CreateFolder createFolder;
    private final LogOut logOut;
    private final UpdateFile updateFile;
    private final UpdateFolder updateFolder;
    private final UploadFile uploadFile;
    private final GetFileContent getFileContent;

    public ServiceLocator() {

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();
        UserStorage userStorage = new UserStorageInMemory();
        FolderStorage folderStorage = new FolderStorageInMemory();
        FileStorage fileStorage = new FileStorageInMemory();
        FIleContentStorage fIleContentStorage = new FileContentStorageInMemory();

        initDataForDB(authorizationStorage, userStorage, folderStorage, fileStorage);

        registerUser = new RegisterUser(userStorage);
        authenticateUser = new AuthenticateUser(userStorage, authorizationStorage);
        getRootFolder = new GetRootFolder(authorizationStorage, folderStorage);
        getFolderById = new GetFolderById(folderStorage, authorizationStorage);
        getUserInfo = new GetUserInfo(userStorage, authorizationStorage);
        getFolderContent = new GetFolderContent(folderStorage, fileStorage, authorizationStorage);
        deleteFolder = new DeleteFolder(folderStorage, fileStorage, authorizationStorage);
        deleteFile = new DeleteFile(fileStorage, folderStorage, authorizationStorage);
        createFolder = new CreateFolder(folderStorage, authorizationStorage);
        logOut = new LogOut(authorizationStorage);
        updateFile = new UpdateFile(fileStorage, authorizationStorage);
        updateFolder = new UpdateFolder(folderStorage, authorizationStorage);
        uploadFile = new UploadFile(fIleContentStorage, fileStorage, folderStorage, authorizationStorage);
        getFileContent = new GetFileContent(fIleContentStorage, authorizationStorage);
    }

    public AuthenticateUser authenticateUser() {
        return authenticateUser;
    }

    public RegisterUser registerUser() {
        return registerUser;
    }

    public GetRootFolder getRootFolder() {
        return getRootFolder;
    }

    public GetFolderById getFolderById() {
        return getFolderById;
    }

    public GetUserInfo getUser() {
        return getUserInfo;
    }

    public GetFolderContent getFolderContent() {
        return getFolderContent;
    }

    public DeleteFolder deleteFolder() {
        return deleteFolder;
    }

    public DeleteFile deleteFile() {
        return deleteFile;
    }

    public CreateFolder createFolder() {
        return createFolder;
    }

    public LogOut logOut() {
        return logOut;
    }

    public UpdateFile updateFile() {
        return updateFile;
    }

    public UpdateFolder updateFolder() {
        return updateFolder;
    }

    public UploadFile uploadFile() {
        return uploadFile;
    }

    public GetFileContent getFileContent() {
        return getFileContent;
    }

    private static void initDataForDB(Storage<UserAuthToken, AuthorizationUsers> authorizationStorage, Storage<UserId, User> userStorage, Storage<FolderId, Folder> folderStorage, Storage<FileId, File> fileStorage) {

        UserId id = new UserId("id");

        User user = new User(id);
        user.setLogin("artrms@kasc.com");
        user.setPassword("41b0af881656217cc2fa99a55a07c61fc4c9d855bd837695b19c5bf2e4f46d38");

        AuthorizationUsers authorizedUser = new AuthorizationUsers(new UserAuthToken("token"), id, ZonedDateTime.now(ZoneId.of("America/Los_Angeles")).plusHours(6));

        Folder folder = new Folder("folder" + id);
        folder.setName("folder");
        folder.setItemsAmount(5L);
        folder.setOwner(id);
        folder.setParentFolder(null);

        Folder folder2 = new Folder("dcsdcsdv" + id);
        folder2.setName("dcsdcsdv");
        folder2.setItemsAmount(666L);
        folder2.setOwner(id);
        folder2.setParentFolder(folder.id().toString());

        File file = new File("test_file.txt" + id + folder.id());
        file.setName("test_file.txt");
        file.setFolder(folder.id().toString());
        file.setMimeType(MediaType.GIF);
        file.setSize(564651894L);
        file.setUserID(id);

        try {
            authorizationStorage.create(authorizedUser);
            userStorage.create(user);
            folderStorage.create(folder);
            folderStorage.create(folder2);
            fileStorage.create(file);
        } catch (DuplicatedIdException e) {
            e.printStackTrace();
        }


    }
}
