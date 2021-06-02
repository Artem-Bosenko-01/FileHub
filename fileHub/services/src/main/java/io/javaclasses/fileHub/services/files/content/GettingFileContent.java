package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.NotExistUserIdException;
import io.javaclasses.fileHub.services.View;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.files.content.FileContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is service to get file's content for existed {@link FileId file}.
 */
public class GettingFileContent implements View<GetFileContentQuery, GetFileContentDTO> {

    private static final Logger logger = LoggerFactory.getLogger(GettingFileContent.class);

    private final FIleContentStorage contentStorage;

    public GettingFileContent(FIleContentStorage contentStorage) {
        this.contentStorage = contentStorage;
    }


    @Override
    public GetFileContentDTO handle(GetFileContentQuery inputCommand) throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start get file's content by id " + inputCommand.fileID());
        }

        try {

            FileContent content = contentStorage.findByID(inputCommand.fileID());

            if (logger.isInfoEnabled()) {
                logger.info("Getting file's content was successful by id " + inputCommand.fileID());
            }

            return new GetFileContentDTO(content.content());

        } catch (NotExistUserIdException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }

            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
