package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;

/**
 * This is object, that contains data after successful
 * execution {@link CreateFolderProcess create folder process}.
 */
public final class CreateFolderDTO {

    private final String name;
    private final FolderID folderID;

    public CreateFolderDTO(String name, FolderID folderID) {
        this.name = Preconditions.checkNotNull(name);
        this.folderID = Preconditions.checkNotNull(folderID);
    }

    public String name() {
        return name;
    }

    public FolderID folderID() {
        return folderID;
    }
}
