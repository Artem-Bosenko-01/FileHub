package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.services.InvalidCommandHandlingException;

public class InvalidFolderContentGettingException extends InvalidCommandHandlingException {

    private static final long serialVersionUID = -3510113330623859197L;

    public InvalidFolderContentGettingException(String message) {
        super(message);
    }
}
