package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;

/**
 *  This is object, that contains data, that needs to create new file without it file content to Filehub application
 *  by authenticated user.
 *
 * */
public final class CreateFileCommand extends AuthenticatedUserCommand {

    private final String name;
    private final MimeType mimeType;
    private final UserID owner;
    private final FolderID folder;

    public CreateFileCommand(AuthToken token, String name, MimeType mimeType, UserID owner, FolderID folder) {
        super(token);
        this.name = Preconditions.checkNotNull(name);
        this.mimeType = Preconditions.checkNotNull(mimeType);
        this.owner = Preconditions.checkNotNull(owner);
        this.folder = Preconditions.checkNotNull(folder);
    }

    public String name() {
        return name;
    }

    public MimeType mimeType() {
        return mimeType;
    }

    public UserID owner() {
        return owner;
    }

    public FolderID folder() {
        return folder;
    }
}
