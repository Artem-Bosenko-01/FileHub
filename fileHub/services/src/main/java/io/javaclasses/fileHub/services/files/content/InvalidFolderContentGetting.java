package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

public class InvalidFolderContentGetting extends InvalidCommandHandlingException {

    public InvalidFolderContentGetting(String message) {
        super(message);
    }
}
