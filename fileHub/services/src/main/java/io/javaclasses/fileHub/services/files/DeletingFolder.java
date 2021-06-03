package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.NotExistUserIdException;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is service to delete existed folder in Filehub application by authenticated user.
 */
public class DeletingFolder implements SecuredUserProcess<DeleteFolderCommand, FolderId> {

    private static final Logger logger = LoggerFactory.getLogger(DeletingFolder.class);

    private final FolderStorage folderStorageInMemory;

    public DeletingFolder(FolderStorage folderStorageInMemory) {
        this.folderStorageInMemory = Preconditions.checkNotNull(folderStorageInMemory);
    }

    @Override
    public FolderId handle(DeleteFolderCommand inputCommand) throws InvalidHandleCommandException {

        if (logger.isInfoEnabled()) {
            logger.info("Start delete folder " + inputCommand.folderID());
        }

        try {

            folderStorageInMemory.delete(inputCommand.folderID());

            if (logger.isInfoEnabled()) {
                logger.info("Deleted " + inputCommand.folderID() + " was successful");
            }

            return inputCommand.folderID();

        } catch (NotExistUserIdException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }

            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
