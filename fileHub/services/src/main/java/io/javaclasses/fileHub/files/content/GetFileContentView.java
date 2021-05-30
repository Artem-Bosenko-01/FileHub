package io.javaclasses.fileHub.files.content;

import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.View;
import io.javaclasses.fileHub.files.FileID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * This is service to get file's content for existed {@link FileID file}.
 */
public class GetFileContentView implements View<GetFileContentQuery, GetFileContentDTO> {

    private final FIleContentStorage contentStorage;
    private final Logger logger = LoggerFactory.getLogger(GetFileContentView.class);

    public GetFileContentView(FIleContentStorage contentStorage) {
        this.contentStorage = contentStorage;
    }


    @Override
    public GetFileContentDTO handle(GetFileContentQuery inputCommand) throws InvalidHandleCommandException {
        if (logger.isInfoEnabled()) {
            logger.info("Start get file's content by id " + inputCommand.fileID());
        }
        try {
            FileContent content = contentStorage.findByID(inputCommand.fileID());

            if(logger.isInfoEnabled()){
                logger.info("Getting file's content was successful by id " + inputCommand.fileID());
            }

            return new GetFileContentDTO(content.content());

        } catch (NotExistIDException e) {

            if(logger.isErrorEnabled()){
                logger.error(e.getMessage());
            }

            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
