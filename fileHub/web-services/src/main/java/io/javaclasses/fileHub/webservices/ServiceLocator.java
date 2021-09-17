package io.javaclasses.fileHub.webservices;

import io.javaclasses.fileHub.services.files.*;
import io.javaclasses.fileHub.services.files.content.GetFileContent;
import io.javaclasses.fileHub.services.files.content.GetFolderContent;
import io.javaclasses.fileHub.services.users.AuthenticateUser;
import io.javaclasses.fileHub.services.users.GetUserInfo;
import io.javaclasses.fileHub.services.users.LogOut;
import io.javaclasses.fileHub.services.users.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Set of processes that allows manages file's and user's systems of FileHub application.
 */
@Component
public class ServiceLocator {

    @Autowired
    private AuthenticateUser authenticateUser;

    @Autowired
    private RegisterUser registerUser;

    @Autowired
    private GetRootFolder getRootFolder;

    @Autowired
    private GetFolderById getFolderById;

    @Autowired
    private GetUserInfo getUserInfo;

    @Autowired
    private GetFolderContent getFolderContent;

    @Autowired
    private DeleteFolder deleteFolder;

    @Autowired
    private DeleteFile deleteFile;

    @Autowired
    private CreateFolder createFolder;

    @Autowired
    private LogOut logOut;

    @Autowired
    private UpdateFile updateFile;

    @Autowired
    private UpdateFolder updateFolder;

    @Autowired
    private UploadFile uploadFile;

    @Autowired
    private GetFileContent getFileContent;

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

    /*@PostConstruct
    public void initDataForDB() {

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


    }*/
}
