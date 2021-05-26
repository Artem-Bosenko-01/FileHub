package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;

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
