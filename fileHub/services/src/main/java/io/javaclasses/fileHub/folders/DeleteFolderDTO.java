package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;

/**
 * This is object, that contains data after successful
 * execution {@link DeleteFolderProcess delete folder process}.
 */
public final class DeleteFolderDTO {

    private final String DELETED_FILE_ID;

    public DeleteFolderDTO(FolderID id) {
        DELETED_FILE_ID = "Folder was deleted: " + Preconditions.checkNotNull(id);
    }

    public String getDELETED_FILE_ID() {
        return DELETED_FILE_ID;
    }
}
