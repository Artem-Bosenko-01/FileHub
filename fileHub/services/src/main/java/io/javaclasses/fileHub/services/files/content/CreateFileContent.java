package io.javaclasses.fileHub.services.files.content;

import io.javaclasses.fileHub.persistent.DuplicatedUserIdException;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.content.FIleContentStorage;
import io.javaclasses.fileHub.persistent.files.content.FileContent;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service to create file's content to exist {@link FileId  file} in authenticated user's directory.
 */
public class CreateFileContent extends SecuredUserProcess<CreateFileContentCommand, byte[]> {

    private static final Logger logger = LoggerFactory.getLogger(CreateFileContent.class);

    private final FIleContentStorage contentStorage;

    public CreateFileContent(FIleContentStorage contentStorage, AuthorizationStorage authorizationStorage) {

        super(authorizationStorage);

        this.contentStorage = contentStorage;
    }


    @Override
    protected byte[] doHandle(CreateFileContentCommand inputCommand) throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start create file content for file: " + inputCommand.fileID());
        }

        try {

            FileContent content = new FileContent(inputCommand.fileID());

            content.setContent(inputCommand.content());

            contentStorage.create(content);

            if (logger.isInfoEnabled()) {
                logger.info("Created file's content was successful. id: " + content.id());
            }

            return content.content();

        } catch (DuplicatedUserIdException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }

            throw new InvalidHandleCommandException(e.getMessage());

        }
    }
}
