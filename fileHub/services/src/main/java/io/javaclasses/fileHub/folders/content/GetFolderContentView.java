package io.javaclasses.fileHub.folders.content;

import io.javaclasses.fileHub.InvalidHandleCommandException;
import io.javaclasses.fileHub.View;
import io.javaclasses.fileHub.files.FileStorageInMemory;
import io.javaclasses.fileHub.folders.FolderStorageInMemory;

public class GetFolderContentView implements View<GetFolderContentQuery, GetFolderContentDTO> {

    private final FolderStorageInMemory folderStorageInMemory;
    private final FileStorageInMemory fileStorage;

    public GetFolderContentView(FolderStorageInMemory folderStorageInMemory, FileStorageInMemory fileStorage) {
        this.folderStorageInMemory = folderStorageInMemory;
        this.fileStorage = fileStorage;
    }

    @Override
    public GetFolderContentDTO handle(GetFolderContentQuery inputCommand) throws InvalidHandleCommandException {

        return new GetFolderContentDTO();
    }
}
