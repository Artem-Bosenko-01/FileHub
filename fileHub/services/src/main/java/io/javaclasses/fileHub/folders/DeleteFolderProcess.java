package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.SecuredProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteFolderProcess implements SecuredProcess<DeleteFolderCommand, DeleteFolderDTO> {

    private final FolderStorageInMemory folderStorageInMemory ;
    private final Logger logger = LoggerFactory.getLogger(DeleteFolderProcess.class);

    public DeleteFolderProcess(FolderStorageInMemory folderStorageInMemory){
        this.folderStorageInMemory = Preconditions.checkNotNull(folderStorageInMemory);
    }

    @Override
    public DeleteFolderDTO handle(DeleteFolderCommand inputCommand) throws InvalidHandleCommandException {
        try {
            folderStorageInMemory.delete(inputCommand.folderID());
            return new DeleteFolderDTO(inputCommand.folderID());
        } catch (NotExistIDException e) {
            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
