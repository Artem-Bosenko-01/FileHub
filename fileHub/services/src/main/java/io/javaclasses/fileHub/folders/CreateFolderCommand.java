package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;
import io.javaclasses.fileHub.users.UserID;

import java.util.Optional;

/**
 *  This is object, that contains data, that needs to create new folder and put to {@link FolderStorage storage}
 *  in Filehub application by {@link UserID authenticated user} .
 *
 * */
public final class CreateFolderCommand extends AuthenticatedUserCommand {

    private final String name;
    private final UserID owner;
    private final Optional<FolderID> parentFolder;

    public CreateFolderCommand(AuthToken token, String name, UserID owner, Optional<FolderID> parentFolder) {
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

    public Optional<FolderID> parentFolder() {
        return parentFolder;
    }
}
