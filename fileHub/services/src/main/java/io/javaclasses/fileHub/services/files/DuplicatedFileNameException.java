package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

/**
 * Throws if the name of the uploaded file already exists in the user's directory.
 */
public class DuplicatedFileNameException extends InvalidCommandHandlingException {

    private static final long serialVersionUID = 5817280306334089702L;

    public DuplicatedFileNameException(String id) {
        super("Uploaded file name already exist: " + id);
    }
}
