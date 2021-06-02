package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This is object, that contains data, that needs to delete existed folder by {@link FolderId id}
 * in Filehub application by {@link UserId authenticated user} .
 */
public final class DeleteFolderCommand extends AuthenticatedUserCommand {

    private final FolderId folderID;

    public DeleteFolderCommand(AuthToken token, FolderId folderID) {

        super(checkNotNull(token));
        this.folderID = checkNotNull(folderID);

    }

    public FolderId folderID() {
        return folderID;
    }

}
