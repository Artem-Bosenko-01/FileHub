package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.SecuredProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is service to delete existed folder in Filehub application by authenticated user.
 */
public class DeleteFolderProcess implements SecuredProcess<DeleteFolderCommand, DeleteFolderDTO> {

    private final FolderStorageInMemory folderStorageInMemory ;
    private final Logger logger = LoggerFactory.getLogger(DeleteFolderProcess.class);

    public DeleteFolderProcess(FolderStorageInMemory folderStorageInMemory){
        this.folderStorageInMemory = Preconditions.checkNotNull(folderStorageInMemory);
    }

    @Override
    public DeleteFolderDTO handle(DeleteFolderCommand inputCommand) throws InvalidHandleCommandException {

        if(logger.isInfoEnabled()){
            logger.info("Start delete folder " + inputCommand.folderID());
        }

        try {
            folderStorageInMemory.delete(inputCommand.folderID());

            if(logger.isInfoEnabled()){
                logger.info("Deleted "+ inputCommand.folderID() +" was successful");
            }

            return new DeleteFolderDTO(inputCommand.folderID());
        } catch (NotExistIDException e) {

            if(logger.isErrorEnabled()){
                logger.error(e.getMessage());
            }
            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
