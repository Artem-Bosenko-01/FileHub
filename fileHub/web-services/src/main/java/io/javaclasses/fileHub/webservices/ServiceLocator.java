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
public final class ServiceLocator {

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
}
