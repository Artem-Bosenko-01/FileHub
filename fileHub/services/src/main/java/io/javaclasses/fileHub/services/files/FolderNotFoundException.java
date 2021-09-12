package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

public class FolderNotFoundException extends InvalidCommandHandlingException {

    private static final long serialVersionUID = 7492335748165704504L;

    public FolderNotFoundException(String fileId) {

        super("Cannot find folder with id: " + fileId);
    }
}
