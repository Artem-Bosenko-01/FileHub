package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;

import java.util.Optional;

/**
 * This is object, that contains data after successful
 * execution {@link UpdateFolderProcess update folder process}.
 */
public final class UpdateFolderDTO {

    private final FolderID folderID;
    private final String name;
    private final Optional<FolderID> parentFolder;

    public UpdateFolderDTO(FolderID folderID, String name, Optional<FolderID> parentFolder) {
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

    public Optional<FolderID> parentFolder() {
        return parentFolder;
    }
}
