package io.javaclasses.fileHub.folders;

import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;
import io.javaclasses.fileHub.users.UserID;

public final class UpdateFolderCommand extends AuthenticatedUserCommand {

    private final FolderID id;
    private final String name;
    private final UserID owner;
    private final FolderID parentFolder;

    public UpdateFolderCommand(AuthToken token, FolderID id, String name, UserID owner, FolderID parentFolder) {
        super(token);
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.parentFolder = parentFolder;
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
