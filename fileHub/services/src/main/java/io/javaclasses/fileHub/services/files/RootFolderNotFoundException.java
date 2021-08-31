package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;

/**
 * The exception that throws when root folder doesn't exist at
 * {@link io.javaclasses.fileHub.persistent.files.FolderStorage storage}.
 */
public final class RootFolderNotFoundException extends InvalidHandleCommandException {

    public RootFolderNotFoundException(UserId id) {

        super("Root folder doesn't exist for user: " + id.toString());
    }
}
