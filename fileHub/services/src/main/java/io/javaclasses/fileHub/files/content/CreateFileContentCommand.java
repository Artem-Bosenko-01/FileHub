package io.javaclasses.fileHub.files.content;

import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;
import io.javaclasses.fileHub.files.FileID;

public class CreateFileContentCommand extends AuthenticatedUserCommand {

    private final FileID fileID;
    private final byte[] content;

    public CreateFileContentCommand(AuthToken token, FileID fileID, byte[] content) {
        super(token);
        this.fileID = fileID;
        this.content = content;
    }

    public FileID fileID() {
        return fileID;
    }

    public byte[] content() {
        return content;
    }
}
