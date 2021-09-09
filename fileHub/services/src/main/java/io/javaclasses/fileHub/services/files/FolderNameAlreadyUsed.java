package io.javaclasses.fileHub.services.files;

import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

public class FolderNameAlreadyUsed extends InvalidCommandHandlingException {

    public FolderNameAlreadyUsed(String name) {
        super("Folder name: " + name + " already used.");
    }
}