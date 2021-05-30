package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;
import io.javaclasses.fileHub.users.UserID;

/**
 *  This is object, that contains data, that needs to update information for existed folder by {@link FolderID id}
 *  in Filehub application by authenticated user.
 *
 * */
public final class UpdateFolderCommand extends AuthenticatedUserCommand {

    private final FolderID id;
    private final String name;
    private final UserID owner;
    private final FolderID parentFolder;

    public UpdateFolderCommand(AuthToken token, FolderID id, String name, UserID owner, FolderID parentFolder) {
        super(Preconditions.checkNotNull(token));
        this.id = Preconditions.checkNotNull(id);
        this.name = Preconditions.checkNotNull(name);
        this.owner = Preconditions.checkNotNull(owner);
        this.parentFolder = Preconditions.checkNotNull(parentFolder);
    }

    public FolderID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public UserID owner() {
        return owner;
    }

    public FolderID parentFolder() {
        return parentFolder;
    }
}
