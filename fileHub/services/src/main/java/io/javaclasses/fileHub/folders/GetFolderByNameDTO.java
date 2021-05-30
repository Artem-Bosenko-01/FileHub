package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;

/**
 * This is object, that contains data after successful
 * execution {@link GetFolderByNameView get folder process}.
 */
public final class GetFolderByNameDTO {

    public GetFolderByNameDTO(FolderID folderID) {
        this.folderID = Preconditions.checkNotNull(folderID);
    }

    private final FolderID folderID;

    public FolderID folderID() {
        return folderID;
    }
}
