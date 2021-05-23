package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;

/**
 *
 * */
public final class DeleteFileCommand extends AuthenticatedUserCommand {

    private final FileID id;

    public DeleteFileCommand(AuthToken token, FileID id) {
        super(token);
        this.id = Preconditions.checkNotNull(id);
    }

    public FileID id() {
        return id;
    }
}
