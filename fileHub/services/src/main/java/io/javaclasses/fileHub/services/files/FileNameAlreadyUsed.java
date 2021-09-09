package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

public class FileNameAlreadyUsed extends InvalidCommandHandlingException {

    public FileNameAlreadyUsed(String name) {

        super("File name: " + name + " already used.");
    }
}
