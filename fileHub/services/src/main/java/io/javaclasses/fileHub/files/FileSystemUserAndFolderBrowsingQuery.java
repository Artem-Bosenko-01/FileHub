package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.Query;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;

/**
 *  This is object, that contains data, that needs to find list of files by {@link FolderID folder} and
 *  {@link UserID owner}.
 *
 * */
public final class FileSystemUserAndFolderBrowsingQuery extends Query {

    private final FolderID folderID;
    private final UserID userID;

    public FileSystemUserAndFolderBrowsingQuery(AuthToken token, FolderID folderID, UserID userID) {
        super(Preconditions.checkNotNull(token));
        this.folderID = Preconditions.checkNotNull(folderID);
        this.userID = Preconditions.checkNotNull(userID);
    }

    public FolderID folderID() {
        return folderID;
    }

    public UserID userID() {
        return userID;
    }
}
