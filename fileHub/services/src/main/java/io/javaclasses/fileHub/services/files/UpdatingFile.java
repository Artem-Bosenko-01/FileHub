package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.NotExistUserIdException;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import io.javaclasses.fileHub.persistent.files.File;
import io.javaclasses.fileHub.persistent.files.FileStorageInMemory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is service to updating information about existed file in authenticated user's directory.
 */
public class UpdatingFile implements SecuredUserProcess<UpdateFileCommand, FileId> {

    private static final Logger logger = LoggerFactory.getLogger(UpdatingFile.class);

    private final FileStorageInMemory fileStorage;

    public UpdatingFile(FileStorageInMemory fileStorage) {
        this.fileStorage = Preconditions.checkNotNull(fileStorage);
    }

    @Override
    public FileId handle(UpdateFileCommand inputCommand) throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start update information for file " + inputCommand.id());
        }

        File file = new File(inputCommand.id());
        file.setName(inputCommand.name());
        file.setSize(inputCommand.size());
        file.setMimeType(inputCommand.mimeType());
        file.setFolder(inputCommand.folder());
        file.setUserID(inputCommand.owner());

        try {

            fileStorage.update(file);

            if (logger.isInfoEnabled()) {
                logger.info("Updating file was successful. id: " + file.id());
            }
            return file.id();

        } catch (NotExistUserIdException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
