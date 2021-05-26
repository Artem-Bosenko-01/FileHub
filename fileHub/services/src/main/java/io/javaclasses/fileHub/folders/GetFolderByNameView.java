package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class GetFolderByNameView implements View<GetFolderByNameQuery, GetFolderByNameDTO> {


    private final FolderStorageInMemory folderStorageInMemory;
    private final Logger logger = LoggerFactory.getLogger(GetFolderByNameView.class);

    public GetFolderByNameView(FolderStorageInMemory userStorage) {
        this.folderStorageInMemory = Preconditions.checkNotNull(userStorage);
    }

    @Override
    public GetFolderByNameDTO handle(GetFolderByNameQuery inputCommand) throws InvalidHandleCommandException {
        Optional<Folder> folder = folderStorageInMemory.findFolderByName(inputCommand.name(), inputCommand.owner());
        if(folder.isPresent()){
            return new GetFolderByNameDTO(folder.get().id());
        }
        else throw new InvalidHandleCommandException(
                        "Folder with name doesn't exist " + inputCommand.name()+".User " + inputCommand.owner());
    }
}
