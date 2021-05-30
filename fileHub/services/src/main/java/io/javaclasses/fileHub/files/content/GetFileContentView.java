package io.javaclasses.fileHub.files.content;

import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.View;
import io.javaclasses.fileHub.files.FileID;

import java.util.Optional;

/**
 * This is service to get file's content for existed {@link FileID file}.
 */
public class GetFileContentView implements View<GetFileContentQuery, GetFileContentDTO> {

    private final FIleContentStorage contentStorage;

    public GetFileContentView(FIleContentStorage contentStorage) {
        this.contentStorage = contentStorage;
    }


    @Override
    public GetFileContentDTO handle(GetFileContentQuery inputCommand) throws InvalidHandleCommandException {
        try {
            FileContent content = contentStorage.findByID(inputCommand.fileID());

                return new GetFileContentDTO(content.content());

        } catch (NotExistIDException e) {
            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
