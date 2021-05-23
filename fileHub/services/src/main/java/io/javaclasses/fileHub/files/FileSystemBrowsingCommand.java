package io.javaclasses.fileHub.files;

import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;
import io.javaclasses.fileHub.users.UserID;

public final class FileSystemBrowsingCommand extends AuthenticatedUserCommand {

    private final UserID id;

    public FileSystemBrowsingCommand(AuthToken token, UserID id) {
        super(token);
        this.id = id;
    }

    public UserID id() {
        return id;
    }
}
