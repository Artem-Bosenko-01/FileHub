package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.DuplicatedIDException;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.SecuredProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateFolderProcess implements SecuredProcess<CreateFolderCommand, CreateFolderDTO> {

    private final FolderStorageInMemory folderStorageInMemory;
    private final Logger logger = LoggerFactory.getLogger(CreateFolderProcess.class);

    public CreateFolderProcess(FolderStorageInMemory userStorage) {
        this.folderStorageInMemory = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public CreateFolderDTO handle(CreateFolderCommand inputCommand) throws InvalidHandleCommandException {

        FolderID id = new FolderID(inputCommand.name(), inputCommand.owner());
        Folder folder = new Folder(id);
        folder.setParentFolder(inputCommand.parentFolder());
        folder.setName(inputCommand.name());
        folder.setOwner(inputCommand.owner());

        try {
            folderStorageInMemory.create(folder);
            return new CreateFolderDTO(folder.name(), folder.id());

        } catch (DuplicatedIDException e) {
            throw new InvalidHandleCommandException(e.getMessage());
        }

    }
}
