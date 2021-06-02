package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.files.FolderId;

/**
 * This is object, that contains data after successful
 * execution {@link GettingFolderByName get folder process}.
 */
public final class GetFolderByNameDto {

    public GetFolderByNameDto(FolderId folderID) {

        this.folderID = Preconditions.checkNotNull(folderID);

    }

    private final FolderId folderID;

    public FolderId folderID() {
        return folderID;
    }
}
