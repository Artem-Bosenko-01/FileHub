package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.MimeType;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This is object, that contains data, that needs to update file by authenticated user in Filehub application.
 */
public final class UpdateFileCommand extends AuthenticatedUserCommand {

    private final FileId id;
    private final String name;
    private final MimeType mimeType;
    private final Integer size;
    private final UserId owner;
    private final FolderId folder;

    public UpdateFileCommand(AuthToken token, FileId id, String name, MimeType mimeType,
                             Integer size, UserId owner, FolderId folder) {

        super(checkNotNull(token));
        this.id = checkNotNull(id);
        this.name = checkNotNull(name);
        this.mimeType = checkNotNull(mimeType);
        this.size = checkNotNull(size);
        this.owner = checkNotNull(owner);
        this.folder = checkNotNull(folder);

    }

    public FileId id() {
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

    public UserId owner() {
        return owner;
    }

    public FolderId folder() {
        return folder;
    }
}