package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

public class FileNotFoundException extends InvalidCommandHandlingException {

    public FileNotFoundException(String fileId) {

        super("Cannot find file with id: " + fileId);
    }
}
