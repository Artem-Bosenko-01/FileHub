package io.javaclasses.fileHub.folders.content;

import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.NotExistIDException;
import io.javaclasses.fileHub.View;
import io.javaclasses.fileHub.files.File;
import io.javaclasses.fileHub.files.FileStorageInMemory;
import io.javaclasses.fileHub.folders.Folder;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.folders.FolderStorageInMemory;

import java.util.List;

public class GetFolderContentView implements View<GetFolderContentQuery, GetFolderContentDTO> {

    private final FolderStorageInMemory folderStorageInMemory;
    private final FileStorageInMemory fileStorage;

    public GetFolderContentView(FolderStorageInMemory folderStorageInMemory, FileStorageInMemory fileStorage) {
        this.folderStorageInMemory = folderStorageInMemory;
        this.fileStorage = fileStorage;
    }

    @Override
    public GetFolderContentDTO handle(GetFolderContentQuery inputCommand) throws InvalidHandleCommandException {

        try {

            FolderID parentFolder = folderStorageInMemory.findParentFolderByChildId(inputCommand.id());

            List<Folder> folders = folderStorageInMemory.findAllFoldersByParentFolderId(inputCommand.id());
            List<File> files = fileStorage.findAllFilesByFolderIDAndUserID(inputCommand.id(), inputCommand.owner());

            return new GetFolderContentDTO(parentFolder,folders,files);

        } catch (NotExistIDException e) {
            throw new InvalidHandleCommandException(e.getMessage());
        }
    }
}
