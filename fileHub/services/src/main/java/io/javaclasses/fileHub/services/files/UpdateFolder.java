package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.NotExistedItem;
import io.javaclasses.fileHub.persistent.files.Folder;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.InvalidCommandHandlingException;
import io.javaclasses.fileHub.services.SecuredUserProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is service to updating information about existed file in authenticated user's folder by {@link FolderId id}.
 */
public class UpdateFolder extends SecuredUserProcess<UpdateFolderCommand, FolderId> {

    private static final Logger logger = LoggerFactory.getLogger(UpdateFolder.class);

    private final FolderStorage folderStorageInMemory;

    public UpdateFolder(FolderStorage userStorage, AuthorizationStorage authorizationStorage) {

        super(Preconditions.checkNotNull(authorizationStorage));
        this.folderStorageInMemory = Preconditions.checkNotNull(userStorage);
    }

    @Override
    protected FolderId doHandle(UpdateFolderCommand inputCommand) throws InvalidCommandHandlingException {

        if (logger.isInfoEnabled()) {
            logger.info("Start update information for folder " + inputCommand.id());
        }

        Folder folder = new Folder(inputCommand.id());
        folder.setParentFolder(inputCommand.parentFolder());
        folder.setOwner(inputCommand.owner());
        folder.setName(inputCommand.name());

        try {

            folderStorageInMemory.update(folder);

            if (logger.isInfoEnabled()) {
                logger.info("Updating folder was successful. id: " + folder.id());
            }

            return folder.id();

        } catch (NotExistedItem e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }

            throw new InvalidCommandHandlingException(e.getMessage());
        }

    }
}
