package io.javaclasses.fileHub.files.content;

import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.View;
import io.javaclasses.fileHub.files.FIleContentStorage;
import io.javaclasses.fileHub.files.FileContent;

import java.util.Optional;

public class GetFileContentView implements View<GetFileContentQuery, GetFileContentDTO> {

    private final FIleContentStorage contentStorage;

    public GetFileContentView(FIleContentStorage contentStorage) {
        this.contentStorage = contentStorage;
    }


    @Override
    public GetFileContentDTO handle(GetFileContentQuery inputCommand) throws InvalidHandleCommandException {
        try {
            Optional<FileContent> content = contentStorage.findByID(inputCommand.fileID());
            if(content.isPresent()){
                return new GetFileContentDTO(content.get().content());
            }
            else throw new InvalidHandleCommandException("Id doesn't exist "+inputCommand.fileID());
        } catch (NotExistIDException e) {
            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
