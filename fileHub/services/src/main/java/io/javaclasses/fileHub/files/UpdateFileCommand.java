package io.javaclasses.fileHub.files;

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
        super(token);
        this.id = id;
        this.name = name;
        this.mimeType = mimeType;
        this.size = size;
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
