package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.SecuredProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is service to update information for existed folder in Filehub application by authenticated user.
 */
public class UpdateFolderProcess implements SecuredProcess<UpdateFolderCommand, UpdateFolderDTO> {

    private final FolderStorage folderStorageInMemory;
    private final Logger logger = LoggerFactory.getLogger(UpdateFolderProcess.class);

    public UpdateFolderProcess(FolderStorage userStorage) {
        this.folderStorageInMemory = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public UpdateFolderDTO handle(UpdateFolderCommand inputCommand) throws InvalidHandleCommandException {

        if(logger.isInfoEnabled()){
            logger.info("Start update information for folder " + inputCommand.id());
        }

        Folder folder = new Folder(inputCommand.id());
        folder.setParentFolder(inputCommand.parentFolder());
        folder.setOwner(inputCommand.owner());
        folder.setName(inputCommand.name());

        try {
            folderStorageInMemory.update(folder);
            if(logger.isInfoEnabled()){
                logger.info("Updating folder was successful. id: " + folder.id());
            }
            return new UpdateFolderDTO(folder.id(), folder.name(), folder.parentFolder());
        } catch (NotExistIDException e) {

            if(logger.isErrorEnabled()){
                logger.error(e.getMessage());
            }
            throw new InvalidHandleCommandException(e.getMessage());
        }

    }
}
