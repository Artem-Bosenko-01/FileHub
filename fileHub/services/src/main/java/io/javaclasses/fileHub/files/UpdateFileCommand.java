package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;

public final class UpdateFileCommand extends AuthenticatedUserCommand {

    private final FileID id;
    private final String name;
    private final MimeType mimeType;
    private final Integer size;
    private final UserID owner;
    private final FolderID folder;

    public UpdateFileCommand(AuthToken token, FileID id, String name, MimeType mimeType, Integer size, UserID owner, FolderID folder) {
        super(Preconditions.checkNotNull(token));
        this.id = Preconditions.checkNotNull(id);
        this.name = Preconditions.checkNotNull(name);
        this.mimeType = Preconditions.checkNotNull(mimeType);
        this.size = Preconditions.checkNotNull(size);
        this.owner = Preconditions.checkNotNull(owner);
        this.folder = Preconditions.checkNotNull(folder);
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

    public Integer size() {
        return size;
    }

    public UserID owner() {
        return owner;
    }

    public FolderID folder() {
        return folder;
    }
}
