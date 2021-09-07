package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.NotExistedItem;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service to delete an existed file in authenticated user's directory.
 */
public class DeleteFile extends SecuredUserProcess<DeleteFileCommand, FileId> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFile.class);


    private final FileStorageInMemory fileStorage;

    public DeleteFile(FileStorageInMemory fileStorage, AuthorizationStorage authorizationStorage) {

        super(Preconditions.checkNotNull(authorizationStorage));

        this.fileStorage = Preconditions.checkNotNull(fileStorage);
    }


    @Override
    protected FileId doHandle(DeleteFileCommand inputCommand) throws InvalidCommandHandlingException {

        if (logger.isInfoEnabled()) {
            logger.info("Start delete file " + inputCommand.id());
        }

        try {

            fileStorage.delete(inputCommand.id());

            if (logger.isInfoEnabled()) {
                logger.info("Deleted " + inputCommand.id() + " was successful");
            }

            return inputCommand.id();

        } catch (NotExistedItem e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }

            throw new InvalidCommandHandlingException(e.getMessage());
        }

    }
}
