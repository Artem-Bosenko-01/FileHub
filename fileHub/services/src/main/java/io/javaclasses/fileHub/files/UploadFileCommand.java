package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;

public class UploadFileCommand extends AuthenticatedUserCommand {

    private final String name;
    private final MimeType mimeType;
    private final UserID owner;
    private final FolderID folder;
    private final byte[] content;

    public UploadFileCommand(AuthToken token, String name, MimeType mimeType, UserID owner, FolderID folder, byte[] content) {
        super(Preconditions.checkNotNull(token));
        this.name = Preconditions.checkNotNull(name);
        this.mimeType = Preconditions.checkNotNull(mimeType);
        this.owner = Preconditions.checkNotNull(owner);
        this.folder = Preconditions.checkNotNull(folder);
        this.content = Preconditions.checkNotNull(content);
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

    public byte[] content() {
        return content;
    }
}
