package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.NotExistedItem;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service to delete an existed folder in Filehub application by authenticated user.
 */
public class DeleteFolder extends SecuredUserProcess<DeleteFolderCommand, String> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFolder.class);

    private final FolderStorage folderStorageInMemory;

    public DeleteFolder(FolderStorage folderStorage, AuthorizationStorage authorizationStorage) {

        super(authorizationStorage);

        this.folderStorageInMemory = Preconditions.checkNotNull(folderStorage);
    }

    @Override
    protected String doHandle(DeleteFolderCommand inputCommand) throws InvalidCommandHandlingException {

        if (logger.isInfoEnabled()) {
            logger.info("Start delete folder " + inputCommand.folderID());
        }

        try {

            folderStorageInMemory.delete(inputCommand.folderID());

            if (logger.isInfoEnabled()) {
                logger.info("Deleted " + inputCommand.folderID() + " was successful");
            }

            return inputCommand.folderID();

        } catch (NotExistedItem e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }

            throw new InvalidCommandHandlingException(e.getMessage());
        }

    }
}
