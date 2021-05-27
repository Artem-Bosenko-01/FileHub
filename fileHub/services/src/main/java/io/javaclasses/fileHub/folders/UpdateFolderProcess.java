package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.SecuredProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateFolderProcess implements SecuredProcess<UpdateFolderCommand, UpdateFolderDTO> {

    private final FolderStorageInMemory folderStorageInMemory;
    private final Logger logger = LoggerFactory.getLogger(UpdateFolderProcess.class);

    public UpdateFolderProcess(FolderStorageInMemory userStorage) {
        this.folderStorageInMemory = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public UpdateFolderDTO handle(UpdateFolderCommand inputCommand) throws InvalidHandleCommandException {

        Folder folder = new Folder(inputCommand.id());
        folder.setParentFolder(inputCommand.parentFolder());
        folder.setOwner(inputCommand.owner());
        folder.setName(inputCommand.name());

        try {
            folderStorageInMemory.update(folder);
            return new UpdateFolderDTO(folder.id(), folder.name(), folder.parentFolder());
        } catch (NotExistIDException e) {
            throw new InvalidHandleCommandException(e.getMessage());
        }

    }
}
