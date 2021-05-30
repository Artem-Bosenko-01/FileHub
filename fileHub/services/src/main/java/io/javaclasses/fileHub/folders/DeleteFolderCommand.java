package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.users.UserID;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;

/**
 *  This is object, that contains data, that needs to delete existed folder by {@link FolderID id}
 *  in Filehub application by {@link UserID authenticated user} .
 *
 * */
public final class DeleteFolderCommand extends AuthenticatedUserCommand {

    private final FolderID folderID;

    public DeleteFolderCommand(AuthToken token, FolderID folderID) {
        super(Preconditions.checkNotNull(token));
        this.folderID = Preconditions.checkNotNull(folderID);
    }

    public FolderID folderID() {
        return folderID;
    }
}
