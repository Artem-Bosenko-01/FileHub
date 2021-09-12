package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

/**
 * The exception that throws when folder id doesn't exist at
 * {@link io.javaclasses.fileHub.persistent.files.FolderStorage storage}.
 */
public final class FolderByIdNotFoundHandlingException extends InvalidCommandHandlingException {

    private static final long serialVersionUID = 7878152519027008140L;

    public FolderByIdNotFoundHandlingException(String id) {

        super("Cannot find folder by id: " + id);
    }
}
