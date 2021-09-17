package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.files.content.FileContent;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Service to get file's content from existed {@link FileId file}.
 */
@Component
public class GetFileContent extends View<GetFileContentQuery, GetFileContentDTO> {

    private static final Logger logger = LoggerFactory.getLogger(GetFileContent.class);

    private final FIleContentStorage contentStorage;

    @Autowired
    public GetFileContent(FIleContentStorage contentStorage, AuthorizationStorage authorizationStorage) {

        super(checkNotNull(authorizationStorage));

        this.contentStorage = checkNotNull(contentStorage);
    }


    @Override
    protected GetFileContentDTO doHandle(GetFileContentQuery inputCommand) throws InvalidCommandHandlingException {

        if (logger.isInfoEnabled()) {
            logger.info("Start get file's content by id " + inputCommand.fileID());
        }

        Optional<FileContent> content = contentStorage.findByID(new FileId(inputCommand.fileID()));

        if (content.isPresent()) {

            if (logger.isInfoEnabled()) {
                logger.info("Getting file's content was successful by id " + content.get().id());
            }

            return new GetFileContentDTO(content.get().content());

        } else {

            if (logger.isErrorEnabled()) {
                logger.error("File with id doesn't exist " + inputCommand.fileID());
            }

            throw new InvalidCommandHandlingException("File with id doesn't exist " + inputCommand.fileID());
        }

    }
}
