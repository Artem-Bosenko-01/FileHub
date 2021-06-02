package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.MimeType;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;

import static com.google.common.base.Preconditions.*;

/**
 * This is object, that contains data, that needs to create new file without it file content to Filehub application
 * by authenticated user.
 */
public final class CreateFileCommand extends AuthenticatedUserCommand {

    private final String name;
    private final MimeType mimeType;
    private final UserId owner;
    private final FolderId folder;

    public CreateFileCommand(AuthToken token, String name, MimeType mimeType, UserId owner, FolderId folder) {

        super(checkNotNull(token));
        this.name = checkNotNull(name);
        this.mimeType = checkNotNull(mimeType);
        this.owner = checkNotNull(owner);
        this.folder = checkNotNull(folder);

    }

    public String name() {
        return name;
    }

    public MimeType mimeType() {
        return mimeType;
    }

    public UserId owner() {
        return owner;
    }

    public FolderId folder() {
        return folder;
    }
}
