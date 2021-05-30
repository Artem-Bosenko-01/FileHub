package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.DuplicatedIDException;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.SecuredProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is service to create new empty folder by authenticated user.
 */
public class CreateFolderProcess implements SecuredProcess<CreateFolderCommand, CreateFolderDTO> {

    private final FolderStorageInMemory folderStorageInMemory;
    private final Logger logger = LoggerFactory.getLogger(CreateFolderProcess.class);

    public CreateFolderProcess(FolderStorageInMemory userStorage) {
        this.folderStorageInMemory = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public CreateFolderDTO handle(CreateFolderCommand inputCommand) throws InvalidHandleCommandException {
        if(logger.isInfoEnabled()){
            logger.info("Start create folder " + inputCommand.name());
        }

        FolderID id = new FolderID(inputCommand.name(), inputCommand.owner());
        Folder folder = new Folder(id);
        folder.setParentFolder(inputCommand.parentFolder());
        folder.setName(inputCommand.name());
        folder.setOwner(inputCommand.owner());

        try {
            folderStorageInMemory.create(folder);

            if(logger.isInfoEnabled()){
                logger.info("Created folder was successful. id: " + folder.id());
            }

            return new CreateFolderDTO(folder.name(), folder.id());

        } catch (DuplicatedIDException e) {

            if(logger.isErrorEnabled()){
                logger.error(e.getMessage());
            }

            throw new InvalidHandleCommandException(e.getMessage());
        }

    }
}
