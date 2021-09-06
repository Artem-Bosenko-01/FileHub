package io.javaclasses.fileHub.services;

import io.javaclasses.fileHub.persistent.DuplicatedUserIdException;
import io.javaclasses.fileHub.persistent.files.Folder;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.files.FolderStorageInMemory;
import io.javaclasses.fileHub.persistent.users.User;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.UserStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationUsers;
import io.javaclasses.fileHub.persistent.users.tokens.UserAuthToken;
import io.javaclasses.fileHub.services.files.GetFolderById;
import io.javaclasses.fileHub.services.files.GetRootFolder;
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

    public ServiceLocator() {

        AuthorizationStorage authorizationStorage = new AuthorizationStorageInMemory();
        UserStorage userStorage = new UserStorageInMemory();
        FolderStorage folderStorage = new FolderStorageInMemory();

        initDataForDB(authorizationStorage, userStorage, folderStorage);

        registerUser = new RegisterUser(userStorage);
        authenticateUser = new AuthenticateUser(userStorage, authorizationStorage);
        getRootFolder = new GetRootFolder(authorizationStorage, folderStorage);
        getFolderById = new GetFolderById(folderStorage, authorizationStorage);
        getUserInfo = new GetUserInfo(userStorage, authorizationStorage);
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

    private void initDataForDB(AuthorizationStorage authorizationStorage, UserStorage userStorage, FolderStorage folderStorage) {

        UserId id = new UserId("id");

        User user = new User(id);
        user.setLogin("artrms@kasc.com");
        user.setPassword("sdvdds");

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
        folder2.setParentFolder(folder.id());

        try {
            authorizationStorage.create(authorizedUser);
            userStorage.create(user);
            folderStorage.create(folder);
            folderStorage.create(folder2);
        } catch (DuplicatedUserIdException e) {
            e.printStackTrace();
        }


    }
}
