package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.NotExistUserIdException;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import io.javaclasses.fileHub.persistent.files.FileStorageInMemory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is service to delete existed file in authenticated user's directory.
 */
public class DeletingFile extends SecuredUserProcess<DeleteFileCommand, FileId> {

    private static final Logger logger = LoggerFactory.getLogger(DeletingFile.class);


    private final FileStorageInMemory fileStorage;

    public DeletingFile(FileStorageInMemory fileStorage, AuthorizationStorage authorizationStorage) {
        super(Preconditions.checkNotNull(authorizationStorage));
        this.fileStorage = Preconditions.checkNotNull(fileStorage);
    }


    @Override
    protected FileId doHandle(DeleteFileCommand inputCommand) throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start delete file " + inputCommand.id());
        }

        try {

            fileStorage.delete(inputCommand.id());

            if (logger.isInfoEnabled()) {
                logger.info("Deleted " + inputCommand.id() + " was successful");
            }

            return inputCommand.id();

        } catch (NotExistUserIdException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }

            throw new InvalidHandleCommandException(e.getMessage());
        }

    }
}
