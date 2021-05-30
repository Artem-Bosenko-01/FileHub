package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;
import io.javaclasses.fileHub.users.UserID;

public final class CreateFolderCommand extends AuthenticatedUserCommand {

    private final String name;
    private final UserID owner;
    private final FolderID parentFolder;

    public CreateFolderCommand(AuthToken token, String name, UserID owner, FolderID parentFolder) {
        super(Preconditions.checkNotNull(token));
        this.name = Preconditions.checkNotNull(name);
        this.owner = Preconditions.checkNotNull(owner);
        this.parentFolder = Preconditions.checkNotNull(parentFolder);
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
