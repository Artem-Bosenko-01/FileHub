package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

/**
 * Throws if the file id doesn't exist at {@link io.javaclasses.fileHub.persistent.files.FileStorage file storage}.
 */
public class FileNotFoundException extends InvalidCommandHandlingException {

    private static final long serialVersionUID = -5290288324879605630L;

    FileNotFoundException(String fileId) {

        super("Cannot find file with id: " + fileId);
    }
}
