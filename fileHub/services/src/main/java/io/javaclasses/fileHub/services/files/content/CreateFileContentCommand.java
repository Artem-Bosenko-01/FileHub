package io.javaclasses.fileHub.services.files.content;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;

/**
 * Data that needs to create file content for existing file in Filehub application.
 */
public final class CreateFileContentCommand extends AuthenticatedUserCommand {

    private final FileId fileID;

    private final byte[] content;

    public CreateFileContentCommand(AuthToken token, FileId fileID, byte[] content) {

        super(Preconditions.checkNotNull(token));

        this.fileID = Preconditions.checkNotNull(fileID);

        this.content = Preconditions.checkNotNull(content);

    }

    public FileId fileID() {

        return fileID;
    }

    public byte[] content() {

        return content.clone();
    }
}
