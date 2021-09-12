package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

/**
 * The exception that throws when root folder doesn't exist at
 * {@link io.javaclasses.fileHub.persistent.files.FolderStorage storage}.
 */
public final class RootFolderNotFoundHandlingException extends InvalidCommandHandlingException {

    private static final long serialVersionUID = -2109606422488737660L;

    RootFolderNotFoundHandlingException(UserId id) {

        super("Root folder doesn't exist for user: " + id.toString());
    }
}
