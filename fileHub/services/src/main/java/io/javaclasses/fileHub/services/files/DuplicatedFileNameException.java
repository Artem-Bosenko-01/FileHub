package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

public class DuplicatedFileNameException extends InvalidCommandHandlingException {

    public DuplicatedFileNameException(String id) {
        super("Uploaded file name already exist: " + id);
    }
}
