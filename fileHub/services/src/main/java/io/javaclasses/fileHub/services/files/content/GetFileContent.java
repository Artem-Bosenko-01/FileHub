package io.javaclasses.fileHub.services.files.content;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.files.content.FileContent;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Service to get file's content for exist {@link FileId file}.
 */
public class GetFileContent extends View<GetFileContentQuery, GetFileContentDTO> {

    private static final Logger logger = LoggerFactory.getLogger(GetFileContent.class);

    private final FIleContentStorage contentStorage;

    public GetFileContent(FIleContentStorage contentStorage, AuthorizationStorage authorizationStorage) {
        super(Preconditions.checkNotNull(authorizationStorage));
        this.contentStorage = Preconditions.checkNotNull(contentStorage);
    }


    @Override
    protected GetFileContentDTO doHandle(GetFileContentQuery inputCommand) throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start get file's content by id " + inputCommand.fileID());
        }

        Optional<FileContent> content = contentStorage.findByID(inputCommand.fileID());

        if (content.isPresent()) {

            if (logger.isInfoEnabled()) {
                logger.info("Getting file's content was successful by id " + content.get().id());
            }

            return new GetFileContentDTO(content.get().content());

        } else {

            if (logger.isErrorEnabled()) {
                logger.error("File with id doesn't exist " + inputCommand.fileID());
            }

            throw new InvalidHandleCommandException("File with id doesn't exist " + inputCommand.fileID());
        }

    }
}
