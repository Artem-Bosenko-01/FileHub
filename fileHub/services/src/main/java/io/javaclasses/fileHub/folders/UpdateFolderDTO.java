package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;

public final class UpdateFolderDTO {

    private final FolderID folderID;
    private final String name;
    private final FolderID parentFolder;

    public UpdateFolderDTO(FolderID folderID, String name, FolderID parentFolder) {
        this.folderID = Preconditions.checkNotNull(folderID);
        this.name = Preconditions.checkNotNull(name);
        this.parentFolder = Preconditions.checkNotNull(parentFolder);
    }

    public FolderID id() {
        return folderID;
    }

    public String name() {
        return name;
    }

    public FolderID parentFolder() {
        return parentFolder;
    }
}
