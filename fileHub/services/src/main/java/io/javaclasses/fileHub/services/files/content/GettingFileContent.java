package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.View;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.files.content.FileContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

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

        Optional<FileContent> content = contentStorage.findByID(inputCommand.fileID());

        if(content.isPresent()){

            if (logger.isInfoEnabled()) {
                logger.info("Getting file's content was successful by id " + content.get().id());
            }

            return new GetFileContentDTO(content.get().content());

        }else {

            if (logger.isErrorEnabled()) {
                logger.error("File with id doesn't exist " + inputCommand.fileID());
            }

            throw new InvalidHandleCommandException("File with id doesn't exist " + inputCommand.fileID());
        }

    }
}
