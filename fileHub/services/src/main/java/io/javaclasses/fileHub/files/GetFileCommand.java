package io.javaclasses.fileHub.files;

import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;

public final class GetFileCommand extends AuthenticatedUserCommand {

    private final FileID id;

    public GetFileCommand(AuthToken token, FileID id) {
        super(token);
        this.id = id;
    }

    public FileID id() {
        return id;
    }
}
