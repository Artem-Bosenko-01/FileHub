package io.javaclasses.fileHub.services.files.content;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;
import io.javaclasses.fileHub.persistent.files.FileId;

/**
 * This is object, that contains data, that needs to create content for existed file's in Filehub application
 * by authenticated user.
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
        return content;
    }
}
