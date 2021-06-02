package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.Query;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This is object, that contains data, that needs to find list of files by {@link FolderId folder} and
 * {@link UserId owner}.
 */
public final class GetFilesByUserAndFolderQuery extends Query {

    private final FolderId folderID;
    private final UserId userID;

    public GetFilesByUserAndFolderQuery(AuthToken token, FolderId folderID, UserId userID) {

        super(checkNotNull(token));
        this.folderID = checkNotNull(folderID);
        this.userID = checkNotNull(userID);

    }

    public FolderId folderID() {
        return folderID;
    }

    public UserId userID() {
        return userID;
    }
}
