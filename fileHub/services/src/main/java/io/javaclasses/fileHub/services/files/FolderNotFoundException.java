package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

public class FolderNotFoundException extends InvalidCommandHandlingException {

    public FolderNotFoundException(String fileId) {

        super("Cannot find folder with id: " + fileId);
    }
}
