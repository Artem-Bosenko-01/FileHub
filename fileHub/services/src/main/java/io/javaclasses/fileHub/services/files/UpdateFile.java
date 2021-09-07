package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.NotExistedItem;
import io.javaclasses.fileHub.persistent.files.File;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.FileStorageInMemory;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is service to updating information about existed file in authenticated user's file by {@link FileId id}.
 */
public class UpdateFile extends SecuredUserProcess<UpdateFileCommand, FileId> {

    private static final Logger logger = LoggerFactory.getLogger(UpdateFile.class);

    private final FileStorageInMemory fileStorage;

    public UpdateFile(FileStorageInMemory fileStorage, AuthorizationStorage authorizationStorage) {

        super(Preconditions.checkNotNull(authorizationStorage));

        this.fileStorage = Preconditions.checkNotNull(fileStorage);
    }

    @Override
    protected FileId doHandle(UpdateFileCommand inputCommand) throws InvalidCommandHandlingException {

        if (logger.isInfoEnabled()) {
            logger.info("Start update information for file " + inputCommand.id());
        }

        File file = new File(inputCommand.id());
        file.setName(inputCommand.name());
        file.setSize(inputCommand.size());
        file.setMimeType(inputCommand.mimeType());
        file.setFolder(inputCommand.folder().toString());
        file.setUserID(inputCommand.owner());

        try {

            fileStorage.update(file);

            if (logger.isInfoEnabled()) {
                logger.info("Updating file was successful. id: " + file.id());
            }

            return file.id();

        } catch (NotExistedItem e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }

            throw new InvalidCommandHandlingException(e.getMessage());
        }

    }
}
