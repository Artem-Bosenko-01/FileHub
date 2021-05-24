package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;

public final class CreateFileCommand extends AuthenticatedUserCommand {

    private final FileID id;
    private final String name;
    private final MimeType mimeType;
    private final UserID owner;
    private final FolderID folder;

    public CreateFileCommand(AuthToken token, FileID id, String name, MimeType mimeType, UserID owner, FolderID folder) {
        super(token);
        this.id = Preconditions.checkNotNull(id);
        this.name = Preconditions.checkNotNull(name);
        this.mimeType = Preconditions.checkNotNull(mimeType);
        this.owner = owner;
        this.folder = folder;
    }

    public FileID id() {
        return id;
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
