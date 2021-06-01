package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * This is object, that contains data after successful
 * execution {@link UpdateFolderProcess update folder process}.
 */
public final class UpdateFolderDTO {

    private final FolderID folderID;
    private final String name;
    private final FolderID parentFolder;

    public UpdateFolderDTO(FolderID folderID, String name, @Nullable FolderID parentFolder) {
        this.folderID = Preconditions.checkNotNull(folderID);
        this.name = Preconditions.checkNotNull(name);
        this.parentFolder = parentFolder;
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
