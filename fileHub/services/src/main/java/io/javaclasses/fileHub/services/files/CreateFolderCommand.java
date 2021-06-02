package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;
import io.javaclasses.fileHub.persistent.users.UserId;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.*;

/**
 * This is object, that contains data, that needs to create new folder and put to {@link FolderStorage storage}
 * in Filehub application by {@link UserId authenticated user} .
 */
public final class CreateFolderCommand extends AuthenticatedUserCommand {

    private final String name;
    private final UserId owner;
    private final FolderId parentFolder;

    public CreateFolderCommand(AuthToken token, String name, UserId owner, @Nullable FolderId parentFolder) {

        super(checkNotNull(token));
        this.name = checkNotNull(name);
        this.owner = checkNotNull(owner);
        this.parentFolder = parentFolder;

    }

    public String name() {
        return name;
    }

    public UserId owner() {
        return owner;
    }

    public FolderId parentFolder() {
        return parentFolder;
    }
}
