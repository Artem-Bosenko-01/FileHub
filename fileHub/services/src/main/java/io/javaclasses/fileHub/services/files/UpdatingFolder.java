package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.services.InvalidHandleCommandException;
import io.javaclasses.fileHub.persistent.NotExistUserIdException;
import io.javaclasses.fileHub.services.SecuredProcess;
import io.javaclasses.fileHub.persistent.files.Folder;
import io.javaclasses.fileHub.persistent.files.FolderStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is service to update information for existed folder in Filehub application by authenticated user.
 */
public class UpdatingFolder implements SecuredProcess<UpdateFolderCommand, FolderId> {

    private static final Logger logger = LoggerFactory.getLogger(UpdatingFolder.class);

    private final FolderStorage folderStorageInMemory;

    public UpdatingFolder(FolderStorage userStorage) {
        this.folderStorageInMemory = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public FolderId handle(UpdateFolderCommand inputCommand) throws InvalidHandleCommandException {

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

        } catch (NotExistUserIdException e) {

            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw new InvalidHandleCommandException(e.getMessage());
        }

    }
}
