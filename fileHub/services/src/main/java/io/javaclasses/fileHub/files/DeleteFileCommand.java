package io.javaclasses.fileHub.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.users.UserID;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.AuthenticatedUserCommand;

/**
 *  This is object, that contains data, that needs to delete file from {@link FileStorage file catalog} by
 *  {@link FileID}.
 *
 * */
public final class DeleteFileCommand extends AuthenticatedUserCommand {

    private final FileID id;

    public DeleteFileCommand(AuthToken token, FileID id) {
        super(Preconditions.checkNotNull(token));
        this.id = Preconditions.checkNotNull(id);
    }

    public FileID id() {
        return id;
    }
}
