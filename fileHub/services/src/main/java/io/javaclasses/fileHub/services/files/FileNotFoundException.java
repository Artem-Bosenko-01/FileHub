package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

public class FileNotFoundException extends InvalidCommandHandlingException {

    private static final long serialVersionUID = -5290288324879605630L;

    FileNotFoundException(String fileId) {

        super("Cannot find file with id: " + fileId);
    }
}
