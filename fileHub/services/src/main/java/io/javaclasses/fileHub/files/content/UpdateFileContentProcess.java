package io.javaclasses.fileHub.files.content;

import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.SecuredProcess;
import io.javaclasses.fileHub.files.FIleContentStorage;
import io.javaclasses.fileHub.files.FileContent;

public class UpdateFileContentProcess implements SecuredProcess<UpdateFileContentCommand, UpdateFileContentDTO> {

    private final FIleContentStorage contentStorage;

    public UpdateFileContentProcess(FIleContentStorage contentStorage) {
        this.contentStorage = contentStorage;
    }

    @Override
    public UpdateFileContentDTO handle(UpdateFileContentCommand inputCommand) throws InvalidHandleCommandException {

        FileContent content = new FileContent(inputCommand.fileID());
        content.setContent(inputCommand.content());

        try {
            contentStorage.update(content);
            return new UpdateFileContentDTO(content.content());
        } catch (NotExistIDException e) {
            throw new InvalidHandleCommandException(e.getMessage());
        }

    }
}
