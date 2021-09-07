package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Data that needs to delete existed folder by {@link FolderId id}
 * in Filehub application by {@link UserId authenticated user} .
 */
public final class DeleteFolderCommand extends AuthenticatedUserCommand {

    private final String folderID;

    public DeleteFolderCommand(AuthToken token, String folderID) {

        super(checkNotNull(token));

        this.folderID = checkNotNull(folderID);

    }

    public String folderID() {

        return folderID;
    }

}
