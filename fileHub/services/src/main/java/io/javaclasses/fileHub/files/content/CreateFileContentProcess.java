package io.javaclasses.fileHub.files.content;

import io.javaclasses.fileHub.DuplicatedIDException;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.SecuredProcess;
import io.javaclasses.fileHub.files.FileID;

/**
 * This is service to create file's content to existed {@link FileID  file} in authenticated user's directory.
 */
public class CreateFileContentProcess implements SecuredProcess<CreateFileContentCommand, CreateFileContentDTO> {

    private final FIleContentStorage contentStorage;

    public CreateFileContentProcess(FIleContentStorage contentStorage) {
        this.contentStorage = contentStorage;
    }

    @Override
    public CreateFileContentDTO handle(CreateFileContentCommand inputCommand) throws InvalidHandleCommandException {
        try {

            FileContent content = new FileContent(inputCommand.fileID());
            content.setContent(inputCommand.content());
            contentStorage.create(content);

            return new CreateFileContentDTO(content.id(), content.content());
        } catch (DuplicatedIDException e) {
            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
