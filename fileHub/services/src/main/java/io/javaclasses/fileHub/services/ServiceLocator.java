package io.javaclasses.fileHub.services;

import com.google.common.net.MediaType;
import io.javaclasses.fileHub.persistent.DuplicatedUserIdException;
import io.javaclasses.fileHub.persistent.files.*;
import io.javaclasses.fileHub.persistent.users.User;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationUsers;
import io.javaclasses.fileHub.persistent.users.tokens.UserAuthToken;
import io.javaclasses.fileHub.services.files.*;
import io.javaclasses.fileHub.services.files.content.GetFolderContent;
import io.javaclasses.fileHub.services.users.AuthenticateUser;
import io.javaclasses.fileHub.services.users.GetUserInfo;
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

    public ServiceLocator() {

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();
        UserStorage userStorage = new UserStorageInMemory();
        FolderStorage folderStorage = new FolderStorageInMemory();
        FileStorage fileStorage = new FileStorageInMemory();

        initDataForDB(authorizationStorage, userStorage, folderStorage, fileStorage);

        registerUser = new RegisterUser(userStorage);
        authenticateUser = new AuthenticateUser(userStorage, authorizationStorage);
        getRootFolder = new GetRootFolder(authorizationStorage, folderStorage);
        getFolderById = new GetFolderById(folderStorage, authorizationStorage);
        getUserInfo = new GetUserInfo(userStorage, authorizationStorage);
        getFolderContent = new GetFolderContent(folderStorage, fileStorage, authorizationStorage);
        deleteFolder = new DeleteFolder(folderStorage, authorizationStorage);
        deleteFile = new DeleteFile(fileStorage, authorizationStorage);
        createFolder = new CreateFolder(folderStorage, authorizationStorage);
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

    private void initDataForDB(AuthorizationStorage authorizationStorage, UserStorage userStorage, FolderStorage folderStorage, FileStorage fileStorage) {

        UserId id = new UserId("id");

        User user = new User(id);
        user.setLogin("artrms@kasc.com");
        user.setPassword("41b0af881656217cc2fa99a55a07c61fc4c9d855bd837695b19c5bf2e4f46d38");

        AuthorizationUsers authorizedUser = new AuthorizationUsers(new UserAuthToken("token"), id, ZonedDateTime.now(ZoneId.of("America/Los_Angeles")).plusHours(6));

        Folder folder = new Folder(new FolderId("folder", id));
        folder.setName("folder");
        folder.setItemsAmount(5);
        folder.setOwner(id);
        folder.setParentFolder(null);

        Folder folder2 = new Folder(new FolderId("dcsdcsdv", id));
        folder2.setName("dcsdcsdv");
        folder2.setItemsAmount(666);
        folder2.setOwner(id);
        folder2.setParentFolder(folder.id().toString());

        File file = new File(new FileId("test_file.txt", id, folder.id()));
        file.setName("test_file.txt");
        file.setFolder(folder.id().toString());
        file.setMimeType(MediaType.GIF);
        file.setSize(564651894);
        file.setUserID(id);

        try {
            authorizationStorage.create(authorizedUser);
            userStorage.create(user);
            folderStorage.create(folder);
            folderStorage.create(folder2);
            fileStorage.create(file);
        } catch (DuplicatedUserIdException e) {
            e.printStackTrace();
        }


    }
}
