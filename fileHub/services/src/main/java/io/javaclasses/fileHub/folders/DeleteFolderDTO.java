package io.javaclasses.fileHub.folders;

import com.google.common.base.Preconditions;

public final class DeleteFolderDTO {

    private final String DELETED_FILE_ID;

    public DeleteFolderDTO(FolderID id) {
        DELETED_FILE_ID = "Folder was deleted: " + Preconditions.checkNotNull(id);
    }

    public String getDELETED_FILE_ID() {
        return DELETED_FILE_ID;
    }
}
