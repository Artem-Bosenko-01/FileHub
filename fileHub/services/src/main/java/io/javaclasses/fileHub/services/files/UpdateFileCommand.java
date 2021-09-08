package io.javaclasses.fileHub.services.files;

import com.google.common.net.MediaType;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.MimeType;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Data that needs to update an existed file.
 */
public final class UpdateFileCommand extends AuthenticatedUserCommand {

    private final FileId id;

    private final String name;

    private final MediaType mimeType;

    private final Long size;

    private final UserId owner;

    private final FolderId folder;

    public UpdateFileCommand(AuthToken token, FileId id, String name, MediaType mimeType,
                             Long size, UserId owner, FolderId folder) {

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

    public MediaType mimeType() {

        return mimeType;
    }

    public Long size() {

        return size;
    }

    public UserId owner() {

        return owner;
    }

    public FolderId folder() {

        return folder;
    }
}