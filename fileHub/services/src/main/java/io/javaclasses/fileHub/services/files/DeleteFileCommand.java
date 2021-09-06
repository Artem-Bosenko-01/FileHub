package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorage;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.AuthenticatedUserCommand;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Data that needs to delete file from {@link FileStorage file catalog} by
 * {@link FileId}.
 */
public final class DeleteFileCommand extends AuthenticatedUserCommand {

    private final FileId id;

    public DeleteFileCommand(AuthToken token, FileId id) {

        super(checkNotNull(token));
        this.id = checkNotNull(id);

    }

    public FileId id() {

        return id;
    }
}
