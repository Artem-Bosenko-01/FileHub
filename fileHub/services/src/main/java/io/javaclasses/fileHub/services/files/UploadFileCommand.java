package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.MimeType;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Data that needs to upload new file to Filehub application by authenticated user.
 */
public final class UploadFileCommand extends AuthenticatedUserCommand {

    private final String name;

    private final MimeType mimeType;

    private final UserId owner;

    private final FolderId folder;

    private final byte[] content;

    public UploadFileCommand(AuthToken token, String name, MimeType mimeType, UserId owner, FolderId folder,
                             byte[] content) {

        super(checkNotNull(token));

        this.name = checkNotNull(name);

        this.mimeType = checkNotNull(mimeType);

        this.owner = checkNotNull(owner);

        this.folder = checkNotNull(folder);

        this.content = checkNotNull(content);

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

    public byte[] content() {

        return content.clone();
    }
}
