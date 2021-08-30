package io.javaclasses.fileHub.services.files;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.persistent.files.FolderId;

/**
 * This is object, that contains data after successful
 * execution {@link GetFolderById get folder process}.
 */
public final class GetFolderByIdDto {

    public GetFolderByIdDto(FolderId folderID) {

        this.folderID = Preconditions.checkNotNull(folderID);

    }

    private final FolderId folderID;

    public FolderId folderID() {
        return folderID;
    }
}
