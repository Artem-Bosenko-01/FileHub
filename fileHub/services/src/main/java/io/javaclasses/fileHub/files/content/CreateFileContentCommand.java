package io.javaclasses.fileHub.files.content;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;
import io.javaclasses.fileHub.files.FileID;

public final class CreateFileContentCommand extends AuthenticatedUserCommand {

    private final FileID fileID;
    private final byte[] content;

    public CreateFileContentCommand(AuthToken token, FileID fileID, byte[] content) {
        super(Preconditions.checkNotNull(token));
        this.fileID = Preconditions.checkNotNull(fileID);
        this.content = Preconditions.checkNotNull(content);
    }

    public FileID fileID() {
        return fileID;
    }

    public byte[] content() {
        return content;
    }
}
