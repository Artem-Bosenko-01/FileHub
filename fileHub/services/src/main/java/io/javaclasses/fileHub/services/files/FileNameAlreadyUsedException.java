package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

/**
 * Throws if the name of updated file already assigned to existed file.
 */
public class FileNameAlreadyUsedException extends InvalidCommandHandlingException {

    private static final long serialVersionUID = 7141732039242462093L;

    FileNameAlreadyUsedException(String name) {

        super("File name: " + name + " already used.");
    }
}
