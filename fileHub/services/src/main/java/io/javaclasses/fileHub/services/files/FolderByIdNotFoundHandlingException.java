package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

/**
 * The exception that throws when folder id doesn't exist at
 * {@link io.javaclasses.fileHub.persistent.files.FolderStorage storage}.
 */
public final class FolderByIdNotFoundHandlingException extends InvalidCommandHandlingException {

    public FolderByIdNotFoundHandlingException(FolderId id) {

        super("Cannot find folder by id: " + id.toString());
    }
}
